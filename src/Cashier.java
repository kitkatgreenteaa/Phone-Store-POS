import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;





public class Cashier extends JInternalFrame implements ActionListener, MouseListener {

	private JPanel mainPanel, panelNorth, panelCenter, panelCtrLeft, PanelCtrRight, panelSouth;
	private JLabel idLbl, productIdLbl, nameLbl, productName, qtyLbl, totalLbl, totalPrice, cartLbl, cashLbl, changeLbl, changeAmountLbl, productLbl, memberlbl, Imagelbl;
	private JButton addBtn, updateBtn, removeBtn, cancelBtn, finishBtn;
	private Border mainBorder, tableBorder;
	private JTable tableProduct, tableCart;
	private DefaultTableModel dtmProduct, dtmCart;
	private JScrollPane jspProduct, jspProduk;
	private JSpinner qtySpinner;
	private SpinnerModel spm;
	private Font fontStyle;
	private Vector<String> columnNames, columnNamesCart;
	private boolean ProductDataSelected = false;
	private boolean cartDataSelected = false;
	private String productId, cartId;
	private MenuFrame MenuFrame;
	private JRadioButton memberrbt;
	private JTextField membertxt, cashtxt;

	public Cashier(MenuFrame MenuFrame) {
		super("Transaction");
		this.MenuFrame = MenuFrame;

		fontStyle = new Font("Arial", Font.BOLD, 20);
		mainBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		tableBorder = BorderFactory.createEmptyBorder(20, 0, 20, 0);
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(mainBorder);
		panelNorth = new JPanel(new GridLayout(4, 3, 10, 10));
		panelCenter = new JPanel(new GridLayout(1, 2, 20, 0));
		panelCenter.setBorder(tableBorder);
		panelCtrLeft = new JPanel(new BorderLayout());
		PanelCtrRight = new JPanel(new BorderLayout());
		panelSouth = new JPanel(new GridLayout(4, 2, 20, 10));

		idLbl = new JLabel("ID:");
		productIdLbl = new JLabel("");
		nameLbl = new JLabel("Name:");
		productName = new JLabel("");
		qtyLbl = new JLabel("Quantity:");
		cashLbl = new JLabel("Payment Amount:");
		cashtxt = new JTextField();
		changeLbl = new JLabel("Change:");
		changeAmountLbl = new JLabel("0");
		totalLbl = new JLabel("Total:");
		totalPrice = new JLabel("0");
		cartLbl = new JLabel("Cart:");
		cartLbl.setFont(fontStyle);
		productLbl = new JLabel("Product:");
		productLbl.setFont(fontStyle);
		memberlbl = new JLabel("Member:");
		memberlbl.setFont(fontStyle);
		membertxt = new JTextField();
		membertxt.setEditable(false);

		addBtn = new JButton("Add");
		updateBtn = new JButton("Update");
		removeBtn = new JButton("Remove");
		cancelBtn = new JButton("Cancel");
		finishBtn = new JButton("Finish");
		memberrbt = new JRadioButton("Member");

		qtySpinner = new JSpinner();
		qtySpinner.setFont(new Font("Calibri", Font.PLAIN, 20));
		spm = new SpinnerNumberModel(0, 0, 100, 1);
		qtySpinner.setModel(spm);

		columnNames = new Vector<String>();
		columnNames.add("ID");
		columnNames.add("Image");
		columnNames.add("Name");
		columnNames.add("Price");
		columnNames.add("Quantity");
		dtmProduct = new DefaultTableModel(columnNames, 0){ 
				@Override
		    	   public Class getColumnClass(int column) {
		    	    switch (column) {
		    	    case 0:
		    	     return String.class;
		    	    case 1:
		    	     return ImageIcon.class;
		    	    case 2:
		    	     return String.class;
		    	    case 3:
		    	     return String.class;
		    	    case 4: 
		    	     return String.class;
		    	    default:
		    	     return String.class;
		    	    }
		    }
		};
		tableProduct = new JTable(dtmProduct);
		tableProduct.setRowHeight(50);
		jspProduct = new JScrollPane(tableProduct);
		getDataProduct();
		tableProduct.addMouseListener(this);

		columnNamesCart = new Vector<String>();
		columnNamesCart.add("ID");
		columnNamesCart.add("Name");
		columnNamesCart.add("Price");
		columnNamesCart.add("Quantity");
		dtmCart = new DefaultTableModel(columnNamesCart, 0);
		
		tableCart = new JTable(dtmCart);
		tableCart.setRowHeight(50);
		jspProduk = new JScrollPane(tableCart);
		tableCart.addMouseListener(this);
		
		panelNorth.add(idLbl);
		panelNorth.add(productIdLbl);
		panelNorth.add(addBtn);

		panelNorth.add(nameLbl);
		panelNorth.add(productName);
		panelNorth.add(updateBtn);

		panelNorth.add(qtyLbl);
		panelNorth.add(qtySpinner);
		panelNorth.add(removeBtn);
		
		panelNorth.add(memberrbt);
		panelNorth.add(memberlbl);
		panelNorth.add(membertxt);

		mainPanel.add(panelNorth, BorderLayout.NORTH);

		PanelCtrRight.add(productLbl, BorderLayout.NORTH);
		PanelCtrRight.add(jspProduct, BorderLayout.CENTER);
		panelCenter.add(panelCtrLeft);

		panelCtrLeft.add(cartLbl, BorderLayout.NORTH);
		panelCtrLeft.add(jspProduk, BorderLayout.CENTER);
		panelCenter.add(PanelCtrRight);

		mainPanel.add(panelCenter, BorderLayout.CENTER);

		panelSouth.add(cashLbl);
		panelSouth.add(cashtxt);
		panelSouth.add(totalLbl);
		panelSouth.add(totalPrice);
		panelSouth.add(changeLbl);
		panelSouth.add(changeAmountLbl);
		panelSouth.add(cancelBtn);
		panelSouth.add(finishBtn);
		

		mainPanel.add(panelSouth, BorderLayout.SOUTH);

		addBtn.addActionListener(this);
		updateBtn.addActionListener(this);
		removeBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		finishBtn.addActionListener(this);
		memberrbt.addActionListener(this);

		add(mainPanel);
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setLocation(50, 15);
		setSize(1000, 600);
	}

