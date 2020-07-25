package com.gokul.optionanalyzer;
import com.gokul.optionanalyzer.model.OptionLeg;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JToolBar;

public class OptionAnalyzer {

	private JFrame frmOptionanalyzer;
	private Connection connection = null;
	private JTextField txtUnderlying;
	private JTextField txtStrike;
	private JTextField txtPrice;
	private JSpinner spnPosition;
	private JComboBox cmbType;
	
	private JPanel pnlChart;
	
	private  JFreeChart lineChart;
	private DefaultCategoryDataset dataset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					OptionAnalyzer window = new OptionAnalyzer();
					window.frmOptionanalyzer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OptionAnalyzer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
//		connectDatabase();
		
		frmOptionanalyzer = new JFrame();
		frmOptionanalyzer.setBackground(SystemColor.activeCaptionBorder);
		frmOptionanalyzer.setTitle("OptionAnalyzer");
		frmOptionanalyzer.setBounds(0, 0, 1416, 765);
		frmOptionanalyzer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmOptionanalyzer.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel.setBounds(10, 60, 391, 622);
		frmOptionanalyzer.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Position");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(21, 66, 127, 25);
		panel.add(lblNewLabel);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblType.setBounds(21, 102, 127, 25);
		panel.add(lblType);
		
		JLabel lblStrike = new JLabel("Strike");
		lblStrike.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStrike.setBounds(21, 139, 127, 25);
		panel.add(lblStrike);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrice.setBounds(21, 175, 127, 25);
		panel.add(lblPrice);
		
		JLabel lblSpot = new JLabel("Underlying Price");
		lblSpot.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSpot.setBounds(21, 30, 127, 25);
		panel.add(lblSpot);
		
		txtUnderlying = new JTextField();
		txtUnderlying.setHorizontalAlignment(SwingConstants.RIGHT);
		txtUnderlying.setBounds(186, 33, 121, 20);
		panel.add(txtUnderlying);
		txtUnderlying.setColumns(10);
		
		spnPosition = new JSpinner();
		spnPosition.setBounds(186, 69, 121, 20);
		panel.add(spnPosition);
		
		cmbType = new JComboBox();
		cmbType.setModel(new DefaultComboBoxModel(new String[] {"Call", "Put"}));
//		cmbType.setEnabled(true);
		cmbType.setSelectedIndex(0);
		cmbType.setBounds(186, 104, 121, 22);
		panel.add(cmbType);
		
		txtStrike = new JTextField();
		txtStrike.setHorizontalAlignment(SwingConstants.RIGHT);
		txtStrike.setColumns(10);
		txtStrike.setBounds(186, 142, 121, 20);
		panel.add(txtStrike);
		
		txtPrice = new JTextField();
		txtPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPrice.setColumns(10);
		txtPrice.setBounds(186, 178, 121, 20);
		panel.add(txtPrice);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Option Stratergy");
		lblNewJgoodiesTitle.setBounds(21, 11, 88, 14);
		panel.add(lblNewJgoodiesTitle);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addOptionLeg();
			}
			
		});
		btnAdd.setBounds(21, 223, 89, 23);
		panel.add(btnAdd);
		
		pnlChart = new JPanel();
		pnlChart.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
		pnlChart.setBounds(412, 60, 906, 622);
		frmOptionanalyzer.getContentPane().add(pnlChart);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(22, 11, 810, 38);
		frmOptionanalyzer.getContentPane().add(toolBar);
		
		
		ImageIcon icon = createImageIcon("/imgAddStratergy.png");
		JButton btnAddStratergy = new JButton("Add Stratergy", icon);
		btnAddStratergy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StrategyAdd strategyAdd = new StrategyAdd();
				strategyAdd.setVisible(true);
			}
		});
		toolBar.add(btnAddStratergy);
		
		ImageIcon iconShow = createImageIcon("/imgShow.png");
		JButton btnShowStratergy = new JButton("Show Stratergy", iconShow);
		btnShowStratergy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				StrategyShow strategyShow = new StrategyShow();
				strategyShow.setVisible(true);				
			}
		});
		toolBar.add(btnShowStratergy);

	}
	
	public void addOptionLeg() {
		OptionLeg optionLeg = new OptionLeg();
		optionLeg.setiPosition((int)spnPosition.getValue());
		optionLeg.setiType(cmbType.getSelectedIndex());

		optionLeg.setnStrike(Integer.parseInt(txtStrike.getText()));
		optionLeg.setnPrice(Integer.parseInt(txtPrice.getText()));
		optionLeg.setPayOffData();

		
		pnlChart.removeAll();
		pnlChart.add(getLineChart(optionLeg.getPayOffData()));
		pnlChart.revalidate();
		pnlChart.repaint();


	}
	
	private void connectDatabase() {
		try {
		
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			JOptionPane.showMessageDialog(null, "Database Connected.");
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "OptionAnalyzer: Error connection database");
			e1.printStackTrace();
		}		
	}
	
	public void showLineChart(String chartTitle, List<Integer> list) {
		JFreeChart lineChart = ChartFactory.createXYLineChart(
		         "PayOff",
		         "Underlying","P/L",
		         createDataset(list) ,
		         PlotOrientation.VERTICAL,
		         true,true,false);	
		 ChartFrame cFrame = new ChartFrame("Line", lineChart	);
		 cFrame.setVisible(true);
		 cFrame.setSize(906, 622);
		
	}
	
	public ChartPanel getLineChart(List<Integer> list) {
		JFreeChart lineChart = ChartFactory.createXYLineChart(
		         "PayOff",
		         "Spot price","P/L",
		         createDataset(list) ,
		         PlotOrientation.VERTICAL,
		         true,true,false);	
		
		ChartPanel chartPanel = new ChartPanel( lineChart );
		chartPanel.setPreferredSize( new java.awt.Dimension( 880, 600 ) );
		chartPanel.setVisible(true);
		
		return chartPanel;

		
	}
	
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = OptionAnalyzer.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
 

	 
	   
	 public XYDataset  createDataset(List<Integer> list) {

	        var series = new XYSeries("Net");
		 	int nUnderlyingPrice = 8000;
		 
			for (Integer element : list) {
				series.add(nUnderlyingPrice, element.doubleValue());
				nUnderlyingPrice += 100;

			}	        
	        

	        var dataset = new XYSeriesCollection();
	        dataset.addSeries(series);

	        return dataset;	 
		 
	 }
	 
}
