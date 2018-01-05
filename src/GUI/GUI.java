package GUI;

import java.awt.EventQueue;


import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;


import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Event.*;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.UIManager;
import javax.swing.border.Border;

import java.awt.Button;
import java.awt.Color;
import java.awt.Choice;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.event.TextListener;
import java.awt.event.TextEvent;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.List;
import java.awt.Canvas;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.Panel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Label;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JSlider;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import com.sun.org.apache.bcel.internal.generic.LNEG;

import IO_Class.OrganizedCSV;
import IO_Class.ReadCSV;
import IO_Class.ReadOrgenizedCSV;
import IO_Class.Write;
import WiFi.WiFi;

import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;

public class GUI extends JFrame {

	File folder;
	String path;
	private JFrame frame;
	private JTextArea textArea;
    Link L1 = new Link();
    private JTextField TimeMINtxt;
    private JTextField TimeMAXtxt;
    private JTextField Devicetxt;
    private JTextField LatMINtxt;
    private JTextField LatMAXtxt;
    private JTextField LonMintxt;
    private JTextField LonMAXtxt;
    private JTextField AltMINtxt;
    private JTextField AltMAXtxt;

	/**
	 * Launch the application.
	 */


	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */


	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(UIManager.getColor("Button.background"));
		frame.setBounds(100, 100, 749, 622);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//frame.paint(null);


		JButton SaveCSV = new JButton("Save ---> CSV");
		SaveCSV.setBackground(new Color(211, 211, 211));
		JButton ClearButton = new JButton("Clear Data");
		ClearButton.setBackground(new Color(211, 211, 211));
		JButton AddDirButton = new JButton("Add Dir");
		AddDirButton.setBackground(new Color(211, 211, 211));
		JButton CSVButton = new JButton("Add CSV");
		CSVButton.setBackground(new Color(211, 211, 211));
		JButton SaveKMLButton = new JButton("Save ---> KML");
		SaveKMLButton.setBackground(new Color(211, 211, 211));
		JButton btnMin = new JButton("Enter MIN");
		JButton btnMax = new JButton("Enter MAX");
		JRadioButton TimeRadioButton = new JRadioButton("");
		JRadioButton DeviceRadioButton = new JRadioButton("");
		JCheckBox TimeAndCheckBox = new JCheckBox("AND");
		JCheckBox TimeORCheckBox = new JCheckBox("OR");
		JCheckBox TimeNOTCheckBox = new JCheckBox("NOT");
		JButton DeviceEnterButton = new JButton("Enter");
		JCheckBox DeviceAndCheckBox = new JCheckBox("AND");
		JCheckBox DeviceORCheckBox = new JCheckBox("OR");
		JCheckBox DeviceNOTCheckBox = new JCheckBox("NOT");
		JRadioButton LocationRadioButton = new JRadioButton("");
		JButton LatEnterButton = new JButton("Enter");
		JButton LonEnterButton = new JButton("Enter");
		JButton AltEnterButton = new JButton("Enter");
		JCheckBox LocationANDCheckBox = new JCheckBox("AND");
		JCheckBox LocationORCheckBox = new JCheckBox("OR");
		JCheckBox LocationNOTCheckBox = new JCheckBox("NOT");




