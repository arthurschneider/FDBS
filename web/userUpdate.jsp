<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nutzer-Daten</title>
</head>
<body>

<table cellpadding="0" cellspacing="0" width="1000" align="center">
	<tr align="center">
		<td><h2>Hier können Nutzerdaten geändert oder hinzugefügt werden</h2></td>
	</tr>
	<tr bgcolor="#58ACFA"><td><br></td><td></td></tr>
</table>


<table cellpadding="0" cellspacing="0" width="1200">
		
		<tr align="left" ><td  ><h3> <font color="#58ACFA">Telefon</font></h3><br></td></tr>
		<tr>
			<td>
				<table>
				<s:form action="getTelData">
					<tr>
						<td width="120">Gespeicherte Telefonnr.:</td>
						<td width="230"><s:select 	label="Telefonnr."
          						headerKey="-1" headerValue="--- Select ---" list="telNummern"
 								name="yourTelNummer" /></td>
 						<td><s:submit value="Laden"></s:submit></td>
					</tr>

					</s:form>
				</table>
			</td>
			<td>
				<table>
					<tr>
						<td>Nummer:</td>
						<td>Bezeichnung:</td>
					</tr>
					<tr>
					
					
				<s:form >
					<td> <s:textfield label="Telefon Nr" key="telefonNr" ></s:textfield></td>
					<td> <s:textfield label="Bezeichnung" key="bezeichnungTel" ></s:textfield></td>
					<td> <s:submit value="Update" action="updateTel"></s:submit>	</td>
					<td> <s:submit value="Insert" action="insertTel"></s:submit>	</td>
					<td> <s:submit value="Delete" action="deleteTel"></s:submit>	</td>
				</s:form>
				</tr>
				</table>
			</td>
		</tr>
	
</table>

<table>
	<tr height="50">
	<td></td>
	</tr>
</table>

<table cellpadding="0" cellspacing="0" width="1200">

	<tr align="left"><td ><h3><font color="#58ACFA">E-Mail</font></h3><br></td></tr>
	
		<tr>
			<td>
			<table>
				<s:form action="getEMailData">
				<tr>
					<td width="120">Gespeicherte E-Mailadressen:</td>
					<td width="230"><s:select 	label="E-MailAdr."
          						headerKey="-1" headerValue="--- Select ---" list="MailAdr"
 								name="yourEmailAdr" /></td>
 					<td><s:submit value="Laden"></s:submit></td>
				</tr>

				</s:form>
			</table>
	
			</td>
			<td>
				<table>
					
					<tr>
						<td>E-Mailadresse:</td>
						<td>Bezeichnung:</td>
					</tr>
					<tr>
						<s:form>
							<td><s:textfield label="E-Mail" key="MailAdress" ></s:textfield></td>
							<td><s:textfield label="Bezeichnung" key="bezeichnungMail" ></s:textfield></td>
							<td> <s:submit value="Update" action="updateMail"></s:submit>	</td>
							<td> <s:submit value="Insert" action="insertMail"></s:submit>	</td>
							<td> <s:submit value="Delete" action="deleteMail"></s:submit>	</td>
						</s:form>
					</tr>
				
				</table>
			</td>
		</tr>
		
</table>

<table>
	<tr height="50">
	<td></td>
	</tr>
</table>

<table cellpadding="0" cellspacing="0" width="1200">
	<tr  align="left"><td><h3><font color="#58ACFA">Adresse</font></h3><br></td></tr>
	
		<tr>
			<td>
			<table>
				<s:form action="getAddressData">
				<tr>
					<td width="120">Gespeicherte Anschriften:</td>
					<td width="230"><s:select 	label="Adress" 
          						headerKey="-1" headerValue="--- Select ---" list="address"
 								name="yourAddress" /></td>
 					<td><s:submit value="Laden"></s:submit></td>
				</tr>
				</s:form>
			</table>
	
			</td>
			<td width="10">
				<table>
					
					<tr>
						<td>Bezeichnung:</td>
						<td>Straße:</td>
						<td>PLZ:</td>
						<td>Ort:</td>
						<td>Land:</td>
					</tr>
					<tr>
						<s:form>
							<td><s:textfield key="AdrBez" ></s:textfield></td>
							<td><s:textfield key="AdrStr" ></s:textfield></td>
							<td><s:textfield size="6" key="AdrPlz" ></s:textfield></td>
							<td><s:textfield key="AdrOrt" ></s:textfield></td>
							<td><s:textfield key="AdrLand" ></s:textfield></td>
							<td> <s:submit value="Update" action="updateAdr"></s:submit>	</td>

						</s:form>
					</tr>
				
				</table>
			</td>
		</tr>
		
</table>

<table cellpadding="0" cellspacing="0" width="1000" align="center">
	<tr height="50"></tr>
	<tr bgcolor="#58ACFA"><td><br></td><td></td></tr>
</table>
	
</body>
</html>