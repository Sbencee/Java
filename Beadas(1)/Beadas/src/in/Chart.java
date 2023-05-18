package in;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.JFreeChart;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Chart extends JDialog {
	
	static EmpTM etm;
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		
		try {
			Chart dialog = new Chart("Lista", etm);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * Create the dialog.
	 */
	public Chart(String title , EmpTM betm) {
		etm = betm;
		
		setBounds(100, 100, 450, 327);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 206);
		contentPanel.add(panel);

		setContentPane(createDemoPanel( ));
//		getContentPane().setLayout(null);
		{
			
			{
				JButton cancelButton = new JButton("Bezárás");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
//				cancelButton.setActionCommand("Cancel");
//				buttonPane.add(cancelButton);
			}
		}
	}
	
	 private static PieDataset createDataset(EmpTM betm) {
	      DefaultPieDataset dataset = new DefaultPieDataset( );
//	      int []etmArray = new int [betm.getRowCount()];
	      for (int i = 0; i < betm.getRowCount(); i++) {
//	    	  etmArray[i] = Integer.parseInt(String.valueOf(betm.getValueAt(i, 5)));
	    	  dataset.setValue( String.valueOf(betm.getValueAt(i, 2)) , new Double( Integer.parseInt(String.valueOf(betm.getValueAt(i, 5)))));
		}
/*	      int egy = Integer.parseInt(String.valueOf(betm.getValueAt(0, 5)));
	      int ketto = Integer.parseInt(String.valueOf(betm.getValueAt(1, 5)));
	      int harom = Integer.parseInt(String.valueOf(betm.getValueAt(2, 5)));
	      int negy = Integer.parseInt(String.valueOf(betm.getValueAt(3, 5)));
	      int ot = Integer.parseInt(String.valueOf(betm.getValueAt(4, 5)));
	      int hat = Integer.parseInt(String.valueOf(betm.getValueAt(5, 5)));
	      int het = Integer.parseInt(String.valueOf(betm.getValueAt(6, 5)));
	      dataset.setValue( "ETM 1." , new Double(egy));
	      dataset.setValue( "ETM 2." , new Double( ketto ) );   
	      dataset.setValue( "ETM 3." , new Double( harom ) );    
	      dataset.setValue( "ETM 4." , new Double( negy ) );
	      dataset.setValue( "ETM 5." , new Double( ot ) );
	      dataset.setValue( "ETM 6." , new Double( hat ) );
	      dataset.setValue( "ETM 7." , new Double( het ) );
*/	      
	      return dataset;         
	   }
	   
	   private static JFreeChart createChart( PieDataset dataset ) {
	      JFreeChart chart = ChartFactory.createPieChart(      
	         "Dolgozók fizetése",   // chart title 
	         dataset,          // data    
	         true,             // include legend   
	         true, 
	         false);

	      return chart;
	   }
	   
	   public static JPanel createDemoPanel( ) {
	      JFreeChart chart = createChart(createDataset( etm) );  
	      return new ChartPanel( chart ); 
	   }
}
