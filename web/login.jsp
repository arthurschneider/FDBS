<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

<table cellpadding="0" cellspacing="0" width="1200">
	<tr>
		<td width="500" height="300" align="center">  </td>
		<td> <img alt="FDBS-Cloud" src="picture/SensorCloud+Logo+Black.svg"></td>
	</tr>
	
	<tr>
	<td width="500">

		
	</td>
	<td>
		<table>
			
			<s:form action="login">
				<tr><td width="300" align="center"> Name:</td> <td><s:textfield  label="Name" key="userName"></s:textfield></td> </tr>
				<tr><td width="300" align="center">Passwort:</td> <td><s:password label="Passwort" key="password"></s:password></td> </tr>
				<tr><td width="300"></td> <td align="Right"><s:submit value="Einlogen" ></s:submit></td> </tr>
			</s:form>
				<tr height="60">	<s:form action="registrieren">
		<td align="center">Hier können sie sich registrieren:</td>
		<td align="right"><s:submit value="Registrieren" ></s:submit></td>
		</s:form>
		</tr>
		</table>
	</td>
	</tr>
</table>
</body>
</html>