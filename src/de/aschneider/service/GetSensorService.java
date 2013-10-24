package de.aschneider.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.aschneider.DBConnect.Verbindung;

public class GetSensorService {
	
	Verbindung verb = new Verbindung();
	
	public ArrayList<String> getAlleSensor(){
		
		ArrayList<String> list = new ArrayList<String>();
		
        try {
        	
             Connection con = verb.connect();
             
             Statement Stmt;
             ResultSet RS;
              
            String CQL = "SELECT * FROM Sensor";
           
            Stmt = con.createStatement();
            RS   = Stmt.executeQuery(CQL);
            
             while (RS.next()) {

                list.add(RS.getString("SenBez"));
                
 
             }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(GetSensorService.class.getName()).log(Level.ERROR, null, ex);
        }
        
        return list;
    }

}
