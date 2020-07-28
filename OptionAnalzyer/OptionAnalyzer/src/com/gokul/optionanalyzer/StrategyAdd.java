package com.gokul.optionanalyzer;

import java.awt.EventQueue;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.gokul.optionanalyzer.model.OptionLeg;
import com.gokul.optionanalyzer.model.StrategyTable;
import com.gokul.optionanalyzer.util.DBConnect;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;



public class StrategyAdd extends JFrame {
	
	private Connection connection ;
	private JTextField txtName;
	private JTextField txtStrike;
	private JTextField txtPrice;
	private JComboBox cmbType;
	
	private JPanel pnlTable;	
	private JTable tblStrategy;
	
	private StrategyTable strategyTableModel;
	private DefaultTableModel model;
	private JTable table;
	private JTextField txtSymbol;
	
	private JDateChooser dteExpiry;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					StrategyAdd frame = new StrategyAdd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StrategyAdd() {
		setTitle("Strategy Add");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1416, 765);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(180, 180, 180), 1, true));
		panel.setBounds(10, 83, 391, 622);
		getContentPane().add(panel);
		panel.setLayout(null);		
		
		pnlTable = new JPanel();
		pnlTable.setBorder(new LineBorder(new Color(180, 180, 180), 1, true));
		pnlTable.setBounds(417, 83, 906, 622);
		getContentPane().add(pnlTable);		
		pnlTable.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 886, 587);
		pnlTable.add(scrollPane);
		
		
		tblStrategy = new JTable(new StrategyTable());
		scrollPane.setViewportView(tblStrategy);
		strategyTableModel = (StrategyTable) tblStrategy.getModel();
		System.out.print(StrategyAdd.class.getResource("/imgAdd.png"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(180, 180, 180), 1, true));
		panel_1.setBounds(10, 11, 1325, 61);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblName = new JLabel("Strategy Name");
		lblName.setBounds(10, 0, 104, 26);
		panel_1.add(lblName);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtName = new JTextField();
		txtName.setBounds(10, 30, 146, 20);
		panel_1.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblExpiry = new JLabel("Expiry");
		lblExpiry.setBounds(165, 1, 127, 25);
		panel_1.add(lblExpiry);
		lblExpiry.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		dteExpiry = new JDateChooser();
		dteExpiry.setBounds(165, 30, 147, 20);
		panel_1.add(dteExpiry);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(321, 1, 127, 25);
		panel_1.add(lblType);
		lblType.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		cmbType = new JComboBox();
		cmbType.setBounds(322, 29, 146, 22);
		panel_1.add(cmbType);
		cmbType.setModel(new DefaultComboBoxModel(new String[] {"Call", "Put"}));
		cmbType.setSelectedIndex(0);
		
		JLabel lblStrike = new JLabel("Strike");
		lblStrike.setBounds(478, 1, 127, 25);
		panel_1.add(lblStrike);
		lblStrike.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtStrike = new JTextField();
		txtStrike.setBounds(478, 30, 146, 20);
		panel_1.add(txtStrike);
		txtStrike.setHorizontalAlignment(SwingConstants.RIGHT);
		txtStrike.setColumns(10);
		
		JComboBox cmbPosition = new JComboBox();
		cmbPosition.setBounds(634, 29, 146, 22);
		panel_1.add(cmbPosition);
		cmbPosition.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		cmbPosition.setModel(new DefaultComboBoxModel(new String[] {"Long", "Short"}));
		cmbPosition.setSelectedIndex(0);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setBounds(634, 1, 127, 25);
		panel_1.add(lblPosition);
		lblPosition.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtPrice = new JTextField();
		txtPrice.setBounds(790, 30, 146, 20);
		panel_1.add(txtPrice);
		txtPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
		        SimpleDateFormat sdf = new SimpleDateFormat("yyddMMM");
		        String date = sdf.format(dteExpiry.getDate());
		        date = "NIFTY" + date.toUpperCase() + txtStrike.getText();
		        if (cmbType.getSelectedIndex() == 0) //0: Call, 1: Put
		        {
		        	date = date + "CE";
		        } else {
		        	date = date + "PE";
		        }
		        txtSymbol.setText(date);
		        System.out.print("Selected Date: " + date);				
			}
		});
		txtPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPrice.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(790, 1, 127, 25);
		panel_1.add(lblPrice);
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtSymbol = new JTextField();
		txtSymbol.setBounds(946, 30, 146, 20);
		panel_1.add(txtSymbol);
		txtSymbol.setEditable(false);
		txtSymbol.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSymbol.setColumns(10);
		
		JLabel lblSymbol = new JLabel("Symbol");
		lblSymbol.setBounds(946, 1, 127, 25);
		panel_1.add(lblSymbol);
		lblSymbol.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(1125, 29, 89, 23);
		panel_1.add(btnAdd);
		
				btnAdd.setIcon(new ImageIcon(StrategyAdd.class.getResource("/ic_check_box.png")));
				
	
				JButton btnClose = new JButton("Close");
				btnClose.setBounds(1224, 29, 89, 23);
				panel_1.add(btnClose);
				btnClose.setIcon(new ImageIcon(StrategyAdd.class.getResource("/imgStop.png")));
				btnClose.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
				JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Option Strategy");
				lblNewJgoodiesTitle.setBounds(10, 0, 88, 14);
				getContentPane().add(lblNewJgoodiesTitle);
				
				btnAdd.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						int nColumnCnt = 4; //
