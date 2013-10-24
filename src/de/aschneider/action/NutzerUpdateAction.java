package de.aschneider.action;

import java.util.List;
import java.util.Map;


import com.opensymphony.xwork2.ActionContext;

import de.aschneider.model.Adresse;
import de.aschneider.model.NutzerEmail;
import de.aschneider.model.NutzerTelefon;
import de.aschneider.service.NutzerUpdateService;

public class NutzerUpdateAction {
	
	
	private List<String> telNummern;
	private String yourTelNummer;
	
	private List<String> MailAdr;
	private String yourEmailAdr;
	
	private String telefonNr;
	private String bezeichnungTel;
	
	private String MailAdress;
	private String bezeichnungMail;
	
	private List<String> address;
	private String yourAddress;
	
	private String AdrBez;
	private String AdrStr;
	private String AdrPlz;
	private String AdrOrt;
	private String AdrLand;
	
	public NutzerUpdateAction(){
		NutzerUpdateService nUServ = new NutzerUpdateService();

		setTelNummern(nUServ.getTelNummerDB());
		setMailAdr(nUServ.getEmailAdrDB());
		setAddress(nUServ.getAddressDB());
	}
	
	
	
	public String getTelData(){
		
		NutzerUpdateService nUServ = new NutzerUpdateService();
		NutzerTelefon nutTel = new NutzerTelefon();
		
		nutTel = nUServ.getTelData(getYourTelNummer());
		Map session = ActionContext.getContext().getSession();
		session.put("NutzerTelefon", nutTel);
		
		setBezeichnungTel(nutTel.getNutTelBez());
		setTelefonNr(nutTel.getNutTelNum());
		return "success";
	}

	
	public String getEMailData(){
		NutzerUpdateService nUServ = new NutzerUpdateService();
		NutzerEmail nutEma = new NutzerEmail();
	
		nutEma = nUServ.getEmailData(getYourEmailAdr());
		Map session = ActionContext.getContext().getSession();
		session.put("NutzerEMail", nutEma);
		
		setBezeichnungMail(nutEma.getNutEmaBez());
		setMailAdress(nutEma.getNutEmaAdr());
		return "success";
	}
	
	
	public String getAddressData(){
		NutzerUpdateService nUServ = new NutzerUpdateService();
		Adresse adr = new Adresse();
		
		adr = nUServ.getAddressData(getYourAddress());
		Map session = ActionContext.getContext().getSession();
		session.put("Adresse", adr);
		
		setAdrBez(adr.getAdrBez());
		setAdrLand(adr.getAdrLan());
		setAdrOrt(adr.getAdrOrt());
		setAdrPlz(adr.getAdrPlz());
		setAdrStr(adr.getAdrStr());
		
		return "success";
	}
	
	
	public String updateTel(){
		NutzerUpdateService nUServ = new NutzerUpdateService();
		NutzerTelefon nutTel = new NutzerTelefon();
		Map session = ActionContext.getContext().getSession();
		nutTel = (NutzerTelefon) session.get("NutzerTelefon");
		
		System.out.println("KEy "+nutTel.getKey()+" Telefonnr "+getTelefonNr()+" Bez "+getBezeichnungTel());
		nUServ.updateTel(nutTel.getKey(), getTelefonNr(), getBezeichnungTel());
		setTelNummern(nUServ.getTelNummerDB());
		setTelefonNr("");
		setBezeichnungTel("");
		return "success";
	}

	
	public String insertTel(){
		NutzerUpdateService nUServ = new NutzerUpdateService();
		Map session = ActionContext.getContext().getSession();
		String nutzID = (String) session.get("NutStaID");
		System.out.println("NutStaID = " +nutzID);
		nUServ.insertTel(nutzID, getTelefonNr(), getBezeichnungTel());
		setTelNummern(nUServ.getTelNummerDB());
		setTelefonNr("");
		setBezeichnungTel("");
		return "success";
	}
	
