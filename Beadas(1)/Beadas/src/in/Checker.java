package in;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;

public class Checker {
	private SimpleDateFormat RDF = new SimpleDateFormat("yyyy.MM.dd");
	
	public boolean filled(JTextField a, String an) {	
		String s = RTF(a);
		if (s.length() > 0)
			return true;
		else{
			SM("Hiba: a(z) "+an+" mező üres!", 0);
			return false;
		}
	}
	
	public boolean filled(JTextField a) {	//Az EmpMod oszt�lyra kifejlesztve, vizsg�lja a m�dos�tand� �rt�kek ki vannak -e t�ltve
		String s = RTF(a);
		if (s.length() > 0)
			return true;
		else{
//			SM("Hiba: az adatmez� �res!", 0);
			return false;
		}
	}
	
	public boolean goodInt(JTextField a, String an) {	
		String s = RTF(a);
		boolean b = filled(a, an);
		if (b) try {
			if (Integer.parseInt(s) >= 0)
				b = true;
			else {
				SM("A(z) "+an+" mezőben csak pozitív szám adható!", 0);
				b = false;
			}
		} catch (Exception e) {
			SM("A(z) "+an+" mezőben hibás a számadat!", 0);
			b = false;
		}
		return b;
	}
	
	public boolean goodSorszam(JTextField a, String an, EmpTM etm) {	
		String s = RTF(a);
		boolean b = filled(a, an);
		for (int i = 0; i < etm.getRowCount(); i++) {
			if (s.equals(etm.getValueAt(i, 1).toString()))
				b = false;
		}
		if (b == false) {
			SM("A(z) "+an+" mezőben egyező a számadat!", 0);
		}
		return b;
	}
	
	public static void SM(String msg, int tipus) {								//Egy �zenetet megjelen�t� met�dus.
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", tipus);
	}
	
	public String RTF(JTextField jtf) {		//Egy sz�vegmez� tartalm�t kiolvas� met�dus.
		return jtf.getText();
	}
	
	public boolean dateFormatChecker(String SDate) {
		try {
			Date date = RDF.parse(SDate);
			if (!RDF.format(date).equals(SDate))
				{return false;}
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	public boolean goodDate(JTextField a, String an) {	
		String s = RTF(a);
		Date currentDate = new Date(); 
		DateFormat datumFormat = new SimpleDateFormat("yyy.MM.dd", new Locale("HU"));
//		System.out.println(datumFormat.format(currentDate) + " " + s);
		boolean b = filled(a, an);
		if (b && dateFormatChecker(s)) {	
			if (datumFormat.format(currentDate).compareTo(s)>=0) { // A születési dátum ellenőrzése a mai dátumhoz képest korábbi legyen
				return true;
			}
			else {
				SM("A(z):"+an+" mezőben a mai dátumtól csak régebbi használható !", 0);
				return false;
			}
		}
		else {
			SM("A(z):"+an+" mezőben hibás a dátumformátum!", 0);
			return false;
		}
	}
	
	public int stringToInt(String s) {
		int x = -1;
		try {
			x = Integer.valueOf(s);
		} catch (NumberFormatException nfe) {
			SM("StringToInt" +nfe.getMessage(), 0);
		}
		return x;
	}
}
