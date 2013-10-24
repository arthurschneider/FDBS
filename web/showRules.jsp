<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Anzeigen der Regeln</title>
</head>
<body>
<table cellpadding="0" cellspacing="0" width="1200" align="center">
<tr height="40" align="center">
<td>
	<h2> <font color="#58ACFA"> Regeln anzeigen</font></h2>
	</td></tr>
</table>

<table cellpadding="0" cellspacing="0" width="1200" align="center">
	<s:form action="getRulesData" >
	<tr height="40">
	</tr>
	<tr>
		<td>Name der gesuchten Regel:
		</td>
		
		<td><s:select label="Regeln"
          		headerKey="-1" headerValue="--- Select ---" list="regeln"
 				name="yourRegel" />
		</td>
		
		<td>
			<s:submit value="Laden" name="submit" />
		</td>
	</tr>
	</s:form>
</table>

<table cellpadding="0" cellspacing="0" width="1200" align="center">
<tr height="60">
</tr>
	<tr>
		<td>
			Voraussetzungen:
		</td>
		
		<td>
			Aktionen:
		</td>
	</tr>
	
	<tr>
		<td>
			<s:textarea label="Voraussetzungen"  value="%{Voraussetzungen}"  cols="50" rows="5"></s:textarea>
		</td>
		<td>
			<s:textarea label="Aktionen"  value="%{Aktionen}"  cols="50" rows="5"></s:textarea>
		</td>
	</tr>
</table>	
<table cellpadding="0" cellspacing="0" width="1200" align="center">
	<tr height="40">
	</tr>
	<tr>
		<td>
			Event-Art:
		</td>
		
		<td>
			Event-Nachricht:
		</td>
		
		<td>
			Benachrichtigung über:
		</td>
	</tr>
	
	<tr>
		<td>
			<s:textfield size="30" label="EventArt" value="%{eventArt}"></s:textfield>
		</td>
		
		<td>
			<s:textarea label="Aktionen"  value="%{nachricht}"  cols="50" rows="5"></s:textarea>
		</td>
		
		<td>
			<s:textfield size="30" label="Beanchtichtigung" value="%{eventBenach}"></s:textfield>
		</td>
	</tr>
</table>

</body>
</html>