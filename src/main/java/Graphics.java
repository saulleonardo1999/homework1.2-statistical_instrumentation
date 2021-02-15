
import java.io.File;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.JFrame;
import java.io.IOException;
import java.util.List;


public class Graphics {

    public Graphics(){

    }
    public void writeGraphics(List<Frequency> frequencies, List<Error> absoluteErrors) {
        errorGraphic(absoluteErrors);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        for (Frequency frequency: frequencies) {
            dataset.addValue(frequency.frequency,"Volts",String.valueOf(frequency.el));

        }

        JFreeChart barChart = ChartFactory.createBarChart("Histograma", "Volts", "Frecuencia",
                dataset, PlotOrientation.HORIZONTAL, true, true, false);
        try {
            ChartUtils.saveChartAsPNG(new File("/home/cesar/Desktop/histogram.png"), barChart, 800, 800);
        }catch(IOException ex){}
        ChartPanel Panel = new ChartPanel(barChart);
        JFrame Ventana = new JFrame("JFreeChart");
        Ventana.getContentPane().add(Panel);
        Ventana.pack();
        Ventana.setVisible(true);
        Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void errorGraphic (List<Error> absoluteErrors){
        System.out.println("HOLAAA DESDE ERROR");
        final XYSeries serie1 = new XYSeries("Absolute Error");
        final XYSeries serie2 = new XYSeries("Relative Error");
        for (Error error: absoluteErrors){
            serie1.add(Float.parseFloat(error.hour), error.getAbsolute());
            serie2.add(Float.parseFloat(error.hour), error.getRelative());
        }

        final XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(serie1);
        collection.addSeries(serie2);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Absolute v Relative error",
                "Hora",
                "Error",
                collection,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        try {
            ChartUtils.saveChartAsPNG(new File("/home/cesar/Desktop/error.png"), chart, 800, 800);
        }catch(IOException ex){}

        ChartPanel Panel = new ChartPanel(chart);
        JFrame Ventana = new JFrame("JFreeChart");
        Ventana.getContentPane().add(Panel);
        Ventana.pack();
        Ventana.setVisible(true);
        Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
