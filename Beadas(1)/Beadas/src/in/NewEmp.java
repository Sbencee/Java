package in;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class NewEmp extends JDialog {
	private JTextField tfFizetes;
	private JTextField tfLakohely;
	private JTextField tfSzulido;
	private JTextField tfNev;
	private JTextField tfKod;
//	private EmpTM etm;
	
	private Checker c;
	private JTextField tfTetelSzam;

	public NewEmp(EmpTM etm) {
		setBounds(100, 100, 450, 316);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("K\u00F3d");
		lblNewLabel.setBounds(10, 73, 54, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNv = new JLabel("N\u00E9v");
		lblNv.setBounds(10, 98, 54, 14);
		getContentPane().add(lblNv);
		
		JLabel lblSzlid = new JLabel("Sz\u00FClid\u0151");
		lblSzlid.setBounds(10, 123, 54, 14);
		getContentPane().add(lblSzlid);
		
		JLabel lblNewLabel_3 = new JLabel("Lak\u00F3hely");
		lblNewLabel_3.setBounds(10, 148, 54, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblFizets = new JLabel("Fizet\u00E9s");
		lblFizets.setBounds(10, 173, 54, 14);
		getContentPane().add(lblFizets);
		
		tfFizetes = new JTextField();
		tfFizetes.setBounds(74, 170, 86, 20);
		getContentPane().add(tfFizetes);
		tfFizetes.setColumns(10);
		
		tfLakohely = new JTextField();
		tfLakohely.setColumns(10);
		tfLakohely.setBounds(74, 145, 286, 20);
		getContentPane().add(tfLakohely);
		
		tfSzulido = new JTextField();
		tfSzulido.setColumns(10);
		tfSzulido.setBounds(74, 120, 86, 20);
		getContentPane().add(tfSzulido);
		
		tfNev = new JTextField();
		tfNev.setColumns(10);
		tfNev.setBounds(74, 95, 156, 20);
		getContentPane().add(tfNev);
		
		tfKod = new JTextField();
		tfKod.setColumns(10);
		tfKod.setBounds(74, 70, 42, 20);
		getContentPane().add(tfKod);
		
		JButton btnBeszur = new JButton("Beszúr");		// Beszúr gomb
		btnBeszur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FileManager.Insert(RTF(tfKod), RTF(tfNev), RTF(tfSzulido), RTF(tfLakohely), RTF(tfFizetes));
				c = new Checker();
				if (c.goodInt(tfKod, "Kód") && c.goodSorszam(tfKod, "Kód", etm))
					if (c.filled(tfNev, "Név"))
						if (c.goodDate(tfSzulido, "Születési idő"))
							if (c.filled(tfLakohely, "Lakcim"))
								if (c.goodInt(tfFizetes, "Fizetés")) {
									FileManager.Insert(RTF(tfKod), RTF(tfNev), RTF(tfSzulido), RTF(tfLakohely), RTF(tfFizetes), etm);
									torolget();
									tfTetelSzam.setText(String.valueOf((Integer.parseInt(String.valueOf(etm.getRowCount())))+1));
									//A megjelenített etm (tábla modell) sorok száma + 1, String-é konvertálás, majd Int-re parsolás + 1, majd vissza String-re konvertálás.
								}
			}
		});
		btnBeszur.setBounds(74, 227, 89, 23);
		getContentPane().add(btnBeszur);
		
		JButton btnKilep = new JButton("Kilép");		// Kilép gomb
		btnKilep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnKilep.setBounds(218, 227, 89, 23);
		getContentPane().add(btnKilep);
		
		tfTetelSzam = new JTextField();
		tfTetelSzam.setBackground(new Color(250, 250, 210));
		tfTetelSzam.setForeground(new Color(0, 0, 255));
		tfTetelSzam.setEditable(false);
		tfTetelSzam.setBounds(74, 45, 42, 20);
		getContentPane().add(tfTetelSzam);
		tfTetelSzam.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tételszám");
		lblNewLabel_1.setBounds(10, 48, 67, 14);
		getContentPane().add(lblNewLabel_1);

		tfTetelSzam.setText(String.valueOf((Integer.parseInt(String.valueOf(etm.getRowCount())))+1));
		//A megjelenített etm (tábla modell) sorok száma + 1, String-é konvertálás, majd Int-re parsolás + 1, majd vissza String-re konvertálás.
	}
	
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
	
	public void torolget() {
		tfKod.setText(null);
		tfNev.setText(null);
		tfSzulido.setText(null);
		tfLakohely.setText(null);
		tfFizetes.setText(null);
	}

}
