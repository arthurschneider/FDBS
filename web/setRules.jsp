<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EventAkt aufstellen</title>
</head>
<body>

<table cellpadding="0" cellspacing="0" width="1000" align="center">
	<tr align="center">
		<td><h2>Regeln aufstellen zu den Sensoren und Aktoren</h2></td>
	</tr>
	<tr bgcolor="#58ACFA"><td><br></td><td></td></tr>
</table>


<table cellpadding="0" cellspacing="0" width="1000" align="center">
<s:form action="setRulesSensor" >
	<tr height="100" ></tr>
	<tr align="left">
		<td> Sensor: </td>
		<td> Messwerttyp: </td>
		<td> Operator: </td>
		<td> Schwellenwert: </td>
	</tr>
	
	<tr align="left">
		<td><s:select label="Sensoren"
          		headerKey="-1" headerValue="--- Select ---" list="sensoren"
 				name="yourSensor" /></td>
 		
 		<td><s:select label="Messwerte"
          		headerKey="-1" headerValue="--- Select ---" list="messwerte"
 				name="yourMesswert" /></td>
 		
 		<td><s:select label="Operator"
          		headerKey="-1" headerValue="--- Select ---" list="operatoren"
 				name="yourOperator" /></td>
 				
 		<td><s:textfield  label="Schwelenwert" key="schwwert"></s:textfield></td>
		<td width="20"><s:submit value="Hinzufügen" name="submit" /></td>
	</tr>
	
	</s:form>
	</table>
	
	<table cellpadding="0" cellspacing="0" width="1000" align="center">
	<tr>
	<td> Voraussetzungen:</td>
	</tr>
	
	<tr align="left">
	<td> <s:textarea label="Voraussetzungen" property="massage"  value="%{Regeln}"  cols="111" rows="5"></s:textarea></td>
	
	</tr>
	
</table>





<table cellpadding="0" cellspacing="0" width="1000" align="center">
<s:form action="setRulesAktor" >
	<tr align="left">
		<td> Aktor: </td>
		<td> Funktion: </td>
		<td> Funktionswert: </td>
		
		
	</tr>
	
	<tr align="left">
		<td><s:select label="Sensoren"
          		headerKey="-1" headerValue="--- Select ---" list="aktoren"
 				name="yourAktor" /></td>
 		
 		<td><s:select label="Messwerte"
          		headerKey="-1" headerValue="--- Select ---" list="funktionen"
 				name="yourFunktion" /></td>
 		
		<td><s:textfield  label="Funktionswert" key="wert"></s:textfield></td>
 				
		<td width="20"><s:submit align="right" value="Hinzufügen" name="submit" /></td>
		
	
	</tr>
	</s:form>
	</table>
	
<table cellpadding="0" cellspacing="0" width="1000" align="center">
	<tr>
	<td> Aktivitäten:</td>
	</tr>
	
	<tr align="left">
	<td> <s:textarea  value="%{Aktionen}"  cols="111" rows="5"></s:textarea></td>
	</tr>

</table>


<s:form action="setRulesInDB" > 
<table cellpadding="0" cellspacing="0" width="1000" align="center">
	<tr height="50" align="center"> 
		<tr><td  align="left">Name für diese Regel:</td></tr>
		<tr><td align="left"><s:textfield label="Name der Regel" key="nameRegel"></s:textfield></td></tr>
	</tr>

	
	<tr>
		<td> Nachricht:</td>
		<td> Nachricht an (SMS/E-Mail):</td>
	</tr>
	
	
	<tr >
		<td><s:textarea name="nachricht"  cols="50" rows="5"></s:textarea></td>
		<td>	
			<table>
				<tr>
					<s:select 	label="Art der Benachrichtigung"
          						headerKey="-1" headerValue="--- Select ---" list="artNachrichten"
 								name="yourartNachricht" />
				</tr>
			
				<tr>
					<s:textfield size="30" label="Empfänger" key="empfaenger"></s:textfield>
				</tr>
				<tr>
					<s:submit value="Speichern" name="submit" />
				</tr>
			</table>
	</td>
	</tr>
	
</table>
	
</s:form>
</body>
</html>