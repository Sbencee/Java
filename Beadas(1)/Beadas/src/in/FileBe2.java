package in;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FileBe2 extends JFrame {

	private JPanel contentPane;
	private JTextField tfFajl;
	private JTextField tfKiterjesztes;
	private EmpTM etm;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileBe2 frame = new FileBe2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FileBe2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fájlnév");
		lblNewLabel.setBounds(56, 64, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Kiterjesztés");
		lblNewLabel_1.setBounds(56, 116, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		tfFajl = new JTextField();
		tfFajl.setBounds(155, 61, 86, 20);
		contentPane.add(tfFajl);
		tfFajl.setColumns(10);
		
		tfKiterjesztes = new JTextField();
		tfKiterjesztes.setBounds(155, 113, 86, 20);
		contentPane.add(tfKiterjesztes);
		tfKiterjesztes.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etm = FileManager.CsvReader(tfFajl.getText() + tfKiterjesztes.getText());
				EmpList el = new EmpList(FileBe2.this ,etm);	//Példányosítás
				el.setVisible(true);

			}
		});
		btnOk.setBounds(59, 199, 89, 23);
		contentPane.add(btnOk);
		
		JButton btnKilep = new JButton("Kilép");
		btnKilep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnKilep.setBounds(266, 199, 89, 23);
		contentPane.add(btnKilep);
	}

}
