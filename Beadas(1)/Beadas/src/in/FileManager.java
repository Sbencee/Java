package in;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class FileManager {

	private JTable table;
	//private EmpTM etm;

	public static EmpTM CsvReader() {	//A met�dus egy EmpTM t�pus� t�blamodell p�ld�nyt fog visszaadni, ezt kapja majd meg a Lista panel.
		Object emptmn[] = {"Jel", "Kód", "Név", "Szülidő", "Lakóhely", "Fizetés"}; //A visszaadand� t�blamodell p�ld�nyhoz sz�ks�gesek a mez�nevek, ezeket mindig egy Object t�mbben adjuk meg.
		EmpTM etm = new EmpTM(emptmn, 0);
		String x = " - ";
		try {
			BufferedReader in = new BufferedReader(new FileReader("adatok.csv"));
			String s = in.readLine();
			while (s != null) {
				String [] st = s.split(";");
				etm.addRow(new Object[]{false, st[0], st[1], st[2], st[3], st[4]});
//				String st = s.replaceAll(";"," - ");
//				System.out.println();
				s = in.readLine();
			}
			in.close();
		} catch (IOException ioe) {
			//System.out.println("CsvReader: "+ioe.getMessage());
			SM("CsvReader: "+ioe.getMessage(),2);
		}
		return etm;
	}
	
	public static EmpTM CsvReader(String file) {	//A met�dus egy EmpTM t�pus� t�blamodell p�ld�nyt fog visszaadni, ezt kapja majd meg a Lista panel.
		Object emptmn[] = {"Jel", "Kód", "Név", "Szülidő", "Lakóhely", "Fizetés"}; //A visszaadand� t�blamodell p�ld�nyhoz sz�ks�gesek a mez�nevek, ezeket mindig egy Object t�mbben adjuk meg.
		EmpTM etm = new EmpTM(emptmn, 0);
		String x = " - ";
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));	//"adatok.csv"
			String s = in.readLine();
			while (s != null) {
				String [] st = s.split(";");
				etm.addRow(new Object[]{false, st[0], st[1], st[2], st[3], st[4]});
//				String st = s.replaceAll(";"," - ");
//				System.out.println();
				s = in.readLine();
			}
			in.close();
			SM("A(z) "+file+" betöltése megtörtént.", 1);
		} catch (IOException ioe) {
			//System.out.println("CsvReader: "+ioe.getMessage());
			if (file.equals(""))
				SM("Kérem adjon meg fájlnevet! ",2);
			else
				SM("CsvReader: "+ioe.getMessage(),2);
		  etm = null;
		}
		return etm;
	}
	
	public static void CsvWrite(String file, EmpTM etm) {
		try {
//			PrintStream out = new PrintStream(new FileOutputStream(file), true, StandardCharsets.UTF_8.toString()); //Karater kódolás
			PrintStream out = new PrintStream(new FileOutputStream(file), false, StandardCharsets.ISO_8859_1.toString());
//			out.println("Jel;Kód;Név;Szülidõ;Lakóhely;Fizetés");
			int rdb = etm.getRowCount();
			int cdb = etm.getColumnCount();
			for (int i = 0; i < rdb; i++) {
				for (int j = 1; j < cdb - 1; j++) {
					out.print("" + etm.getValueAt(i, j) + ";");
				}
				out.println("" + etm.getValueAt(i, cdb - 1));
			}
			out.close();
			SM("A(z) "+ file +" kiírása megtörtént !", 1);
		} catch (IOException ioe) {
			SM("Adatok kiírása közben hiba történt !\nCsWriter: "+ioe, 1);
		}
	}
	
	public static void TxtWrite(String file, EmpTM etm) {
		try {
			PrintStream out = new PrintStream(new FileOutputStream(file));
			//out.println("Jel;Kód;Név;Szülidõ;Lakóhely;Fizetés");
			int rdb = etm.getRowCount();
			int cdb = etm.getColumnCount();
			for (int i = 0; i < rdb; i++) {
				for (int j = 1; j < cdb - 1; j++) {
					out.print("" + etm.getValueAt(i, j) + ";");
				}
				out.println("" + etm.getValueAt(i, cdb - 1));
			}
			out.close();
			SM("A(z) "+ file +" kiírása megtörtént !", 1);
		} catch (IOException ioe) {
			SM("Az adatok kiírása közben hiba történt !\nTxtWriter: "+ioe, 1);
		}
	}
	
	public static EmpTM TxtReader(String file) {	//A met�dus egy EmpTM t�pus� t�blamodell p�ld�nyt fog visszaadni, ezt kapja majd meg a Lista panel.
		Object emptmn[] = {"Jel", "Kód", "Név", "Szülidő", "Lakóhely", "Fizetés"}; //A visszaadand� t�blamodell p�ld�nyhoz sz�ks�gesek a mez�nevek, ezeket mindig egy Object t�mbben adjuk meg.
		EmpTM etm = new EmpTM(emptmn, 0);
		String x = " - ";
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));	//"adatok.csv"
			String s = in.readLine();
			while (s != null) {
				String [] st = s.split(";");
				etm.addRow(new Object[]{false, st[0], st[1], st[2], st[3], st[4]});
//				String st = s.replaceAll(";"," - ");
//				System.out.println();
				s = in.readLine();
			}
			in.close();
			SM("A(z) "+file+" betöltése megtörtént.", 1);
		} catch (IOException ioe) {
			//System.out.println("CsvReader: "+ioe.getMessage());
			if (file.equals(""))
				SM("Kérem adjon meg fájlnevet! ",2);
			else
				SM("TxtReader: "+ioe.getMessage(),2);
		  etm = null;
		}
		return etm;
	}
	
	public static void DatWrite(String file, EmpTM etm) throws IOException {
        File f = new File(file);
        try (FileOutputStream fos = new FileOutputStream(f);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject((EmpTM)(etm));
            SM("A(z) "+ file +" kiírása megtörtént !", 1);
        }
        catch (IOException xIo) {
            xIo.printStackTrace();
            SM("Az adatok kiírása közben hiba történt !\nTxtWriter: "+xIo, 1);
        }
    }
	
	public static EmpTM DatRead(String file, EmpTM etm) {
        File f = new File(file);
        try (FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
               etm = ((EmpTM) ois.readObject());
               SM("A(z) "+file+" betöltése megtörtént.", 1);
           }
           catch (IOException | ClassNotFoundException x) {
               x.printStackTrace();
               	if (file.equals(""))
   					SM("Kérem adjon meg fájlnevet! ",2);
   				else {
   					SM("TxtReader: "+x.getMessage(),2);
               		etm = null;
   				}
           }
        return etm;
    }
	
	public static void Insert(String kod, String nev, String szid, String lak, String fiz, EmpTM etm) {
			try {
				etm.insertRow(etm.getRowCount(), new Object[]{false, kod, nev, szid, lak, fiz});
				SM("FM.Insert: Az új adat hozzáadása megtörtént!", 1);
			} catch (Exception e) {
				SM("FM.Insert: "+e.getMessage(), 1);
			//	e.printStackTrace();
			}
	}
		
	public static void Insert(String kod, String nev, String szid, String lak, String fiz, EmpTM etm, String file) {
		String x = ";";
		try {
			etm.addRow(new Object[]{false, kod, nev, szid, lak, fiz});
			PrintStream out = new PrintStream(new FileOutputStream(file, true));
			out.println(kod+x+nev+x+szid+x+lak+x+fiz);
			out.close();
			SM("FM.Insert: Adatok kiírása megtörtént!", 1);
		} catch (Exception ioe) {
			SM("FM.Insert: "+ioe.getMessage(), 1);
		}
	}
	
	public static void Insert(EmpTM etm) {
		String x=";";
		try {
			PrintStream out = new PrintStream(new FileOutputStream("adatok.csv"));
//			out.println("K�d;N�v;Sz�let�si_id�;Lakc�m;Fizet�s");
			for (int i = 0; i < etm.getRowCount(); i++) {
				String kod=etm.getValueAt(i, 1).toString();
				String nev=etm.getValueAt(i, 2).toString();
				String szid=etm.getValueAt(i, 3).toString();
				String lak=etm.getValueAt(i, 4).toString();
				String fiz=etm.getValueAt(i, 5).toString();
				out.println(kod+x+nev+x+szid+x+lak+x+fiz);
			}
			out.close();
		} catch (IOException ioe) {
			SM("FM.Insert: "+ioe.getMessage(), 0);
		}
	}
	
	

	
	public static void SM(String msg, int tipus) {
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", tipus);
	}
}
