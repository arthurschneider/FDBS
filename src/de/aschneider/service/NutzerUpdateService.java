package de.aschneider.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;

import de.aschneider.DBConnect.Verbindung;
import de.aschneider.model.Adresse;
import de.aschneider.model.NutzerEmail;
import de.aschneider.model.NutzerTelefon;

public class NutzerUpdateService {
  
  Verbindung verb = new Verbindung();
  
  
  public ArrayList<String> getTelNummerDB(){
    
    ArrayList<String> telnr = new ArrayList<String>();
    Map session = ActionContext.getContext().getSession(); 
    
    try {
      
            Connection con = verb.connect();
            Statement Stmt = con.createStatement();
            ResultSet RS;
            
            String CQL = "SELECT NutTelNum FROM NutzerTelefon WHERE NutTelNutStaID = "+(String)session.get("NutStaID");
           
            RS   = Stmt.executeQuery(CQL);
       
            while (RS.next()) {

               telnr.add(RS.getString("NutTelNum"));
          
            }
          
            
       } catch (SQLException ex) {
         Logger.getLogger(NutzerUpdateService.class.getName()).log(Level.ERROR, null, ex);
       }
  
    return telnr;
  }
  
  
  public ArrayList<String> getEmailAdrDB(){
    
    ArrayList<String> eMailAdr = new ArrayList<String>();
    Map session = ActionContext.getContext().getSession(); 
    
    try {
      
            Connection con = verb.connect();
            Statement Stmt = con.createStatement();
            ResultSet RS;
            
            String CQL = "SELECT NutEmaAdr FROM NutzerEmail WHERE NutEmaNutStaID = "+(String)session.get("NutStaID");
           
            RS   = Stmt.executeQuery(CQL);
       
            while (RS.next()) {

              eMailAdr.add(RS.getString("NutEmaAdr"));
             
            }
          
            
       } catch (SQLException ex) {
         Logger.getLogger(NutzerUpdateService.class.getName()).log(Level.ERROR, null, ex);
       }
  
    return eMailAdr;
  }

  
  public ArrayList<String> getAddressDB(){
    ArrayList<String> address = new ArrayList<String>();
    Map session = ActionContext.getContext().getSession(); 
    String adrID = null;
    try {
      
            Connection con = verb.connect();
            Statement Stmt = con.createStatement();
            ResultSet RS;
            
            String CQL = "SELECT NutStaAdrID FROM NutzerStammdaten WHERE KEY = "+(String)session.get("NutStaID");
           
            RS   = Stmt.executeQuery(CQL);
       
            while (RS.next()) {

              adrID = RS.getString("NutStaAdrID");
             
            }
          
            
    } catch (SQLException ex) {
         Logger.getLogger(NutzerUpdateService.class.getName()).log(Level.ERROR, null, ex);
    }
    
    try {
      
            Connection con = verb.connect();
            Statement Stmt = con.createStatement();
            ResultSet RS;
            
            String CQL = "SELECT AdrBez FROM Adresse WHERE KEY = "+adrID;
           
            RS   = Stmt.executeQuery(CQL);
       
            while (RS.next()) {

              address.add(RS.getString("AdrBez"));
             
            }
          
            
       } catch (SQLException ex) {
         Logger.getLogger(NutzerUpdateService.class.getName()).log(Level.ERROR, null, ex);
       }
  
    return address;
  }
  

  public NutzerTelefon getTelData(String telnr){
    
    NutzerTelefon nutTel = new NutzerTelefon();
    Map session = ActionContext.getContext().getSession(); 
    
    try {
      
            Connection con = verb.connect();
            Statement Stmt = con.createStatement();
            ResultSet RS;
            
            String CQL = "SELECT * FROM NutzerTelefon WHERE NutTelNum = '"+telnr+"' AND NutTelNutStaID = '"+(String)session.get("NutStaID")+"'";
           
            RS   = Stmt.executeQuery(CQL);
       
            while (RS.next()) {
              
              nutTel.setKey(RS.getString("KEY"));
              nutTel.setNutTelBez(RS.getString("NutTelBez"));
              nutTel.setNutTelNum(RS.getString("NutTelNum"));
              nutTel.setNutTelNutStaID(RS.getString("NutTelNutStaID"));
            }
          
            
       } catch (SQLException ex) {
         Logger.getLogger(NutzerUpdateService.class.getName()).log(Level.ERROR, null, ex);
       }
    
    return nutTel;
  }
  
public NutzerEmail getEmailData(String emaAdr){
    
    NutzerEmail nutEma = new NutzerEmail();
    Map session = ActionContext.getContext().getSession(); 
    
    try {
      
            Connection con = verb.connect();
            Statement Stmt = con.createStatement();
            ResultSet RS;
            
            String CQL = "SELECT * FROM NutzerEmail WHERE NutEmaAdr = '"+emaAdr+"' AND NutEmaNutStaID = '"+(String)session.get("NutStaID")+"'";
           
            RS   = Stmt.executeQuery(CQL);
       
            while (RS.next()) {
              
              nutEma.setKey(RS.getString("KEY"));
              nutEma.setNutEmaBez(RS.getString("NutEmaBez"));
              nutEma.setNutEmaAdr(RS.getString("NutEmaAdr"));
              nutEma.setNutEmaNutStaID(RS.getString("NutEmaNutStaID"));
            }
          
            
       } catch (SQLException ex) {
         Logger.getLogger(NutzerUpdateService.class.getName()).log(Level.ERROR, null, ex);
       }
    
    return nutEma;
  }


