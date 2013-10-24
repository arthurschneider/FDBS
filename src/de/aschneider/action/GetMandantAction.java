package de.aschneider.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import de.aschneider.model.MandantData;
import de.aschneider.model.Messwerte;
import de.aschneider.model.NutzerEmail;
import de.aschneider.model.NutzerTelefon;
import de.aschneider.service.GetMandantService;

public class GetMandantAction {
	
	GetMandantService manServ = new GetMandantService();
	
	
	private String name;
	private String pass;
	private String bezeichnung;
	private String telnummer;
	private String telbezeichnung;
	private String emailAdr;
	private String emailBezeichnung;
	private String strasse;
	private String ort;
	private String plz;
	private String land;
	private String anrede;
	private String vorname;
	private String nachName;
	private String firma;
	
	private String lufttemperatur;
	private String luftfeuchte;
	private String bewegung;
	private String tuer;
	private String steckdose;
	private String wirkleistung;
	private String gesammtleistung;
	
	private String yourMandant;
	private ArrayList<String> mandanten;
	
	
	public GetMandantAction(){
		System.out.println("----Konstruktor---");
		ArrayList<MandantData> manListe = new ArrayList<MandantData>();
		manListe = manServ.loadMandant();
		Map session = ActionContext.getContext().getSession();
		session.put("manListe",manListe); 
		ArrayList<String> mandanten = new ArrayList<String>();
		for (MandantData man : manListe) {
			String buff = man.getVorname()+"  "+man.getName()+"  "+man.getStrasse();
			mandanten.add(buff);
			System.out.println("Im Select sollte stehen:: = "+buff);
		}
		setMandanten(mandanten);
		
	}
	
	
	public String execute(){
		System.out.println("GetMandantAction ausgeführt!");
		ArrayList<MandantData> manListe = new ArrayList<MandantData>();
		manListe = manServ.loadMandant();
		Map session = ActionContext.getContext().getSession();
		session.put("manListe",manListe); 
		ArrayList<String> mandanten = new ArrayList<String>();
		for (MandantData man : manListe) {
			String buff = man.getVorname()+"  "+man.getName()+"  "+man.getStrasse();
			mandanten.add(buff);
			System.out.println("Im Select sollte stehen:: = "+buff);
		}
		setMandanten(mandanten);
		return "success";
	}
	
	public String loadMandant(){
		
		String nutzID;
		ArrayList<MandantData> manListe = new ArrayList<MandantData>();
		Map session = ActionContext.getContext().getSession();
		manListe = (ArrayList<MandantData>) session.get("manListe"); 
		
		String[] mandant = new String[3];
		mandant= getYourMandant().split("  ", 3);
		System.out.println("Mandant 1 "+mandant[0]);
		System.out.println("Mandant 2 "+mandant[1]);
		System.out.println("Mandant 3 "+mandant[2]);
		for (MandantData man : manListe) {
			 if (mandant[0].equals(man.getVorname()) && mandant[1].equals(man.getName()) && mandant[2].equals(man.getStrasse())) {
				 
				 setAnrede(man.getAnrede());
				 setVorname(man.getVorname());
				 setNachName(man.getName());
				 setFirma(man.getFirma());
				 setStrasse(man.getStrasse());
				 setOrt(man.getOrt());
				 setLand(man.getLand());
				 setPlz(man.getPlz());
				 ArrayList<NutzerTelefon> telListe = new ArrayList<NutzerTelefon>();
				 telListe = man.getNutTel();
				 String telefonData = "";
				 for (NutzerTelefon tel : telListe) {
					
					
					 telefonData = telefonData+tel.getNutTelNum()+"  "+ tel.getNutTelBez()+"\n";
				 }
				 
				 setTelnummer(telefonData);
				 
				 ArrayList<NutzerEmail> mailListe = new ArrayList<NutzerEmail>();
				 mailListe = man.getNutEma();
				 String eMailData = "";
				 for (NutzerEmail mail : mailListe) {
					
					
					 eMailData = eMailData+mail.getNutEmaAdr()+"  "+ mail.getNutEmaBez()+"\n";
				 }
				 
				 setEmailAdr(eMailData);
			}
			 
			 Messwerte mWerte = new Messwerte();
			 mWerte = manServ.getMesswerte();
			setBewegung(mWerte.getBewegung());
			setLuftfeuchte(mWerte.getLuftFeuchtigkeit());
			setLufttemperatur(mWerte.getLuftTemperatur());
			if (mWerte.getTuerZuAuf().equals("00")) {
				setTuer("Tür zu");
			}else{
				setTuer("Tür auf");
			}
			
			//setWirkleistung(mWerte.getWirkLeistung());
			//setGesammtleistung(mWerte.getGesammtLeistung());
			if (mWerte.getSteckdose().equals("0101C800")) {
				setSteckdose("An");
			}else{
				setSteckdose("Aus");
			}
			
			
		 }
	
		 
		 
		return "success";
	}
	
