<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sx:head />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sensorwerte Chart</title>
</head>
<body>
<s:form >
<table cellpadding="0" cellspacing="0" width="1200" align="center">
	<tr height="50">
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<tr >
	<td>Sensoren: </td>
	<td>Datum: </td>
	</tr>
	<tr>
		
			<td> 
				<s:select label="Sensoren"
          		headerKey="-1" headerValue="--- Select ---" list="sensoren" 
 				name="yourSensor"/>
 			</td>
 			<td>
 			 	<sx:datetimepicker name="date1" 
				displayFormat="dd-MMM-yyyy" value="todayDate" />
			 </td>
			<td>
				<%-- <s:submit value="Chart" name="submit" action="showChart" />--%>
			</td>
		
	</tr>
</table>



<table cellpadding="0" cellspacing="0" width="1200" align="center">
<tr height = "100">
</tr>
	<tr height="70">
		<td>
			Multiraumsensor
			<table>
				
				<tr>
					<td>
						Bewegung:
					</td>
				
					<td>
						<s:checkbox name="mrsBewegung" fieldValue="true" />
					</td>
				</tr>
				<tr>
					<td>
						Luftfeuchte:
					</td>
				
					<td>
						<s:checkbox name="mrsFeuchte" fieldValue="true" />
					</td>
				</tr>
				<tr>
					<td>
						Lufttemperatur:
					</td>
				
					<td>
						<s:checkbox name="mrsTemperatur" fieldValue="true" />
					</td>
				</tr>
				<tr>
					<td>
						VOC:
					</td>
				
					<td>
						<s:checkbox name="mrsVOC" fieldValue="true" />
					</td>
				</tr>
				<tr>
				
					<td>
						<s:submit value="Chart erstellen" action="showChartMRS" name="submit" />
					</td>
				</tr>
				
			</table>
		</td>
		<td>
			Temperatursensor und Luftfeuchtesensor
			<table>
			
			<tr><td></td><td></td></tr>
			<tr>
			
					<td>
						Lufttemperatur:
					</td>
				
					<td>
						<s:checkbox name="tlTemperatur" fieldValue="true" />
					</td>
				</tr>
				<tr>
					<td>
						Luftfeuchtigkeit:
					</td>
				
					<td>
						<s:checkbox name="tlFeuchtigkeit" fieldValue="true" />
					</td>
				</tr>
				<tr>
				
					<td>
						<s:submit value="Chart erstellen" action="showChartTempLuft" name="submit" />
					</td>
					
				</tr>
				<tr><td></td><td></td></tr>
				
				</table>
		</td>
		<td>
			ShutterContact
			<table>
		
			<tr><td></td><td></td></tr>
			<tr>
			
					<td>
				
					</td>
				
					<td>
						
				</tr>
				<tr>
					<td>
						
					</td>
				
					<td>
					
					</td>
				</tr>
				<tr>
				
					<td>
						<s:submit value="Chart erstellen" action="showChartShutCont" name="submit" />
					</td>
					
				</tr>
				<tr><td></td><td></td></tr>
		
				</table>
		</td>
		<td>
			Schaltsteckdose
			<table>
			
			<tr><td></td><td></td></tr>
			<tr>
			
					<td>
				
					</td>
				
					<td>
						
				</tr>
				<tr>
					<td>
						
					</td>
				
					<td>
					
					</td>
				</tr>
				<tr>
				
					<td>
						<s:submit value="Chart erstellen" action="showChartSteckdose" name="submit" />
					</td>
					
				</tr>
				<tr><td></td><td></td></tr>
				
				</table>
		</td>
	<%--	<td>
			Wechselrichter
			<table>
			
			<tr>
				<td>
					Eingespeiste Wirkleistung;
				</td>
				<td>
					<s:checkbox name="wirkleistung" fieldValue="true" />
				</td>
			</tr>
			<tr>
			
					<td>
						Eingespeiste Gesammtleistung;
					</td>
				
					<td>
						<s:checkbox name="gesammtleistung" fieldValue="true" />
					</td>
						
				</tr>
				<tr>
					<td>
						Strom Eingang A String 1:
					</td>
				
					<td>
						<s:checkbox name="eingAString1" fieldValue="true" />
					</td>
				</tr>
				<tr>
					<td>
						Strom Eingang A String 2:
					</td>
				
					<td>
						<s:checkbox name="eingAString2" fieldValue="true" />
					</td>
				</tr>
				<tr>
					<td>
						Strom Eingang A String 3:
					</td>
				
					<td>
						<s:checkbox name="eingAString3" fieldValue="true" />
					</td>
				</tr>
				<tr>
					<td>
						Spannung Eingang A :
					</td>
				
					<td>
						<s:checkbox name="spannungEingA" fieldValue="true" />
					</td>
				</tr>
				<tr>
					<td>
						Strom Eingang B :
					</td>
				
					<td>
						<s:checkbox name="stromEingB" fieldValue="true" />
					</td>
				</tr>
				<tr>
					<td>
						Spannung Eingang B:
					</td>
				
					<td>
						<s:checkbox name="spannungEingB" fieldValue="true" />
					</td>
				</tr>
				<tr>
				
					<td>
						<s:submit value="Chart erstellen" name="submit" />
					</td>
					
				</tr>
				<tr><td></td><td></td></tr>
				
				</table>
		</td> --%>
		<td>
			Bewegungsmelder
			<table>
			
			<tr>
				
					<td>
						<s:submit value="Chart erstellen"  action="showChartBewegungsmelder" name="submit" />
					</td>
					
				</tr>
				<tr><td></td><td></td></tr>
				
				</table>
		</td>
	
	</tr>
</table>
</s:form>
</body>
</html>