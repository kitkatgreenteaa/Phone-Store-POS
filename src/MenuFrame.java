import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuFrame extends JFrame implements ActionListener {

	private JDesktopPane desktopPane;
	private JMenuBar menuBar;
	private JMenu menuAccount, menuManage, menuFinance, menuTransaction;
	private JMenuItem logoutItm, manageAccountItm, viewMonthlyReport, viewDailyTransaction, createTrItm;
	public Connect conn;
	private AccountAdmin manages;
	private MonthlyReport reportform;
	private DailyReport reportformD;
	private Cashier cashierF;
	String userid;

	public MenuFrame(String userid, String role) {
		desktopPane = new JDesktopPane();
		setContentPane(desktopPane);
		conn = new Connect();
		setTitle("Handphone POS");
		this.userid = userid;

		menuBar = new JMenuBar();
		menuAccount = new JMenu("Account");
		menuManage = new JMenu("Manage");
		menuFinance = new JMenu("Finance");
		menuTransaction = new JMenu("Transaction");
		logoutItm = new JMenuItem("Logout");
		manageAccountItm = new JMenuItem("Accounts");
		viewMonthlyReport = new JMenuItem("View Monthly Transaction");
		viewDailyTransaction = new JMenuItem("View Daily Transaction");
		createTrItm = new JMenuItem("Do Transaction");

		menuAccount.add(logoutItm);
		menuBar.add(menuAccount);

		// Based on Login Role
		if (role.equals("Cashier")) {
			menuTransaction.add(createTrItm);
			menuBar.add(menuTransaction);
		} else if (role.equals("Supervisor")) {
			menuFinance.add(viewMonthlyReport);
			menuFinance.add(viewDailyTransaction);
			menuBar.add(menuFinance);
			menuManage.add(manageAccountItm);
			menuBar.add(menuManage);
		}

		// Add Action Listener
		logoutItm.addActionListener(this);
		manageAccountItm.addActionListener(this);
		viewMonthlyReport.addActionListener(this);
		viewDailyTransaction.addActionListener(this);
		createTrItm.addActionListener(this);

		// Frame
		setJMenuBar(menuBar);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutItm) {
			// new LoginForm();
			this.dispose();
			new LoginPage().setVisible(true);
		} else if (e.getSource() == createTrItm) {
			// new TransactionForm();
			cashierF = new Cashier(this);
			cashierF.setVisible(true);
			desktopPane.add(cashierF);
		} else if (e.getSource() == manageAccountItm) {
			// new ManageAccountForm();
			manages = new AccountAdmin(this);
			manages.setVisible(true);
			desktopPane.add(manages);
		} else if (e.getSource() == viewMonthlyReport) {
			// new FinanceForm();
			reportform = new MonthlyReport(this);
			reportform.setVisible(true);
			desktopPane.add(reportform);
		}
		else if (e.getSource() == viewDailyTransaction) {
			// new FinanceForm();
			reportformD = new DailyReport(this);
			reportformD.setVisible(true);
			desktopPane.add(reportformD);
		}
	}
}
