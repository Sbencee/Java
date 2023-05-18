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
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class EmpList extends JDialog {
	
	private JTable table;
	private EmpTM etm;
	private JTextField tfSorokSzama;
	private JTextField tfFizSum;
	private JTextField tfFizAvg;

	public EmpList(JFrame f, EmpTM betm) {
		super(f, "Dolgoz�k list�ja", true);
		setTitle("Dolgozók listája");
		etm = betm;	
		setBounds(100, 100, 450, 342);
		getContentPane().setLayout(null);
		
		JButton btnBezar = new JButton("Bez\u00E1r");		//Bez�r gomb
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezar.setBounds(173, 269, 89, 23);
		getContentPane().add(btnBezar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 187);
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
		
		tfSorokSzama = new JTextField();
		tfSorokSzama.setEditable(false);
		tfSorokSzama.setBounds(10, 223, 86, 20);
		getContentPane().add(tfSorokSzama);
		tfSorokSzama.setColumns(10);
				
		JLabel lblNewLabel = new JLabel("Tételek száma");
		lblNewLabel.setBounds(10, 209, 86, 14);
		getContentPane().add(lblNewLabel);
		
		tfFizSum = new JTextField();
		tfFizSum.setEditable(false);
		tfFizSum.setBounds(106, 223, 86, 20);
		getContentPane().add(tfFizSum);
		tfFizSum.setColumns(10);
		
		tfFizAvg = new JTextField();
		tfFizAvg.setEditable(false);
		tfFizAvg.setBounds(202, 223, 86, 20);
		getContentPane().add(tfFizAvg);
		tfFizAvg.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Fizetések SUM");
		lblNewLabel_1.setBounds(106, 209, 86, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fizetések AVG");
		lblNewLabel_2.setBounds(202, 209, 86, 14);
		getContentPane().add(lblNewLabel_2);
		
		tfSorokSzama.setText(String.valueOf(etm.getRowCount()));	//A megjelenített etm (tábla modell) sorok száma
		tfFizSum.setText(String.valueOf(osszFiz(etm)));						//A dolgozók összfizetése
		tfFizAvg.setText(String.valueOf(atlagFiz(etm)));	//Az átlagfizetés
		TableRowSorter<EmpTM> trs = (TableRowSorter<EmpTM>)table.getRowSorter();
		trs.setSortable(0, false);
		
	}
	
	static int osszFiz(EmpTM etm) {
		int fizSum = 0;
		for (int i = 0; i < etm.getRowCount() ; i++) {
			fizSum += (Integer.parseInt(String.valueOf(etm.getValueAt(i, 5))));	//Az etm táblamodellből a sor-oszlop alaján vett objektum String-é konvertálás majd Int-re parsolás.
//			System.out.println(Integer.parseInt(String.valueOf(etm.getValueAt(i, 5))));
		}
		return fizSum;
	}
	
	static int atlagFiz(EmpTM etm) {
		return osszFiz(etm) / etm.getRowCount();
	}
}
