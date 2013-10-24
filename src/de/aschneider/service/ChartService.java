package de.aschneider.service;

import java.awt.List;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;

import de.aschneider.DBConnect.Verbindung;
import de.aschneider.helper.Messwert;
import de.sensorcloud.cryptography.symmetry.aes.CryptAES;

public class ChartService {

	Verbindung verb = new Verbindung();
	String [] zeit;
	
	public Map<String, String> getSensoren(){
		Map<String, String> sensoren = new HashMap();
		
		try {
        	
            Connection con = verb.connect();
            
            Statement Stmt;
            ResultSet RS;
             
           String CQL = "SELECT SenBez, SenID FROM Sensor";
          
           Stmt = con.createStatement();
           RS   = Stmt.executeQuery(CQL);
           
            while (RS.next()) {
           	 
           
           		sensoren.put(RS.getString("SenID"), RS.getString("SenBez"));
           	
           		
		
            }
           
          
       } catch (SQLException ex) {
           Logger.getLogger(ShowRulesService.class.getName()).log(Level.ERROR, null, ex);
       }
		
		
		
		return sensoren;
	}
	
	public long getBeginnTime(String datum){

		System.out.println("Datum "+datum+"-");
		String time = datum.substring(0, 10);
		zeit = time.split("-", 3);
		long beginn = 0;
		
		DateFormat dfm = new SimpleDateFormat("yyyyMMdd");
			try {
				beginn = dfm.parse(""+zeit[0]+""+zeit[1]+""+zeit[2]).getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return beginn;
	}
	
	public long getEndeTime(long beginn){
		long ende = beginn+86399*1000L;
		return ende;
	}
	
	
	public TimeSeries getMRSBewegung(String datum, String senID){
		final TimeSeries series = new TimeSeries("Bewegung über Zeit", Minute.class);
		long beginn = getBeginnTime(datum);
		long ende = getEndeTime(beginn);
		CryptAES cryptAes = new CryptAES();
		ArrayList<Messwert> werte = new ArrayList<Messwert>();
		
		try {
        	
			Connection con = verb.connect();
            
            Statement Stmt;
            ResultSet RS;
            //System.out.println("sensor"+getYourSensor()+ "anfang "+beginn+" ende "+ ende);
            String CQLA = "SELECT * FROM Messwert WHERE MesWerSenID = '"+senID+"' AND MesWerTimSta >= "+beginn+" AND MesWerTimSta <= "+ende;
           
           	Stmt = con.createStatement();
           	RS   = Stmt.executeQuery(CQLA);
           	
           	
            while (RS.next()) {
   
            	String buff = CryptAES.decryptAesBase64(RS.getString("MesWerTup"), "FdbsIstSoToll123");
            	int wert = Integer.parseInt(buff.substring(0, 1));
            	String datumMilis = RS.getString("MesWerTimSta");
            	
            	
            	werte.add(new Messwert (datumMilis, wert));
            }
           
	} catch (SQLException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | IOException ex) {
    }
		
		Collections.sort(werte);
		for(Messwert m : werte){
			Date mydate = new Date(Long.parseLong(m.getDatumMilis()));
			series.addOrUpdate(new Minute(mydate.getMinutes(),mydate.getHours(), Integer.parseInt(zeit[2]),Integer.parseInt(zeit[1]),Integer.parseInt(zeit[0])), m.getWert());
        	
		}
		
		return series;
	}
	public TimeSeries getMRSTemperatur(String datum, String senID){
		final TimeSeries series = new TimeSeries("Temperatur über Zeit", Minute.class);
		long beginn = getBeginnTime(datum);
		long ende = getEndeTime(beginn);
		ArrayList<Messwert> werte = new ArrayList<Messwert>();
		
			try {
        	
			Connection con = verb.connect();
            
            Statement Stmt;
            ResultSet RS;
            //System.out.println("sensor"+getYourSensor()+ "anfang "+beginn+" ende "+ ende);
            String CQLA = "SELECT * FROM Messwert WHERE MesWerSenID = '"+senID+"' AND MesWerTimSta >= "+beginn+" AND MesWerTimSta <= "+ende;
           
           	Stmt = con.createStatement();
           	RS   = Stmt.executeQuery(CQLA);
           	
           	
            while (RS.next()) {
   
            	String buff = CryptAES.decryptAesBase64(RS.getString("MesWerTup"), "FdbsIstSoToll123");
            	int wert = Integer.parseInt(buff.substring(2, 4));
            	String datumMilis = RS.getString("MesWerTimSta");
            	
            	
            	werte.add(new Messwert (datumMilis, wert));
            }
           
	} catch (SQLException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | IOException ex) {
    }
		
		Collections.sort(werte);
		for(Messwert m : werte){
			Date mydate = new Date(Long.parseLong(m.getDatumMilis()));
			series.addOrUpdate(new Minute(mydate.getMinutes(),mydate.getHours(), Integer.parseInt(zeit[2]),Integer.parseInt(zeit[1]),Integer.parseInt(zeit[0])), m.getWert());
        	
		}
		
		return series;
	}
	public TimeSeries getMRSFeuchte(String datum, String senID){
		final TimeSeries series = new TimeSeries("Feuchtigkeit über Zeit", Minute.class);
		long beginn = getBeginnTime(datum);
		long ende = getEndeTime(beginn);
		ArrayList<Messwert> werte = new ArrayList<Messwert>();
		
		try {
        	
			Connection con = verb.connect();
            
            Statement Stmt;
            ResultSet RS;
            //System.out.println("sensor"+getYourSensor()+ "anfang "+beginn+" ende "+ ende);
            String CQLA = "SELECT * FROM Messwert WHERE MesWerSenID = '"+senID+"' AND MesWerTimSta >= "+beginn+" AND MesWerTimSta <= "+ende;
           
           	Stmt = con.createStatement();
           	RS   = Stmt.executeQuery(CQLA);
           	
           	
            while (RS.next()) {
   
            	String buff = CryptAES.decryptAesBase64(RS.getString("MesWerTup"), "FdbsIstSoToll123");
            	int wert = Integer.parseInt(buff.substring(5, 7));
            	String datumMilis = RS.getString("MesWerTimSta");
            	
            	
            	werte.add(new Messwert (datumMilis, wert));
            }
           
	} catch (SQLException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | IOException ex) {
    }
		
		Collections.sort(werte);
		for(Messwert m : werte){
			Date mydate = new Date(Long.parseLong(m.getDatumMilis()));
			series.addOrUpdate(new Minute(mydate.getMinutes(),mydate.getHours(), Integer.parseInt(zeit[2]),Integer.parseInt(zeit[1]),Integer.parseInt(zeit[0])), m.getWert());
        	
		}
		
		return series;
	}
	public TimeSeries getMRSVOC(String datum, String senID){
		System.out.println(senID);
		final TimeSeries series = new TimeSeries("VOC über Zeit", Minute.class);
		long beginn = getBeginnTime(datum);
		long ende = getEndeTime(beginn);
		ArrayList<Messwert> werte = new ArrayList<Messwert>();
		
		try {
        	
			Connection con = verb.connect();
            
            Statement Stmt;
            ResultSet RS;
            //System.out.println("sensor"+getYourSensor()+ "anfang "+beginn+" ende "+ ende);
            String CQLA = "SELECT * FROM Messwert WHERE MesWerSenID = '"+senID+"' AND MesWerTimSta >= "+beginn+" AND MesWerTimSta <= "+ende;
           
           	Stmt = con.createStatement();
           	RS   = Stmt.executeQuery(CQLA);
           	
           	
            while (RS.next()) {
   
            	String buff = CryptAES.decryptAesBase64(RS.getString("MesWerTup"), "FdbsIstSoToll123");
            	int wert = Integer.parseInt(buff.substring(8, 10));
            	String datumMilis = RS.getString("MesWerTimSta");
            	
            	
            	werte.add(new Messwert (datumMilis, wert));
            }
           
	} catch (SQLException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | IOException ex) {
    }
		
		Collections.sort(werte);
		for(Messwert m : werte){
			Date mydate = new Date(Long.parseLong(m.getDatumMilis()));
			series.addOrUpdate(new Minute(mydate.getMinutes(),mydate.getHours(), Integer.parseInt(zeit[2]),Integer.parseInt(zeit[1]),Integer.parseInt(zeit[0])), m.getWert());
        	
		}
		
		return series;
	}
	
	
	public TimeSeries getTLFeuchtigkeit(String datum, String senID){
		final TimeSeries series = new TimeSeries("Feuchtigkeit über Zeit", Minute.class);
		long beginn = getBeginnTime(datum);
		long ende = getEndeTime(beginn);
		
		ArrayList<Messwert> werte = new ArrayList<Messwert>();
		
		try {
        	
			Connection con = verb.connect();
            
            Statement Stmt;
            ResultSet RS;
            //System.out.println("sensor"+getYourSensor()+ "anfang "+beginn+" ende "+ ende);
            String CQLA = "SELECT * FROM Messwert WHERE MesWerSenID = '"+senID+"' AND MesWerTimSta >= "+beginn+" AND MesWerTimSta <= "+ende;
           
           	Stmt = con.createStatement();
           	RS   = Stmt.executeQuery(CQLA);
           	
           	
            while (RS.next()) {
   
            	String buff = CryptAES.decryptAesBase64(RS.getString("MesWerTup"), "FdbsIstSoToll123");
            	int wert = Integer.parseInt(buff.substring(3, 5));
            	String datumMilis = RS.getString("MesWerTimSta");
            	
            	
            	werte.add(new Messwert (datumMilis, wert));
            }
           
	} catch (SQLException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | IOException ex) {
    }
		
		Collections.sort(werte);
		for(Messwert m : werte){
			Date mydate = new Date(Long.parseLong(m.getDatumMilis()));
			series.addOrUpdate(new Minute(mydate.getMinutes(),mydate.getHours(), Integer.parseInt(zeit[2]),Integer.parseInt(zeit[1]),Integer.parseInt(zeit[0])), m.getWert());
        	
		}
		
		return series;
	}
	
	
	public TimeSeries getTLTemperatur(String datum, String senID){
		final TimeSeries series = new TimeSeries("Temperatur über Zeit", Minute.class);
		long beginn = getBeginnTime(datum);
		long ende = getEndeTime(beginn);
		
		ArrayList<Messwert> werte = new ArrayList<Messwert>();
		
		try {
        	
			Connection con = verb.connect();
            
            Statement Stmt;
            ResultSet RS;
           
            String CQLA = "SELECT * FROM Messwert WHERE MesWerSenID = '"+senID+"' AND MesWerTimSta >= "+beginn+" AND MesWerTimSta <= "+ende;
           
           	Stmt = con.createStatement();
           	RS   = Stmt.executeQuery(CQLA);
           	
           	
            while (RS.next()) {
   
            	String buff = CryptAES.decryptAesBase64(RS.getString("MesWerTup"), "FdbsIstSoToll123");
            	int wert = Integer.parseInt(buff.substring(0, 2));
            	String datumMilis = RS.getString("MesWerTimSta");
            	
            	
            	werte.add(new Messwert (datumMilis, wert));
            }
           
		} catch (SQLException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | IOException ex) {
		}
		
		Collections.sort(werte);
		for(Messwert m : werte){
			Date mydate = new Date(Long.parseLong(m.getDatumMilis()));
			series.addOrUpdate(new Minute(mydate.getMinutes(),mydate.getHours(), Integer.parseInt(zeit[2]),Integer.parseInt(zeit[1]),Integer.parseInt(zeit[0])), m.getWert());
        	
		}
		
		return series;
	}
	
	public TimeSeries getSCTuer(String datum, String senID){
		final TimeSeries series = new TimeSeries("Tuerzustand über Zeit", Minute.class);
		long beginn = getBeginnTime(datum);
		long ende = getEndeTime(beginn);
		ArrayList<Messwert> werte = new ArrayList<Messwert>();
		
		try {
        	
			Connection con = verb.connect();
            
            Statement Stmt;
            ResultSet RS;
            //System.out.println("sensor"+getYourSensor()+ "anfang "+beginn+" ende "+ ende);
            String CQLA = "SELECT * FROM Messwert WHERE MesWerSenID = '"+senID+"' AND MesWerTimSta >= "+beginn+" AND MesWerTimSta <= "+ende;
           
           	Stmt = con.createStatement();
           	RS   = Stmt.executeQuery(CQLA);
  
           	
            while (RS.next()) {
            	
            	String werttup = CryptAES.decryptAesBase64(RS.getString("MesWerTup"), "FdbsIstSoToll123");
            	String datumMilis = RS.getString("MesWerTimSta");
            	int wert = 0;
            	if (werttup.equals("00")) {
            		wert = 0;
            	}
            	if (werttup.equals("C8")) {
            		wert = 1;
            	}
            	werte.add(new Messwert (datumMilis, wert));
            	
            }
           
		} catch (SQLException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | IOException ex) {
		}
		
		Collections.sort(werte);
		for(Messwert m : werte){
			Date mydate = new Date(Long.parseLong(m.getDatumMilis()));
			series.addOrUpdate(new Minute(mydate.getMinutes(),mydate.getHours(), Integer.parseInt(zeit[2]),Integer.parseInt(zeit[1]),Integer.parseInt(zeit[0])), m.getWert());
        	
		}
		
		return series;
	}
	
	
	public TimeSeries getSteckdose(String datum, String senID){
		final TimeSeries series = new TimeSeries("Steckdose Zustand über Zeit", Minute.class);
		long beginn = getBeginnTime(datum);
		long ende = getEndeTime(beginn);
		ArrayList<Messwert> werte = new ArrayList<Messwert>();
		
		try {
        	
			Connection con = verb.connect();
            
            Statement Stmt;
            ResultSet RS;
            //System.out.println("sensor"+getYourSensor()+ "anfang "+beginn+" ende "+ ende);
            String CQLA = "SELECT * FROM Messwert WHERE MesWerSenID = '"+senID+"' AND MesWerTimSta >= "+beginn+" AND MesWerTimSta <= "+ende;
           
           	Stmt = con.createStatement();
           	RS   = Stmt.executeQuery(CQLA);
  
           	
            while (RS.next()) {
            	
            	String werttup = CryptAES.decryptAesBase64(RS.getString("MesWerTup"), "FdbsIstSoToll123");
            	
            	String datumMilis = RS.getString("MesWerTimSta");
            	int wert = 0;
            	
            	if (werttup.equals("06013C00")) {
            		wert = 0;
            	}
            	if (werttup.equals("0101C800")) {
            		wert = 1;
            	}
            	werte.add(new Messwert (datumMilis, wert));
            	
            }
           
		} catch (SQLException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | IOException ex) {
		}
		
		Collections.sort(werte);
		for(Messwert m : werte){
			Date mydate = new Date(Long.parseLong(m.getDatumMilis()));
			series.addOrUpdate(new Minute(mydate.getMinutes(),mydate.getHours(), Integer.parseInt(zeit[2]),Integer.parseInt(zeit[1]),Integer.parseInt(zeit[0])), m.getWert());
        	
		}
		
		return series;
	}
	
	
	public TimeSeries getBewegungsmelder(String datum, String senID){
		final TimeSeries series = new TimeSeries("Bewegung über Zeit", Minute.class);
		long beginn = getBeginnTime(datum);
		long ende = getEndeTime(beginn);
		ArrayList<Messwert> werte = new ArrayList<Messwert>();
		
	     
		
		try {
        	
			Connection con = verb.connect();
            
            Statement Stmt;
            ResultSet RS;
            //System.out.println("sensor"+getYourSensor()+ "anfang "+beginn+" ende "+ ende);
            String CQLA = "SELECT * FROM Messwert WHERE MesWerSenID = '"+senID+"' AND MesWerTimSta >= "+beginn+" AND MesWerTimSta <= "+ende;
           
           	Stmt = con.createStatement();
           	RS   = Stmt.executeQuery(CQLA);
  
           	
            while (RS.next()) {
            	
            	String werttup = CryptAES.decryptAesBase64(RS.getString("MesWerTup"), "FdbsIstSoToll123");
            	
            	String datumMilis = RS.getString("MesWerTimSta");
            	
            	int wert = 0;

            	if (werttup.equals("41")) {
            		wert = 1;
            	}else{
					wert = 0;
            	}
            	werte.add(new Messwert (datumMilis, wert));
            	
            }
           
		} catch (SQLException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | IOException ex) {
		}
		
		Collections.sort(werte);
		for(Messwert m : werte){
			Date mydate = new Date(Long.parseLong(m.getDatumMilis()));
			System.out.println((Long.parseLong(m.getDatumMilis())));
			System.out.println(mydate);
			//series.addOrUpdate(new Minute(mydate.getMinutes(),mydate.getHours(), Integer.parseInt(zeit[2]),Integer.parseInt(zeit[1]),Integer.parseInt(zeit[0])), m.getWert());
		
			series.addOrUpdate(new Minute(mydate.getMinutes(), new Hour(mydate.getHours(), new Day(Integer.parseInt(zeit[2]), Integer.parseInt(zeit[1]), Integer.parseInt(zeit[0])))), m.getWert());
		}
		
		return series;
	}
}
