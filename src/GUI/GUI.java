package GUI;

import java.awt.EventQueue;


import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import oop2.OrganizedCSV;
import oop2.ReadCSV;
import oop2.ReadOrgenizedCSV;
import oop2.WiFi;
import oop2.Write;

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
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class GUI extends JFrame {

	File folder;
	String path;
	private JFrame frame;
	private JTextArea textArea;
    Connection L1 = new Connection();

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

	public void paint(Graphics g){

		g.drawLine(0, 90, 400, 617);

	}


	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(UIManager.getColor("Button.background"));
		frame.setBounds(100, 100, 725, 523);
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



		// Save CSV Button
		SaveCSV.setEnabled(false);
		SaveCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				L1.SaveCSV("FinalCSV");
				JOptionPane.showMessageDialog(null, "Operation Done !");
			}
		});
		SaveCSV.setBounds(12, 184, 128, 44);
		frame.getContentPane().add(SaveCSV);



		//Clear Button

		ClearButton.setEnabled(false);
		ClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L1 = new Connection();
				JOptionPane.showMessageDialog(null, "Operation Done !!!");
				ClearButton.setEnabled(false);
				AddDirButton.setEnabled(true);
				SaveCSV.setEnabled(false);
				textArea.setText(null);
		
			}
		});
		ClearButton.setBounds(12, 127, 128, 44);
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

		AddDirButton.setBounds(12, 13, 128, 44);
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
		CSVButton.setBounds(12, 70, 128, 44);
		frame.getContentPane().add(CSVButton);

		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		textArea.setBounds(12, 320, 128, 117);
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
		SaveKMLButton.setBounds(12, 241, 128, 44);
		frame.getContentPane().add(SaveKMLButton);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(233, 23, 97, 25);
		frame.getContentPane().add(btnNewButton);

		
		
		
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
