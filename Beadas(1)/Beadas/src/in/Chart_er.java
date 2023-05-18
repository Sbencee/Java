package in;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.JFreeChart;

public class Chart_er extends JFrame {

	static EmpTM etm;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane = new JPanel();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Chart_er(String title , EmpTM betm) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}
	
	 private static PieDataset createDataset( ) {
	      DefaultPieDataset dataset = new DefaultPieDataset( );
	      dataset.setValue( "IPhone 5s" , new Double( 20 ) );  
	      dataset.setValue( "SamSung Grand" , new Double( 20 ) );   
	      dataset.setValue( "MotoG" , new Double( 40 ) );    
	      dataset.setValue( "Nokia Lumia" , new Double( 10 ) );  
	      return dataset;         
	   }
	   
	   private static JFreeChart createChart( PieDataset dataset ) {
	      JFreeChart chart = ChartFactory.createPieChart(      
	         "Mobile Sales",   // chart title 
	         dataset,          // data    
	         true,             // include legend   
	         true, 
	         false);

	      return chart;
	   }
	   
	   public static JPanel createDemoPanel( ) {
	      JFreeChart chart = createChart(createDataset( ) );  
	      return new ChartPanel( chart ); 
	   }

/*	   public static void main( String[ ] args ) {
		   Chart_er demo = new Chart_er( "Mobile Sales", etm );  
	      demo.setSize( 560 , 367 );    
	      RefineryUtilities.centerFrameOnScreen( demo );    
	      demo.setVisible( true ); 
	   }
*/	   
}
