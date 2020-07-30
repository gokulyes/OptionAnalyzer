package com.gokul.optionanalyzer.panel;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.gokul.optionanalyzer.OptionAnalyzer;
import com.gokul.optionanalyzer.model.OptionLeg;
import com.gokul.optionanalyzer.model.OptionStrategy;
import com.gokul.optionanalyzer.model.StrategyTableModel;
import com.gokul.optionanalyzer.util.DBConnect;
import com.gokul.optionanalyzer.util.Util;
import com.toedter.calendar.JDateChooser;


public class StrgShowPanel extends JPanel {

	private Connection connection ;
//	private OptionAnalyzer objOptionAnalyzer;
	
	private String strStrgName="";
	
	private JPanel pnlTable;	
	private JTable tblStrategy;
	
	private StrategyTableModel StgTableModel;
	private OptionLeg objOptionLeg =  new OptionLeg();
	
	
	
//	private JDateChooser dteExpiry;

	private JPanel pnlChart;	
	private JFrame frame;
	private JPanel pnlStrgShow;
	
    public StrgShowPanel(JFrame frame, String strStrgName) {
        super(new BorderLayout());
        this.frame = frame;
        this.strStrgName =  strStrgName;
        
        createGUI();
    }
    
	private void createGUI() {
		// TODO Auto-generated method stub
		frame.setTitle("Current Strategy View");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//		setBounds(0, 0, 1416, 765);
		frame.getContentPane().setLayout(null);
		
		pnlStrgShow = new JPanel();
		pnlStrgShow.setBounds(10, 50, 1346, 645);
		frame.getContentPane().add(pnlStrgShow);
		pnlStrgShow.setLayout(null);
		
		
		pnlTable = new JPanel();
		pnlTable.setBounds(10, 11, 368, 321);
		pnlStrgShow.add(pnlTable);
		pnlTable.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
		pnlTable.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 348, 299);
		pnlTable.add(scrollPane);
		
		
		tblStrategy = new JTable(new StrategyTableModel());
		scrollPane.setViewportView(tblStrategy);
		
		pnlChart = new JPanel();
		pnlChart.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
		pnlChart.setBounds(388, 11, 949, 622);
		pnlStrgShow.add(pnlChart);
		StgTableModel = (StrategyTableModel) tblStrategy.getModel();
		
		// set the column width for each column
		tblStrategy.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblStrategy.getColumnModel().getColumn(1).setPreferredWidth(20);
		tblStrategy.getColumnModel().getColumn(2).setPreferredWidth(10);
		
		
		tblStrategy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tblStrategy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int iSelectedRow = tblStrategy.getSelectedRow();
//				showSelectedStrategyTable( model.getValueAt(iSelectedRow, 0).toString());
				
//				lblStrgName.setText(model.getValueAt(iSelectedRow, 0).toString());
//				
//				objOptionAnalyzer.setStrgSelected(model.getValueAt(iSelectedRow, 0).toString());
				objOptionLeg = StgTableModel.getRowData(iSelectedRow);
				
				System.out.print("\nmouseClicked : " + StgTableModel.getRowData(iSelectedRow).toString());// StgTableModel.getValueAt(iSelectedRow, 0));
			}
		});		
		
//		dteExpiry = new JDateChooser();
//		dteExpiry.setBounds(165, 41, 147, 20);
				

		createTableNew();
		
		showSelectedStrategyTable(strStrgName);
		
//		objOptionAnalyzer.setContentPanel(pnlStrgShow);
		
		

	}
	
	
	public void showSelectedStrategyTable(String strStgName) {
		
		String strSQL =  "Select * from STRATEGY where name ='" + strStgName + "'";
		String strStrategyName = "";
		
		try {
			
			ResultSet rs = DBConnect.getTable(strSQL);
			
			if(rs != null) {
				
//				int nColumnCnt = 10; //
//				
//				Object rowData[] = new Object[nColumnCnt] ;
				
				StgTableModel.clearAllRows();
				while(rs.next()) {

					strStrategyName = rs.getString(2);
					OptionLeg objOptionLeg = new OptionLeg(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getInt(5), rs.getBoolean(6), rs.getInt(7));

					StgTableModel.addRowData(strStrategyName, objOptionLeg);
					StgTableModel.fireTableDataChanged();					
					
//					System.out.print( "\n" + "ID: " + rowData[0]  + " Name: "+ rowData[1]  + " objOptionLeg. ID: "+ objOptionLeg.getID() + " Name: "+objOptionLeg.getStrategyName() );// + " Price: "+ rowData[3]   );

				}

			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Now that we show the chart
		showChart(strStrategyName);
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
	

	
	public void showChart (String strStgName) {

		OptionStrategy objOptionStrategy = new OptionStrategy();
		String strSQL =  "Select * from STRATEGY where name ='" + strStgName + "'";
//		String strSQL =  "Select * from STRATEGY";
		
		try {
			
			ResultSet rs = DBConnect.getTable(strSQL);
			
			
			
			while(rs.next()) {
//				JOptionPane.showInternalMessageDialog(null, "Result: " + rs.getString(2));
				objOptionStrategy.setStrName(rs.getString(2));
//			Ref:OptionLeg(int iPosition, int iType, int nStrike, int nPrice)
				objOptionStrategy.setOptLeg(new OptionLeg(rs.getInt(1), rs.getNString(2), rs.getString(3), rs.getBoolean(4), rs.getInt(5)));  
				
			
				
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		pnlChart.removeAll();
		pnlChart.add(Util.getLineChart(objOptionStrategy.getPayOffData()));
		pnlChart.revalidate();
		pnlChart.repaint();				
	}
}
