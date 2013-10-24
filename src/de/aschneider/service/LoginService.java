package de.aschneider.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;

import de.aschneider.DBConnect.Verbindung;


public class LoginService {
	
	Verbindung verb = new Verbindung();
	String NutStaID;
	String NutStaNam;
	String buffer;
	
	public boolean verifyPass(String pass){
		
		boolean logPass = false;
		
		try {
            Connection con = verb.connect();
            Statement Stmt = con.createStatement();
            ResultSet RS;
           
            String CQLP = "SELECT * FROM NutzerSicherheit WHERE NutSicNutStaID = "+NutStaID;
      
            RS   = Stmt.executeQuery(CQLP);
       
            while (RS.next()) {

               String NutSicPas = RS.getString("NutSicPas");
               if (pass.equals(NutSicPas)) {
            	   logPass = true;
               }
            }
            
       } catch (SQLException ex) {
    	   Logger.getLogger(LoginService.class.getName()).log(Level.ERROR, null, ex);
       }
		
            return logPass;
	}
	
	
public boolean verifyName(String name){
	
	boolean logName = false;
	
		try {
			
            Connection con = verb.connect();
            Statement Stmt = con.createStatement();
         
            String CQL = "SELECT * FROM NutzerStammdaten WHERE NutStaNam = "+name;
         
            ResultSet RS = Stmt.executeQuery(CQL);
       
            while (RS.next()) {
  
            	NutStaNam = RS.getString("NutStaNam");
            	
            	if (name.equals(NutStaNam)) {
            		NutStaID = RS.getString("KEY");
            		logName = true;
				}
            	
            }
            
            
       } catch (SQLException ex) {
    	   Logger.getLogger(LoginService.class.getName()).log(Level.ERROR, null, ex);
       }
		
		Map session = ActionContext.getContext().getSession();
		session.put("NutStaID", NutStaID);
		
			return logName;
	}

}
