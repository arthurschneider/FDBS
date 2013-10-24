package de.aschneider.helper;

public class Messwert implements Comparable<Messwert>{
	
	private String datumMilis;
	private int wert;
	
	public Messwert( String d, int w){
		this.datumMilis = d;
		this.wert = w;
	}
	

	
	@Override
	public int compareTo(Messwert mWert) {
		return this.datumMilis.compareTo(mWert.getDatumMilis());
	}



	public int getWert() {
		return wert;
	}



	public void setWert(int wert) {
		this.wert = wert;
	}



	public String getDatumMilis() {
		return datumMilis;
	}



	public void setDatumMilis(String datumMilis) {
		this.datumMilis = datumMilis;
	}







	

}
