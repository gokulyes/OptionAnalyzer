package com.gokul.optionanalyzer.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.gokul.optionanalyzer.Strategy;
import com.gokul.optionanalyzer.util.DBConnect;
import com.gokul.optionanalyzer.util.Util;
import com.toedter.calendar.JDateChooser;


public class StrgShowAllDialog extends JDialog{
	
	JPanel pnlAllStrgTable;
	JLabel lblStrgName;
	
	private Connection connection ;
	
	private DefaultTableModel model;
	private JTable table;
	
	private JDateChooser dteExpiry;
	private JTable tblAllStrategy;	

	public StrgShowAllDialog(Frame frmFrame) {
		super(frmFrame, true);
	
		showGUI();
	
		setLocationRelativeTo(null);
		setVisible(true);

		
	}

	private void showGUI() {
		setTitle("Strategy Add");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 391, 441);
		getContentPane().setLayout(null);
		
		pnlAllStrgTable = new JPanel();
		pnlAllStrgTable.setBorder(new LineBorder(new Color(180, 180, 180), 1, true));
		pnlAllStrgTable.setBounds(10, 11, 358, 365);
		getContentPane().add(pnlAllStrgTable);
		pnlAllStrgTable.setLayout(null);		
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 339, 265);
		pnlAllStrgTable.add(scrollPane_1);
		
		tblAllStrategy = new JTable();
		scrollPane_1.setViewportView(tblAllStrategy);

		tblAllStrategy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		tblAllStrategy.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Strategy"
			}
		));
		
		JLabel lblNewLabel = new JLabel("Selected Strategy : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 287, 122, 14);
		pnlAllStrgTable.add(lblNewLabel);
		
		lblStrgName = new JLabel("");
		lblStrgName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStrgName.setBounds(129, 288, 152, 14);
		pnlAllStrgTable.add(lblStrgName);
		

		JButton btnClose = new JButton("Close");
		btnClose.setBounds(114, 313, 89, 23);
		pnlAllStrgTable.add(btnClose);
		btnClose.setIcon(Util.createImageIcon(Strategy.class,"/imgStop.png"));

		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});		

		
		model = (DefaultTableModel) tblAllStrategy.getModel();
		
		
		tblAllStrategy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int iSelectedRow = tblAllStrategy.getSelectedRow();
//				showSelectedStrategyTable( model.getValueAt(iSelectedRow, 0).toString());
				
				lblStrgName.setText(model.getValueAt(iSelectedRow, 0).toString());

				System.out.print("\nmouseClicked : " + model.getValueAt(iSelectedRow, 0));
			}
		});
		
		dteExpiry = new JDateChooser();
		dteExpiry.setBounds(165, 41, 147, 20);
				
		pnlAllStrgTable.invalidate();
		pnlAllStrgTable.repaint();

		createTableNew();
		
		showAllStrategyTable();
		
	}	
	public void showAllStrategyTable() {
		
		String strSQL =  "Select distinct(name) from STRATEGY"; // Get all Distinct Strategy names from table.
		
		
		try {
			
			ResultSet rs = DBConnect.getTable(strSQL);
			
			if(rs != null) {
				
				int nColumnCnt = 1; //
				
				Object rowData[] = new Object[nColumnCnt] ;
				
				while(rs.next()) {
					
					rowData[0] = rs.getString(1);
					
					showAllStrategyTableDataChange(rowData);
//
//					model.addRow(rowData);
//					model.fireTableDataChanged();
					
				
					
//					System.out.print( "\n" + "Name: " + rowData[0]); 

				}

			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}	
	
	public void showAllStrategyTableDataChange(Object rowData[]) {
		model.addRow(rowData);
		model.fireTableDataChanged();
	}
	
	public void createTableNew() {
		
		try {
			
			connection =  DBConnect.getDBConnection();
			
			DatabaseMetaData dmd = connection.getMetaData();
			
			ResultSet resultSet = dmd.getTables(null, null, "STRATEGY", null);
			
			
			if( resultSet.next()) { // if table exists do nothing
			
			} else  { 				// else create new table
//				String create_Table = "CREATE TABLE STRATEGY (id int auto_increment, name varchar2(20), position int, type int, strike int, price int)";
				String create_Table = "CREATE TABLE STRATEGY (id int auto_increment, name varchar2(20), symbol varchar2(20), position int, price int)";
				PreparedStatement statement = connection.prepareStatement(create_Table);
				statement.executeUpdate();
				System.out.print("\nStrategy Table created\n");
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
}
