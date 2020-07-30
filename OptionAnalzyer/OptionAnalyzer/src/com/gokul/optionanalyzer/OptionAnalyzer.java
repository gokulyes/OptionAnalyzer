package com.gokul.optionanalyzer;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import com.gokul.optionanalyzer.dialog.StrgAddDialog;
import com.gokul.optionanalyzer.dialog.StrgCoverDialog;
import com.gokul.optionanalyzer.dialog.StrgShowAllDialog;
import com.gokul.optionanalyzer.model.OptionLeg;
import com.gokul.optionanalyzer.panel.StrgShowPanel;
import com.gokul.optionanalyzer.util.Util;

public class OptionAnalyzer {

	public static OptionAnalyzer window;
	private JFrame frmOptionanalyzer;
	private Connection connection = null;
	private JLabel lblStrgSel;
	
	private JPanel pnlMain;
	private String strStrgSelected ="";
	private OptionLeg selectedOptionLeg;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new OptionAnalyzer();
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
		
		showGUI();
		

	}
	
	
	
	private void showGUI() {

		createMainWindow();
		createToolBar();
		
	}
	
	private void createMainWindow() {
		frmOptionanalyzer = new JFrame();
		frmOptionanalyzer.setBackground(SystemColor.activeCaptionBorder);
		frmOptionanalyzer.setTitle("OptionAnalyzer");
		frmOptionanalyzer.setBounds(0, 0, 1416, 765);
		frmOptionanalyzer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		frmOptionanalyzer.setLocationRelativeTo(null);
		frmOptionanalyzer.getContentPane().setLayout(new BorderLayout(0, 0));
		
		pnlMain = new JPanel();
		pnlMain.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
		frmOptionanalyzer.getContentPane().add(pnlMain, BorderLayout.CENTER);
		
		
	}
	
	private void createToolBar() {
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(22, 11, 810, 38);
		frmOptionanalyzer.getContentPane().add(toolBar, BorderLayout.PAGE_START);
//		frmOptionanalyzer.getContentPane().add(toolBar);
		
		
		ImageIcon icon = Util.createImageIcon("/imgAddStratergy.png");
		JButton btnAddStratergy = new JButton("    Add    ", icon);
		btnAddStratergy.setMnemonic(KeyEvent.VK_A);
		btnAddStratergy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				new StrgAddDialog(frmOptionanalyzer);
			}
		});
		toolBar.add(btnAddStratergy);
		
		ImageIcon iconSelect = Util.createImageIcon("/imgStrgSelect.png");
		JButton btnStrgSettingSelect = new JButton(" Select  ", iconSelect);
		btnStrgSettingSelect.setMnemonic(KeyEvent.VK_E);
		btnStrgSettingSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new StrgShowAllDialog(frmOptionanalyzer, window);
			}
			
		});
		toolBar.add(btnStrgSettingSelect);
		
		
		ImageIcon iconShow = Util.createImageIcon("/imgShow.png");
		JButton btnShowStratergy = new JButton(" Show ", iconShow);
		btnShowStratergy.setMnemonic(KeyEvent.VK_S);
		btnShowStratergy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new StrgShowPanel(frmOptionanalyzer, strStrgSelected);
//				new StrgShowAllDialog(frmOptionanalyzer);			
			}
		});

		
		toolBar.add(btnShowStratergy);
		
		JButton btnCoverPosition = new JButton(" Cover ", Util.createImageIcon("/imgCoverPostion.png"));
		btnCoverPosition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StrgCoverDialog(frmOptionanalyzer, new OptionLeg(100, "First", "NIFTY2030JUL10000CE", false, 200));
			}
		});
		btnCoverPosition.setMnemonic(KeyEvent.VK_C);
		
		toolBar.add(btnCoverPosition);
		
		JLabel lblNewLabel = new JLabel(" Current Strategy : ");
		toolBar.add(lblNewLabel);
		
		lblStrgSel = new JLabel(" No Stratergy Selected");
		lblStrgSel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStrgSel.setPreferredSize(new Dimension(80,40));// Width, Height
		toolBar.add(lblStrgSel);
		
		
	}
	
	public void setStrgSelected(String strStrgSelected) {
		
		this.strStrgSelected = strStrgSelected;
		lblStrgSel.setText(this.strStrgSelected);
		
		new StrgShowPanel(frmOptionanalyzer, strStrgSelected);

//		System.out.print("\nsetStrgSelected : " + strStrgSelected);
	}
	
	public void setSelectedRow(OptionLeg obj) {
		this.selectedOptionLeg = obj;
	}
	
	public void setContentPanel(JPanel pnlMain) {
		this.pnlMain =  pnlMain;
		
	}
	
//	public void clearContentPanel() {
//		this.pnlMain.removeAll();
//	}


	 
}
