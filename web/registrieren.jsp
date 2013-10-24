<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrieren</title>
</head>
<body>
<table cellpadding="0" cellspacing="0" width="1000" align="center">
	<tr align="center">
		<td><h2>Registrierung</h2></td>
	</tr>
	<tr align="center">
		<td><h3>Geben sie die erforderlichen Daten an</h3></td>
		<td></td>
	</tr>
	<tr bgcolor="#58ACFA"><td><br></td><td></td></tr>
</table>

<table cellpadding="0" cellspacing="0" width="1000" align="center">
	<s:form action="anlegen">
	<tr height="30">
		<td align="center">
			Anrede:
		</td>
		<td>
			<s:textfield  label="Anrede" key="anrede"></s:textfield>
		</td>
	</tr>
		<tr height="30" bgcolor="#E0F2F7">
		<td align="center">
			Vorname:
		</td>
		<td>
			<s:textfield  key="vorName"></s:textfield>
		</td>
	</tr>
		<tr height="30">
		<td align="center">
			Name:
		</td>
		<td>
			<s:textfield  key="name"></s:textfield>
		</td>
	</tr>
		<tr height="30" bgcolor="#E0F2F7">
		<td align="center">
			Firma:
		</td>
		<td>
			<s:textfield  label="Firma" key="firma"></s:textfield>
		</td>
	</tr>
		<tr height="30">
		<td align="center">
			Telefon:
		</td>
		<td>
			<s:textfield  label="Telefon" key="telefon"></s:textfield>
		</td>
	</tr>
		<tr height="30" bgcolor="#E0F2F7">
		<td align="center">
			Telefon Bezeichnung:
		</td>
		<td>
			<s:textfield  label="TelBez" key="telBez"></s:textfield>
		</td>
	</tr>
		<tr height="30">
		<td align="center">
			E-Mail:
		</td>
		<td>
			<s:textfield   key="emaAdr"></s:textfield>
		</td>
	</tr>
		<tr height="30" bgcolor="#E0F2F7">
		<td align="center">
			E-Mail Bezeichnung:
		</td>
		<td>
			<s:textfield  key="emaBez"></s:textfield>
		</td>
	</tr>

		<tr height="30">
		<td align="center">
			Passwort:
		</td>
		<td>
			<s:password label="Passwort" key="passwort"></s:password>
		</td>
	</tr>
	
		<tr height="30" bgcolor="#E0F2F7">
		<td align="center">
			Privater Key:
		</td>
		<td>
			<s:password  key="privKey"></s:password>
		</td>
	</tr>
	<tr>
		<td align="center">
			Public Key:
		</td>
		<td>
			<s:password  key="pubKey"></s:password>
		</td>
	</tr>
	<tr height="30" bgcolor="#E0F2F7">
		<td align="center">
			Anschrift Bezeichnung:
		</td>
		<td>
			<s:textfield key="anschrift"></s:textfield>
		</td>
	</tr>
	<tr height="30">
		<td align="center">
			Strasse & Hausnr:
		</td>
		<td>
			<s:textfield key="strasse"></s:textfield>
		</td>
	</tr>
	<tr height="30" bgcolor="#E0F2F7">
		<td align="center">
			Stadt:
		</td>
		<td>
			<s:textfield  key="stadt"></s:textfield>
		</td>
	</tr>
	<tr height="30">
		<td align="center">
			PLZ:
		</td>
		<td>
			<s:textfield  key="plz"></s:textfield>
		</td>
	</tr>
	<tr height="30" bgcolor="#E0F2F7">
		<td align="center">
			Land:
		</td>
		<td>
			<s:textfield  key="land"></s:textfield>
		</td>
	</tr>
	<tr height="50">
		<td align="right"><s:submit value="Registrieren" ></s:submit></td>
	</tr>
</s:form>
</table>
</body>
</html>