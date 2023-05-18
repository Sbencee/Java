package in;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EmpMod2_modlap extends JDialog {
	private JTextField tfkod;
	private JTextField tfnev;
	private JTextField tfszid;
	private JTextField tflak;
	private JTextField tffiz;
	private JTextField tfnevM;
	private JTextField tfszidM;
	private JTextField tflakM;
	private JTextField tffizM;
	
	private EmpTM etm;
	private Checker c = new Checker();
	private JTextField tfTetelSzam;
	private JLabel lblNewLabel_1;
	
	public EmpMod2_modlap(EmpTM betm) {
		etm = betm;
		setBounds(450, 100, 584, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("K\u00F3d");
		lblNewLabel.setBounds(37, 42, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNv = new JLabel("N\u00E9v");
		lblNv.setBounds(37, 67, 46, 14);
		getContentPane().add(lblNv);
		
		JLabel lblSzletsiId = new JLabel("Sz\u00FClet\u00E9si id\u0151");
		lblSzletsiId.setBounds(37, 92, 73, 14);
		getContentPane().add(lblSzletsiId);
		
		JLabel lblLakhely = new JLabel("Lakhely");
		lblLakhely.setBounds(37, 117, 46, 14);
		getContentPane().add(lblLakhely);
		
		JLabel lblFizets = new JLabel("Fizet\u00E9s");
		lblFizets.setBounds(37, 142, 46, 14);
		getContentPane().add(lblFizets);
		
		tfkod = new JTextField();
		tfkod.setEditable(false);
		tfkod.setBounds(117, 39, 46, 20);
		getContentPane().add(tfkod);
		tfkod.setColumns(10);
		
		tfnev = new JTextField();
		tfnev.setEditable(false);
		tfnev.setColumns(10);
		tfnev.setBounds(117, 64, 138, 20);
		getContentPane().add(tfnev);
		
		tfszid = new JTextField();
		tfszid.setEditable(false);
		tfszid.setColumns(10);
		tfszid.setBounds(117, 89, 86, 20);
		getContentPane().add(tfszid);
		
		tflak = new JTextField();
		tflak.setEditable(false);
		tflak.setColumns(10);
		tflak.setBounds(117, 114, 138, 20);
		getContentPane().add(tflak);
		
		tffiz = new JTextField();
		tffiz.setEditable(false);
		tffiz.setColumns(10);
		tffiz.setBounds(117, 139, 86, 20);
		getContentPane().add(tffiz);
		
		tfnevM = new JTextField();
		tfnevM.setColumns(10);
		tfnevM.setBounds(287, 64, 138, 20);
		getContentPane().add(tfnevM);
		
		tfszidM = new JTextField();
		tfszidM.setColumns(10);
		tfszidM.setBounds(287, 89, 86, 20);
		getContentPane().add(tfszidM);
		
		tflakM = new JTextField();
		tflakM.setColumns(10);
		tflakM.setBounds(287, 114, 138, 20);
		getContentPane().add(tflakM);
		
		tffizM = new JTextField();
		tffizM.setColumns(10);
		tffizM.setBounds(287, 139, 86, 20);
		getContentPane().add(tffizM);
		
		JButton btnEdit = new JButton("M\u00F3dos\u00EDt");	//M�dos�t gomb
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (toltottseg() >= 1) {
					boolean ok = true;
//					System.out.println(toltottseg());
					int jel = 0;
					for (int x = 0; x < etm.getRowCount(); x++) {
						if ((boolean)etm.getValueAt(x, 0))
							{jel = x;}
					}
					if(c.filled(tfnevM))
						etm.setValueAt(c.RTF(tfnevM), jel, 2);
					if (c.filled(tfszidM)) {
						if (c.goodDate(tfszidM, "Sz�let�si id�"))
							etm.setValueAt(c.RTF(tfszidM), jel, 3);
						else {
							ok = false;
							tfszidM.setText("");
						}
					}
					if(c.filled(tflakM))
						etm.setValueAt(c.RTF(tflakM), jel, 4);
					if(c.filled(tffizM))
						etm.setValueAt(c.stringToInt(c.RTF(tffizM)), jel, 5);
					if (ok == true) {
						FileManager.Insert(etm);
						c.SM("A rekord m�dos�tva !", 1);
						reset();
						betolt();
						etm.setValueAt(false, jel, 0);
					}
				}
				else
					c.SM("Nincs m�dos�tand� mez�!", 1);
					
					
			}
		});
		btnEdit.setBounds(117, 202, 89, 23);
		getContentPane().add(btnEdit);
		
		JButton btnClosed = new JButton("Bez\u00E1r");		//Bez�r gomb
		btnClosed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnClosed.setBounds(287, 202, 89, 23);
		getContentPane().add(btnClosed);
		
		tfTetelSzam = new JTextField();
		tfTetelSzam.setForeground(new Color(0, 0, 205));
		tfTetelSzam.setBackground(new Color(253, 245, 230));
		tfTetelSzam.setEditable(false);
		tfTetelSzam.setBounds(117, 11, 46, 20);
		getContentPane().add(tfTetelSzam);
		tfTetelSzam.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Tételszám");
		lblNewLabel_1.setBounds(37, 14, 73, 14);
		getContentPane().add(lblNewLabel_1);

		betolt();
		reset();
	}
	
	public void betolt() {
  		try {
		int db=0, jel = 0;
		for (int i = 0; i < etm.getRowCount(); i++) {
			if ((boolean)etm.getValueAt(i, 0)) 
				{db++; jel = i;}
//			System.out.println(etm.getValueAt(jel, 0));
		}
		tfTetelSzam.setText(String.valueOf(jel+1));
		tfkod.setText(String.valueOf(etm.getValueAt(jel, 1)));
		tfnev.setText(String.valueOf(etm.getValueAt(jel, 2)));
		tfszid.setText(String.valueOf(etm.getValueAt(jel, 3)));
		tflak.setText(String.valueOf(etm.getValueAt(jel, 4)));
		tffiz.setText(String.valueOf(etm.getValueAt(jel, 5)));
		} catch (Exception e) {
			
		}
	}
	
	public void betolt(int jel) {
  		
		tfkod.setText(String.valueOf(etm.getValueAt(jel, 1)));
		tfnev.setText(String.valueOf(etm.getValueAt(jel, 2)));
		tfszid.setText(String.valueOf(etm.getValueAt(jel, 3)));
		tflak.setText(String.valueOf(etm.getValueAt(jel, 4)));
		tffiz.setText(String.valueOf(etm.getValueAt(jel, 5)));
	}
	
	public void reset() {
		//	tfkod.setText("");
			tfnevM.setText("");
			tfszidM.setText("");
			tflakM.setText("");
			tffizM.setText("");
	}
	
	public void reset(int i) {
	//	tfkod.setText("");
		tfnevM.setText("");
		tfszidM.setText("");
		tflakM.setText("");
		tffizM.setText("");
		etm.setValueAt(false, i, 0);
	}
	
	public int toltottseg() {
		int db = 0;
		if(c.filled(tfnevM))
			db += 1;
		if(c.filled(tfszidM))
			db += 1;
		if(c.filled(tflakM))
			db += 1;
		if(c.filled(tffizM))
			db += 1;
		return db; 
	}
}
