package de.aschneider.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import de.aschneider.service.GetSensorService;
import de.aschneider.service.SetRulesDB;

public class SetRulesAction extends ActionSupport {
	
	String result = "";
	String aktion = "";
	private List<String> sensoren;
	private String yourSensor;
	
	private List<String> messwerte;
	private String yourMesswert;
	
	private List<String> operatoren;
	private String yourOperator;
	
	private String schwwert;
	
	private List <String> aktoren;
	private String yourAktor;
	
	private List<String> funktionen;
	private String yourFunktion;
	
	private String wert;
	
	private List<String> artNachrichten;
	private String yourartNachricht;
	
	private String nachricht;
	private String empfaenger;
	private String nameRegel;
	
	
	private String Regeln;
	private String Aktionen;
	
	public SetRulesAction() {
		
		GetSensorService gsServ = new GetSensorService();
		
		setSensoren(new ArrayList<String>());
		setMesswerte(new ArrayList<String>());
		setOperatoren(new ArrayList<String>());
		
		ArrayList<String> oper = new ArrayList<String>();
		oper.add("<");
		oper.add(">");
		oper.add("=");
		oper.add("<=");
		oper.add(">=");

		ArrayList<String> messw = new ArrayList<String>();
		messw.add("Luftfeuchtigkeit");
		messw.add("Temperatur");
		messw.add("Bewegung");
		messw.add("Tuer_auf");
		messw.add("Tuer_zu");
		messw.add("Stromverbrauch");
		
		ArrayList<String> akt = new ArrayList<String>();
		akt.add("Fenster");
		akt.add("Roboter");
		akt.add("Jalousie");
		akt.add("Tuer");
		
		ArrayList<String> funk = new ArrayList<String>();
		funk.add("hoch");
		funk.add("runter");
		funk.add("oeffnen");
		funk.add("schliessen");
		funk.add("vorwaerts");
		funk.add("vorwaerts");
		funk.add("rueckwaerts");
		funk.add("links");
		funk.add("rechts");
		
		ArrayList<String> nachricht = new ArrayList<String>();
		nachricht.add("E-Mail");
		nachricht.add("SMS");
		
		
		setOperatoren(oper);
		setMesswerte(messw);
		setAktoren(akt);
		setFunktionen(funk);
		setArtNachrichten(nachricht);
		setSensoren(gsServ.getAlleSensor());
	}
	
	
	public String execute() {
		Map setRulesSession = ActionContext.getContext().getSession();
		setRulesSession.remove("result"); 
		setRulesSession.remove("aktion"); 
		System.out.println("SetRulesAction läuft");
		return "success";
		
	}
	
	public String setRulesSensor(){
		Map setRulesSession = ActionContext.getContext().getSession();
		System.out.println(""+yourSensor+""+yourMesswert+""+yourOperator+""+schwwert);
	

		if (setRulesSession.get("result") == null) {
			result =  ""+yourSensor+" "+yourMesswert+" "+yourOperator+" "+schwwert+"\n";
		} else {
			result = setRulesSession.get("result") + ""+yourSensor+" "+yourMesswert+" "+yourOperator+" "+schwwert+"\n";
		}

		setRulesSession.put("result",result); 
			
		setRegeln(result);
		
		System.out.println("aktion = "+setRulesSession.get("aktion"));
		setAktionen((String)setRulesSession.get("aktion"));
	
		return "success";
	}
	
