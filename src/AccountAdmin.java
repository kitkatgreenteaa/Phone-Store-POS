 import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AccountAdmin extends JInternalFrame implements ActionListener, MouseListener {

	private JLabel idLbl, userIdLbl, fullnameLbl, roleLbl, emailLbl, passwordLbl;
	private JTextField fullnameField, emailField, passField;
	private JComboBox<String> roleCB;
	private JButton insertBtn, updateBtn, deleteBtn;
	private JTable table;
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private JPanel mainPanel, panelNorth, panelSouth;

	private Vector<String> columnNames;
	private boolean dataSelected = false;
	private String userId;
	private MenuFrame MenuFrame;

	public AccountAdmin(MenuFrame MenuFrame) {
		super("Manage Account Menu");
		this.MenuFrame = MenuFrame;

		idLbl = new JLabel("ID:");
		userIdLbl = new JLabel();
		fullnameLbl = new JLabel("Fullname:");
		roleLbl = new JLabel("Role:");
		emailLbl = new JLabel("Email:");
		passwordLbl = new JLabel("Password:");

		fullnameField = new JTextField();
		emailField = new JTextField();
		passField = new JTextField();
		roleCB = new JComboBox<String>();

		insertBtn = new JButton("Insert");
		updateBtn = new JButton("Update");
		deleteBtn = new JButton("Delete");

		// Main Panel
		mainPanel = new JPanel(new GridLayout(2, 1));
		mainPanel.setBorder(new EmptyBorder(10, 20, 0, 20));

		// Panel North
		panelNorth = new JPanel(new GridLayout(5, 3, 10, 10));
		panelNorth.setBorder(new EmptyBorder(0, 0, 10, 0));
		panelNorth.add(idLbl);
		panelNorth.add(userIdLbl);
		panelNorth.add(new JPanel());
		panelNorth.add(fullnameLbl);
		panelNorth.add(fullnameField);
		panelNorth.add(insertBtn);
		panelNorth.add(roleLbl);
		panelNorth.add(roleCB);
		panelNorth.add(updateBtn);
		panelNorth.add(emailLbl);
		panelNorth.add(emailField);
		panelNorth.add(deleteBtn);
		panelNorth.add(passwordLbl);
		panelNorth.add(passField);
		panelNorth.add(new JPanel());
		roleCB.addItem("Supervisor");
		roleCB.addItem("Cashier");
		mainPanel.add(panelNorth);

		// Panel South
		panelSouth = new JPanel(new BorderLayout());
		columnNames = new Vector<String>();
		columnNames.add("ID");
		columnNames.add("Fullname");
		columnNames.add("Role");
		columnNames.add("Email");
		columnNames.add("Password");

		dtm = new DefaultTableModel(columnNames, 0);
		table = new JTable(dtm);
		jsp = new JScrollPane(table);
		panelSouth.add(jsp, BorderLayout.CENTER);
		getDataUsers();
		table.addMouseListener(this);

		mainPanel.add(panelSouth);
		add(mainPanel);

		// Add Action Listener
		insertBtn.addActionListener(this);
		updateBtn.addActionListener(this);
		deleteBtn.addActionListener(this);

		// Frame
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setLocation(50, 15);
		setSize(500, 500);
	}

	public void getDataUsers() {
		try {
			String query = "SELECT * FROM users";
			MenuFrame.conn.rs = MenuFrame.conn.stat.executeQuery(query);
			MenuFrame.conn.rsm = MenuFrame.conn.rs.getMetaData();
			while (MenuFrame.conn.rs.next()) {
				Vector<String> data = new Vector<String>();
				for (int i = 1; i <= MenuFrame.conn.rsm.getColumnCount(); i++) {
					String str = MenuFrame.conn.rs.getObject(i).toString();
					data.add(str);
				}
				dtm.addRow(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkDataEmail(String emailTemp) {
		boolean emailIsFound = false;
		try {
			String queryEmail = "SELECT email FROM users";
			MenuFrame.conn.rs = MenuFrame.conn.stat.executeQuery(queryEmail);
			while (MenuFrame.conn.rs.next()) {
				String email = MenuFrame.conn.rs.getString("email");
				if (emailTemp.equals(email)) {
					emailIsFound = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailIsFound;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insertBtn) {
			String emailTemp = emailField.getText();
			String fullnameTemp = fullnameField.getText();
			String passTemp = passField.getText();
			if (fullnameTemp.isEmpty() || emailTemp.isEmpty() || passTemp.isEmpty()) {
				JOptionPane.showMessageDialog(this, "All Fields must be filled!", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			else {
				String[] temp = emailTemp.split("@");
				// email validation
				if (emailTemp.startsWith("@") || emailTemp.endsWith("@")) {
					JOptionPane.showMessageDialog(this, "Wrong email format!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (!emailTemp.contains("@") || !(temp.length == 2)) {
					JOptionPane.showMessageDialog(this, "Wrong email format!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} 
				boolean emailIsfound;
				emailIsfound = checkDataEmail(emailTemp);
				if (emailIsfound) {
					JOptionPane.showMessageDialog(this, "Email already used!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				// password validation
				boolean containsAlpha = false;
				boolean containsNumeric = false;
				for (int i = 0; i < passTemp.length(); i++) {

					if (Character.isAlphabetic(passTemp.charAt(i))) {
						containsAlpha = true;
					} else if (Character.isDigit(passTemp.charAt(i))) {
						containsNumeric = true;
					}
				}
				if (containsAlpha == true && containsNumeric == true) {
					// Random ID
					Random r = new Random();

					String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
					String idTemp = "";
					for (int i = 0; i < 10; i++) {
						idTemp += alphabet.charAt(r.nextInt(alphabet.length()));
					}
					JOptionPane.showMessageDialog(this, "Insert Success!", "Message", JOptionPane.INFORMATION_MESSAGE);

					// Query Insert
					try {
						String query = "INSERT INTO users(userid, fullname, role, email, password) VALUES('%s','%s','%s','%s','%s')";
						query = String.format(query, idTemp, fullnameTemp, roleCB.getSelectedItem(), emailTemp,
								passTemp);
						MenuFrame.conn.stat.execute(query);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					fullnameField.setText("");
					emailField.setText("");
					passField.setText("");
					roleCB.setSelectedIndex(0);
					userIdLbl.setText("");
					table.getSelectionModel().clearSelection();

					return;
				} else {
					JOptionPane.showMessageDialog(this, "Password must be Alphanumeric", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		} else if (e.getSource() == updateBtn) {
			if (!dataSelected) {
				JOptionPane.showMessageDialog(this, "Please select data to be updated first", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {
				String emailUpTemp = emailField.getText();
				String fullnameUpTemp = fullnameField.getText();
				String passUpTemp = passField.getText();
				if (fullnameUpTemp.isEmpty() || emailUpTemp.isEmpty() || passUpTemp.isEmpty()) {
					JOptionPane.showMessageDialog(this, "All Fields must be filled!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				else {
					String[] temp = emailUpTemp.split("@");
					// Email Validation
					if (emailUpTemp.startsWith("@") || emailUpTemp.endsWith("@")) {
						JOptionPane.showMessageDialog(this, "Wrong email format!", "Message",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					} else if (emailUpTemp.endsWith(".")) {
						JOptionPane.showMessageDialog(this, "Wrong email format!", "Message",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					} else if (!emailUpTemp.contains("@") || !(temp.length == 2)) {
						JOptionPane.showMessageDialog(this, "Wrong email format!", "Message",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					} else if (temp[0].endsWith(".") || temp[1].startsWith(".")) {
						JOptionPane.showMessageDialog(this, "Wrong email format!", "Message",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					boolean emailIsfound;
					emailIsfound = checkDataEmail(emailUpTemp);
					if (emailIsfound) {
						JOptionPane.showMessageDialog(this, "Email already used!", "Message",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}

					// Password Validation
					boolean containsAlpha = false;
					boolean containsNumeric = false;
					for (int i = 0; i < passUpTemp.length(); i++) {

						if (Character.isAlphabetic(passUpTemp.charAt(i))) {
							containsAlpha = true;
						} else if (Character.isDigit(passUpTemp.charAt(i))) {
							containsNumeric = true;
						}
					}
					if (containsAlpha && containsNumeric) {
						JOptionPane.showMessageDialog(this, "Update Success!", "Message",
								JOptionPane.INFORMATION_MESSAGE);

						// Query Update
						try {
							String query = "UPDATE users SET fullname='%s', role='%s', email='%s', password='%s' WHERE userid='%s'";
							query = String.format(query, fullnameUpTemp, roleCB.getSelectedItem(), emailUpTemp,
									passUpTemp, userId);
							MenuFrame.conn.stat.execute(query);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						fullnameField.setText("");
						emailField.setText("");
						passField.setText("");
						roleCB.setSelectedIndex(0);
						userIdLbl.setText("");
						table.getSelectionModel().clearSelection();

						return;
					} else {
						JOptionPane.showMessageDialog(this, "Password must be Alphanumeric", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		} else if (e.getSource() == deleteBtn) {
			try {
				if (!dataSelected) {
					JOptionPane.showMessageDialog(this, "Please select data to be deleted first", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					String query = "DELETE FROM users WHERE userid='%s'";
					query = String.format(query, userId);
					MenuFrame.conn.stat.execute(query);
					JOptionPane.showMessageDialog(this, "Delete Success!", "Message", JOptionPane.INFORMATION_MESSAGE);

					fullnameField.setText("");
					emailField.setText("");
					passField.setText("");
					roleCB.setSelectedIndex(0);
					userIdLbl.setText("");
					table.getSelectionModel().clearSelection();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			try {
				userId = "";
				dataSelected = false;
				int row = table.getSelectedRow();
				String value = table.getModel().getValueAt(row, 0).toString();
				if (!value.isEmpty()) {
					dataSelected = true;
				}
				String query = "SELECT * FROM users WHERE userid='%s'";
				query = String.format(query, value);
				MenuFrame.conn.rs = MenuFrame.conn.stat.executeQuery(query);
				MenuFrame.conn.rs.next();
				userIdLbl.setText(MenuFrame.conn.rs.getString("Userid"));
				userId = MenuFrame.conn.rs.getString("Userid");
				fullnameField.setText(MenuFrame.conn.rs.getString("Fullname"));
				String role = MenuFrame.conn.rs.getString("Role");
				roleCB.setSelectedItem(role);
				emailField.setText(MenuFrame.conn.rs.getString("Email"));
				passField.setText(MenuFrame.conn.rs.getString("Password"));
			} catch (Exception error) {
				error.printStackTrace();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}