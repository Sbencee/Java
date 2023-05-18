package in;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.ui.RefineryUtilities;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;


public class program extends JFrame {

	private EmpTM etm;
//	private FileBe fb;
//	private FileBe2 fb2;
	private FileManager fmg;
	private NewEmp ne;
	private FileGzip fgzip;
	private JFreeChart jfc;
	private Chart ch;
	private Chart_bar cher;
	
	private JTextField tfFajl;
	private JTextField tfKiterjesztes;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JComboBox comboBox;
	private JButton btnFajlbaKiir;
	private JPanel contentPane;
	private JCheckBox chckbxGzip;
	private JButton btnDiagram;
	private JRadioButton rdbtnDiagram;
	private JButton btnPieChart;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					program frame = new program();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public program() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 337, 455);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String kiterjesztes[] = {"Válassz", ".txt", ".csv", ".dat"};
		
		JButton btnKilep = new JButton("Kilép");
		btnKilep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnKilep.setBounds(42, 359, 257, 23);
		contentPane.add(btnKilep);
		
		JButton btnFileBe2 = new JButton("Fájlból beolvas");
		btnFileBe2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	fb2 = new FileBe2();
			//	fb2.setVisible(true);
			//	etm = FileManager.CsvReader(tfFajl.getText() + tfKiterjesztes.getText());
			//	mezoTorles();
	
				if (tfKiterjesztes.getText().equals(".csv")) {
					etm = fmg.CsvReader(tfFajl.getText() + tfKiterjesztes.getText());
				}
				else if(tfKiterjesztes.getText().equals(".dat")) {
					etm = fmg.DatRead(tfFajl.getText() + tfKiterjesztes.getText(), etm);
				}
				else if(tfKiterjesztes.getText().equals(".txt")) {
					etm = fmg.TxtReader(tfFajl.getText() + tfKiterjesztes.getText());
				}
				else if(tfFajl.getText().equals("") || tfKiterjesztes.getText().equals("")) {
					fmg.SM("Nem adott meg fájlnevet vagy kiterjesztést !", 2);
				}
				else {
					fmg.SM("A megadott kiterjesztés nem megfelelő!", 2);
				}
					
				mezoTorles();
			}
		});
		btnFileBe2.setBounds(42, 121, 129, 23);
		contentPane.add(btnFileBe2);
		
		JButton btnLista2 = new JButton("Lista");
		btnLista2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (etm != null) {
					EmpList el = new EmpList(program.this ,etm);	//Példányosítás
					el.setVisible(true);
				}else
					fmg.SM("Töltsön be egy fájlt!", 2);
			}
		});
		btnLista2.setBounds(42, 155, 129, 23);
		contentPane.add(btnLista2);
		
		tfFajl = new JTextField();
		tfFajl.setBounds(145, 49, 133, 20);
		contentPane.add(tfFajl);
		tfFajl.setColumns(10);
		
		tfKiterjesztes = new JTextField();
		tfKiterjesztes.setBounds(145, 80, 46, 20);
		contentPane.add(tfKiterjesztes);
		tfKiterjesztes.setColumns(10);
		
		lblNewLabel = new JLabel("Fájlnév");
		lblNewLabel.setBounds(42, 52, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Kiterjesztés");
		lblNewLabel_1.setBounds(42, 83, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		comboBox = new JComboBox(kiterjesztes);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object cmboitem = comboBox.getSelectedItem();
			   // System.out.println(cmboitem);
				tfKiterjesztes.setText(cmboitem.toString());
			}
		});
		comboBox.setBounds(201, 79, 77, 22);
		contentPane.add(comboBox);
		
		btnFajlbaKiir = new JButton("Fájlba kiírás");			//Fájlba kiírás gomb
		btnFajlbaKiir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//fmg.CsvWrite("csvkiirTeszt.csv");
				if (tfKiterjesztes.getText().equals(".csv")) {
					if (chckbxGzip.isSelected()) {
						fmg.CsvWrite(tfFajl.getText() + tfKiterjesztes.getText(), etm);
						fgzip.compressGzipFile(tfFajl.getText() + tfKiterjesztes.getText(),tfFajl.getText() + tfKiterjesztes.getText()+".gz");
					}
					else
						fmg.CsvWrite(tfFajl.getText() + tfKiterjesztes.getText(), etm);
									}
				else if(tfKiterjesztes.getText().equals(".dat")) {
					try {
						if (chckbxGzip.isSelected()) {
							fmg.DatWrite(tfFajl.getText() + tfKiterjesztes.getText(), etm);
							fgzip.compressGzipFile(tfFajl.getText() + tfKiterjesztes.getText(),tfFajl.getText() + tfKiterjesztes.getText()+".gz");
						}
						else
							fmg.DatWrite(tfFajl.getText() + tfKiterjesztes.getText(), etm);
						
					} catch (IOException e1) {
						fmg.SM(".dat "+e1, 2);
						//e1.printStackTrace();
					}
				}
				else if(tfKiterjesztes.getText().equals(".txt")) {
					if (chckbxGzip.isSelected()) {
						fmg.TxtWrite(tfFajl.getText() + tfKiterjesztes.getText(), etm);
						fgzip.compressGzipFile(tfFajl.getText() + tfKiterjesztes.getText(),tfFajl.getText() + tfKiterjesztes.getText()+".gz");
					}
					else
						fmg.TxtWrite(tfFajl.getText() + tfKiterjesztes.getText(), etm);
					
				}
				else if(tfFajl.getText().equals("") || tfKiterjesztes.getText().equals("")) {
					fmg.SM("Nem adott meg fájlnevet vagy kiterjesztést !", 2);
				}
				else {
					fmg.SM("A megadott kiterjesztés nem megfelelő!", 2);
				}
				mezoTorles();
