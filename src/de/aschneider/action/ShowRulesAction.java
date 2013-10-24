package de.aschneider.action;

import java.util.List;

import de.aschneider.model.Event;
import de.aschneider.service.ShowRulesService;

public class ShowRulesAction {
	
	ShowRulesService srs = new ShowRulesService();
	
	private List<String> regeln;
	private String yourRegel;
	
	private String voraussetzungen;
	private String aktionen;
	private String eventArt;
	private String nachricht;
	private String eventBenach;
	
	
	
	public ShowRulesAction(){
		
		
		setRegeln(srs.showRulesName());
		
	}
	
	
	public String getRulesData(){
		System.out.println("----------AufruF--------------------------------"+yourRegel);
		Event eve = new Event();
		eve = srs.showRulesEvent(yourRegel);
		setNachricht(eve.getEveNac());
		setEventArt(eve.getEveArt());
		System.out.println("Das ist der KEY"+eve.getKey());
		setVoraussetzungen(srs.showRulesEventMitglieder(eve.getKey()));
		setEventBenach(srs.showRulesEventBenachrichtigung(eve.getKey()));
		setAktionen(srs.showRulesEventAktion(eve.getKey()));
		return "success";
		
	}
	
	public String execute(){
		System.out.println("ShowRulesAction ausgeführt!");
		
		return "success";
	}



	public List<String> getRegeln() {
		return regeln;
	}



	public void setRegeln(List<String> regeln) {
		this.regeln = regeln;
	}



	public String getYourRegel() {
		return yourRegel;
	}



	public void setYourRegel(String yourRegel) {
		this.yourRegel = yourRegel;
	}


	public String getEventArt() {
		return eventArt;
	}


	public void setEventArt(String eventArt) {
		this.eventArt = eventArt;
	}


	public String getNachricht() {
		return nachricht;
	}


	public void setNachricht(String nachricht) {
		this.nachricht = nachricht;
	}


	public String getEventBenach() {
		return eventBenach;
	}


	public void setEventBenach(String eventBenach) {
		this.eventBenach = eventBenach;
	}


	public String getVoraussetzungen() {
		return voraussetzungen;
	}


	public void setVoraussetzungen(String voraussetzungen) {
		this.voraussetzungen = voraussetzungen;
	}


	public String getAktionen() {
		return aktionen;
	}


	public void setAktionen(String aktionen) {
		this.aktionen = aktionen;
	}

}