	public String insertMandant(){
		System.out.println("NAme"+getName());
		System.out.println("Pass"+getPass());
		System.out.println("Bez"+getBezeichnung());
		manServ.insertMandant(getName(), getPass(), getBezeichnung());
		
		return "success";
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getBezeichnung() {
		return bezeichnung;
	}


	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getYourMandant() {
		return yourMandant;
	}

	public void setYourMandant(String yourMandant) {
		this.yourMandant = yourMandant;
	}

	public ArrayList<String> getMandanten() {
		return mandanten;
	}

	public void setMandanten(ArrayList<String> mandanten) {
		this.mandanten = mandanten;
	}


	public String getTelnummer() {
		return telnummer;
	}


	public void setTelnummer(String telnummer) {
		this.telnummer = telnummer;
	}


	public String getTelbezeichnung() {
		return telbezeichnung;
	}


	public void setTelbezeichnung(String telbezeichnung) {
		this.telbezeichnung = telbezeichnung;
	}


	public String getEmailAdr() {
		return emailAdr;
	}


	public void setEmailAdr(String emailAdr) {
		this.emailAdr = emailAdr;
	}


	public String getEmailBezeichnung() {
		return emailBezeichnung;
	}


	public void setEmailBezeichnung(String emailBezeichnung) {
		this.emailBezeichnung = emailBezeichnung;
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


	public String getAnrede() {
		return anrede;
	}


	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}


	public String getVorname() {
		return vorname;
	}


	public void setVorname(String vorname) {
		this.vorname = vorname;
	}


	public String getNachName() {
		return nachName;
	}


	public void setNachName(String nachName) {
		this.nachName = nachName;
	}


	public String getFirma() {
		return firma;
	}


	public void setFirma(String firma) {
		this.firma = firma;
	}


	public String getLufttemperatur() {
		return lufttemperatur;
	}


	public void setLufttemperatur(String lufttemperatur) {
		this.lufttemperatur = lufttemperatur;
	}


	public String getLuftfeuchte() {
		return luftfeuchte;
	}


	public void setLuftfeuchte(String luftfeuchte) {
		this.luftfeuchte = luftfeuchte;
	}


	public String getBewegung() {
		return bewegung;
	}


	public void setBewegung(String bewegung) {
		this.bewegung = bewegung;
	}


	public String getTuer() {
		return tuer;
	}


	public void setTuer(String tuer) {
		this.tuer = tuer;
	}


	public String getSteckdose() {
		return steckdose;
	}


	public void setSteckdose(String steckdose) {
		this.steckdose = steckdose;
	}


	public String getWirkleistung() {
		return wirkleistung;
	}


	public void setWirkleistung(String wirkleistung) {
		this.wirkleistung = wirkleistung;
	}


	public String getGesammtleistung() {
		return gesammtleistung;
	}


	public void setGesammtleistung(String gesammtleistung) {
		this.gesammtleistung = gesammtleistung;
	}

}
