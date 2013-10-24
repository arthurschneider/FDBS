<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mandanten</title>
</head>
<body>

<table cellpadding="0" cellspacing="0" width="1200" align="center">

	<tr> 
		<td>
			<table>
				<s:form action="loadMandant">
				<tr height="50">
				</tr>
				<tr>
					<td>
						<s:select label="Mandanten"
          				headerKey="-1" headerValue="--- Select ---" list="mandanten"
 						name="yourMandant" />
 					</td>
 					<td>
 						<s:submit align="right" value="Laden" name="submit" />
 					</td>
				</tr>
				</s:form>
			</table>
		</td>
		<td>
		<table cellpadding="0" cellspacing="0" >
			<tr>
				<td>
					Mandanten einfügen !
				</td>
			</tr>
			<s:form >
			<tr>
			
				<td width="200">

					Name :
				</td>
				<td>
					<s:textfield label="Name" key="name" ></s:textfield>
				</td>
			</tr>
			<tr>
				<td>
					PublicKey :
				</td>
				<td>
					<s:textfield label="Passwort" key="pass" ></s:textfield>
				</td>
				
			</tr>
			<tr>
				<td>
					Bezeichnung des Mandanten :
				</td>
				<td >
					<s:textfield label="Bezeichnung" key="bezeichnung" ></s:textfield>
				</td>
				
				
			</tr>
			<tr>
				<td>
				</td>
				<td align="right">
					<s:submit value="Einfügen" action="insertMandant"></s:submit>
				</td>
			</tr>
			</s:form>
		</table>
		</td>
	</tr>	
	<tr>
	<td>
			<table>
			<tr height="50">
				</tr>
				<tr>
					<td>
						Anrede:
					</td>
					<td>
						Vorname:
					<td>
						Name:
					</td>
					<td>
						Firma:
					</td>
					
				</tr>
				<tr>
					<td>
						<s:textfield label="Anrede" key="anrede" ></s:textfield>
					</td>
					<td>
						<s:textfield label="Vorname" key="vorname" ></s:textfield>
					</td>
					<td>
						<s:textfield label="Name" key="nachName" ></s:textfield>
					</td>
					<td>
						<s:textfield label="Firma" key="firma" ></s:textfield>
					</td>
				</tr>
				<tr height="50">
				</tr>
				<tr>
					<td>
						Telefon:
					</td>
					<td>
						E-Mail:
					</td>
				
				</tr>
				<tr>
					<td>
						<s:textarea rows="4" cols="30" label="Telefon-Nummer" key="telnummer" ></s:textarea>
					</td>
					<td>
						<s:textarea rows="4" cols="30" label="EMail-Adresse" key="emailAdr" ></s:textarea>
					</td>
					
				</tr>
				<tr>
					
					
				</tr>
				<tr>
					
					
				</tr>
				<tr height="50">
				</tr>
				<tr>
					<td>
						Strasse:
					</td>
					<td>
						Ort:
					</td>
					<td>
						PLZ:
					</td>
					<td>
						Land:
					</td>
				</tr>
				<tr>
					<td>
						<s:textfield label="Strasse" key="strasse" ></s:textfield>
					</td>
					<td>
						<s:textfield label="Ort" key="ort" ></s:textfield>
					</td>
						<td>
						<s:textfield label="PLZ" key="plz" ></s:textfield>
					</td>
					<td>
						<s:textfield label="Land" key="land" ></s:textfield>
					</td>
				</tr>
				
			</table>
		
		</td>

		</tr>
		
		<tr>
			<td>
				<table >
				<tr height="50">
				</tr>
					<tr height="50">
						<td>
							Momentane Angabe über den Haushalt des Mitgliedes:
						</td>
		
					</tr>
					<tr>
						<td>
							Lufttemperatur:
						</td>
						<td>
							Luftfeuchte:
						</td>
						<td>
							Bewegung:
						</td>
					</tr>
					<tr>
						<td>
							<s:textfield label="Lufttemperatur" key="lufttemperatur" ></s:textfield>
						</td>
						<td>
							<s:textfield label="Luftfeuchte" key="luftfeuchte" ></s:textfield>
						</td>
						<td>
							<s:textfield label="Bewegung" key="bewegung" ></s:textfield>
						</td>
					</tr>
					<tr>
						<td>
							Tür:
						</td>
						<td>
							Steckdose:
						</td>
					</tr>
					<tr>
						<td>
							<s:textfield label="Tür" key="tuer" ></s:textfield>
						</td>
						<td>
							<s:textfield label="Steckdose" key="steckdose" ></s:textfield>
						</td>
					</tr>
				
					
					
			</table>
			</td>
		</tr>	
</table>


</body>
</html>