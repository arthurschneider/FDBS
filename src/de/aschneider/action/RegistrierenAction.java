package de.aschneider.action;

import de.aschneider.service.RegistrierenService;

public class RegistrierenAction {

	RegistrierenService regServ = new RegistrierenService();
	private String anrede;
	private String vorName;
	private String name;
	private String firma;
	private String telefon;
	private String telBez;
	private String emaAdr;
	private String emaBez;
	private String passwort;
	private String privKey;
	private String pubKey;
	private String anschrift;
	private String stasse;
	private String stadt;
	private String plz;
	private String land;
	
	public String execute(){
		
		return "success";
		
	}
	
	public void anlegen(){
		regServ.anlegenNutzer(getAnrede(), getVorName(), getName(), getFirma());
		regServ.anlegenTel(getTelefon(), getTelBez()); 
		regServ.anlegenMail(getEmaAdr(),getEmaBez());
		regServ.anlegenSicherheit(getPasswort(), getPrivKey(), getPubKey());
		regServ.anlegenAdresse(getAnschrift(), getStasse(), getStadt(), getPlz(), getLand());
	}

	public String getAnrede() {
		return anrede;
	}

	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}

	public String getVorName() {
		return vorName;
	}

	public void setVorName(String vorName) {
		this.vorName = vorName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getTelBez() {
		return telBez;
	}

	public void setTelBez(String telBez) {
		this.telBez = telBez;
	}

	public String getEmaAdr() {
		return emaAdr;
	}

	public void setEmaAdr(String emaAdr) {
		this.emaAdr = emaAdr;
	}

	public String getEmaBez() {
		return emaBez;
	}

	public void setEmaBez(String emaBez) {
		this.emaBez = emaBez;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getPrivKey() {
		return privKey;
	}

	public void setPrivKey(String privKey) {
		this.privKey = privKey;
	}

	public String getPubKey() {
		return pubKey;
	}

	public void setPubKey(String pubKey) {
		this.pubKey = pubKey;
	}

	public String getAnschrift() {
		return anschrift;
	}

	public void setAnschrift(String anschrift) {
		this.anschrift = anschrift;
	}

	public String getStasse() {
		return stasse;
	}

	public void setStasse(String stasse) {
		this.stasse = stasse;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}
	
	
}
