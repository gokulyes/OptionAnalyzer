package com.gokul.optionanalyzer.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.gokul.optionanalyzer.model.OptionLeg;
import com.gokul.optionanalyzer.util.DBConnect;
import com.gokul.optionanalyzer.util.Util;



public class StrgCoverDialog extends JDialog{
	

	private JPanel pnlCoverPostion;
	private JLabel lblNewLabel;
	private JTextField txtCStrategy;
	private JLabel label;
	private JTextField txtCSymbol;
	private JLabel lblPosition;
	private JRadioButton rdbtnLong;
//	private JTextField txtCPosition;
	private JLabel lblPrice;
	private JTextField txtCPrice;
	private JLabel lblCovered;
	private JLabel lblClosePrice;
	private JTextField txtCCoverPrice;	
	
	private JCheckBox chckbxNewCheckBox;
	
	private OptionLeg objOptionLeg;
	
	public StrgCoverDialog(Frame frmFrame, OptionLeg  objOptionLeg) {
		super(frmFrame, true);

			this.objOptionLeg = objOptionLeg;
			showGUI();

			setLocationRelativeTo(null);
			setVisible(true);

		
	}

	private void showGUI() {
		setTitle("Option Analyzer: Cover Postion");
		setBounds(0, 0, 400, 350);
		getContentPane().setLayout(null);
		
		pnlCoverPostion = new JPanel();
		pnlCoverPostion.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
		pnlCoverPostion.setBounds(10, 11, 360, 290);
		getContentPane().add(pnlCoverPostion);
		pnlCoverPostion.setLayout(null);

		lblNewLabel = new JLabel("Strategy");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(23, 30, 122, 14);
		pnlCoverPostion.add(lblNewLabel);
		
		txtCStrategy = new JTextField();
		txtCStrategy.setBounds(155, 30, 179, 20);
		pnlCoverPostion.add(txtCStrategy);
		txtCStrategy.setColumns(10);
		
		label = new JLabel("Symbol");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(23, 55, 122, 14);
		pnlCoverPostion.add(label);
		
		txtCSymbol = new JTextField();
		txtCSymbol.setColumns(10);
		txtCSymbol.setBounds(155, 55, 179, 20);
		pnlCoverPostion.add(txtCSymbol);
		
		lblPosition = new JLabel("Position");
		lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPosition.setBounds(23, 80, 122, 14);
		pnlCoverPostion.add(lblPosition);
		
	    Box horizontalBox_1 = Box.createHorizontalBox();
	    horizontalBox_1.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
	    horizontalBox_1.setBounds(155, 80, 179, 20);
	    pnlCoverPostion.add(horizontalBox_1);
	    
	    rdbtnLong = new JRadioButton("Long");
	    rdbtnLong.setSelected(true);
	    horizontalBox_1.add(rdbtnLong);
	    
	    JRadioButton rdbtnShort = new JRadioButton("Short");
	    horizontalBox_1.add(rdbtnShort);	
	    
	    //Group the radio buttons.
	    ButtonGroup groupType = new ButtonGroup();
	    groupType.add(rdbtnLong);
	    groupType.add(rdbtnShort);		    
			    
		
//		txtCPosition = new JTextField();
//		txtCPosition.setColumns(10);
//		txtCPosition.setBounds(155, 80, 179, 20);
//		pnlCoverPostion.add(txtCPosition);
		
		lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrice.setBounds(23, 105, 122, 14);
		pnlCoverPostion.add(lblPrice);
		
		txtCPrice = new JTextField();
		txtCPrice.setColumns(10);
		txtCPrice.setBounds(155, 105, 179, 20);
		pnlCoverPostion.add(txtCPrice);
		
		lblCovered = new JLabel("Covered");
		lblCovered.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCovered.setBounds(23, 170, 122, 14);
		pnlCoverPostion.add(lblCovered);
		
		lblClosePrice = new JLabel("Close Price");
		lblClosePrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblClosePrice.setBounds(23, 195, 122, 14);
		pnlCoverPostion.add(lblClosePrice);
		
		txtCCoverPrice = new JTextField();
		txtCCoverPrice.setColumns(10);
		txtCCoverPrice.setBounds(155, 195, 179, 20);
		pnlCoverPostion.add(txtCCoverPrice);
		
		chckbxNewCheckBox = new JCheckBox("Postion Covered");
		chckbxNewCheckBox.setBounds(154, 170, 180, 23);
		pnlCoverPostion.add(chckbxNewCheckBox);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(153, 180, 209));
		separator.setBounds(23, 130, 311, 10);
		pnlCoverPostion.add(separator);
		
		JButton btnCUpdate = new JButton("Update", Util.createImageIcon("/imgCoverPostion.png"));
		btnCUpdate.setMnemonic(KeyEvent.VK_U);
		btnCUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
//		btnCUpdate.setBounds(196, 248, 110, 23);
		btnCUpdate.setBounds(204, 248, 110, 23);
		pnlCoverPostion.add(btnCUpdate);
	
		JButton btnClose = new JButton("Close", Util.createImageIcon("/imgStrgClose.png"));
		btnClose.setMnemonic(KeyEvent.VK_C);		
//		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();				
			}
		});
//		btnClose.setBounds(70, 248, 110, 23);		
		btnClose.setBounds(78, 248, 110, 23);
		pnlCoverPostion.add(btnClose);		
		
		setValuesToFields(); 				// Populate field values from table.
	}	
	
	public void setValuesToFields() {

		txtCStrategy.setText(objOptionLeg.getStrategyName());
		txtCSymbol.setText(objOptionLeg.getSymbol());
		rdbtnLong.setSelected(objOptionLeg.getPosition());
		txtCPrice.setText(Integer.toString(objOptionLeg.getnPrice()));
		
		txtCStrategy.setEnabled(false);
		txtCSymbol.setEnabled(false);
		rdbtnLong.setEnabled(false);
		txtCPrice.setEnabled(false);
		
		chckbxNewCheckBox.setSelected(objOptionLeg.getPostionCover());
		txtCCoverPrice.setText(Integer.toString(objOptionLeg.getPostionCoverPrice()));
		
		
	}
	
	public void populateDataTable() {
		String strSQL =  "Select * from STRATEGY"; // Get all Distinct Strategy names from table.
		
		
		try {
			
			ResultSet rs = DBConnect.getTable(strSQL);
			
			if(rs != null) {
				
				int nColumnCnt = 1; //
				
				Object rowData[] = new Object[nColumnCnt] ;
				
				while(rs.next()) {
					
					rowData[0] = rs.getString(1);
					
				
					
//					System.out.print( "\n" + "Name: " + rowData[0]); 

				}

			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}

}