		// Save CSV Button
		SaveCSV.setEnabled(false);
		SaveCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				L1.SaveCSV("FinalCSV");
				JOptionPane.showMessageDialog(null, "Operation Done !");
			}
		});
		SaveCSV.setBounds(12, 251, 128, 44);
		frame.getContentPane().add(SaveCSV);



		//Clear Button

		ClearButton.setEnabled(false);
		ClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L1 = new Link();
				JOptionPane.showMessageDialog(null, "Operation Done !!!");
				ClearButton.setEnabled(false);
				AddDirButton.setEnabled(true);
				SaveCSV.setEnabled(false);
				textArea.setText(null);
		
			}
		});
		ClearButton.setBounds(12, 194, 128, 44);
		frame.getContentPane().add(ClearButton);


		// Add directory 
		AddDirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Open dir = new Open();
				File wiggleDir = dir.PickFolder();
				if(wiggleDir == null)
				JOptionPane.showMessageDialog(null, "You Didn't choose a Directory ");
					else{
						L1.ReadDir(wiggleDir);
						if(!L1.DataBase.isEmpty()){
							ClearButton.setEnabled(true);
							SaveCSV.setEnabled(true);					
							SaveKMLButton.setEnabled(true);
							textArea.append(" Data Size : "+ L1.DataBase.size() + "\n");
							textArea.append(" # of Mac : "+ ReadCSV.MacCount(L1.DataBase));

						}
						AddDirButton.setEnabled(false);
					}		   

				}
			});

		AddDirButton.setBounds(12, 80, 128, 44);
		frame.getContentPane().add(AddDirButton);



		//Add CSV Button
		CSVButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Open file = new Open();
				File csv = file.PickFile();
				if(csv == null)
					JOptionPane.showMessageDialog(null, "You Didn't choose a File ");
				else{
					if(L1.DataBase.size()==0)
						L1.ReadCSV(csv,false);
					else
						L1.ReadCSV(csv,true);
					SaveCSV.setEnabled(true);					
					ClearButton.setEnabled(true);
					textArea.setText(null);
					textArea.append(" Data Size : "+ L1.DataBase.size() + "\n");
					textArea.append(" # of Mac : "+ ReadCSV.MacCount(L1.DataBase));
				}
		
			}
		});
		CSVButton.setBounds(12, 137, 128, 44);
		frame.getContentPane().add(CSVButton);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		textArea.setBounds(12, 374, 128, 117);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		frame.getContentPane().add(textArea);
		
		// SaveKML Button
		
		SaveKMLButton.setEnabled(false);
		SaveKMLButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				L1.SaveKML();
				JOptionPane.showMessageDialog(null, "Operation Done !");

			}
		});
		SaveKMLButton.setBounds(12, 308, 128, 44);
		frame.getContentPane().add(SaveKMLButton);
		
		
		// TimeMINButton
		
		btnMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnMin.setEnabled(false);
		btnMin.setBounds(299, 112, 103, 25);
		frame.getContentPane().add(btnMin);
		
		// MAXButton
		
		btnMax.setEnabled(false);
		btnMax.setBounds(299, 146, 103, 25);
		frame.getContentPane().add(btnMax);
		
		//TimeRadioButton
		TimeRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(TimeRadioButton.isSelected()==true){
					TimeMINtxt.setEnabled(true);
					TimeMINtxt.setEditable(true);
					TimeMAXtxt.setEnabled(true);
					TimeMAXtxt.setEditable(true);
					btnMax.setEnabled(true);
					btnMin.setEnabled(true);
					TimeAndCheckBox.setEnabled(true);
					TimeORCheckBox.setEnabled(true);
					TimeNOTCheckBox.setEnabled(true);
				}
				else if(TimeRadioButton.isSelected()==false){
					TimeMINtxt.setEnabled(false);
					TimeMINtxt.setEditable(false);
					TimeMAXtxt.setEnabled(false);
					TimeMAXtxt.setEditable(false);
					btnMax.setEnabled(false);
					btnMin.setEnabled(false);
					TimeMINtxt.setText("hh:mm:ss");
					TimeMAXtxt.setText("hh:mm:ss");
					TimeAndCheckBox.setEnabled(false);
					TimeORCheckBox.setEnabled(false);
					TimeNOTCheckBox.setEnabled(false);
					TimeAndCheckBox.setSelected(false);
					TimeORCheckBox.setSelected(false);
					TimeNOTCheckBox.setSelected(false);
					
				}
			
			
			
			}
		});
		TimeRadioButton.setBackground(new Color(255, 255, 255));
		TimeRadioButton.setBounds(232, 80, 25, 25);
		frame.getContentPane().add(TimeRadioButton);
		
		// DeviceRadioButton
		
		DeviceRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DeviceRadioButton.isSelected()==true){
					Devicetxt.setEnabled(true);
					Devicetxt.setEditable(true);
					DeviceEnterButton.setEnabled(true);
					DeviceAndCheckBox.setEnabled(true);
					DeviceORCheckBox.setEnabled(true);
					DeviceNOTCheckBox.setEnabled(true);
				}
					else if(TimeRadioButton.isSelected()==false){
						Devicetxt.setEnabled(false);
						Devicetxt.setEditable(false);
						DeviceEnterButton.setEnabled(false);
						DeviceAndCheckBox.setEnabled(false);
						DeviceORCheckBox.setEnabled(false);
						DeviceNOTCheckBox.setEnabled(false);
						DeviceAndCheckBox.setSelected(false);
						DeviceORCheckBox.setSelected(false);
						DeviceNOTCheckBox.setSelected(false);
					}
					
				}
				
		});
		DeviceRadioButton.setBackground(Color.WHITE);
		DeviceRadioButton.setBounds(244, 226, 25, 25);
		frame.getContentPane().add(DeviceRadioButton);
		
		//LocationRadioButton
		LocationRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(LocationRadioButton.isSelected()==true){
					LatMINtxt.setEnabled(true);
					LatMAXtxt.setEnabled(true);
					LonMintxt.setEnabled(true);
					LonMAXtxt.setEnabled(true);
					AltMINtxt.setEnabled(true);
					AltMAXtxt.setEnabled(true);
					LatEnterButton.setEnabled(true);
					LonEnterButton.setEnabled(true);
					AltEnterButton.setEnabled(true);
					LocationANDCheckBox.setEnabled(true);
					LocationORCheckBox.setEnabled(true);
					LocationNOTCheckBox.setEnabled(true);
					
				}
				else{
					LatMINtxt.setEnabled(false);
					LatMAXtxt.setEnabled(false);
					LonMintxt.setEnabled(false);
					LonMAXtxt.setEnabled(false);
					AltMINtxt.setEnabled(false);
					AltMAXtxt.setEnabled(false);
					LatEnterButton.setEnabled(false);
					LonEnterButton.setEnabled(false);
					AltEnterButton.setEnabled(false);
					LocationANDCheckBox.setEnabled(false);
					LocationORCheckBox.setEnabled(false);
					LocationNOTCheckBox.setEnabled(false);
					LocationANDCheckBox.setSelected(false);
					LocationORCheckBox.setSelected(false);
					LocationNOTCheckBox.setSelected(false);
				}
			}
		});
				LocationRadioButton.setBackground(Color.WHITE);
				LocationRadioButton.setBounds(257, 339, 25, 25);
				frame.getContentPane().add(LocationRadioButton);
		
		//TimeANDCheckBox
		
		TimeAndCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		TimeAndCheckBox.setBounds(191, 180, 59, 25);
		TimeAndCheckBox.setEnabled(false);
		frame.getContentPane().add(TimeAndCheckBox);
		
		//TimeORCheckBox
		
		TimeORCheckBox.setBounds(264, 180, 51, 25);
		TimeORCheckBox.setEnabled(false);
		frame.getContentPane().add(TimeORCheckBox);
		
		//TimeNOTCheckBOX
		
		TimeNOTCheckBox.setBounds(331, 180, 59, 25);
		TimeNOTCheckBox.setEnabled(false);
		frame.getContentPane().add(TimeNOTCheckBox);
		
		//DeviceEnterButton
		

		DeviceEnterButton.setEnabled(false);
		DeviceEnterButton.setBounds(299, 260, 103, 25);
		frame.getContentPane().add(DeviceEnterButton);
		
		//DeviceAndCheckBox
		DeviceAndCheckBox.setEnabled(false);
		DeviceAndCheckBox.setBounds(191, 294, 59, 25);
		frame.getContentPane().add(DeviceAndCheckBox);
		
		//DeviceORCheckBox
		DeviceORCheckBox.setEnabled(false);
		DeviceORCheckBox.setBounds(264, 294, 51, 25);
		frame.getContentPane().add(DeviceORCheckBox);
		
		//DeviceNOTCheckBox
		DeviceNOTCheckBox.setEnabled(false);
		DeviceNOTCheckBox.setBounds(331, 294, 59, 25);
		frame.getContentPane().add(DeviceNOTCheckBox);
		
	
		//LatEnterButton
		LatEnterButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LatEnterButton.setEnabled(false);
		LatEnterButton.setBounds(232, 439, 65, 25);
		frame.getContentPane().add(LatEnterButton);
		
		//LonEnterButton
		LonEnterButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LonEnterButton.setEnabled(false);
		LonEnterButton.setBounds(361, 439, 65, 25);
		frame.getContentPane().add(LonEnterButton);
		
		//AltEnterButton
		AltEnterButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		AltEnterButton.setEnabled(false);
		AltEnterButton.setBounds(489, 439, 65, 25);
		frame.getContentPane().add(AltEnterButton);
		
		
		//LocationANDCheckBox
		LocationANDCheckBox.setEnabled(false);
		LocationANDCheckBox.setBounds(232, 473, 59, 25);
		frame.getContentPane().add(LocationANDCheckBox);
		
		//LocationORCheckBox
		LocationORCheckBox.setEnabled(false);
		LocationORCheckBox.setBounds(305, 473, 51, 25);
		frame.getContentPane().add(LocationORCheckBox);
		
		//LocationNOTCheckBox
		LocationNOTCheckBox.setEnabled(false);
		LocationNOTCheckBox.setBounds(367, 473, 59, 25);
		frame.getContentPane().add(LocationNOTCheckBox);
		
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setForeground(new Color(255, 255, 255));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(170, 13, 9, 507);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 0, 0));
		separator_1.setForeground(new Color(255, 255, 255));
		separator_1.setBounds(178, 55, 393, 2);
		frame.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(Color.WHITE);
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(579, 13, 9, 507);
		frame.getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(new Color(0, 0, 0));
		separator_3.setForeground(new Color(255, 255, 255));
		separator_3.setBounds(12, 55, 146, 2);
		frame.getContentPane().add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setForeground(Color.WHITE);
		separator_4.setBackground(Color.BLACK);
		separator_4.setBounds(306, 376, 9, 88);
		frame.getContentPane().add(separator_4);
		
		JLabel lblFilters = new JLabel("Filters");
		lblFilters.setHorizontalAlignment(SwingConstants.CENTER);
		lblFilters.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFilters.setBounds(317, 1, 91, 64);
		frame.getContentPane().add(lblFilters);
				
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTime.setBounds(191, 69, 91, 44);
		frame.getContentPane().add(lblTime);
		
		JLabel lblDevice = new JLabel("Device");
		lblDevice.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDevice.setBounds(191, 214, 59, 44);
		frame.getContentPane().add(lblDevice);

		JLabel lblStart = new JLabel("Start");
		lblStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblStart.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblStart.setBounds(29, 18, 80, 30);
		frame.getContentPane().add(lblStart);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLocation.setBounds(191, 328, 71, 44);
		frame.getContentPane().add(lblLocation);
		
		
		TimeMINtxt = new JTextField();
		TimeMINtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TimeMINtxt.setText("");
			}
		});
		TimeMINtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TimeMINtxt.setEnabled(false);
		TimeMINtxt.setText("hh:mm:ss");
		TimeMINtxt.setBounds(191, 112, 91, 25);
		frame.getContentPane().add(TimeMINtxt);
		TimeMINtxt.setColumns(10);
		
		
	
		TimeMAXtxt = new JTextField();
		TimeMAXtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TimeMAXtxt.setText("");
			}
		});
		TimeMAXtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TimeMAXtxt.setEnabled(false);
		TimeMAXtxt.setText("hh:mm:ss");
		TimeMAXtxt.setBounds(191, 146, 91, 25);
		frame.getContentPane().add(TimeMAXtxt);
		TimeMAXtxt.setColumns(10);
		
		Devicetxt = new JTextField();
		Devicetxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Devicetxt.setText("");
			}
		});
		Devicetxt.setText("Name");
		Devicetxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Devicetxt.setEnabled(false);
		Devicetxt.setColumns(10);
		Devicetxt.setBounds(191, 260, 91, 25);
		frame.getContentPane().add(Devicetxt);
			
		LatMINtxt = new JTextField();
		LatMINtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LatMINtxt.setText("");
			}
		});
		LatMINtxt.setText("XX.XXX");
		LatMINtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LatMINtxt.setEnabled(false);
		LatMINtxt.setColumns(10);
		LatMINtxt.setBounds(232, 376, 65, 25);
		frame.getContentPane().add(LatMINtxt);
		
		JLabel lblLat = new JLabel("Lat :");
		lblLat.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLat.setBounds(191, 380, 39, 16);
		frame.getContentPane().add(lblLat);
		
		LatMAXtxt = new JTextField();
		LatMAXtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LatMAXtxt.setText("");
			}
		});
		LatMAXtxt.setText("YY.YYY");
		LatMAXtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LatMAXtxt.setEnabled(false);
		LatMAXtxt.setColumns(10);
		LatMAXtxt.setBounds(232, 408, 65, 25);
		frame.getContentPane().add(LatMAXtxt);
		
		JLabel lblLon = new JLabel("Lon :");
		lblLon.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLon.setBounds(317, 380, 39, 16);
		frame.getContentPane().add(lblLon);
		
		LonMintxt = new JTextField();
		LonMintxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LonMintxt.setText("");
			}
		});
		LonMintxt.setText("XX.XXX");
		LonMintxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LonMintxt.setEnabled(false);
		LonMintxt.setColumns(10);
		LonMintxt.setBounds(361, 376, 65, 25);
		frame.getContentPane().add(LonMintxt);
		
		LonMAXtxt = new JTextField();
		LonMAXtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LonMAXtxt.setText("");
			}
		});
		LonMAXtxt.setText("YY.YYY");
		LonMAXtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LonMAXtxt.setEnabled(false);
		LonMAXtxt.setColumns(10);
		LonMAXtxt.setBounds(361, 408, 65, 25);
		frame.getContentPane().add(LonMAXtxt);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setForeground(Color.WHITE);
		separator_5.setBackground(Color.BLACK);
		separator_5.setBounds(437, 376, 9, 88);
		frame.getContentPane().add(separator_5);
		
		JLabel lblAlt = new JLabel("Alt :");
		lblAlt.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlt.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAlt.setBounds(448, 381, 39, 16);
		frame.getContentPane().add(lblAlt);
		
		AltMINtxt = new JTextField();
		AltMINtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AltMINtxt.setText("");
			}
		});
		AltMINtxt.setText("XX.XXX");
		AltMINtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		AltMINtxt.setEnabled(false);
		AltMINtxt.setColumns(10);
		AltMINtxt.setBounds(489, 376, 65, 25);
		frame.getContentPane().add(AltMINtxt);
		
		AltMAXtxt = new JTextField();
		AltMAXtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AltMAXtxt.setText("");
			}
		});
		AltMAXtxt.setText("YY.YYY");
		AltMAXtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		AltMAXtxt.setEnabled(false);
		AltMAXtxt.setColumns(10);
		AltMAXtxt.setBounds(489, 408, 65, 25);
		frame.getContentPane().add(AltMAXtxt);
		
	
		
	
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");

		mntmExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		mnFile.add(mntmExit);
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);






		}
	}
