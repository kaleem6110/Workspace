<%
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
%>

<%@ include file="/init.jsp" %>
<%@ page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@ page import="com.liferay.portal.model.User"%>

<%@ page import="com.liferay.portal.kernel.util.WebKeys"%>


<form name="<portlet:namespace />fm" target="_blank" onSubmit="submitForm(document.<portlet:namespace />fm, 'http://www.weather.com/search/search', false); return false;">

<table class="lfr-table">

<%




String userId = new Long( user.getUserId() ).toString();

int size= zips.length;
String[] newZips = new String [size+1];

String wcode = WeatherUtil.getUserLocation( userId );

if( wcode!= null&& wcode!=""  )
{
	String city = WeatherUtil.getCapitalServiceByCode( wcode );
	if( city!= null && city!="" )
	{
			for( int i=0;i<=size; i++)
			{
					if( i==0)
					{				
						System.out.println( " wcode : "+ wcode );					
						newZips[i]=city;
						System.out.println( " newZips[i] : "+ newZips[i]);
					}
					else
					{
						
						newZips[i] = zips[i-1];
					}
			}

		zips = newZips;
	}
}


for (String zip : zips)
{
	Weather weather = WeatherUtil.getWeather(zip);

	System.out.println(" zip : "+zip);
	if (weather != null) {
%>

		<tr>
			<td>
				<a href="http://www.weather.com/search/search?where=<%= weather.getZip() %>" style="font-size: xx-small; font-weight: bold;" target="_blank"><%= weather.getZip() %></a>
			</td>
			<td align="right">
				<span style="font-size: xx-small;">

				<c:if test="<%= fahrenheit %>">
					<%= weather.getCurrentTemp() %> &deg;F
				</c:if>

				<c:if test="<%= !fahrenheit %>">
					<%= Math.round((.5555555555 * (weather.getCurrentTemp() + 459.67)) - 273.15) %> &deg;C
				</c:if>

				</span>
			</td>
			<td align="right">
				<img alt="" src="<%= weather.getIconURL() %>" />
			</td>
		</tr>

<%
	}
}
%>

</table>

<br />

<liferay-ui:message key="city-or-zip-code" />

<input name="where" size="23" type="text" />

<input type="submit" value="<liferay-ui:message key="search" />" />

</form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<aui:script>
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.where);
	</aui:script>
</c:if>