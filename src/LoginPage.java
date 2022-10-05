import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginPage extends JFrame implements ActionListener {
 
	private JPanel panelNorth;
	private JPanel panelCenter;
	private JPanel panelSouth;

	private JLabel titleLbl;
	private JLabel emailLbl;
	private JLabel passLbl;

	private JButton loginBtn;
	private JTextField emailTxt;
	private JPasswordField passTxt;

	private Font fontStyle;
	private Connect conn;

	public LoginPage() {

		conn = new Connect();

		
		setLayout(new BorderLayout());
		fontStyle = new Font("Calibri", Font.BOLD, 18);

		
		panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout());
		panelNorth.setBorder(new EmptyBorder(30, 35, 20, 35));
		titleLbl = new JLabel("Welcome to HandPhone POS");
		titleLbl.setFont(fontStyle);
		panelNorth.add(titleLbl);
		add(panelNorth, "North");

		
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(2, 2, 10, 30));
		panelCenter.setBorder(new EmptyBorder(15, 35, 10, 35));
		
		
		emailLbl = new JLabel("Email:");
		emailTxt = new JTextField();
		panelCenter.add(emailLbl);
		panelCenter.add(emailTxt);
		
		
		passLbl = new JLabel("Password:");
		passTxt = new JPasswordField();
		panelCenter.add(passLbl);
		panelCenter.add(passTxt);

		add(panelCenter, "Center");

		
		panelSouth = new JPanel();
		panelSouth.setLayout(new GridLayout(1, 1));
		panelSouth.setBorder(new EmptyBorder(20, 35, 35, 35));
		loginBtn = new JButton("Login");
		panelSouth.add(loginBtn);
		add(panelSouth, "South");
		loginBtn.addActionListener(this);

		// Frame Login
		setTitle("HandPhone POS");
		setSize(450, 330);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginBtn) {
			login();
		}

	}

	public void login() {
		try {
			String emailTemp = emailTxt.getText();
			String passTemp = new String(passTxt.getPassword());
			if (emailTemp.isEmpty() || passTemp.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Email and Password cannot be Empty!", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			boolean emailIsFound = false;
			boolean passIsFound = false;
			String queryEmail = "SELECT email FROM users";
			conn.rs = conn.stat.executeQuery(queryEmail);
			while (conn.rs.next()) {
				String email = conn.rs.getString("email");
				if (emailTemp.equals(email)) {
					emailIsFound = true;
					break;
				}
			}
			if (emailIsFound) {
				String queryPassword = "SELECT password FROM users WHERE email='%s'";
				queryPassword = String.format(queryPassword, emailTemp);
				conn.rs = conn.stat.executeQuery(queryPassword);
				conn.rs.next();
				String password = conn.rs.getString("password");
				if (passTemp.equals(password)) {
					passIsFound = true;
				}
			}
			if (emailIsFound && passIsFound) {
				String queryUsername = "SELECT userid, fullname FROM users WHERE email='%s'";
				queryUsername = String.format(queryUsername, emailTemp);
				conn.rs = conn.stat.executeQuery(queryUsername);
				conn.rs.next();
				String userid = conn.rs.getString("userid");
				String username = conn.rs.getString("fullname");
				JOptionPane.showMessageDialog(this, "Welcome, " + username, "Message", JOptionPane.INFORMATION_MESSAGE);
				String role=getRole(emailTemp);
				this.dispose();
				new MenuFrame(userid, role).setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Incorrect Email or Password", "Message",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getRole(String email) {
		String role = "";
		try {
			String queryUsername = "SELECT role FROM users WHERE email='%s'";
			queryUsername = String.format(queryUsername, email);
			conn.rs = conn.stat.executeQuery(queryUsername);
			conn.rs.next();
			role = conn.rs.getString("role").toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

}