	public String deleteTel(){
		NutzerUpdateService nUServ = new NutzerUpdateService();
		NutzerTelefon nutTel = new NutzerTelefon();
		Map session = ActionContext.getContext().getSession();
		nutTel = (NutzerTelefon) session.get("NutzerTelefon");
	
		nUServ.deleteTel(nutTel.getKey(), getTelefonNr(), getBezeichnungTel());
		setTelNummern(nUServ.getTelNummerDB());
		setTelefonNr("");
		setBezeichnungTel("");
		return "success";
	}
	
	
	public String updateMail(){
		NutzerUpdateService nUServ = new NutzerUpdateService();
		NutzerEmail nutEma = new NutzerEmail();
		Map session = ActionContext.getContext().getSession();
		nutEma = (NutzerEmail) session.get("NutzerEMail");
		
		
		nUServ.updateMail(nutEma.getKey(), getMailAdress(), getBezeichnungMail());
		setMailAdr(nUServ.getEmailAdrDB());
		setMailAdress("");
		setBezeichnungMail("");
		return "success";
	}
	
	
	public String insertMail(){
		NutzerUpdateService nUServ = new NutzerUpdateService();
		NutzerEmail nutEma = new NutzerEmail();
		Map session = ActionContext.getContext().getSession();
		String nutzID = (String) session.get("NutStaID");
		
		
		nUServ.insertMail(nutzID, getMailAdress(), getBezeichnungMail());
		setMailAdr(nUServ.getEmailAdrDB());
		setMailAdress("");
		setBezeichnungMail("");
		return "success";
	}
	
	
	public String deleteMail(){
		NutzerUpdateService nUServ = new NutzerUpdateService();
		NutzerEmail nutEma = new NutzerEmail();
		Map session = ActionContext.getContext().getSession();
		nutEma = (NutzerEmail) session.get("NutzerEMail");
	
		nUServ.deleteMail(nutEma.getKey(), getMailAdress(), getBezeichnungMail());
		setMailAdr(nUServ.getEmailAdrDB());
		setMailAdress("");
		setBezeichnungMail("");
		return "success";
	}
	
	
	public String updateAdr(){
		NutzerUpdateService nUServ = new NutzerUpdateService();
		Adresse adr = new Adresse();
		Map session = ActionContext.getContext().getSession();
		adr = (Adresse) session.get("Adresse");
		nUServ.updateAdr(adr.getAdrID(), getAdrBez(), getAdrStr(), getAdrPlz(), getAdrOrt(), getAdrLand());
		setAddress(nUServ.getAddressDB());
		setAdrBez("");
		setAdrStr("");
		setAdrPlz("");
		setAdrOrt("");
		setAdrLand("");
		return "success";
	}
	
	
	public String execute(){
	
		return "success";
		
	}


	public List<String> getTelNummern() {
		return telNummern;
	}


	public void setTelNummern(List<String> telNummern) {
		this.telNummern = telNummern;
	}


	public String getYourTelNummer() {
		return yourTelNummer;
	}


	public void setYourTelNummer(String yourTelNummer) {
		this.yourTelNummer = yourTelNummer;
	}


	public List<String> getMailAdr() {
		return MailAdr;
	}


	public void setMailAdr(List<String> MailAdr) {
		this.MailAdr = MailAdr;
	}


	public String getYourEmailAdr() {
		return yourEmailAdr;
	}


	public void setYourEmailAdr(String yourEmailAdr) {
		this.yourEmailAdr = yourEmailAdr;
	}



	public String getTelefonNr() {
		return telefonNr;
	}



	public void setTelefonNr(String telefonNr) {
		this.telefonNr = telefonNr;
	}



	public String getBezeichnungTel() {
		return bezeichnungTel;
	}



	public void setBezeichnungTel(String bezeichnungTel) {
		this.bezeichnungTel = bezeichnungTel;
	}



	public String getMailAdress() {
		return MailAdress;
	}



	public void setMailAdress(String mailAdress) {
		MailAdress = mailAdress;
	}



	public String getBezeichnungMail() {
		return bezeichnungMail;
	}



	public void setBezeichnungMail(String bezeichnungMail) {
		this.bezeichnungMail = bezeichnungMail;
	}



	public List<String> getAddress() {
		return address;
	}



	public void setAddress(List<String> address) {
		this.address = address;
	}



	public String getYourAddress() {
		return yourAddress;
	}



	public void setYourAddress(String yourAddress) {
		this.yourAddress = yourAddress;
	}



	public String getAdrBez() {
		return AdrBez;
	}



	public void setAdrBez(String adrBez) {
		AdrBez = adrBez;
	}



	public String getAdrStr() {
		return AdrStr;
	}



	public void setAdrStr(String adrStr) {
		AdrStr = adrStr;
	}



	public String getAdrPlz() {
		return AdrPlz;
	}



	public void setAdrPlz(String adrPlz) {
		AdrPlz = adrPlz;
	}



	public String getAdrOrt() {
		return AdrOrt;
	}



	public void setAdrOrt(String adrOrt) {
		AdrOrt = adrOrt;
	}



	public String getAdrLand() {
		return AdrLand;
	}



	public void setAdrLand(String adrLand) {
		AdrLand = adrLand;
	}

}	