	public String setRulesAktor(){
		Map setRulesSession = ActionContext.getContext().getSession();
		System.out.println(""+yourAktor+""+yourFunktion+"");
	

		if (setRulesSession.get("aktion") == null) {
			aktion =  ""+yourAktor+" "+yourFunktion+" "+wert+"\n";
		} else {
			aktion = setRulesSession.get("aktion") + ""+yourAktor+" "+yourFunktion+" "+wert+"\n";
		}

		setRulesSession.put("aktion",aktion); //Merken
			
		setAktionen(aktion);//Anzeigen
		
		setRegeln((String)setRulesSession.get("result"));//Anzeigen Regeln
		return "success";
	}

	
	public String setRulesInDB(){
		
		String eventUUID = UUID.randomUUID().toString();
		SetRulesDB setRulesDB = new SetRulesDB(eventUUID);
		Map setRulesSession = ActionContext.getContext().getSession();
		
		
		System.out.println("NAchricht:"+getNachricht());
		System.out.println("geht an :"+getEmpfaenger());
		System.out.println("result \n"+ (String)setRulesSession.get("result"));
		System.out.println("aktor \n"+ (String)setRulesSession.get("aktion"));
		System.out.println("Name der Rgel " +getNameRegel());
		System.out.println("yourNachrichtArt " +getYourartNachricht());
		
		setRulesDB.splitLineS((String)setRulesSession.get("result"));
		setRulesDB.splitLineA((String)setRulesSession.get("aktion"));
		setRulesDB.setEvent(getNameRegel(),getNachricht());
		String weg = getYourartNachricht()+":"+getEmpfaenger();
		setRulesDB.setBenachrichtigung(weg);
	       
		
		setRulesSession.remove("result"); 
		setRulesSession.remove("aktion"); 
		return "success";
	}
	
	
	public List<String> getSensoren() {
		return sensoren;
	}


	public void setSensoren(List<String> sensoren) {
		this.sensoren = sensoren;
	}


	public String getYourSensor() {
		return yourSensor;
	}


	public void setYourSensor(String yourSensor) {
		this.yourSensor = yourSensor;
	}


	public List<String> getMesswerte() {
		return messwerte;
	}


	public void setMesswerte(List<String> messwerte) {
		this.messwerte = messwerte;
	}


	public String getYourMesswert() {
		return yourMesswert;
	}


	public void setYourMesswert(String yourMesswert) {
		this.yourMesswert = yourMesswert;
	}


	public List<String> getOperatoren() {
		return operatoren;
	}


	public void setOperatoren(List<String> operatoren) {
		this.operatoren = operatoren;
	}


	public String getYourOperator() {
		return yourOperator;
	}


	public void setYourOperator(String yourOperator) {
		this.yourOperator = yourOperator;
	}


	public List <String> getAktoren() {
		return aktoren;
	}


	public void setAktoren(List <String> aktoren) {
		this.aktoren = aktoren;
	}


	public String getYourAktor() {
		return yourAktor;
	}


	public void setYourAktor(String yourAktor) {
		this.yourAktor = yourAktor;
	}


	public List<String> getFunktionen() {
		return funktionen;
	}


	public void setFunktionen(List<String> funktionen) {
		this.funktionen = funktionen;
	}


	public String getYourFunktion() {
		return yourFunktion;
	}


	public void setYourFunktion(String yourFunktion) {
		this.yourFunktion = yourFunktion;
	}


	public String getSchwwert() {
		return schwwert;
	}


	public void setSchwwert(String schwwert) {
		this.schwwert = schwwert;
	}


	public String getRegeln() {
		return Regeln;
	}


	public void setRegeln(String regeln) {
		Regeln = regeln;
	}


	public String getAktionen() {
		return Aktionen;
	}


	public void setAktionen(String aktionen) {
		Aktionen = aktionen;
	}


	public String getNachricht() {
		return nachricht;
	}


	public void setNachricht(String nachricht) {
		this.nachricht = nachricht;
	}


	public String getEmpfaenger() {
		return empfaenger;
	}


	public void setEmpfaenger(String empfaenger) {
		this.empfaenger = empfaenger;
	}


	public String getNameRegel() {
		return nameRegel;
	}


	public void setNameRegel(String nameRegel) {
		this.nameRegel = nameRegel;
	}


	public List<String> getArtNachrichten() {
		return artNachrichten;
	}


	public void setArtNachrichten(List<String> artNachrichten) {
		this.artNachrichten = artNachrichten;
	}


	public String getYourartNachricht() {
		return yourartNachricht;
	}


	public void setYourartNachricht(String yourartNachricht) {
		this.yourartNachricht = yourartNachricht;
	}


	public String getWert() {
		return wert;
	}


	public void setWert(String wert) {
		this.wert = wert;
	}



	
}
