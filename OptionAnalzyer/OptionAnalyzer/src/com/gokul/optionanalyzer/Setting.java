package com.gokul.optionanalyzer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import com.gokul.optionanalyzer.dialog.StrgAddDialog;
import com.gokul.optionanalyzer.dialog.StrgShowAllDialog;
import com.gokul.optionanalyzer.util.Util;

import java.awt.SystemColor;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class Setting extends JFrame {

	private JPanel contentPane;
	private Setting frame;
	ImageIcon icon = Util.createImageIcon(Setting.class,"/imgDialogIcon.png");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Setting frame = new Setting();
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
	public Setting() {
		setTitle("Setting");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlSettingMain = new JPanel();
		pnlSettingMain.setBorder(new LineBorder(SystemColor.activeCaption));
		contentPane.add(pnlSettingMain, BorderLayout.CENTER);
		pnlSettingMain.setLayout(null);
		
		JButton btnStrgSettingSelect = new JButton("Select");
		btnStrgSettingSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				StrgShowAllDialog obj = new StrgShowAllDialog(frame);
			}
			
		});
		btnStrgSettingSelect.setIcon(new ImageIcon(Setting.class.getResource("/imgStrgSelect.png")));
		btnStrgSettingSelect.setMnemonic(KeyEvent.VK_S);
		btnStrgSettingSelect.setBounds(0, 0, 124, 23);
		pnlSettingMain.add(btnStrgSettingSelect);
		
		JButton btnStrgSettingAdd = new JButton("Add");
		btnStrgSettingAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StrgAddDialog objStrgAddDialog = new StrgAddDialog(frame);
//				objStrgAddDialog.setVisible(true);
			}
		});
		btnStrgSettingAdd.setIcon(new ImageIcon(Setting.class.getResource("/imgAddStratergy.png")));
		btnStrgSettingAdd.setMnemonic(KeyEvent.VK_A);
		btnStrgSettingAdd.setBounds(0, 23, 124, 23);
		pnlSettingMain.add(btnStrgSettingAdd);
		
		JButton btnStrgSettingClose = new JButton("Close");
		btnStrgSettingClose.setIcon(new ImageIcon(Setting.class.getResource("/imgStrgClose.png")));
		btnStrgSettingClose.setMnemonic(KeyEvent.VK_C);
		btnStrgSettingClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnStrgSettingClose.setBounds(0, 57, 124, 23);
		pnlSettingMain.add(btnStrgSettingClose);
		
		setLocationRelativeTo(null);
	}
	
}
