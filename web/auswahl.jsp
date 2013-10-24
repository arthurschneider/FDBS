<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Willkommen</title>
</head>
<body>
<table cellpadding="0" cellspacing="0" width="1200" align="center">
<tr>
		<td align="center" >
			<h2><font color="#58ACFA">Willkommen auf der FDBS-Seite</font></h2>
		</td>
  </tr>
  <tr height="100">
  </tr>
</table>


<table cellpadding="0" cellspacing="20" width="1200" align="center">
	
	

	
	
	
  <tr align="center">
		<td >
			<s:form action="userUpdate">
			
		<s:submit value="Nutzerstammdaten"></s:submit>
		</s:form>
		</td>

		<td >
			<s:form action="setRules">
			
		<s:submit value="Regeln erstellen"></s:submit>
		</s:form>

		</td>

		<td>
			<s:form action="showRules">
		
		<s:submit value="Regeln anzeigen"></s:submit>
		</s:form>

		</td>
	
		<td >
			<s:form action="getMandant">
			
		<s:submit value="Mandanten erstellen/betrachten"></s:submit>
		</s:form>

		</td>

		<td >
			<s:form action="getChart">
		
		<s:submit value="Charts anzeigen"></s:submit>
		</s:form>

		</td>
	</tr>
		
	<tr align="center">	
	<td >
			Anschrift, E-Mail oder <br> 
			Telefon des Nutzers ändern. 

		</td>
		<td >
				Regeln aufstellen, die besagen <br>
			 	wie Aktor(en) auf die Werte <br>
			 	des Sensor(en)  reagieren soll.  

		</td>
		<td >
			Regeln anschauen,<br>
			die zuvor vom Nutzer erstellt<br>
			worden sind.

		</td>
		<td >
			In die Daten eines<br> 
			Mandanten einsehen oder<br> 
			einen Mandanten erstellen.

		</td>
		<td >
			
			Anzeigen von Diagrammen<br> 
			zu verschiedenen Sensoren.

		</td>
	</tr>
		
	

</table>
</body>
</html>