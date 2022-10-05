import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MonthlyReport extends JInternalFrame implements ActionListener {

	private JPanel panelTop;
	private JPanel panelDown;
	private JPanel mainPanel;

	private MenuFrame MenuFrame;

	private JLabel lblMonth;
	private JLabel lblYear;

	private JTextField monthTxt;
	private JTextField yearTxt;

	private JButton viewBtn;

	private JTable table;
	private DefaultTableModel dtm;
	private Vector<String> columnNames;
	private JScrollPane scrPane;

	public MonthlyReport(MenuFrame MenuFrame) {
		super("Monthly Report");
		this.MenuFrame = MenuFrame;

		lblMonth = new JLabel("Month:");
		lblYear = new JLabel("Year:");

		monthTxt = new JTextField();
		yearTxt = new JTextField();

		viewBtn = new JButton("View");

		// Main Panel
		mainPanel = new JPanel(new GridLayout(2, 1));
		mainPanel.setBorder(new EmptyBorder(20, 20, 0, 20));

		// Panel North
		panelTop = new JPanel(new GridLayout(2, 3, 10, 10));
		panelTop.setBorder(new EmptyBorder(0, 0, 20, 0));
		panelTop.add(lblMonth);
		panelTop.add(monthTxt);
		panelTop.add(viewBtn);
		panelTop.add(lblYear);
		panelTop.add(yearTxt);
		panelTop.add(new JPanel());

		mainPanel.add(panelTop);

		// Panel South
		panelDown = new JPanel(new BorderLayout());
		columnNames = new Vector<String>();
		columnNames.add("Member ID");
		columnNames.add("Transaction ID");
		columnNames.add("Base Price");
		columnNames.add("Earn");
		columnNames.add("Gain");

		dtm = new DefaultTableModel(columnNames, 0);
		table = new JTable(dtm);
		scrPane = new JScrollPane(table);

		panelDown.add(scrPane, BorderLayout.CENTER);

		mainPanel.add(panelDown);
		add(mainPanel);

		// Add ActionListener
		viewBtn.addActionListener(this);

		// Frame
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setLocation(50, 15);
		setSize(500, 300);

	}

	private void getDataFinance() {
		try {
			String monthTemp = monthTxt.getText();
			String yearTemp = yearTxt.getText();
			String query = "SELECT TD.memberId AS Member, TH.transactionid, SUM(TD.quantity*P.baseprice) AS Base, SUM(TD.quantity*P.sellprice) AS Earn, SUM(TD.quantity*P.sellprice)-SUM(TD.quantity*P.baseprice) AS Gain FROM transactionheader AS TH JOIN transactiondetail AS TD ON TH.transactionid=TD.transactionid JOIN product AS P ON TD.productID=P.productID WHERE MONTH(TH.transactiondate)=%s AND YEAR(TH.transactiondate)=%s GROUP BY TH.transactionid";
			query = String.format(query, monthTemp, yearTemp);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == viewBtn) {
			dtm.setRowCount(0);
			boolean yearIsNumeric = false;
			boolean monthIsNumeric = false;
			String monthTemp = monthTxt.getText();
			String yearTemp = yearTxt.getText();

			for (int i = 0; i < monthTemp.length(); i++) {
				if (Character.isDigit(monthTemp.charAt(i))) {
					monthIsNumeric = true;
				} else if (Character.isAlphabetic(monthTemp.charAt(i))) {
					monthIsNumeric = false;
				}
			}
			for (int i = 0; i < yearTemp.length(); i++) {
				if (Character.isDigit(yearTemp.charAt(i))) {
					yearIsNumeric = true;
				} else if (Character.isAlphabetic(yearTemp.charAt(i))) {
					yearIsNumeric = false;
				}
			}
			if (monthIsNumeric && yearIsNumeric) {
				if (Integer.parseInt(monthTemp) < 1 || Integer.parseInt(monthTemp) > 12) {
					JOptionPane.showMessageDialog(this, "Month must be between 1-12", "Message", JOptionPane.INFORMATION_MESSAGE);
				} else {
					getDataFinance();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Month and Year must be number", "Message",	JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