  public Adresse getAddressData(String bez){
    Adresse adr = new Adresse();
    Map session = ActionContext.getContext().getSession(); 
    String adrID = null;
    try {
      
            Connection con = verb.connect();
            Statement Stmt = con.createStatement();
            ResultSet RS;
            
            String CQL = "SELECT NutStaAdrID FROM NutzerStammdaten WHERE KEY = '"+session.get("NutStaID")+"'";
           
            RS   = Stmt.executeQuery(CQL);
       
            while (RS.next()) {
              
              adrID = RS.getString("NutStaAdrID");
              
            }
          
            
    } catch (SQLException ex) {
         Logger.getLogger(NutzerUpdateService.class.getName()).log(Level.ERROR, null, ex);
    }
    
    try {
      
            Connection con = verb.connect();
            Statement Stmt = con.createStatement();
            ResultSet RS;
            System.out.println("!11111111111 BEZ " +bez);
            String CQL = "SELECT * FROM Adresse WHERE KEY = "+adrID+" AND AdrBez = "+bez;
           
            RS   = Stmt.executeQuery(CQL);
       
            while (RS.next()) {
               System.out.println("!2222222222222");
              adr.setAdrID(RS.getString("KEY"));
              adr.setAdrBez(RS.getString("AdrBez"));
              adr.setAdrStr(RS.getString("AdrStr"));
              adr.setAdrPlz(RS.getString("AdrPlz"));
              adr.setAdrOrt(RS.getString("AdrOrt"));
              adr.setAdrLan(RS.getString("AdrLan"));
            }
          
            
    } catch (SQLException ex) {
         Logger.getLogger(NutzerUpdateService.class.getName()).log(Level.ERROR, null, ex);
    }
    
    return adr;
  }

  
  public void updateTel(String key, String telnr, String bez){
    Connection con;
    try {
      con = verb.connect();
          Statement stmt = con.createStatement();
          
          String CQL = "UPDATE NutzerTelefon SET NutTelNum = '"+telnr+"', NutTelBez = '"+bez+"' WHERE KEY = "+key;
          stmt.executeUpdate(CQL);
    } catch (SQLException ex) {
       Logger.getLogger(NutzerUpdateService.class.getName()).log(Level.ERROR, null, ex);
    }

  }
  
  public void updateMail(String key, String mailadr, String bez){
    Connection con;
    try {
      con = verb.connect();
          Statement stmt = con.createStatement();
          
          String CQL = "UPDATE NutzerEmail SET NutEmaAdr = '"+mailadr+"', NutEmaBez = '"+bez+"' WHERE KEY = "+key;
          stmt.executeUpdate(CQL);
    } catch (SQLException ex) {
       Logger.getLogger(NutzerUpdateService.class.getName()).log(Level.ERROR, null, ex);
    }
  }
  
  
  public void insertTel(String nutzID, String telnr, String bez){
    Connection con;
    try {
      con = verb.connect();
          Statement stmt = con.createStatement();
          
          String CQL = "UPDATE NutzerTelefon SET NutTelNum = '"+telnr+"', NutTelBez = '"+bez+"', NutTelNutStaID = '"+nutzID+"' WHERE KEY = "+UUID.randomUUID().toString();
          stmt.executeUpdate(CQL);
    } catch (SQLException ex) {
       Logger.getLogger(NutzerUpdateService.class.getName()).log(Level.ERROR, null, ex);
    }

  }
  
  public void deleteTel(String key, String telnr, String bez){
    Connection con;
    try {
      con = verb.connect();
          Statement stmt = con.createStatement();
          
          String CQL = "DELETE NutTelNutStaID, NutTelNum, NutTelBez FROM NutzerTelefon  WHERE KEY = "+key;
          stmt.executeUpdate(CQL);
    } catch (SQLException ex) {
       Logger.getLogger(NutzerUpdateService.class.getName()).log(Level.ERROR, null, ex);
    }

  }
  
  
  public void insertMail(String nutzID, String mailAdr, String bez){
    Connection con;
    try {
      con = verb.connect();
          Statement stmt = con.createStatement();
          
          String CQL = "UPDATE NutzerEmail SET NutEmaAdr = '"+mailAdr+"', NutEmaBez = '"+bez+"', NutEmaNutStaID = '"+nutzID+"' WHERE KEY = "+UUID.randomUUID().toString();
          stmt.executeUpdate(CQL);
    } catch (SQLException ex) {
       Logger.getLogger(NutzerUpdateService.class.getName()).log(Level.ERROR, null, ex);
    }

  }
  
  public void deleteMail(String key, String telnr, String bez){
    Connection con;
    try {
      con = verb.connect();
          Statement stmt = con.createStatement();
          
          String CQL = "DELETE NutEmaNutStaID, NutEmaAdr, NutEmaBez FROM NutzerEmail  WHERE KEY = "+key;
          stmt.executeUpdate(CQL);
    } catch (SQLException ex) {
       Logger.getLogger(NutzerUpdateService.class.getName()).log(Level.ERROR, null, ex);
    }

  }
  
  public void updateAdr(String adrID, String bez, String str, String plz, String ort,  String land){
    Connection con;
    try {
      con = verb.connect();
          Statement stmt = con.createStatement();
          
           String CQL = "UPDATE Adresse SET AdrBez = '"+bez+"', AdrStr = '"+str+"', AdrPlz = '"+plz+"', AdrOrt = '"+ort+"', AdrLan = '"+land+"' WHERE KEY = "+adrID;
          stmt.executeUpdate(CQL);
    } catch (SQLException ex) {
       Logger.getLogger(NutzerUpdateService.class.getName()).log(Level.ERROR, null, ex);
    }
  }

}
