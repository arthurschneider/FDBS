package de.aschneider.action;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import de.aschneider.DBConnect.Verbindung;
import de.aschneider.service.ChartService;
import de.sensorcloud.cryptography.symmetry.aes.CryptAES;


public class ChartAction {
	
	ChartService chartServ = new ChartService();
	
	private JFreeChart chart;
	
	private String date1;
	private String yourSensor;
	private Map<String, String> sensoren;
	
	private boolean mrsBewegung;
	private boolean mrsFeuchte;
	private boolean mrsTemperatur;
	private boolean mrsVOC;
	private boolean tlFeuchtigkeit;
	private boolean tlTemperatur;
	
	public ChartAction(){
		setSensoren(chartServ.getSensoren()); 
	}
	
	
	public String execute(){
		System.out.println("chartaction");
		return "success";
	}
	

	public String showChartMRS(){
		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		
		if (isMrsBewegung() == true) {
			TimeSeries seriesBew;
			seriesBew = chartServ.getMRSBewegung(getDate1(), getYourSensor());
			dataset.addSeries(seriesBew);
		}
			
		if (isMrsTemperatur() == true) {
			TimeSeries seriesTemperatur;
			seriesTemperatur = chartServ.getMRSTemperatur(getDate1(), getYourSensor());
			dataset.addSeries(seriesTemperatur);
		}
		
		if (isMrsFeuchte() == true) {
			TimeSeries seriesFeuchte;
			seriesFeuchte =  chartServ.getMRSFeuchte(getDate1(), getYourSensor());
			dataset.addSeries(seriesFeuchte);
		}
		
		if (isMrsVOC() == true) {
			TimeSeries seriesVOC;
			seriesVOC = chartServ.getMRSVOC(getDate1(), getYourSensor());
			dataset.addSeries(seriesVOC);
		}

		chart = ChartFactory.createTimeSeriesChart(
	            "Multiraumsensor",
	            "Zeit", 
	            "Werte",
	            dataset,
	            true,
	            true,
	            false
	        );
		chart.setBackgroundPaint(java.awt.Color.WHITE);
		return "success";
	}
	
	
	public String showChartTempLuft(){
		
		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		
		if (isTlFeuchtigkeit() == true) {
			TimeSeries seriesFeuchtigkeit;
			seriesFeuchtigkeit = chartServ.getTLFeuchtigkeit(getDate1(), getYourSensor());
			dataset.addSeries(seriesFeuchtigkeit);
		}
			
		if (isTlTemperatur() == true) {
			TimeSeries seriesTemperatur;
			seriesTemperatur = chartServ.getTLTemperatur(getDate1(), getYourSensor());
			dataset.addSeries(seriesTemperatur);
		}
		


		chart = ChartFactory.createTimeSeriesChart(
	            "Temperatursensor und Luftfeuchtesonsor",
	            "Zeit", 
	            "Werte",
	            dataset,
	            true,
	            true,
	            false
	        );
		chart.setBackgroundPaint(java.awt.Color.WHITE);
		return "success";
	}
	
	
	public String showChartShutCont(){
		
		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		
		
			TimeSeries seriesTuer;
			seriesTuer = chartServ.getSCTuer(getDate1(), getYourSensor());
			dataset.addSeries(seriesTuer);
		


			chart = ChartFactory.createScatterPlot(
	            "Shutter Contact",
	            "Zeit", 
	            "Werte",
	            dataset,
	            PlotOrientation.VERTICAL,
	            true,
	            true,
	            false
	        );
		chart.setBackgroundPaint(java.awt.Color.WHITE);

		 final XYPlot plot = chart.getXYPlot();
		 final DateAxis domainAxis = new DateAxis("Zeit");
	        domainAxis.setUpperMargin(0.50);
	        plot.setDomainAxis(domainAxis);
	        final ValueAxis rangeAxis = plot.getRangeAxis();
	        rangeAxis.setAutoRangeMinimumSize(2.0);
		return "success";
	}


	public String showChartSteckdose(){
	
		final TimeSeriesCollection dataset = new TimeSeriesCollection();
	
	
		TimeSeries seriessteckdose;
		seriessteckdose = chartServ.getSteckdose(getDate1(), getYourSensor());
		dataset.addSeries(seriessteckdose);
	


		chart = ChartFactory.createScatterPlot(
            "Schaltsteckdose",
            "Zeit", 
            "Werte",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
		chart.setBackgroundPaint(java.awt.Color.WHITE);
		 final XYPlot plot = chart.getXYPlot();
		 final DateAxis domainAxis = new DateAxis("Zeit");
	        domainAxis.setUpperMargin(0.50);
	        plot.setDomainAxis(domainAxis);
	        final ValueAxis rangeAxis = plot.getRangeAxis();
	        rangeAxis.setAutoRangeMinimumSize(2.0);    
		return "success";
	}

	public String showChartBewegungsmelder(){
	
		final TimeSeriesCollection dataset = new TimeSeriesCollection();
	
	
		TimeSeries series;
		series = chartServ.getBewegungsmelder(getDate1(), getYourSensor());
		dataset.addSeries(series);
	


		chart = ChartFactory.createScatterPlot(
            "Bewegungsmelder",
            "Zeit", 
            "Werte",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
				);
		chart.setBackgroundPaint(java.awt.Color.WHITE);
		
		 final XYPlot plot = chart.getXYPlot();
		 final DateAxis domainAxis = new DateAxis("Zeit");
	        domainAxis.setUpperMargin(0.50);
	        plot.setDomainAxis(domainAxis);
		return "success";
	}
	
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getYourSensor() {
		return yourSensor;
	}
	public void setYourSensor(String yourSensor) {
		this.yourSensor = yourSensor;
	}
	
	public JFreeChart getChart() {
		return chart;
	}
	public Map<String, String> getSensoren() {
		return sensoren;
	}
	public void setSensoren(Map<String, String> sensoren) {
		this.sensoren = sensoren;
	}


	public boolean isMrsBewegung() {
		return mrsBewegung;
	}


	public void setMrsBewegung(boolean mrsBewegung) {
		this.mrsBewegung = mrsBewegung;
	}


	public boolean isMrsFeuchte() {
		return mrsFeuchte;
	}


	public void setMrsFeuchte(boolean mrsFeuchte) {
		this.mrsFeuchte = mrsFeuchte;
	}


	public boolean isMrsTemperatur() {
		return mrsTemperatur;
	}


	public void setMrsTemperatur(boolean mrsTemperatur) {
		this.mrsTemperatur = mrsTemperatur;
	}


	public boolean isMrsVOC() {
		return mrsVOC;
	}


	public void setMrsVOC(boolean mrsVOC) {
		this.mrsVOC = mrsVOC;
	}


	public boolean isTlFeuchtigkeit() {
		return tlFeuchtigkeit;
	}


	public void setTlFeuchtigkeit(boolean tlFeuchtigkeit) {
		this.tlFeuchtigkeit = tlFeuchtigkeit;
	}


	public boolean isTlTemperatur() {
		return tlTemperatur;
	}


	public void setTlTemperatur(boolean tlTemperatur) {
		this.tlTemperatur = tlTemperatur;
	}

}
