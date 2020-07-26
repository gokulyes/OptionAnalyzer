package com.gokul.optionanalyzer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.gokul.optionanalyzer.model.OptionLeg;
import com.gokul.optionanalyzer.model.OptionStrategy;
import com.gokul.optionanalyzer.util.DBConnect;
import com.gokul.optionanalyzer.util.Util;

import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class StrategyShow extends JFrame {

	private JPanel contentPane;
	private JPanel pnlChart;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					StrategyShow frame = new StrategyShow();
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
	public StrategyShow() {
		setTitle("Stratergy Show");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1920, 1040);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(5, 5, 1894, 27);
		contentPane.add(toolBar);
		
		pnlChart = new JPanel();
		pnlChart.setBounds(15, 53, 1372, 684);
		contentPane.add(pnlChart);		
		
		JButton btnShow = new JButton("Show", Util.createImageIcon("/imgShow.png"));
		
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
//				OptionLeg opt = new OptionLeg(0, 0, 8000, 500);
				OptionStrategy objOptionStrategy = new OptionStrategy();
				
				String strSQL =  "Select * from STRATEGY";
				
				try {
					
					ResultSet rs = DBConnect.getTable(strSQL);
					
					
					
					while(rs.next()) {
//						JOptionPane.showInternalMessageDialog(null, "Result: " + rs.getString(2));
						objOptionStrategy.setStrName(rs.getString(2));
//					Ref:OptionLeg(int iPosition, int iType, int nStrike, int nPrice)
						objOptionStrategy.setOptLeg(new OptionLeg(rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));  
						
					
						
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
		});
		toolBar.add(btnShow);
		
		JButton btnClose = new JButton("Close", Util.createImageIcon("/imgStop.png"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		toolBar.add(btnClose);
		

	}
}
