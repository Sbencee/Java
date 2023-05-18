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

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class Chart_bar extends JFrame {

	static EmpTM etm;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane = new JPanel();

	public Chart_bar(String applicationTitle , String chartTitle, EmpTM betm) {
		super( applicationTitle ); 
		etm = betm;
	      JFreeChart barChart = ChartFactory.createBarChart(
	         chartTitle,           
	         "Dolgozók",            
	         "Ft",            
	         createDataset(betm),          
	         PlotOrientation.VERTICAL,           
	         true, true, false);
	         
	      ChartPanel chartPanel = new ChartPanel( barChart );        
	      chartPanel.setPreferredSize(new java.awt.Dimension( 770 , 450 ) );        
	      setContentPane( chartPanel ); 
	}
	
	private CategoryDataset createDataset( EmpTM betm ) {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  
//		int []etmArray = new int [betm.getRowCount()];
	      for (int i = 0; i < betm.getRowCount(); i++) {
//	    	  etmArray[i] = Integer.parseInt(String.valueOf(betm.getValueAt(i, 5)));
	    	  dataset.addValue(Integer.parseInt(String.valueOf(betm.getValueAt(i, 5))), "Ft", String.valueOf(betm.getValueAt(i, 2)));
	      }
	      return dataset; 
	   }    

/*	   public static void main( String[ ] args ) {
		   Chart_er chart = new Chart_er("Dolgozói fizetések", 
         "Melyikkel lenne elégedett?", etm);
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
	   }
*/	   
}
