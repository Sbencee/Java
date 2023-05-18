package in;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFreeChart extends ApplicationFrame {

	static EmpTM etm;
	private static final long serialVersionUID = 1L;

	public JFreeChart( String title , EmpTM betm) {
	      super( title );
	      setResizable(false);
	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      etm = betm;
	      setContentPane(createDemoPanel( ));
	      getContentPane().setLayout(null);
	      
	      JButton btnBezar = new JButton("Bezár");
	      btnBezar.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		dispose();
	      	}
	      });
	      btnBezar.setBounds(506, 332, 89, 23);
	      getContentPane().add(btnBezar);
	   }
	   
	   private static PieDataset createDataset(EmpTM betm) {
	      DefaultPieDataset dataset = new DefaultPieDataset( );
	      int egy = Integer.parseInt(String.valueOf(betm.getValueAt(0, 5)));
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
	      return dataset;         
	   }
	   
	   private static org.jfree.chart.JFreeChart createChart( PieDataset dataset ) {
	      org.jfree.chart.JFreeChart chart = ChartFactory.createPieChart(      
	         "Dolgozók fizetése",   // chart title 
	         dataset,          // data    
	         true,             // include legend   
	         true, 
	         false);

	      return chart;
	   }
	   
	   public static JPanel createDemoPanel( ) {
	      org.jfree.chart.JFreeChart chart = createChart(createDataset( etm) );  
	      return new ChartPanel( chart ); 
	   }
}
