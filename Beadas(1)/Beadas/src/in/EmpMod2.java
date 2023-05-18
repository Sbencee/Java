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

public class EmpMod2 extends JDialog {
	
	private JTable table;
	private EmpTM etm;
	private Checker c = new Checker();
	private EmpList empl;
	private JTextField tfSorokSzama;
	private JTextField tfFizSum;
	private JTextField tfFizAvg;
	
//	private dbMethods dbm = new dbMethods();

	public EmpMod2(JFrame f, EmpTM betm) {
		super(f, "Dolgoz�k m�dos�t�sa", true);
		etm = betm;	
		setBounds(350, 100, 450, 284);
		getContentPane().setLayout(null);
		
		JButton btnBezar = new JButton("Bez\u00E1r");		//Bez�r gomb
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezar.setBounds(234, 195, 153, 23);
		getContentPane().add(btnBezar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 110);
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
		
//		int rowIndex = 0;
//		rowIndex = table.getSelectedRow();
//		System.out.println(rowIndex);
		
		
		JButton btnRowEdit = new JButton("Adatsor m\u00F3dos\u00EDt\u00E1sa");	//Adatsor m�dos�t�sa
		btnRowEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//etm = FileManager.CsvReader();
				
				int db=0, jel = 0;
				for (int x = 0; x < etm.getRowCount(); x++) {	//végigjárjuk a táblamodellt
					if ((boolean)etm.getValueAt(x, 0)) 			//ha a nulladik oszlopban valaholpipa van, logika igen
						{db++; jel = x;}						//akkor megszámoljuk és eltároljuk
				}
				if (db == 0)
					c.SM("Nincs kijel�lve a m�dos�tand� rekord!", 0);
				if (db > 1)
					c.SM("T�bb rekord van kijel�lve! \nEgyszerre csak egy rekord m�dos�that�.", 0);
				if (db == 1) {
					EmpMod2_modlap em2m = new EmpMod2_modlap(etm);	//P�ld�nyos�t�s
					em2m.setVisible(true);
					dispose();
				}
/*
				int db=0, jel = 0;
				for (int x = 0; x < etm.getRowCount(); x++) {
					if ((boolean)etm.getValueAt(x, 0)) 
						{db++; jel = x;}
				}
				if (db == 0)
					c.SM("Nincs kijel�lve a m�dos�tand� rekord!", 0);
				if (db > 1)
					c.SM("T�bb rekord van kijel�lve! \nEgyszerre csak egy rekord m�dos�that�.", 0);
				if (db == 1) {
					if (modDataPc() > 0){
				//		betolt(jel);
						boolean ok = true;
						if(c.filled(tfkod)) 
							ok = c.goodInt(tfkod, "K�d");
						if(ok && c.filled(tffiz))
							ok = c.goodInt(tffiz, "Fizet�s");
						if(ok) {
							if(c.filled(tfkod))
								etm.setValueAt(c.stringToInt(c.RTF(tfkod)), jel, 1);
							if(c.filled(tfnev))
								etm.setValueAt(c.RTF(tfnev), jel, 2);
							//if(c.filled(tfszid))
							if (c.goodDate(tfszid, "Sz�let�si id�"))
								etm.setValueAt(c.RTF(tfszid), jel, 3);
							if(c.filled(tflak))
								etm.setValueAt(c.RTF(tflak), jel, 4);
							if(c.filled(tffiz))
								etm.setValueAt(c.stringToInt(c.RTF(tffiz)), jel, 5);
							FileManager.Insert(etm);
							c.SM("A rekord m�dos�tva!", 1);
							reset(jel);
						}
					}
					else{
						c.SM("Nincs kit�ltve m�dos�t� adatmez�!", 1);
					}
				}
	*/
			}
	
		}); 
		
		btnRowEdit.setBounds(55, 195, 153, 23);
		getContentPane().add(btnRowEdit);
		
		tfSorokSzama = new JTextField();
		tfSorokSzama.setEditable(false);
		tfSorokSzama.setBounds(10, 151, 86, 20);
		getContentPane().add(tfSorokSzama);
		tfSorokSzama.setColumns(10);
		
		tfFizSum = new JTextField();
		tfFizSum.setEditable(false);
		tfFizSum.setBounds(106, 151, 86, 20);
		getContentPane().add(tfFizSum);
		tfFizSum.setColumns(10);
		
		tfFizAvg = new JTextField();
		tfFizAvg.setEditable(false);
		tfFizAvg.setBounds(202, 151, 86, 20);
		getContentPane().add(tfFizAvg);
		tfFizAvg.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tételek száma");
		lblNewLabel.setBounds(10, 132, 86, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fizetések SUM");
		lblNewLabel_1.setBounds(106, 132, 86, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fizetések AVG");
		lblNewLabel_2.setBounds(202, 132, 86, 14);
		getContentPane().add(lblNewLabel_2);
		
		tfSorokSzama.setText(String.valueOf(etm.getRowCount()));	//A megjelenített etm (tábla modell) sorok száma
		tfFizSum.setText(String.valueOf(empl.osszFiz(etm)));						//A dolgozók összfizetése
		tfFizAvg.setText(String.valueOf(empl.atlagFiz(etm)));	//Az átlagfizetés
		
		TableRowSorter<EmpTM> trs = (TableRowSorter<EmpTM>)table.getRowSorter();
		trs.setSortable(0, false);
		
	}
}
