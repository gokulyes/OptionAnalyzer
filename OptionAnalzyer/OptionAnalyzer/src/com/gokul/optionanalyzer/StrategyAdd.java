package com.gokul.optionanalyzer;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JTable;

public class StrategyAdd extends JFrame {
	
	private Connection connection ;
	private JTextField txtName;
	
	private JTextField txtUnderlying;
	private JTextField txtStrike;
	private JTextField txtPrice;
	private JComboBox cmbType;
	
	private JPanel pnlTable;	
	private JTable tblStrategy;

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
		panel.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		panel.setBounds(10, 60, 391, 622);
		getContentPane().add(panel);
		panel.setLayout(null);		
		
		JLabel lblName = new JLabel("Strategy Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(21, 48, 104, 26);
		panel.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(186, 52, 121, 20);
		panel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPosition.setBounds(21, 121, 127, 25);
		panel.add(lblPosition);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblType.setBounds(21, 157, 127, 25);
		panel.add(lblType);
		
		JLabel lblStrike = new JLabel("Strike");
		lblStrike.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStrike.setBounds(21, 194, 127, 25);
		panel.add(lblStrike);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrice.setBounds(21, 230, 127, 25);
		panel.add(lblPrice);
		
		JLabel lblSpot = new JLabel("Underlying Price");
		lblSpot.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSpot.setBounds(21, 85, 127, 25);
		panel.add(lblSpot);
		
		txtUnderlying = new JTextField();
		txtUnderlying.setHorizontalAlignment(SwingConstants.RIGHT);
		txtUnderlying.setBounds(186, 88, 121, 20);
		panel.add(txtUnderlying);
		txtUnderlying.setColumns(10);
		
		JComboBox cmbPosition = new JComboBox();
		cmbPosition.setModel(new DefaultComboBoxModel(new String[] {"Long", "Short", "Covered"}));
		cmbPosition.setSelectedIndex(0);
		cmbPosition.setBounds(186, 121, 121, 22);
		panel.add(cmbPosition);		
		
		cmbType = new JComboBox();
		cmbType.setModel(new DefaultComboBoxModel(new String[] {"Call", "Put"}));
		cmbType.setSelectedIndex(0);
		cmbType.setBounds(186, 159, 121, 22);
		panel.add(cmbType);
		
		txtStrike = new JTextField();
		txtStrike.setHorizontalAlignment(SwingConstants.RIGHT);
		txtStrike.setColumns(10);
		txtStrike.setBounds(186, 197, 121, 20);
		panel.add(txtStrike);
		
		txtPrice = new JTextField();
		txtPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPrice.setColumns(10);
		txtPrice.setBounds(186, 233, 121, 20);
		panel.add(txtPrice);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Option Strategy");
		lblNewJgoodiesTitle.setBounds(21, 11, 88, 14);
		panel.add(lblNewJgoodiesTitle);
		
		pnlTable = new JPanel();
		pnlTable.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, true));
		pnlTable.setBounds(412, 60, 906, 622);
		getContentPane().add(pnlTable);		
		pnlTable.setLayout(null);
		
		tblStrategy = new JTable();
		tblStrategy.setBounds(10, 11, 886, 600);
		pnlTable.add(tblStrategy);
		
		JButton btnAdd = new JButton("Add");

		btnAdd.setIcon(new ImageIcon(StrategyAdd.class.getResource("/ic_check_box.png")));
		System.out.print(StrategyAdd.class.getResource("/imgAdd.png"));
		

		btnAdd.setBounds(218, 309, 89, 23);
		panel.add(btnAdd);
		
	
		JButton btnClose = new JButton("Close");
		btnClose.setIcon(new ImageIcon(StrategyAdd.class.getResource("/imgStop.png")));
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(104, 309, 89, 23);
		panel.add(btnClose);
		

		
//		connectDatabase();
		createTableNew();
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String insert_into_strategy = "insert into STRATEGY (name, position, type, strike, price)values ( ?, ?, ?, ?, ?)";
				
				try {
					
					PreparedStatement preparedStatement = connection.prepareStatement(insert_into_strategy);
					preparedStatement.setString(1, txtName.getText());
					preparedStatement.setInt( 2, cmbPosition.getSelectedIndex());
					preparedStatement.setInt( 3, cmbType.getSelectedIndex());
					preparedStatement.setInt( 4, Integer.parseInt(txtStrike.getText()));
					preparedStatement.setInt( 5, Integer.parseInt(txtPrice.getText()));
					
					
					
					if (preparedStatement.executeUpdate() > 0) {
						JOptionPane.showMessageDialog(null, "New Strategy name inserted into table");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});		
	}
	
	public void createTableNew() {
		
		try {
			
			connection =  DBConnect.getDBConnection();
			
			DatabaseMetaData dmd = connection.getMetaData();
			
			ResultSet resultSet = dmd.getTables(null, null, "STRATEGY", null);
			
			
			if( resultSet.next()) { // if table exists do nothing
			
			} else  { 				// else create new table
				String create_Table = "CREATE TABLE STRATEGY (id int auto_increment, name varchar2(20), position int, type int, strike int, price int)";
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