	public void getDataProduct() {
		try {
			String query = "SELECT productID, picture, name, sellprice, qty FROM product";
			MenuFrame.conn.rs = MenuFrame.conn.stat.executeQuery(query);
			MenuFrame.conn.rsm = MenuFrame.conn.rs.getMetaData();
			
			while (MenuFrame.conn.rs.next()) {
				
				Vector<Object> newRow = new Vector<Object>();

			    for (int i = 1; i <= MenuFrame.conn.rsm.getColumnCount(); i++) {
			        if (i == 2) {  
			            Blob blob =  MenuFrame.conn.rs.getBlob("picture");
			            int blobLength = (int) blob.length();  

			            byte[] bytes = blob.getBytes(1, blobLength);
			            blob.free();
			            BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
			            ImageIcon icon = new ImageIcon(img);  
			            newRow.addElement(icon);  
			        } else {
			            newRow.addElement(MenuFrame.conn.rs.getObject(i));
			        }
			    }
			    dtmProduct.addRow(newRow);
			    

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getTotalPrice(){
		int totalPriceTemp=0;
		for(int i=0;i<dtmCart.getRowCount();i++){
			int price=Integer.parseInt((String) dtmCart.getValueAt(i, 2));
			int qty=Integer.parseInt((String) dtmCart.getValueAt(i, 3));
			int priceTemp=price*qty;
			totalPriceTemp+=priceTemp;
		}
		totalPrice.setText(String.valueOf(totalPriceTemp));
	}

	public void getExchange() {
		int exchtemp = 0;
		int total = Integer.parseInt(cashtxt.getText());
		int totalPriceTemp=0;
		for(int i=0;i<dtmCart.getRowCount();i++){
			int price=Integer.parseInt((String) dtmCart.getValueAt(i, 2));
			int qty=Integer.parseInt((String) dtmCart.getValueAt(i, 3));
			int priceTemp=price*qty;
			totalPriceTemp+=priceTemp;
		}
		exchtemp = total - totalPriceTemp;
		changeAmountLbl.setText(String.valueOf(exchtemp));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addBtn) {
			if (!ProductDataSelected) {
				JOptionPane.showMessageDialog(this, "Please select Product to be inserted", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {
				int valueSpinner = (int) qtySpinner.getValue();
				if (valueSpinner <= 0) {
					JOptionPane.showMessageDialog(this, "Item quantity cannot be zero!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					String id = productIdLbl.getText();
					try {
						String dos = "Insert";
						String query = "SELECT * FROM product WHERE productID='%s'";
						query = String.format(query, productId);
						MenuFrame.conn.rs = MenuFrame.conn.stat.executeQuery(query);
						MenuFrame.conn.rs.next();
						String idProduct = MenuFrame.conn.rs.getString("productId");
						String ProductsName = MenuFrame.conn.rs.getString("name");
						String price = MenuFrame.conn.rs.getString("sellprice");
						String qty = MenuFrame.conn.rs.getString("qty");
	
						try {
							qtySpinner.commitEdit();
						} catch (java.text.ParseException e1) {
							e1.printStackTrace();
						}
						int quantityTemp = (Integer) qtySpinner.getValue();
						String quantity = Integer.toString(quantityTemp);
						int row = 0;
						for (int i = 0; i < tableCart.getRowCount(); i++) {
							String value = (String) tableCart.getValueAt(i, 0);
							if (idProduct.equals(value)) {
								row = i;
								dos = "Update";
								break;
							}
						}
						Vector<String> data = new Vector<String>();
						data.add(idProduct);
						data.add(ProductsName);
						data.add(price);
						data.add(quantity);
						
						int qty1 = Integer.parseInt(qty);
						int qty2 = Integer.parseInt(quantity);
					
						int newqty = qty1 - qty2;
						
						String newQuantity = Integer.toString(newqty);
						String queryqty = "UPDATE product SET qty = ('%s') WHERE name = " + "'ProductsName'";
						queryqty = String.format(queryqty, newQuantity);
						MenuFrame.conn.stat.execute(queryqty);
						
						
						for (int i = 0; i < tableProduct.getRowCount(); i++) {
							String value = (String) tableProduct.getValueAt(i, 0);
							if (idProduct.equals(value)) {
								dtmProduct.setValueAt(newqty, i, 4);
							}
						}
						
						
						if (dos.equals("Insert")) {
							dtmCart.addRow(data);
						} else if (dos.equals("Update")) {
							Integer qtyTemp = Integer.parseInt((String) dtmCart.getValueAt(row, 3))
									+ Integer.parseInt(data.get(3));
							String qtyString = Integer.toString(qtyTemp);
							dtmCart.setValueAt(qtyString, row, 3);
						}
						tableCart.setModel(dtmCart);
						getTotalPrice();
						JOptionPane.showMessageDialog(this, "Item Added!", "Message", JOptionPane.INFORMATION_MESSAGE);
						productIdLbl.setText("");
						productName.setText("");
						qtySpinner.setValue(0);
						tableCart.getSelectionModel().clearSelection();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}

		} else if (e.getSource() == updateBtn) {
			if (!cartDataSelected) {
				JOptionPane.showMessageDialog(this, "Please select item to be updated", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else if (!cartId.isEmpty()) {
				int valueSpinner = (int) qtySpinner.getValue();
				if (valueSpinner <= 0) {
					JOptionPane.showMessageDialog(this, "Item quantity cannot be zero!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					
					String idProduct = productIdLbl.getText();
					try {
						qtySpinner.commitEdit();
					} catch (java.text.ParseException e1) {
						e1.printStackTrace();
					}
					int quantityTemp = (Integer) qtySpinner.getValue();
					String quantity = Integer.toString(quantityTemp);
					for (int i = 0; i < tableCart.getRowCount(); i++) {
						String value = (String) tableCart.getValueAt(i, 0);
						if (idProduct.equals(value)) {
							dtmCart.setValueAt(quantity, i, 3);
							break;
						}
					}
					tableCart.setModel(dtmCart);
					getTotalPrice();
					JOptionPane.showMessageDialog(this, "Item Updated!", "Message", JOptionPane.INFORMATION_MESSAGE);
					productIdLbl.setText("");
					productName.setText("");
					qtySpinner.setValue(0);
					tableCart.getSelectionModel().clearSelection();
					productId="";
					ProductDataSelected=false;
				}
			}
		} else if (e.getSource() == removeBtn) {
			if (!cartDataSelected) {
				JOptionPane.showMessageDialog(this, "Please select item to be removed", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else if (!cartId.isEmpty()) {
				
				String idProduct = productIdLbl.getText();
				for (int i = 0; i < tableCart.getRowCount(); i++) {
					String value = (String) tableCart.getValueAt(i, 0);
					if (idProduct.equals(value)) {
						dtmCart.removeRow(i);
					}
				}
				tableCart.setModel(dtmCart);
				getTotalPrice();
				JOptionPane.showMessageDialog(this, "Item Removed!", "Message", JOptionPane.INFORMATION_MESSAGE);
				productIdLbl.setText("");
				productName.setText("");
				qtySpinner.setValue(0);
				tableCart.getSelectionModel().clearSelection();
				cartId="";
				cartDataSelected=false;
			}
		} else if (e.getSource() == finishBtn) {
			if(dtmCart.getRowCount()==0){
				JOptionPane.showMessageDialog(this, "Your cart is empty!", "Message", JOptionPane.INFORMATION_MESSAGE);
			}else{
				int supervisor = JOptionPane.showConfirmDialog(this, "Have supervisor check your transaction?");
				if (supervisor == 0) {
					
					Random r = new Random();
		
					String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
					String idTemp = "";
					for (int i = 0; i < 10; i++) {
						idTemp += alphabet.charAt(r.nextInt(alphabet.length()));
					}
					
					
					try {
						String queryTr = "INSERT INTO transactionheader (transactionid, staffid, transactiondate) VALUES "
								+ "('%s', '%s', CURRENT_DATE)";
						queryTr = String.format(queryTr, idTemp, MenuFrame.userid);
						MenuFrame.conn.stat.execute(queryTr);
		
					
						Vector<String> ProductCart = new Vector<String>();
						Vector<String> ProductQtyCart = new Vector<String>();
						
						try {
							for (int i = 0; i < tableCart.getRowCount(); i++) {
								String valueProduct = (String) tableCart.getValueAt(i, 0);
								ProductCart.add(valueProduct);
								String valueQty = (String) tableCart.getValueAt(i, 3);
								ProductQtyCart.add(valueQty);
							}
						} catch (Exception e2) {
							e2.printStackTrace();
						}
		
						
						for (int i = 0; i < ProductCart.size(); i++) {
							String productId = ProductCart.get(i);
							String qty = ProductQtyCart.get(i);
							String MemberID = membertxt.getText();
							if (MemberID.isEmpty()) {
								MemberID = "0";
								String queryDetail = "INSERT INTO transactiondetail (transactionid, productId, quantity, memberId) "
										+ "VALUES ('%s', '%s', '%s', '%s')";
								queryDetail = String.format(queryDetail, idTemp, productId, qty, MemberID);
								MenuFrame.conn.stat.execute(queryDetail);
							} else {
								String queryDetail = "INSERT INTO transactiondetail (transactionid, productId, quantity, memberId) "
										+ "VALUES ('%s', '%s', '%s', '%s')";
								queryDetail = String.format(queryDetail, idTemp, productId, qty, MemberID);
								MenuFrame.conn.stat.execute(queryDetail);
							}
						}
						
						getExchange();
						
						int finishtrans = JOptionPane.showConfirmDialog(this, "Have you check your change to customer?");
						
						if (finishtrans == 0) {
							this.dispose();
						} else {
							JOptionPane.showMessageDialog(this, "You can recheck your transaction.", "Message", JOptionPane.INFORMATION_MESSAGE);
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please contact your supervisor!", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if (e.getSource() == cancelBtn) {
			this.dispose();
		}
		
		if (e.getSource() == memberrbt) {
			if (memberrbt.isSelected()) {
				membertxt.setEditable(true);
			} else {
				membertxt.setEditable(false);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tableProduct) {
			try {
				tableCart.clearSelection();
				productId = "";
				cartId = "";
				ProductDataSelected = false;
				cartDataSelected = false;
				int row = tableProduct.getSelectedRow();
				String value = tableProduct.getModel().getValueAt(row, 0).toString();
				if (!value.isEmpty()) {
					ProductDataSelected = true;
				}
				String query = "SELECT * FROM product WHERE productID='%s'";
				query = String.format(query, value);
				MenuFrame.conn.rs = MenuFrame.conn.stat.executeQuery(query);
				MenuFrame.conn.rs.next();
				productId = MenuFrame.conn.rs.getString("productID");
				productIdLbl.setText(MenuFrame.conn.rs.getString("productID"));
				productName.setText(MenuFrame.conn.rs.getString("name"));
			} catch (Exception error) {
				error.printStackTrace();
			}
		} else if (e.getSource() == tableCart) {
			try {
				tableProduct.clearSelection();
				productId = "";
				cartId = "";
				ProductDataSelected = false;
				cartDataSelected = false;
				int row = tableCart.getSelectedRow();
				String id = tableCart.getModel().getValueAt(row, 0).toString();
				String name = tableCart.getModel().getValueAt(row, 1).toString();
				String quantity = tableCart.getModel().getValueAt(row, 3).toString();
				if (!id.isEmpty()) {
					cartDataSelected = true;
				}
				cartId = id;
				productIdLbl.setText(id);
				productName.setText(name);
				qtySpinner.setValue(Integer.valueOf(quantity));
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