//				fmg.SM("Jelenleg csak .csv kiterjesztéssel működik a fáljba írás!", 2);
			}
		});
		btnFajlbaKiir.setBounds(42, 291, 129, 23);
		contentPane.add(btnFajlbaKiir);
		
		JButton btnModosit = new JButton("Módosít");
		btnModosit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (etm != null) {
					EmpMod2 em2 = new EmpMod2(program.this ,etm);	//Példányosítás
					em2.setVisible(true);
				}else
					fmg.SM("Töltsön be egy fájlt!", 2);
			}
		});
		btnModosit.setBounds(42, 189, 129, 23);
		contentPane.add(btnModosit);
		
		JButton btnTorles = new JButton("Törlés");
		btnTorles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (etm != null) {
					EmpDel ed = new EmpDel(program.this ,etm);	//Példányosítás
					ed.setVisible(true);
				}else
					fmg.SM("Töltsön be egy fájlt!", 2);
			}
		});
		btnTorles.setBounds(42, 223, 129, 23);
		contentPane.add(btnTorles);
		
		JButton btnUjAdat = new JButton("Új adat");
		btnUjAdat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (etm != null) {
					ne = new NewEmp(etm);
					ne.setVisible(true);
				}else
					fmg.SM("Töltsön be egy fájlt!", 2);
			}
		});
		btnUjAdat.setBounds(42, 257, 129, 23);
		contentPane.add(btnUjAdat);
		
		chckbxGzip = new JCheckBox("Tömörít .gz");
		chckbxGzip.setBounds(202, 291, 97, 23);
		contentPane.add(chckbxGzip);
		
		btnDiagram = new JButton("Diagram");
		btnDiagram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (etm != null) {
					if (rdbtnDiagram.getText().equals("Oszlop")) {
						cher = new Chart_bar("Oszlopdiagram", "Dolgozók fizetése", etm);
						cher.setSize( 700 , 450 );    
						RefineryUtilities.centerFrameOnScreen( ch );    
						cher.setVisible( true );
					}
					else {
						ch = new Chart("Kördiagram", etm);
						ch.setSize( 700 , 450 );    
						RefineryUtilities.centerFrameOnScreen( ch );    
						ch.setVisible( true );
					}
				}else
					fmg.SM("Töltsön be egy fájlt!", 2);
			}
		});
		btnDiagram.setBounds(42, 325, 129, 23);
		contentPane.add(btnDiagram);
		
		rdbtnDiagram = new JRadioButton("Kör");
		rdbtnDiagram.setBounds(201, 325, 98, 23);
		contentPane.add(rdbtnDiagram);
		
		chckbxGzip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxGzip.isSelected())
					chckbxGzip.setForeground(Color.red);
				else
					chckbxGzip.setForeground(Color.black);
			}
		});
		
		rdbtnDiagram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnDiagram.isSelected())
					rdbtnDiagram.setText("Oszlop");
				else
					rdbtnDiagram.setText("Kör");
				
			}
		});

	}
	
	public void mezoTorles() {
		comboBox.setSelectedIndex(0);
		tfFajl.setText(null);
		tfKiterjesztes.setText(null);
		chckbxGzip.setSelected(false);
	}
}
