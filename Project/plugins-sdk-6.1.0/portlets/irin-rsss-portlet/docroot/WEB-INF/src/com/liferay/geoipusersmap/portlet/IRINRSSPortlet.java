/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.geoipusersmap.portlet;

import java.io.*;
import java.net.*;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.jsp.JSPPortlet;
import com.liferay.util.portlet.PortletProps;
import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.PortletSession;
import com.liferay.util.bridges.mvc.MVCPortlet;


import javax.portlet.PortletMode;

import javax.portlet.PortletMode;


/**
 * <a href="IRINRSSPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jose Miguel Trinchan
 *
 */

public class IRINRSSPortlet extends MVCPortlet  {

	public void init() throws PortletException {
		editJSP = getInitParameter("edit-jsp");
		helpJSP = getInitParameter("help-jsp");
		viewJSP = getInitParameter("view-jsp");
	}

	public void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
		
		String jspPage = renderRequest.getParameter("jspPage");
		System.out.println(" ################2221112  doDispatch jspPage  "+jspPage +" :"+ renderRequest.getPortletMode() );
		if(renderRequest.getPortletMode() == PortletMode.EDIT) {
			include(editJSP, renderRequest, renderResponse);
	    }
		if(renderRequest.getPortletMode() == PortletMode.VIEW) {
			include(viewJSP, renderRequest, renderResponse);
	    }
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	public void doEdit(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
		
		System.out.println(" ################2221112  Action performed ");

		if (renderRequest.getPreferences() == null) {
			super.doEdit(renderRequest, renderResponse);
		}
		else {
			include(editJSP, renderRequest, renderResponse);
		}
	}

	public void doHelp(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		include(helpJSP, renderRequest, renderResponse);
	}

	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
		System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		System.out.println(" !!!!!!!!!!!!!!!!!!!IRINRSS PORTLET  doView START !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		
		PortletSession portletSession = renderRequest.getPortletSession();		
		String userId = renderRequest.getRemoteUser();
		String actionType = null;
		Object actOnj = renderRequest.getParameter("actionType");
		System.out.println(" !!!!actOnj :"+actOnj );
		if( actOnj!= null ) { actionType = actOnj.toString(); 	System.out.println(" !!!!!"+actionType ); }
		String code=null;
		if( userId!=null && userId!="0")
		{
			Object obj  = portletSession.getAttribute("code-"+userId,  PortletSession.APPLICATION_SCOPE); //getUserCode( userId );
		
			if( obj!=null) code = obj.toString();
		
		
			System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! code : "+code+" userId : "+userId );	
			if( code== null || code=="" )
			{
				
			
			}
			
		
		}
		else //if( ( actionType!= null ) && ( actionType.equals("updateIP")|| actionType!= null && actionType.equals("changeLocation") ) )
		{
			renderRequest.setAttribute("Location",renderRequest.getParameter("Location"+userId ));
			renderRequest.setAttribute("code",renderRequest.getParameter("code-"+userId ));
			System.out.println(" 999999  !!!!!"+actionType+ " : "+ renderRequest.getParameter("Location" ) + ": "+renderRequest.getParameter("code" ) ); 
		}
			//renderRequest.getPortletSession().setAttribute("City-"+user.getUserId(),"Dubai" , PortletSession.APPLICATION_SCOPE);
		
		//include(viewJSP, renderRequest, renderResponse);
		
		super.doView(renderRequest, renderResponse);
		
		System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		System.out.println(" !!!!!!!!!!!!!!!!!!!IRINRSS PORTLET  doView END !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		
	}
	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {
		
		System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		System.out.println(" !!!!!!!!!!!!!!!!!!!IRINRSS PORTLET  processAction START !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		try
		{
			
			
		}catch(Exception e){e.printStackTrace(); }
		
		System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		System.out.println(" !!!!!!!!!!!!!!!!!!!IRINRSS PORTLET  processAction END !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
	}

	protected void include(
			String path, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws IOException, PortletException {

		PortletRequestDispatcher portletRequestDispatcher =
			getPortletContext().getRequestDispatcher(path);

		if (portletRequestDispatcher == null) {
			_log.error(path + " is not a valid include");
		}
		else {
			portletRequestDispatcher.include(renderRequest, renderResponse);
		}
	}

	protected String editJSP;
	protected String helpJSP;
	protected String viewJSP;

	private static Log _log = LogFactoryUtil.getLog(IRINRSSPortlet.class);
	
	
	

	
	 /*public  static String getUserCountry() throws Exception 
	 {
		 System.out.println(" #########~START getUserCountry() ##################" );
	        URL oracle = new URL("http://www.geoiptool.com/webapi.php");
	        URLConnection yc = oracle.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                                    yc.getInputStream()));
	        String inputLine;
	        
	        String countryName=null;
	        while ((inputLine = in.readLine()) != null) 
	        {	            
	        	if( inputLine.contains("IP:") )
	        	{
			
	        		System.out.println(inputLine.trim() );

	        	}
	        	if( inputLine.contains("imgx") )
	        	{
	        		String[] s = inputLine.split("imgx\">");
	        		System.out.println(s[1] );
	        		countryName = s[1] ;
	        	}
	        }
	        in.close();
	        System.out.println(" #########~END getUserCountry()  countryName : "+countryName+"##################" );
	        return countryName;
	        
	 }*/
	/* public  static String getUserIP() throws Exception 
	 {
		 System.out.println(" #########~START getUserIP() ##################" );
	        URL oracle = new URL("http://www.geoiptool.com/webapi.php");
	        URLConnection yc = oracle.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                                    yc.getInputStream()));
	        String inputLine;
	        
	        String ip=null;
	        while ((inputLine = in.readLine()) != null) 
	        {	            
	        	if( inputLine.contains("IP:") )
	        	{			
	        		System.out.println(inputLine.trim() );
	        		String[] s = inputLine.split("IP:");
	        		System.out.println(s[1] );
	        		ip = s[1] ;
	        		System.out.println(" getUserIP from URL : ip : "+ip);

	        	}
	        	
	        }
	        in.close();
	        System.out.println(" #########~END getUserIP() ##################" );
	        
	        return ip;
		 
		 
	        
	 }*/
	
}
