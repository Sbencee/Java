package in;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class EmpDel extends JDialog {
	
	private JTable table;
	private EmpTM etm;
	private Checker c = new Checker();
	private EmpList empl;
	private static JTextField tfTetelSzam;
	private static JTextField tfFizSum;
	private static JTextField tfFizAvg;
//	private dbMethods dbm = new dbMethods();

	public EmpDel(JFrame f, EmpTM betm) {
		super(f, "Dolgoz�k t�rl�se", true);
		etm = betm;	
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnBezar = new JButton("Bez\u00E1r");		//Bez�r gomb
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezar.setBounds(251, 227, 123, 23);
		getContentPane().add(btnBezar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 157);
		getContentPane().add(scrollPane);
		
		table = new JTable(etm);
		scrollPane.setViewportView(table);
		
		TableColumn tc = null;
		for (int i = 0; i < 6; i++) {
			tc = table.getColumnModel().getColumn(i);
			if (i ==0 || i ==1) tc.setPreferredWidth(30);
				else if (i ==4) tc.setPreferredWidth(150);
				else {tc.setPreferredWidth(100);}
		}
		
		table.setAutoCreateRowSorter(true);
		
		JButton btnRowDelete = new JButton("Adatsor t\u00F6rl\u00E9se");	//Adatsor t�rl�se
		btnRowDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0, jel = 0;
				for (int x = 0; x < etm.getRowCount(); x++) {
					if ((boolean)etm.getValueAt(x, 0)) 
						{db++; jel = x;}
				}
				if (db == 0)
					c.SM("Nincs kijel�lve t�rlend� rekord!", 0);
				if (db > 1)
					c.SM("T�bb rekord van kijel�lve! \nEgyszerre csak egy rekord t�r�lhet�.", 0);
				if (db == 1) {
					etm.removeRow(jel);
					FileManager.Insert(etm);
//					dispose();
					statisztika(etm, empl);
					c.SM("A rekord t�r�lve!", 1);
				}				
			}
		}); 
		btnRowDelete.setBounds(67, 227, 123, 23);
		getContentPane().add(btnRowDelete);
		
		tfTetelSzam = new JTextField();
		tfTetelSzam.setEditable(false);
		tfTetelSzam.setBounds(10, 191, 86, 20);
		getContentPane().add(tfTetelSzam);
		tfTetelSzam.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tételek száma");
		lblNewLabel.setBounds(10, 179, 86, 14);
		getContentPane().add(lblNewLabel);
		
		tfFizSum = new JTextField();
		tfFizSum.setEditable(false);
		tfFizSum.setBounds(106, 191, 86, 20);
		getContentPane().add(tfFizSum);
		tfFizSum.setColumns(10);
		
		tfFizAvg = new JTextField();
		tfFizAvg.setEditable(false);
		tfFizAvg.setBounds(202, 191, 86, 20);
		getContentPane().add(tfFizAvg);
		tfFizAvg.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Fizetés SUM");
		lblNewLabel_1.setBounds(106, 179, 84, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fizetés AVG");
		lblNewLabel_2.setBounds(202, 179, 86, 14);
		getContentPane().add(lblNewLabel_2);
		TableRowSorter<EmpTM> trs = (TableRowSorter<EmpTM>)table.getRowSorter();
		trs.setSortable(0, false);
		
		statisztika(etm, empl);
//		tfTetelSzam.setText(String.valueOf(etm.getRowCount()));	//A megjelenített etm (tábla modell) sorok száma
//		tfFizSum.setText(String.valueOf(empl.osszFiz(etm)));	//Az összfizetés
//		tfFizAvg.setText(String.valueOf(empl.atlagFiz(etm)));	//Az átlagfizetés
	}
	
	static void statisztika(EmpTM etm,  EmpList empl) {
		tfTetelSzam.setText(String.valueOf(etm.getRowCount()));	//A megjelenített etm (tábla modell) sorok száma
		tfFizSum.setText(String.valueOf(empl.osszFiz(etm)));	//Az összfizetés
		tfFizAvg.setText(String.valueOf(empl.atlagFiz(etm)));	//Az átlagfizetés
	}
}
