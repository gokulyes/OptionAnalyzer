package com.gokul.optionanalyzer.util;

import java.util.List;

import javax.swing.ImageIcon;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.gokul.optionanalyzer.OptionAnalyzer;

public class Util {
	
    /** Returns an ImageIcon, or null if the path was invalid. */
    public static ImageIcon createImageIcon(String path) {
        
    	java.net.URL imgURL = OptionAnalyzer.class.getResource(path);
    	
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    public static ImageIcon createImageIcon(Class parent, String path) {
        
    	java.net.URL imgURL = parent.getResource(path);
    	
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }    
    
	public static ChartPanel getLineChart(List<Integer> list) {
		JFreeChart lineChart = ChartFactory.createXYLineChart(
		         "PayOff",
		         "Spot price","P/L",
		         createDataset(list) ,
		         PlotOrientation.VERTICAL,
		         true,true,false);	
		
		ChartPanel chartPanel = new ChartPanel( lineChart );
		chartPanel.setPreferredSize( new java.awt.Dimension( 880, 600 ) );
		chartPanel.setVisible(true);
		
		return chartPanel;
		
	}    
	
	 public static XYDataset createDataset(List<Integer> list) {

	        var series = new XYSeries("Net");
		 	int nUnderlyingPrice = 8000;
		 
			for (Integer element : list) {
				series.add(nUnderlyingPrice, element.doubleValue());
				nUnderlyingPrice += 100;
			}	        

	        var dataset = new XYSeriesCollection();
	        dataset.addSeries(series);

	        return dataset;	 
		 
	 }	

}
