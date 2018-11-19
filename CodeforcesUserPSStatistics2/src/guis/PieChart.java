package guis;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import java.util.HashMap;
public class PieChart extends ApplicationFrame {
	private static final long serialVersionUID = 1L;
public HashMap<String,Integer> H;
public static int total=0;
   public PieChart( String title,HashMap<String,Integer>HM,int t) {
      super( title ); 
      H=HM;
      total=t;
      setContentPane(createDemoPanel( ));
   }
   
   private PieDataset createDataset() {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      for(String s:H.keySet())
    	  dataset.setValue(s,H.get(s));
      return dataset;         
   }
   
   private static JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Problems solved: "+total,   // Chart Title 
         dataset,          // Data Set   
         true,             // Legend 
         true, 
         false);

      return chart;
   }
   
   public JPanel createDemoPanel( ) {
      JFreeChart chart = createChart(this.createDataset( ) );  
      return new ChartPanel( chart ); 
   }
}