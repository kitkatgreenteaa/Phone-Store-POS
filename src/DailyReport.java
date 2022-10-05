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

public class DailyReport extends JInternalFrame implements ActionListener {

	private JPanel panelTop;
	private JPanel panelDown;
	private JPanel mainPanel;

	private MenuFrame MenuFrame;

	private JLabel lblDay;
	private JLabel lblMonth;

	private JTextField dayTxt;
	private JTextField MonthTxt;

	private JButton viewBtn;

	private JTable table;
	private DefaultTableModel dtm;
	private Vector<String> columnNames;
	private JScrollPane scrPane;

	public DailyReport(MenuFrame MenuFrame) {
		super("Daily Report");
		this.MenuFrame = MenuFrame;

		lblDay = new JLabel("Day:");
		lblMonth = new JLabel("Month:");

		dayTxt = new JTextField();
		MonthTxt = new JTextField();

		viewBtn = new JButton("View");

		// Main Panel
		mainPanel = new JPanel(new GridLayout(2, 1));
		mainPanel.setBorder(new EmptyBorder(20, 20, 0, 20));

		// Panel North
		panelTop = new JPanel(new GridLayout(2, 3, 10, 10));
		panelTop.setBorder(new EmptyBorder(0, 0, 20, 0));
		panelTop.add(lblDay);
		panelTop.add(dayTxt);
		panelTop.add(viewBtn);
		panelTop.add(lblMonth);
		panelTop.add(MonthTxt);
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
			String dayTemp = dayTxt.getText();
			String MonthTemp = MonthTxt.getText();
			String query = "SELECT TD.memberId AS Member, TH.transactionid, SUM(TD.quantity*P.baseprice) AS Base, SUM(TD.quantity*P.sellprice) AS Earn, SUM(TD.quantity*P.sellprice)-SUM(TD.quantity*P.baseprice) AS Gain FROM transactionheader AS TH JOIN transactiondetail AS TD ON TH.transactionid=TD.transactionid JOIN product AS P ON TD.productID=P.productID WHERE DAY(TH.transactiondate)=%s AND MONTH(TH.transactiondate)=%s GROUP BY TH.transactionid";
			query = String.format(query, dayTemp, MonthTemp);
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
			boolean MonthIsNumeric = false;
			boolean dayIsNumeric = false;
			String dayTemp = dayTxt.getText();
			String MonthTemp = MonthTxt.getText();

			for (int i = 0; i < dayTemp.length(); i++) {
				if (Character.isDigit(dayTemp.charAt(i))) {
					dayIsNumeric = true;
				} else if (Character.isAlphabetic(dayTemp.charAt(i))) {
					dayIsNumeric = false;
				}
			}
			for (int i = 0; i < MonthTemp.length(); i++) {
				if (Character.isDigit(MonthTemp.charAt(i))) {
					MonthIsNumeric = true;
				} else if (Character.isAlphabetic(MonthTemp.charAt(i))) {
					MonthIsNumeric = false;
				}
			}
			if (dayIsNumeric && MonthIsNumeric) {
				if (Integer.parseInt(dayTemp) < 1 || Integer.parseInt(dayTemp) > 31) {
					JOptionPane.showMessageDialog(this, "Day must be between 1-31", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					getDataFinance();
				}
			} else if (dayIsNumeric && MonthIsNumeric) {
				if (Integer.parseInt(MonthTemp) < 1 || Integer.parseInt(MonthTemp) > 12) {
					JOptionPane.showMessageDialog(this, "Month must be between 1-12", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					getDataFinance();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Day and Month must be number", "Message",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}

	}

}
