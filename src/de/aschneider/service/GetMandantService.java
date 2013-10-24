package de.aschneider.service;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import javassist.bytecode.Mnemonic;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;

import de.aschneider.DBConnect.Verbindung;
import de.aschneider.model.MandantData;
import de.aschneider.model.Messwerte;
import de.aschneider.model.NutzerEmail;
import de.aschneider.model.NutzerTelefon;
import de.sensorcloud.cryptography.symmetry.aes.CryptAES;

public class GetMandantService {
	
	 Verbindung verb = new Verbindung();
	 
	 
	 
	 public void insertMandant(String name, String pass, String bezeichnung){
		 
		 String nutzerID = "";
		 String ManNutStaID = "";
		 String manID = UUID.randomUUID().toString();
		 Map session = ActionContext.getContext().getSession();
		 try {
		      
	            Connection con = verb.connect();
	            Statement Stmt = con.createStatement();
	            ResultSet RS;
	            String CQL = "SELECT KEY FROM NutzerStammdaten WHERE NutStaNam = "+name;
	           
	            RS   = Stmt.executeQuery(CQL);
	       
	            while (RS.next()) {

	              nutzerID = RS.getString("KEY");
	              System.out.println("NutStaID ="+nutzerID);
	             
	            }
	          
	            
	       } catch (SQLException ex) {
	         Logger.getLogger(GetMandantService.class.getName()).log(Level.ERROR, null, ex);
	       }
		 
		 try {
		      	
	            Connection con = verb.connect();
	            Statement Stmt = con.createStatement();
	            ResultSet RS;
	            
	            String CQL = "SELECT NutSicPubKey FROM NutzerSicherheit WHERE NutSicNutStaID = "+nutzerID;
	           
	            RS   = Stmt.executeQuery(CQL);
	       
	            while (RS.next()) {
	            	if ( RS.getString("NutSicPubKey").equals(pass)) {
						ManNutStaID = nutzerID;
					}
	              
	             
	            }
	          
	            
	       } catch (SQLException ex) {
	         Logger.getLogger(GetMandantService.class.getName()).log(Level.ERROR, null, ex);
	       }
		 
		 try {
		      	
	            Connection con = verb.connect();
	            Statement Stmt = con.createStatement();
	          
	            System.out.println("ManNutStaID"+ManNutStaID);
	            String CQL = "UPDATE Mandanten SET ManNutStaID = '"+ManNutStaID+"', ManBez = '"+bezeichnung+"' WHERE KEY = "+manID;
	            Stmt.executeUpdate(CQL);
 
	       } catch (SQLException ex) {
	         Logger.getLogger(GetMandantService.class.getName()).log(Level.ERROR, null, ex);
	       }
		 
		 try {
		      	
	            Connection con = verb.connect();
	            Statement Stmt = con.createStatement();
	           
	            
	            String CQL = "UPDATE MandantenMitglieder SET ManMitManID = '"+manID+"', ManMitNutStaID = '"+session.get("NutStaID")+"' WHERE KEY = '"+UUID.randomUUID().toString()+"'";
	            Stmt.executeUpdate(CQL);

	       } catch (SQLException ex) {
	         Logger.getLogger(GetMandantService.class.getName()).log(Level.ERROR, null, ex);
	       }
		 
	 }
	 
	 
	 public ArrayList<MandantData> loadMandant(){
		 
		 MandantData manData = new MandantData();
		 ArrayList<MandantData> mandantListe = new  ArrayList<MandantData>();
		 ArrayList<NutzerTelefon> telList = new ArrayList<NutzerTelefon>();
		 ArrayList<NutzerEmail> emaList = new ArrayList<NutzerEmail>();
		 Map session = ActionContext.getContext().getSession();
		 String[] manID  = new String[100];
		 String[] manNutStaID = new String[100];
		 int i= 0;
		 int k= 0;
		 System.out.println("loadMandant Methode aufgerufen"+session.get("NutStaID"));
		 
		 try {
		      
	            Connection con = verb.connect();
	            Statement Stmt = con.createStatement();
	            ResultSet RS;
	            String CQL = "SELECT ManMitManID FROM MandantenMitglieder WHERE ManMitNutStaID = '"+session.get("NutStaID")+"'";
	           
	            RS   = Stmt.executeQuery(CQL);
	       
	            while (RS.next()) {
	            	manID[i] = RS.getString("ManMitManID");
	            	System.out.println("ManMitManID ::= "+manID[i]);
	            	i++;
	            	
	             
	            }
	          
	            
	       } catch (SQLException ex) {
	         Logger.getLogger(GetMandantService.class.getName()).log(Level.ERROR, null, ex);
	       }
		 
		 try {
		      
	            Connection con = verb.connect();
	            Statement Stmt = con.createStatement();
	            ResultSet RS;
	            for (int j = 0; j < i; j++) {
	            	
	            	String CQL = "SELECT * FROM Mandanten WHERE KEY = "+manID[j];
	 	           
		            RS   = Stmt.executeQuery(CQL);
		       
		            while (RS.next()) {
		            	manNutStaID[k] = RS.getString("ManNutStaID");
		            	k++;

		             
		            }
				}
	            
	          
	            
	       } catch (SQLException ex) {
	         Logger.getLogger(GetMandantService.class.getName()).log(Level.ERROR, null, ex);
	       }
		 
		 try {
		      
	            Connection con = verb.connect();
	            Statement Stmt = con.createStatement();
	            ResultSet RS;
	            for (int l = 0; l < k; l++) {
	            	
	            	String CQL = "SELECT * FROM NutzerStammdaten WHERE KEY = "+manNutStaID[l];
	 	           
		            RS   = Stmt.executeQuery(CQL);
		       
		            while (RS.next()) {
		            	manData.setNutzerID(RS.getString("KEY"));
		            	manData.setAdressID(RS.getString("NutStaAdrID"));
		            	manData.setAnrede(RS.getString("NutStaAnr"));
		            	manData.setName(RS.getString("NutStaNam"));
		            	manData.setVorname(RS.getString("NutStaVor"));
		            	manData.setFirma(RS.getString("NutStaFir"));
		            	
		            	
		            	try {
		      		      

		    	            Statement Stmt1 = con.createStatement();
		    	            ResultSet RS1;
		    	          
		    	            	
		    	            	String CQL1 = "SELECT AdrBez, AdrStr, AdrLan, AdrOrt, AdrPlz FROM Adresse WHERE KEY = "+manData.getAdressID();
		    	 	           
		    		            RS1   = Stmt1.executeQuery(CQL1);
		    		       
		    		            while (RS1.next()) {
		    		            	manData.setStrasse(RS1.getString("AdrStr"));
		    		            	manData.setLand(RS1.getString("AdrLan"));
		    		            	manData.setOrt(RS1.getString("AdrOrt"));
		    		            	manData.setPlz(RS1.getString("AdrPlz"));
	
		    		            }
		    			
		    	            
		    	       } catch (SQLException ex) {
		    	         Logger.getLogger(GetMandantService.class.getName()).log(Level.ERROR, null, ex);
		    	       }
		            	
		            	
		            	try {
			      		      
		            		
		    	            Statement Stmt2 = con.createStatement();
		    	            ResultSet RS2;
		    	          
		    	            	
		    	            	String CQL2 = "SELECT * FROM NutzerTelefon WHERE NutTelNutStaID = "+manData.getNutzerID();
		    	 	           
		    		            RS2   = Stmt2.executeQuery(CQL2);
		    		       
		    		            while (RS2.next()) {
		    		            	NutzerTelefon nutTel = new NutzerTelefon();
		    		            	nutTel.setNutTelNum(RS2.getString("NutTelNum"));
		    		            	nutTel.setNutTelBez(RS2.getString("NutTelBez"));
		    		            	telList.add(nutTel);
	
		    		            }
		    			
		    	            
		    	       } catch (SQLException ex) {
		    	         Logger.getLogger(GetMandantService.class.getName()).log(Level.ERROR, null, ex);
		    	       }
		            	
		            	manData.setNutTel(telList);
		            	
		            	try {
			      		      
		            		
		    	            Statement Stmt3 = con.createStatement();
		    	            ResultSet RS3;
		    	          
		    	            	
		    	            	String CQL3 = "SELECT * FROM NutzerEmail WHERE NutEmaNutStaID = "+manData.getNutzerID();
		    	 	           
		    		            RS3   = Stmt3.executeQuery(CQL3);
		    		       
		    		            while (RS3.next()) {
		    		            	NutzerEmail nutEma = new NutzerEmail();
		    		            	nutEma.setNutEmaAdr(RS3.getString("NutEmaAdr"));
		    		            	nutEma.setNutEmaBez(RS3.getString("NutEmaBez"));
		    		            	emaList.add(nutEma);
	
		    		            }
		    			
		    	            
		    	       } catch (SQLException ex) {
		    	         Logger.getLogger(GetMandantService.class.getName()).log(Level.ERROR, null, ex);
		    	       }
		            	manData.setNutEma(emaList);
		            	
		            	mandantListe.add(manData);
		            	
		            }
				}
	            
	          
	            
	       } catch (SQLException ex) {
	         Logger.getLogger(GetMandantService.class.getName()).log(Level.ERROR, null, ex);
	       }
		 
		 
		 return mandantListe;
	 }
	 
	 
	 public Messwerte getMesswerte(){
		 
		
		 long zeit = System.currentTimeMillis();
		 System.out.println("Zeit"+zeit);
		 String[] buff = new String[4];
		 
		 Messwerte mWerte = new Messwerte();
		 
		 try {
 		      
			 	Connection con = verb.connect();
	            Statement Stmt1 = con.createStatement();
	            ResultSet RS1;
	            String tupel = "";
	            	//Multiraumsensor
	            	String CQL1 = "SELECT MesWerTup FROM Messwert WHERE MesWerSenID = '"+1151267 +"' AND MesWerTimSta <= '"+zeit+"'";
	 	           
		            RS1   = Stmt1.executeQuery(CQL1);
		       
		            while (RS1.next()) {
		            	
		            	tupel = CryptAES.decryptAesBase64(RS1.getString("MesWerTup"),"FdbsIstSoToll123");
		            	

		            }
		          
		            buff = tupel.split(";", 5);
		            mWerte.setBewegung(buff[0]);
		            mWerte.setLuftFeuchtigkeit(buff[1]);
		            mWerte.setLuftTemperatur(buff[2]);
	            
	       	} catch (SQLException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | IOException ex) {
	         Logger.getLogger(GetMandantService.class.getName()).log(Level.ERROR, null, ex);
	       	}
		 	try {
		      
			 	Connection con = verb.connect();
	            Statement Stmt2 = con.createStatement();
	            ResultSet RS2;
	          
	            	//Steckdose
	            	String CQL2 = "SELECT MesWerTup FROM Messwert WHERE MesWerSenID = '"+1676566 +"' AND MesWerTimSta <= '"+zeit+"'";
	 	           
		            RS2   = Stmt2.executeQuery(CQL2);
		       
		            while (RS2.next()) {
		            	mWerte.setSteckdose(CryptAES.decryptAesBase64(RS2.getString("MesWerTup"),"FdbsIstSoToll123"));
		            }
		       
	            
		 	} catch (SQLException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | IOException ex) {
	         Logger.getLogger(GetMandantService.class.getName()).log(Level.ERROR, null, ex);
		 	}
		 	
		 	
		 	try {
			      
			 	Connection con = verb.connect();
	            Statement Stmt3 = con.createStatement();
	            ResultSet RS3;
	          
	            	//ShutterContact
	            	String CQL3 = "SELECT MesWerTup FROM Messwert WHERE MesWerSenID = '"+1606160 +"' AND MesWerTimSta <= '"+zeit+"'";
	 	           
		            RS3   = Stmt3.executeQuery(CQL3);
		       
		            while (RS3.next()) {
		            	mWerte.setTuerZuAuf((CryptAES.decryptAesBase64(RS3.getString("MesWerTup"),"FdbsIstSoToll123")));
		            }
		           
	            
		 	} catch (SQLException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | IOException ex) {
	         Logger.getLogger(GetMandantService.class.getName()).log(Level.ERROR, null, ex);
		 	}
		 	
		 /*	try {
			      
			 	Connection con = verb.connect();
	            Statement Stmt3 = con.createStatement();
	            ResultSet RS3;
	            String id = "7fd80ea8-9f9b-46f4-b228-24c7b32170fb";
	            String[] temp = new String[3];	
	            	String CQL3 = "SELECT MesWerTup FROM Messwert WHERE MesWerSenID = '"+id+"' AND MesWerTimSta <= '"+zeit+"'";
	 	           
		            RS3   = Stmt3.executeQuery(CQL3);
		       
		            while (RS3.next()) {
		            	System.out.println("HIEEEEEEEEEEEEEEEEEEER");
		            	String str = CryptAES.decryptAesBase64(RS3.getString("MesWerTup"),"FdbsIstSoToll123");
		            	temp = str.split(";", 3);
		            	
		            	
		            	

		            }
		            System.out.println(temp[0]);
		            System.out.println(temp[1]);
		            mWerte.setWirkLeistung(temp[0]);
		            mWerte.setGesammtLeistung(temp[1]);
	            
		 	} catch (SQLException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | IOException ex) {
	         Logger.getLogger(GetMandantService.class.getName()).log(Level.ERROR, null, ex);
		 	}*/
		
		 return mWerte;
	 }
}
