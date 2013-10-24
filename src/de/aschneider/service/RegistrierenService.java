package de.aschneider.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.aschneider.DBConnect.Verbindung;

public class RegistrierenService {
	
	Verbindung verb = new Verbindung();
	
	String adrID = UUID.randomUUID().toString();
	String nutzerID = UUID.randomUUID().toString();  
	
	
	public void anlegenNutzer(String anrede, String vorName, String name, String firma){	
		Connection con;
		try {
			con = verb.connect();
			Statement stmt = con.createStatement();
			String CQLA = "UPDATE NutzerStammdaten SET NutStaAdrID = '"+adrID+"', NutStaAnr = '"+anrede+"', NutStaNam = '"+name+ "', NutStaVor = '"+vorName+"', NutStaFir = '"+firma+"', NutStaDatEin = '"+System.currentTimeMillis()/1000L+"' Where KEY="+nutzerID;
			stmt.executeUpdate(CQLA);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		   
	}
	
	
	public void anlegenTel(String telefon, String telBez){	
		Connection con;
	    try {
	      con = verb.connect();
	          Statement stmt = con.createStatement();
	          
	          String CQL = "UPDATE NutzerTelefon SET NutTelNum = '"+telefon+"', NutTelBez = '"+telBez+"', NutTelNutStaID = '"+nutzerID+"' WHERE KEY = "+UUID.randomUUID().toString();
	          stmt.executeUpdate(CQL);
	    } catch (SQLException ex) {
	       
	    }
	}
	
	
	public void anlegenMail(String emaAdr, String emaBez){	
		 Connection con;
		    try {
		      con = verb.connect();
		          Statement stmt = con.createStatement();
		          
		          String CQL = "UPDATE NutzerEmail SET NutEmaAdr = '"+emaAdr+"', NutEmaBez = '"+emaBez+"', NutEmaNutStaID = '"+nutzerID+"' WHERE KEY = "+UUID.randomUUID().toString();
		          stmt.executeUpdate(CQL);
		    } catch (SQLException ex) {
		     
		    }
	}

	
	public void anlegenSicherheit(String passwort, String privKey, String pubKey){	
		Connection con;
		try {
			con = verb.connect();
			Statement stmt = con.createStatement();
			String CQLB = "UPDATE NutzerSicherheit SET NutSicNutStaID = '"+nutzerID+"', NutSicPas = '"+passwort+ "', NutSicPriKey = '"+privKey+"', NutSicPubKey = '"+pubKey+"' Where Key = "+UUID.randomUUID().toString();

			stmt.executeUpdate(CQLB);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	}
	public void anlegenAdresse(String anschrift, String strasse, String stadt,String plz, String land){
		
	}
}
