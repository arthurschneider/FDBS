package de.aschneider.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.aschneider.DBConnect.Verbindung;
import de.aschneider.model.Event;

public class ShowRulesService {
	
	Verbindung verb = new Verbindung();

	
	public ArrayList<String> showRulesName(){
		
		ArrayList<String> list = new ArrayList<String>();
		 try {
	        	
             Connection con = verb.connect();
             
             Statement Stmt;
             ResultSet RS;
              
            String CQL = "SELECT EveBez FROM Event";
           
            Stmt = con.createStatement();
            RS   = Stmt.executeQuery(CQL);
            
             while (RS.next()) {
            	 
            	if (RS.getString("EveBez") != null) {
            		
            		list.add(RS.getString("EveBez"));
            		
				}
               
             }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ShowRulesService.class.getName()).log(Level.ERROR, null, ex);
        }
		 
		 return list;
	}
	
	
	public Event showRulesEvent(String eveName){

		Event eve = new Event();
		 try {
	        	
             Connection con = verb.connect();
             
             Statement Stmt;
             ResultSet RS;
             System.out.println("-"+eveName+"-EVENTNAME");
             String CQL = "SELECT KEY, EveArt, EveBez, EveNac FROM Event";
           
             Stmt = con.createStatement();
             RS   = Stmt.executeQuery(CQL);
             
             while (RS.next()) {
            	 if (RS.getString("EveBez") != null) {
            		 if (RS.getString("EveBez").equals(eveName) ) {
                		 System.out.println(RS.getString("EveBez")+"KEEYYYYYYYYYYYYYYY");
                		 eve.setKey(RS.getString("KEY"));
                         eve.setEveArt(RS.getString("EveArt"));
                         eve.setEveBez(RS.getString("EveBez"));
                         eve.setEveNac(RS.getString("EveNac"));
    				}
				}
            	 
              
             }
            
          System.out.println("ARt"+eve.getEveArt());
           System.out.println("Bez"+eve.getEveBez());
           System.out.println("Nac"+eve.getEveNac());
           
        } catch (SQLException ex) {
            Logger.getLogger(ShowRulesService.class.getName()).log(Level.ERROR, null, ex);
        }
		 
		 return eve;
	}
	
public String showRulesEventMitglieder(String key){
		String [] ids = new String [100];
		String[] result = null;
		int i = 0;
		
		 try {
	        	
             Connection con = verb.connect();
             
             Statement Stmt;
             ResultSet RS;
              
            String CQL = "SELECT EveMitSenEveID FROM EventMitglieder WHERE EveMitEveID = "+key;
           
            Stmt = con.createStatement();
            RS   = Stmt.executeQuery(CQL);
            
             while (RS.next()) {

               ids[i] = RS.getString("EveMitSenEveID");
               i++;
             }
              result = new String[i];
             for (int j = 0; j < i; j++) {
				result[j] = ids[j];
			}
             
           
        } catch (SQLException ex) {
            Logger.getLogger(ShowRulesService.class.getName()).log(Level.ERROR, null, ex);
        }
		 
		 return showRulesSensorEvent(result);
	}
	

	public String showRulesSensorEvent(String ... ids){
		String result = "";
		int i = 0;
		
		
		String[] sensorid = new String[100];
		String[] param = new String[100];
		String[] operator = new String[100];
		String[] wert = new String[100];
		String[] sensorbez = new String[100];
		
		for(String arg : ids){
	        System.out.println("arg "+arg);
	        
	        try {
	        	
	             Connection con = verb.connect();
	             
	             Statement Stmt;
	             ResultSet RS;
	              
	            String CQL = "SELECT * FROM SensorEvent WHERE KEY = "+arg;
	           
	            Stmt = con.createStatement();
	            RS   = Stmt.executeQuery(CQL);
	            
	           
	            
	             while (RS.next()) {

	               sensorid[i] = RS.getString("SenEveSenID");
	               param[i] = RS.getString("SenEvePhyNam");
	               operator[i] = RS.getString("SenEveVop");
	               wert[i] = RS.getString("SenEveWer");
	               i++;
	             
	             }
	             
	           
	        } catch (SQLException ex) {
	            Logger.getLogger(ShowRulesService.class.getName()).log(Level.ERROR, null, ex);
	        }
	        
	        for (int j = 0; j < i; j++) {
				 try {
			        	
		             Connection con = verb.connect();
		             
		             Statement Stmt;
		             ResultSet RS;
		           
		            String CQL = "SELECT * FROM Sensor WHERE KEY = "+sensorid[j];
		           
		            Stmt = con.createStatement();
		            RS   = Stmt.executeQuery(CQL);
		            
		             while (RS.next()) {

		               sensorbez[j] = RS.getString("SenBez");
		               System.out.println("SENBEZ"+sensorbez[j]);
		               
		             }
		             
		           
		        } catch (SQLException ex) {
		            Logger.getLogger(ShowRulesService.class.getName()).log(Level.ERROR, null, ex);
		        }
			}
	        
	       
	        
	        
	    }
		
		for (int x = 0; x < i; x++) {
			 result = result+""+ sensorbez[x]+" "+param[x]+" "+operator[x]+" "+wert[x]+"\n";
		}
		
		System.out.println(result);
		return result;
		
	}
	
	
	
	public String showRulesEventBenachrichtigung(String key){
	
		String result = null;
		
		
		 try {
	        	
             Connection con = verb.connect();
             
             Statement Stmt;
             ResultSet RS;
              
            String CQL = "SELECT EveBenWeg FROM EventBenachrichtigung WHERE EveBenEveID = "+key;
           
            Stmt = con.createStatement();
            RS   = Stmt.executeQuery(CQL);
            
             while (RS.next()) {

               result = RS.getString("EveBenWeg");
               System.out.println("EveBenWeg"+result);
             }
      
             
           
        } catch (SQLException ex) {
            Logger.getLogger(ShowRulesService.class.getName()).log(Level.ERROR, null, ex);
        }
		 
		 return result;
	}
	
	
	public String showRulesEventAktion(String key){
	System.out.println("111111111");
		String result = "";
		String bez;
		String id;
		String param;
		String wert;
		
		 try {
	        	
             Connection con = verb.connect();
             
             Statement Stmt;
             ResultSet RS;
              
            String CQL = "SELECT * FROM EventAktion WHERE EveAktiEveID = "+key;
            
            Stmt = con.createStatement();
            RS   = Stmt.executeQuery(CQL);
            
             while (RS.next()) {
            	 System.out.println("2222222");
              bez = RS.getString("EveAktiBez");
               //id = RS.getString("EveAktiZieID");
               param = RS.getString("EveAktiZiePar");
               wert = RS.getString("EveAktiZieWer");
             
               result = result+""+bez+" "+param+" "+wert+"\n";
               
             }
      
             
           
        } catch (SQLException ex) {
            Logger.getLogger(ShowRulesService.class.getName()).log(Level.ERROR, null, ex);
        }
		 System.out.println(result);
		 return result;
	}
}
