package in;

import javax.swing.table.DefaultTableModel;

public class EmpTM extends DefaultTableModel {
	//public Checker c;
	//public EmpMod em;

	public EmpTM (Object fildNames [], int rows) {
		super(fildNames , rows);	
	}
	
	public boolean isCellEditable (int row , int col) {
		if	(col == 0){
//			c.SM("Pipa", 0);
			return true;
		}
	return	false;
	}
	
	public Class <?> getColumnClass (int index){
		if (index == 0)
			return (Boolean.class);
		else if (index==1 || index==5)
			return (Integer.class);
	return	(String.class);
	}
}