//				"CREATE TABLE STRATEGY (id int auto_increment, name varchar2(20), symbol varchar2(20), position int, price int)";
						String insert_into_strategy = "insert into STRATEGY (name, symbol, position, price)values ( ?, ?, ?, ?)";
						
						try {
							
							PreparedStatement preparedStatement = connection.prepareStatement(insert_into_strategy);
							preparedStatement.setString(1, txtName.getText());
							preparedStatement.setString(2, txtSymbol.getText());
							
							preparedStatement.setInt( 3, cmbPosition.getSelectedIndex());
							preparedStatement.setInt( 4, Integer.parseInt(txtPrice.getText()));

							if (preparedStatement.executeUpdate() > 0) {
								JOptionPane.showMessageDialog(null, "New Strategy name inserted into table");
								Object rowData[] = new Object[nColumnCnt] ;
								rowData[0] = txtName.getText();
								rowData[1] = txtSymbol.getText();
								rowData[2] = cmbPosition.getSelectedIndex();
								rowData[3] =Integer.parseInt(txtPrice.getText());
					
								System.out.print(rowData.toString());

//						strategyTableModel.addRow(rowData);
								strategyTableModel.addRowData( txtName.getText(), new OptionLeg(txtSymbol.getText(), cmbPosition.getSelectedIndex(),Integer.parseInt(txtPrice.getText())));
								strategyTableModel.fireTableDataChanged();						
							}
							
//					initTable();
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});	
		panel.invalidate();
		panel.repaint();

		createTableNew();
		
		initTable();

	}
	
	public void initTable() {
//		OptionStrategy objOptionStrategy = new OptionStrategy();
		
		String strSQL =  "Select * from STRATEGY";
		
		
		try {
			
			ResultSet rs = DBConnect.getTable(strSQL);
			
			if(rs != null) {
				
				int nColumnCnt = 4; //
				
				Object rowData[] = new Object[nColumnCnt] ;
				String strStrategyName = "";
				
				while(rs.next()) {
	//				JOptionPane.showInternalMessageDialog(null, "Result: " + rs.getString(2));
					strStrategyName = rs.getString(2);
//					objOptionStrategy.setStrName(rs.getString(2));
	//			Ref:OptionLeg(int iPosition, int iType, int nStrike, int nPrice)
					OptionLeg objOptionLeg = new OptionLeg(rs.getString(3), rs.getInt(4), rs.getInt(5));
//					objOptionStrategy.setOptLeg(objOptionLeg);  
					
					rowData[0] = rs.getString(2);
					rowData[1] = rs.getString(3);
					rowData[2] = rs.getInt(4);
					rowData[3] = rs.getInt(5);
			    	
//					System.out.print( "\n ObJ: " + objOptionLeg.toString() );
//			    	if(objOptionLeg == null)
//			    	{
//			    		JOptionPane.showMessageDialog(null, "addData: obj null");
//			    	}else {
//
//			    	}
					
					strategyTableModel.addRowData(strStrategyName, objOptionLeg);
					strategyTableModel.fireTableDataChanged();					
					
					System.out.print( "\n" + "Name: " + rowData[0]  + " Symbol: "+ rowData[1]  + " Position: "+ rowData[2]  + " Price: "+ rowData[3]   );

				}

			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
	
	
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}	
}
