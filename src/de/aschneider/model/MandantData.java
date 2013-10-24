package de.aschneider.model;

import java.util.ArrayList;

public class MandantData {
	
	private String nutzerID;
	private String adressID;
	private String anrede;
	private String name;
	private String vorname;
	private String firma;
	private String strasse;
	private String ort;
	private String plz;
	private String land;
	private ArrayList<NutzerTelefon> nutTel;
	private ArrayList<NutzerEmail> nutEma;
	
	
	public String getNutzerID() {
		return nutzerID;
	}
	public void setNutzerID(String nutzerID) {
		this.nutzerID = nutzerID;
	}
	public String getAdressID() {
		return adressID;
	}
	public void setAdressID(String adressID) {
		this.adressID = adressID;
	}
	public String getAnrede() {
		return anrede;
	}
	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getFirma() {
		return firma;
	}
	public void setFirma(String firma) {
		this.firma = firma;
	}
	
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
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
	public ArrayList<NutzerTelefon> getNutTel() {
		return nutTel;
	}
	public void setNutTel(ArrayList<NutzerTelefon> nutTel) {
		this.nutTel = nutTel;
	}
	public ArrayList<NutzerEmail> getNutEma() {
		return nutEma;
	}
	public void setNutEma(ArrayList<NutzerEmail> nutEma) {
		this.nutEma = nutEma;
	}

	
}
