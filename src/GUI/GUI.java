package GUI;

import java.awt.EventQueue;
import Filters.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import IO_Class.ReadCSV;
import IO_Class.Write;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI extends JFrame {

	File folder;
	String path;
	int numberOfCheckedButtons = 0;
	private JFrame frame;
	private JTextArea textArea;
    Link L1 = new Link();
	Link L2 = new Link();
    JRadioButton TimeRadioButton = new JRadioButton("");
	JRadioButton DeviceRadioButton = new JRadioButton("");
	JRadioButton LocationRadioButton = new JRadioButton("");
	JButton SaveFilterButton = new JButton("Save Filter");
	JButton StartFiltrationButton = new JButton("Start Filtration");
	Time_Filter time;
	ID_Filter id;
	Location_Filter loc;
	private String TimeGetMin;
	private String TimeGetMax;
	private String DeviceGetInput;
	private String LocationGetMinLat;
	private String LocationGetMaxLat;
	private String LocationGetMinLon;
	private String LocationGetMaxLon;
	private String LocationGetMinAlt;
	private String LocationGetMaxAlt;
	private JTextField TimeMINtxt;
    private JTextField TimeMAXtxt;
    private JTextField Devicetxt;
    private JTextField LatMINtxt;
    private JTextField LatMAXtxt;
    private JTextField LonMINtxt;
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

	private void setEnabled(){
		if(TimeRadioButton.isSelected() && DeviceRadioButton.isSelected())
			LocationRadioButton.setEnabled(false);
		if(TimeRadioButton.isSelected() && LocationRadioButton.isSelected())
			DeviceRadioButton.setEnabled(false);
		if(DeviceRadioButton.isSelected() && LocationRadioButton.isSelected())
			TimeRadioButton.setEnabled(false);
	}
	
	
	private void setDisabled(){
		if(TimeRadioButton.isSelected()){
			DeviceRadioButton.setEnabled(true);
			LocationRadioButton.setEnabled(true);
			
		}
		if(DeviceRadioButton.isSelected()){
			TimeRadioButton.setEnabled(true);
			LocationRadioButton.setEnabled(true);
			
		}
			
		if(LocationRadioButton.isSelected())
			TimeRadioButton.setEnabled(true);
			DeviceRadioButton.setEnabled(true);
	}
	
	private void CheckboxSelected(){
		System.out.println(numberOfCheckedButtons);
		if(numberOfCheckedButtons==2)
			setEnabled();
		
		
	}

	private void CheckboxUnSelected(){
		System.out.println(numberOfCheckedButtons);
		if(numberOfCheckedButtons==1)
			setDisabled();
		
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(UIManager.getColor("Button.background"));
		frame.setBounds(100, 100, 749, 723);
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
		JButton TimeMinEnterButton = new JButton("Enter MIN");
		JButton TimeMaxEnterButton = new JButton("Enter MAX");
		JCheckBox TimeNOTCheckBox = new JCheckBox("NOT");
		JButton DeviceEnterButton = new JButton("Enter");
		JCheckBox DeviceNOTCheckBox = new JCheckBox("NOT");
		JButton LatEnterButton = new JButton("Enter");
		JButton LonEnterButton = new JButton("Enter");
		JButton AltEnterButton = new JButton("Enter");
		JCheckBox LocationNOTCheckBox = new JCheckBox("NOT");
		JCheckBox AndCheckBox = new JCheckBox("AND");
		JCheckBox ORCheckBox = new JCheckBox("OR");
	




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
		
		
		
		//TimeRadioButton
		TimeRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(TimeRadioButton.isSelected()==true){
					TimeMINtxt.setEnabled(true);
					TimeMINtxt.setEditable(true);
					TimeMAXtxt.setEnabled(true);
					TimeMAXtxt.setEditable(true);
					TimeNOTCheckBox.setEnabled(true);
					numberOfCheckedButtons++;
					CheckboxSelected();
				}
				else if(TimeRadioButton.isSelected()==false){
					TimeMINtxt.setEnabled(false);
					TimeMINtxt.setEditable(false);
					TimeMAXtxt.setEnabled(false);
					TimeMAXtxt.setEditable(false);
					TimeMaxEnterButton.setEnabled(false);
					TimeMinEnterButton.setEnabled(false);
					TimeMINtxt.setText("hh:mm:ss");
					TimeMAXtxt.setText("hh:mm:ss");
					TimeNOTCheckBox.setEnabled(false);
					TimeNOTCheckBox.setSelected(false);
					numberOfCheckedButtons--;
					CheckboxUnSelected();
				
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
					DeviceNOTCheckBox.setEnabled(true);
					numberOfCheckedButtons++;
					CheckboxSelected();
				}
					else if(DeviceRadioButton.isSelected()==false){
						Devicetxt.setEnabled(false);
						Devicetxt.setEditable(false);
						DeviceEnterButton.setEnabled(false);
						DeviceNOTCheckBox.setEnabled(false);
						DeviceNOTCheckBox.setSelected(false);
						numberOfCheckedButtons--;
						CheckboxUnSelected();
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
					LonMINtxt.setEnabled(true);
					LonMAXtxt.setEnabled(true);
					AltMINtxt.setEnabled(true);
					AltMAXtxt.setEnabled(true);
					LocationNOTCheckBox.setEnabled(true);
					numberOfCheckedButtons++;
					CheckboxSelected();
				}
				else{
					LatMINtxt.setEnabled(false);
					LatMAXtxt.setEnabled(false);
					LonMINtxt.setEnabled(false);
					LonMAXtxt.setEnabled(false);
					AltMINtxt.setEnabled(false);
					AltMAXtxt.setEnabled(false);
					LatMINtxt.setText("XX.XXX");
					LatMAXtxt.setText("YY.YYY");
					LonMINtxt.setText("XX.XXX");
					LonMAXtxt.setText("YY.YYY");
					AltMINtxt.setText("XX.XXX");
					AltMAXtxt.setText("YY.YYY");
					LatEnterButton.setEnabled(false);
					LonEnterButton.setEnabled(false);
					AltEnterButton.setEnabled(false);
					LocationNOTCheckBox.setEnabled(false);
					LocationNOTCheckBox.setSelected(false);
					numberOfCheckedButtons--;
					CheckboxUnSelected();
				}
			}
		});
				LocationRadioButton.setBackground(Color.WHITE);
				LocationRadioButton.setBounds(257, 339, 25, 25);
				frame.getContentPane().add(LocationRadioButton);
		
		
				// TimeMinEnterButton
				
				TimeMinEnterButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TimeGetMin = TimeMINtxt.getText();
						
					}
				});
				TimeMinEnterButton.setEnabled(false);
				TimeMinEnterButton.setBounds(343, 113, 103, 25);
				frame.getContentPane().add(TimeMinEnterButton);
				
				// TimeMaxEnterButton
				TimeMaxEnterButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TimeGetMax = TimeMAXtxt.getText();
						time = new Time_Filter(TimeGetMin,TimeGetMax);

					
					}
				});
				TimeMaxEnterButton.setEnabled(false);
				TimeMaxEnterButton.setBounds(343, 147, 103, 25);
				frame.getContentPane().add(TimeMaxEnterButton);
						
				
		//TimeNOTCheckBOX
		
		TimeNOTCheckBox.setBounds(232, 180, 59, 25);
		TimeNOTCheckBox.setEnabled(false);
		frame.getContentPane().add(TimeNOTCheckBox);
		
		//DeviceEnterButton
		
		DeviceEnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeviceGetInput = Devicetxt.getText();
				id = new ID_Filter(DeviceGetInput);
			}
		});
		DeviceEnterButton.setEnabled(false);
		DeviceEnterButton.setBounds(343, 261, 103, 25);
		frame.getContentPane().add(DeviceEnterButton);
		
		//DeviceNOTCheckBox
		DeviceNOTCheckBox.setEnabled(false);
		DeviceNOTCheckBox.setBounds(232, 294, 59, 25);
		frame.getContentPane().add(DeviceNOTCheckBox);
		
	
		//LatEnterButton
		LatEnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocationGetMinLat = LatMINtxt.getText();
				LocationGetMaxLat = LatMAXtxt.getText(); 
			}
		});
		LatEnterButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LatEnterButton.setEnabled(false);
		LatEnterButton.setBounds(232, 439, 65, 25);
		frame.getContentPane().add(LatEnterButton);
		
		//LonEnterButton
		LonEnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocationGetMinLon = LonMINtxt.getText();
				LocationGetMaxLon = LonMAXtxt.getText();
			}
		});
		LonEnterButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LonEnterButton.setEnabled(false);
		LonEnterButton.setBounds(361, 439, 65, 25);
		frame.getContentPane().add(LonEnterButton);
		
		//AltEnterButton
		AltEnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocationGetMinAlt = AltMINtxt.getText();
				LocationGetMaxAlt = AltMAXtxt.getText();
				Location_Filter loc = new Location_Filter(LocationGetMinLat, LocationGetMinLon, LocationGetMinAlt, LocationGetMaxLat, LocationGetMaxLon, LocationGetMaxAlt);
			}
		});
		AltEnterButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		AltEnterButton.setEnabled(false);
		AltEnterButton.setBounds(489, 439, 65, 25);
		frame.getContentPane().add(AltEnterButton);
		
		//LocationNOTCheckBox
		LocationNOTCheckBox.setEnabled(false);
		LocationNOTCheckBox.setBounds(232, 473, 59, 25);
		frame.getContentPane().add(LocationNOTCheckBox);
		
		
		//StartFiltrationButton
		StartFiltrationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L2.DataBase = L1.DataBase;
				Write w = new Write(); 
				Not_Filter not1;
				Not_Filter not2;
				And_Filter and;
				Or_Filter or;
				
				if(AndCheckBox.isSelected()){ // and selected 
				if(TimeRadioButton.isSelected() && DeviceRadioButton.isSelected()){ // if time & device selected
						if(TimeNOTCheckBox.isSelected() && DeviceNOTCheckBox.isSelected()){ // not selected both
						not1 = new Not_Filter(time);
						not2 = new Not_Filter(id);
						and = new And_Filter(not1, not2);
						w.SortbyFilter(L2.DataBase, and, "FilteredCSV");
						}
						if(TimeNOTCheckBox.isSelected() && !DeviceNOTCheckBox.isSelected()){ 
							not1 = new Not_Filter(time);
							and = new And_Filter(not1, id);
							w.SortbyFilter(L2.DataBase, and, "FilteredCSV");
						}
					
						else{
							not1 = new Not_Filter(id);
							and = new And_Filter(not1, time);
							w.SortbyFilter(L2.DataBase, and, "FilteredCSV");
						}
					
					}
						
				if(TimeRadioButton.isSelected() && LocationRadioButton.isSelected()){
					if(TimeNOTCheckBox.isSelected() && DeviceNOTCheckBox.isSelected()){ // not selected both
						not1 = new Not_Filter(time);
						not2 = new Not_Filter(loc);
						and = new And_Filter(not1, not2);
						w.SortbyFilter(L2.DataBase, and, "FilteredCSV");
						}
						if(TimeNOTCheckBox.isSelected() && !LocationNOTCheckBox.isSelected()){ 
							not1 = new Not_Filter(time);
							and = new And_Filter(not1, loc);
							w.SortbyFilter(L2.DataBase, and, "FilteredCSV");
						}
					
						else{
							not1 = new Not_Filter(loc);
							and = new And_Filter(not1, time);
							w.SortbyFilter(L2.DataBase, and, "FilteredCSV");
						}
				}
				if(DeviceRadioButton.isSelected() && LocationRadioButton.isSelected()){
					if(DeviceNOTCheckBox.isSelected() && LocationNOTCheckBox.isSelected()){ // not selected both
						not1 = new Not_Filter(id);
						not2 = new Not_Filter(loc);
						and = new And_Filter(not1, not2);
						w.SortbyFilter(L2.DataBase, and, "FilteredCSV");
						}
						if(DeviceNOTCheckBox.isSelected() && !LocationNOTCheckBox.isSelected()){ 
							not1 = new Not_Filter(id);
							and = new And_Filter(not1, loc);
							w.SortbyFilter(L2.DataBase, and, "FilteredCSV");
						}
					
						else{
							not1 = new Not_Filter(loc);
							and = new And_Filter(not1, id);
							w.SortbyFilter(L2.DataBase, and, "FilteredCSV");
						}
					
				}
			}
		
				
				if(ORCheckBox.isSelected()){ // or selected
					if(TimeRadioButton.isSelected() && DeviceRadioButton.isSelected()){ 
						if(TimeNOTCheckBox.isSelected() && DeviceNOTCheckBox.isSelected()){ // not selected both
						not1 = new Not_Filter(time);
						not2 = new Not_Filter(id);
						or = new Or_Filter(not1, not2);
						w.SortbyFilter(L2.DataBase, or, "FilteredCSV");
						}
						if(TimeNOTCheckBox.isSelected() && !DeviceNOTCheckBox.isSelected()){ 
							not1 = new Not_Filter(time);
							or = new Or_Filter(not1, id);
							w.SortbyFilter(L2.DataBase, or, "FilteredCSV");
						}
					
						else{
							not1 = new Not_Filter(id);
							or = new Or_Filter(not1, time);
							w.SortbyFilter(L2.DataBase, or, "FilteredCSV");
						}
					
					}
					if(TimeRadioButton.isSelected() && LocationRadioButton.isSelected()){
						if(TimeNOTCheckBox.isSelected() && DeviceNOTCheckBox.isSelected()){ // not selected both
							not1 = new Not_Filter(time);
							not2 = new Not_Filter(loc);
							or = new Or_Filter(not1, not2);
							w.SortbyFilter(L2.DataBase, or, "FilteredCSV");
							}
							if(TimeNOTCheckBox.isSelected() && !LocationNOTCheckBox.isSelected()){ 
								not1 = new Not_Filter(time);
								or = new Or_Filter(not1, loc);
								w.SortbyFilter(L2.DataBase, or, "FilteredCSV");
							}
						
							else{
								not1 = new Not_Filter(loc);
								or = new Or_Filter(not1, time);
								w.SortbyFilter(L2.DataBase, or, "FilteredCSV");
							}
					}
					
					if(DeviceRadioButton.isSelected() && LocationRadioButton.isSelected()){
						if(DeviceNOTCheckBox.isSelected() && LocationNOTCheckBox.isSelected()){ // not selected both
							not1 = new Not_Filter(id);
							not2 = new Not_Filter(loc);
							or = new Or_Filter(not1, not2);
							w.SortbyFilter(L2.DataBase, or, "FilteredCSV");
							}
							if(DeviceNOTCheckBox.isSelected() && !LocationNOTCheckBox.isSelected()){ 
								not1 = new Not_Filter(id);
								or = new Or_Filter(not1, loc);
								w.SortbyFilter(L2.DataBase, or, "FilteredCSV");
							}
						
							else{
								not1 = new Not_Filter(loc);
								or = new Or_Filter(not1, id);
								w.SortbyFilter(L2.DataBase, or, "FilteredCSV");
							}
						
					}
					
					
					
				}
			
				if(TimeRadioButton.isSelected() && !LocationRadioButton.isSelected() && !DeviceRadioButton.isSelected()){
					if(TimeNOTCheckBox.isSelected()){
						not1 = new Not_Filter(time);
						w.SortbyFilter(L2.DataBase, not1, "FilteredCSV");

					}
				
					else
						w.SortbyFilter(L2.DataBase, time, "FilteredCSV");

				}
		
				
				if(DeviceRadioButton.isSelected() && !TimeRadioButton.isSelected() && !LocationRadioButton.isSelected()){
					if(DeviceNOTCheckBox.isSelected()){
						not1 = new Not_Filter(id);
						w.SortbyFilter(L2.DataBase, not1, "FilteredCSV");
					}
					
					else
						w.SortbyFilter(L2.DataBase, id, "FilteredCSV");
				}
				
				if(LocationRadioButton.isSelected() && !TimeRadioButton.isSelected() && !DeviceRadioButton.isSelected()){
					if(LocationNOTCheckBox.isSelected()){
						not1 = new Not_Filter(loc);
						w.SortbyFilter(L2.DataBase, not1, "FilteredCSV");
					}
					
					else
						w.SortbyFilter(L2.DataBase, loc, "FilteredCSV");

					
					
					
				}
				
				if(LocationRadioButton.isSelected() && !TimeRadioButton.isSelected() && !DeviceRadioButton.isSelected()){
					if(LocationNOTCheckBox.isSelected()){
						not1 = new Not_Filter(loc);
						w.SortbyFilter(L2.DataBase, not1, "FilteredCSV");
					}
					
					else
						w.SortbyFilter(L2.DataBase, loc, "FilteredCSV");

					
					
					
				}
			
			}
			});
		StartFiltrationButton.setBounds(191, 572, 115, 44);
		frame.getContentPane().add(StartFiltrationButton);
		
		//SaveFilterButton
		SaveFilterButton.setBounds(318, 572, 115, 44);
		SaveFilterButton.setBackground(new Color(211, 211, 211));
		frame.getContentPane().add(SaveFilterButton);
		
		//AndCheckBox
		AndCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AndCheckBox.isSelected())
					ORCheckBox.setEnabled(false);
				else
					ORCheckBox.setEnabled(true);
			}
		});
		AndCheckBox.setBounds(191, 541, 59, 25);
		frame.getContentPane().add(AndCheckBox);
		
		
		//ORCheckBox
		ORCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ORCheckBox.isSelected())
					AndCheckBox.setEnabled(false);
				else
					AndCheckBox.setEnabled(true);
			}
		});
		ORCheckBox.setBounds(247, 541, 59, 25);
		frame.getContentPane().add(ORCheckBox);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setForeground(new Color(255, 255, 255));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(170, 13, 9, 624);
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
		separator_2.setBounds(579, 13, 9, 624);
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
		TimeMINtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(!(TimeMINtxt.getText().isEmpty()))
					TimeMinEnterButton.setEnabled(true);
				else
					TimeMinEnterButton.setEnabled(false);

			}
		});
		TimeMINtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TimeMINtxt.setText("");
				
			
			}
		});
		TimeMINtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TimeMINtxt.setEnabled(false);
		TimeMINtxt.setText("hh:mm:ss");
		TimeMINtxt.setBounds(232, 112, 91, 25);
		frame.getContentPane().add(TimeMINtxt);
		TimeMINtxt.setColumns(10);
		
		
	
		TimeMAXtxt = new JTextField();
		TimeMAXtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(TimeMAXtxt.getText().isEmpty()))
					TimeMaxEnterButton.setEnabled(true);
				else
					TimeMaxEnterButton.setEnabled(false);
			}
		});
		TimeMAXtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TimeMAXtxt.setText("");
			}
		});
		TimeMAXtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TimeMAXtxt.setEnabled(false);
		TimeMAXtxt.setText("hh:mm:ss");
		TimeMAXtxt.setBounds(232, 146, 91, 25);
		frame.getContentPane().add(TimeMAXtxt);
		TimeMAXtxt.setColumns(10);
		
		Devicetxt = new JTextField();
		Devicetxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(Devicetxt.getText().isEmpty()))
					DeviceEnterButton.setEnabled(true);
				else
					DeviceEnterButton.setEnabled(false);
			}
		});
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
		Devicetxt.setBounds(232, 260, 91, 25);
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
		LatMAXtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(LatMAXtxt.getText().isEmpty()))
					LatEnterButton.setEnabled(true);
				else
					LatEnterButton.setEnabled(false);
			}
		});
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
		
		LonMINtxt = new JTextField();
		LonMINtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LonMINtxt.setText("");
			}
		});
		LonMINtxt.setText("XX.XXX");
		LonMINtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LonMINtxt.setEnabled(false);
		LonMINtxt.setColumns(10);
		LonMINtxt.setBounds(361, 376, 65, 25);
		frame.getContentPane().add(LonMINtxt);
		
		LonMAXtxt = new JTextField();
		LonMAXtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(LonMAXtxt.getText().isEmpty()))
					LonEnterButton.setEnabled(true);
				else
					LonEnterButton.setEnabled(false);
			}
		});
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
		AltMAXtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(AltMAXtxt.getText().isEmpty()))
					AltEnterButton.setEnabled(true);
				else
					AltEnterButton.setEnabled(false);
			}
		});
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
