package com.gokul.optionanalyzer.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import com.gokul.optionanalyzer.util.DBConnect;
import com.gokul.optionanalyzer.util.Util;
import com.toedter.calendar.JDateChooser;


public class StrgAddDialog extends JDialog{
	
	JPanel pnlStrgAdd;

	private Connection connection ;
	private JTextField txtName;
	private JTextField txtStrike;
	private JTextField txtPrice;
	private JComboBox cmbType;
	private JDateChooser dteExpiry ;
	
	private JTextField txtSymbol;	
	
	public StrgAddDialog(Frame frmFrame) {
		super(frmFrame, true);

			showGUI();

			setLocationRelativeTo(null);
			setVisible(true);

		
	}
	
	public void showGUI() {
		setTitle("Strategy Add");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 371, 413);
		getContentPane().setLayout(null);
		
		pnlStrgAdd = new JPanel();
		pnlStrgAdd.setBorder(new LineBorder(new Color(180, 180, 180), 1, true));
		pnlStrgAdd.setBounds(10, 11, 342, 340);
		getContentPane().add(pnlStrgAdd);
		pnlStrgAdd.setLayout(null);
		
		JLabel lblName = new JLabel("Strategy Name");
		lblName.setBounds(10, 10, 104, 26);
		pnlStrgAdd.add(lblName);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtName = new JTextField();
		txtName.setBounds(165, 15, 146, 20);
		pnlStrgAdd.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblExpiry = new JLabel("Expiry");
		lblExpiry.setBounds(10, 38, 127, 25);
		pnlStrgAdd.add(lblExpiry);
		lblExpiry.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		dteExpiry = new JDateChooser();
		dteExpiry.setBounds(165, 40, 146, 20);
		pnlStrgAdd.add(dteExpiry);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(10, 63, 127, 25);
		pnlStrgAdd.add(lblType);
		lblType.setFont(new Font("Tahoma", Font.BOLD, 12));
		
//		cmbType = new JComboBox();
//		cmbType.setBounds(165, 65, 146, 22);
//		pnlStrgAdd.add(cmbType);
//		cmbType.setModel(new DefaultComboBoxModel(new String[] {"Call", "Put"}));
//		cmbType.setSelectedIndex(0);
		
	    Box horizontalBox = Box.createHorizontalBox();
	    horizontalBox.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
	    horizontalBox.setBounds(165, 65,  146, 20);
	    pnlStrgAdd.add(horizontalBox);
	    
	    JRadioButton rdbtnCall = new JRadioButton("Call");
	    horizontalBox.add(rdbtnCall);
	    rdbtnCall.setSelected(true);
	    
	    JRadioButton rdbtnPut = new JRadioButton("Put");
	    horizontalBox.add(rdbtnPut);

	    //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnCall);
	    group.add(rdbtnPut);		
		
		JLabel lblStrike = new JLabel("Strike");
		lblStrike.setBounds(10, 95, 127, 25);
		pnlStrgAdd.add(lblStrike);
		lblStrike.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtStrike = new JTextField();
		txtStrike.setBounds(165, 98, 146, 20);
		pnlStrgAdd.add(txtStrike);
		txtStrike.setHorizontalAlignment(SwingConstants.RIGHT);
		txtStrike.setColumns(10);
		
//		JComboBox cmbPosition = new JComboBox();
//		cmbPosition.setBounds(165, 129, 146, 22);
//		pnlStrgAdd.add(cmbPosition);
//		cmbPosition.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusGained(FocusEvent e) {
//			}
//		});
//		cmbPosition.setModel(new DefaultComboBoxModel(new String[] {"Long", "Short"}));
//		cmbPosition.setSelectedIndex(0);
		
	    Box horizontalBox_1 = Box.createHorizontalBox();
	    horizontalBox_1.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
	    horizontalBox_1.setBounds(165, 129, 140, 25);
	    pnlStrgAdd.add(horizontalBox_1);
	    
	    JRadioButton rdbtnLong = new JRadioButton("Long");
	    rdbtnLong.setSelected(true);
	    horizontalBox_1.add(rdbtnLong);
	    
	    JRadioButton rdbtnShort = new JRadioButton("Short");
	    horizontalBox_1.add(rdbtnShort);	
	    
	    //Group the radio buttons.
	    ButtonGroup groupType = new ButtonGroup();
	    groupType.add(rdbtnLong);
	    groupType.add(rdbtnShort);		    
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setBounds(10, 124, 127, 25);
		pnlStrgAdd.add(lblPosition);
		lblPosition.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtPrice = new JTextField();
		txtPrice.setBounds(165, 162, 146, 20);
		pnlStrgAdd.add(txtPrice);
		txtPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
		        SimpleDateFormat sdf = new SimpleDateFormat("yyddMMM");
		        String date = sdf.format(dteExpiry.getDate());
		        date = "NIFTY" + date.toUpperCase() + txtStrike.getText();
		        if( rdbtnCall.isSelected()) {
		        	date = date + "CE";
		        } else {
		        	date = date + "PE";
		        }

		        txtSymbol.setText(date);
//		        System.out.print("Selected Date: " + date);				
			}
		});
		txtPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPrice.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 160, 127, 25);
		pnlStrgAdd.add(lblPrice);
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtSymbol = new JTextField();
		txtSymbol.setBounds(165, 199, 146, 20);
		pnlStrgAdd.add(txtSymbol);
		txtSymbol.setEditable(false);
		txtSymbol.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSymbol.setColumns(10);
		
		JLabel lblSymbol = new JLabel("Symbol");
		lblSymbol.setBounds(10, 196, 127, 25);
		pnlStrgAdd.add(lblSymbol);
		lblSymbol.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		
		JButton btnAdd = new JButton("Add", Util.createImageIcon("/imgStrgSelect.png"));
		btnAdd.setBounds(66, 262, 89, 23);
		btnAdd.setMnemonic(KeyEvent.VK_A);
		pnlStrgAdd.add(btnAdd);
		
	
		JButton btnClose = new JButton("Close", Util.createImageIcon("/imgStop.png"));
		btnClose.setBounds(178, 262, 89, 23);
		btnClose.setMnemonic(KeyEvent.VK_C);
		pnlStrgAdd.add(btnClose);
		
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int nColumnCnt = 4; //
//						"CREATE TABLE STRATEGY (id int auto_increment, name varchar2(20), symbol varchar2(20), position int, price int)";
				String insert_into_strategy = "insert into STRATEGY (name, symbol, position, price, covered, coverprice)values ( ?, ?, ?, ?, false, 0)";
				
				try {
					
					PreparedStatement preparedStatement = connection.prepareStatement(insert_into_strategy);
					preparedStatement.setString(1, txtName.getText());
					preparedStatement.setString(2, txtSymbol.getText());
					preparedStatement.setBoolean( 3, rdbtnLong.isSelected());
					
//							if(rdbtnLong.isSelected()) {
//								preparedStatement.setBoolean( 3, true);
//							} else {
//								preparedStatement.setBoolean( 3, false);
//							}
					
					preparedStatement.setInt( 4, Integer.parseInt(txtPrice.getText()));

					if (preparedStatement.executeUpdate() > 0) {
						JOptionPane.showMessageDialog(null, "New Strategy name inserted into table");
						Object rowData[] = new Object[nColumnCnt] ;
						rowData[0] = txtName.getText();
						rowData[1] = txtSymbol.getText();
						rowData[2] = rdbtnLong.isSelected();
						rowData[3] =Integer.parseInt(txtPrice.getText());
			
						System.out.print(rowData.toString());

					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		connection =  DBConnect.getDBConnection();


		
	}
	

}
