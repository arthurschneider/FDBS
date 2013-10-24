package de.aschneider.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.struts2.components.Date;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import de.aschneider.service.GetSensorService;
import de.aschneider.service.LoginService;

public class LoginAction extends ActionSupport{
	
	private String userName;
	private String password;
	
	private List<String> sensoren;
	private String yourSensor;

	private Date date;
	 
	//return today date
	public Date getTodayDate(){

		return new Date(null);
	}

	
	
	public LoginAction() {
		
		GetSensorService gsServ = new GetSensorService();
		sensoren = new ArrayList<String>();

		setSensoren(gsServ.getAlleSensor());
	}
	
	
	public String execute(){
		
		LoginService logserv = new LoginService();
		
		if (logserv.verifyName(userName)==true && logserv.verifyPass(password)==true ) {
			Map session = ActionContext.getContext().getSession();
			session.put("userName",userName); 
			return SUCCESS;
		} else {
			return LOGIN;
		}
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
