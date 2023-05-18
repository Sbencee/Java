package in;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FileBe extends JDialog {
	private JTextField tfFajl;
	private JTextField tfKiterjesztes;
	private EmpTM etm;

	/**

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileBe dialog = new FileBe();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	 * Create the dialog.
	 */
	public FileBe() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fájlnév");
		lblNewLabel.setBounds(37, 71, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Kiterjesztés");
		lblNewLabel_1.setBounds(37, 124, 67, 14);
		getContentPane().add(lblNewLabel_1);
		
		tfFajl = new JTextField();
		tfFajl.setBounds(114, 68, 86, 20);
		getContentPane().add(tfFajl);
		tfFajl.setColumns(10);
		
		tfKiterjesztes = new JTextField();
		tfKiterjesztes.setBounds(114, 121, 86, 20);
		getContentPane().add(tfKiterjesztes);
		tfKiterjesztes.setColumns(10);
		
		JButton btnBeolvas = new JButton("Beolvas");
		btnBeolvas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etm = FileManager.CsvReader(tfFajl.getText() + tfKiterjesztes.getText());
			//	EmpList el = new EmpList(FileBe,etm);	//Példányosítás
			//	el.setVisible(true);

			}
		});
		btnBeolvas.setBounds(47, 204, 89, 23);
		getContentPane().add(btnBeolvas);
		
		JButton btnKilep = new JButton("Kilép");
		btnKilep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnKilep.setBounds(245, 204, 89, 23);
		getContentPane().add(btnKilep);

	}
}
