package de.aschneider.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.aschneider.DBConnect.Verbindung;

public class SetRulesDB {
	
	String eventUUID;
	Verbindung verb = new Verbindung();
	int reihenFolge, prioAkt;
	int j;
	
	
	public SetRulesDB(String eventUUID){
		this.eventUUID = eventUUID;
		j = 0;
		reihenFolge = 0;
		prioAkt = 0;
	}
	
	public void insertAktorEvent(String aktEventUUID, String aktor, String funktion, String wert){
		String aktID = "";
		
		try {
        	
            Connection con = verb.connect();
            
            Statement Stmt;
            ResultSet RS;
             
            String CQL = "SELECT * FROM Aktor WHERE AktBez = "+aktor;
          
            Stmt = con.createStatement();
            RS   = Stmt.executeQuery(CQL);
           
            while (RS.next()) {
            	aktID = "123456";
               //aktID = RS.getString("KEY");
               System.out.println(" AktorID       ----"+aktID);

            }
           
          
		} catch (SQLException ex) {
           Logger.getLogger(SetRulesDB.class.getName()).log(Level.ERROR, null, ex);
		}
		
		try {
            Connection con = verb.connect();
            Statement Stmt = con.createStatement();
            
            
           
          
            String CQL = "UPDATE EventAktion SET EveAktiEveID = '"+eventUUID+"', EveAktiBez = '"+aktor+"', EveAktiZieID = '"+1234567+"', EveAktiZiePar = '"+funktion+"', EveAktiZieWer = '"+wert+"', EveAktiPri = '"+prioAkt+"' WHERE KEY = "+UUID.randomUUID().toString();
            
            System.out.println( "eingefügt Eventaktion");
            Stmt.executeUpdate(CQL);
       
            
		} catch (SQLException ex) {
	    	   Logger.getLogger(SetRulesDB.class.getName()).log(Level.ERROR, null, ex);
	    }
		prioAkt++;
		
	}
	
	public void setBenachrichtigung(String weg){
		
		try {
            Connection con = verb.connect();
            Statement Stmt = con.createStatement();
            
            
           
          
            String CQL = "UPDATE EventBenachrichtigung SET EveBenEveID = '"+eventUUID+"', EveBenWeg = '"+weg+"' WHERE KEY = "+UUID.randomUUID().toString();
            
            System.out.println( "eingefügt Eventbenachrichtigung");
            Stmt.executeUpdate(CQL);
       
            
		} catch (SQLException ex) {
	    	   Logger.getLogger(SetRulesDB.class.getName()).log(Level.ERROR, null, ex);
	    }
		
	}
	
	
	public void setEvent(String eveBez, String nachricht){
		
		try {
            Connection con = verb.connect();
            Statement Stmt = con.createStatement();
            
            
           
          
            String CQL = "UPDATE Event SET EveArt = '"+400+"', EveBez = '"+eveBez+"', EveNac = '"+nachricht+"' WHERE KEY = "+eventUUID;
       
            System.out.println( "eingefügt Event");
            Stmt.executeUpdate(CQL);
       
            
		} catch (SQLException ex) {
	    	   Logger.getLogger(SetRulesDB.class.getName()).log(Level.ERROR, null, ex);
	    }
		
	}
	
	
	public void insertSensorEvent(String senEventUUID, String sen, String messw, String oper, String wert){
		
		String senID = "";
		
		System.out.println("sen"+sen);
		
		try {
        	
            Connection con = verb.connect();
            
            Statement Stmt;
            ResultSet RS;
             
            String CQL = "SELECT * FROM Sensor WHERE SenBez ='"+sen+"'";
          
            Stmt = con.createStatement();
            RS   = Stmt.executeQuery(CQL);
           
            while (RS.next()) {

               senID = RS.getString("KEY");
               System.out.println("SenID "+senID);

            }
           
          
		} catch (SQLException ex) {
           Logger.getLogger(SetRulesDB.class.getName()).log(Level.ERROR, null, ex);
		}
		
		
		try {
            Connection con = verb.connect();
            Statement Stmt = con.createStatement();
           
          
            String CQL = "UPDATE SensorEvent SET SenEveSenID = '"+senID+"', SenEvePhyNam = '"+messw+"', SenEveVop = '"+oper+"', SenEveWer = '"+wert+"' WHERE KEY = '"+senEventUUID+"'";
       
            System.out.println( "eingefügt SensorEvent");
            Stmt.executeUpdate(CQL);
       
            
		} catch (SQLException ex) {
	    	   Logger.getLogger(SetRulesDB.class.getName()).log(Level.ERROR, null, ex);
	    }
		
		
		try {
            Connection con = verb.connect();
            Statement Stmt = con.createStatement();
          
            String CQL = "UPDATE EventMitglieder SET EveMitEveID = '"+eventUUID+"', EveMitSenEveID = '"+senEventUUID+"', EveMitAktEveID = '"+null+"', EveMitRei = '"+reihenFolge+"' WHERE KEY = '"+UUID.randomUUID().toString()+"'";
            
            System.out.println( "eingefügt Eventmitglieder");
            Stmt.executeUpdate(CQL);
       
            
		} catch (SQLException ex) {
	    	   Logger.getLogger(SetRulesDB.class.getName()).log(Level.ERROR, null, ex);
	    }
		reihenFolge++;
	}
	
	
	public void splitWordS(String line){
		String name = "";
		String senEventUUID = UUID.randomUUID().toString();
		
		String lines[] = line.split(" ");
		if (lines.length > 4) {
			
			for (int i = 0; i < lines.length-3; i++) {
				if (i==0) {
					name = name+lines[i];
				} else {
					name = name+" "+lines[i];
				}
				
			}
			
			insertSensorEvent(senEventUUID, name,  lines[lines.length-3],  lines[lines.length-2],  lines[lines.length-1]);
			System.out.println("          NAME                   -"+name+"-");
		}else{
			
			insertSensorEvent(senEventUUID, lines[0],  lines[1],  lines[2],  lines[3]);
			
		}
		
		/*for (int i = 0; i < lines.length; i++) {
			System.out.println(""+i+"."+lines[i]);
			
		}*/
	}
	
	
	
	public void splitLineS(String text){
		
		String lines[] = text.split("\n");
		
		for (int i = 0; i < lines.length; i++) {
			
			splitWordS(lines[i]);
			
		}
	}

	
	public void splitWordA(String line){
		
		String aktEventUUID = UUID.randomUUID().toString();
		
		String lines[] = line.split(" ");
		if (lines.length <= 2) {
			insertAktorEvent( aktEventUUID, lines[0], lines[1], "");
		}else{
			insertAktorEvent( aktEventUUID, lines[0], lines[1], lines[2]);
		}
		for (int i = 0; i < lines.length; i++) {
			System.out.println(""+i+"."+lines[i]);
			
		}
	}
	
	
	
	public void splitLineA(String text){
		
		String lines[] = text.split("\n");
		
		for (int i = 0; i < lines.length; i++) {
			System.out.println(""+i+"."+lines[i]);
			splitWordA(lines[i]);
			
		}
	}

}
