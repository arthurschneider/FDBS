package de.aschneider.model;

public class Messwerte {
	private String luftTemperatur;
	private String luftFeuchtigkeit;
	private String bewegung;
	private String tuerZuAuf;
	private String steckdose;
	private String wirkLeistung;
	private String gesammtLeistung;
	
	
	
	public String getLuftTemperatur() {
		return luftTemperatur;
	}
	public void setLuftTemperatur(String luftTemperatur) {
		this.luftTemperatur = luftTemperatur;
	}
	public String getLuftFeuchtigkeit() {
		return luftFeuchtigkeit;
	}
	public void setLuftFeuchtigkeit(String luftFeuchtigkeit) {
		this.luftFeuchtigkeit = luftFeuchtigkeit;
	}
	public String getTuerZuAuf() {
		return tuerZuAuf;
	}
	public void setTuerZuAuf(String tuerZuAuf) {
		this.tuerZuAuf = tuerZuAuf;
	}
	public String getSteckdose() {
		return steckdose;
	}
	public void setSteckdose(String steckdose) {
		this.steckdose = steckdose;
	}
	public String getWirkLeistung() {
		return wirkLeistung;
	}
	public void setWirkLeistung(String wirkLeistung) {
		this.wirkLeistung = wirkLeistung;
	}
	public String getGesammtLeistung() {
		return gesammtLeistung;
	}
	public void setGesammtLeistung(String gesammtLeistung) {
		this.gesammtLeistung = gesammtLeistung;
	}
	public String getBewegung() {
		return bewegung;
	}
	public void setBewegung(String bewegung) {
		this.bewegung = bewegung;
	}
	

}
