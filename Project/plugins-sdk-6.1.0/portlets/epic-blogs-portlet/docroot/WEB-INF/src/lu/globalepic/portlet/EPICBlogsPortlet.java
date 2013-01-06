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

package lu.globalepic.portlet;

import java.io.*;
import java.net.*;
import java.util.List;

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


import lu.maxmind.geoip.Location;
import lu.maxmind.geoip.LookupService;

import lu.maxmind.geoip.CountryCodes;
import lu.maxmind.geoip.LatLngCountryBean;
import lu.maxmind.geoip.LatLngfromCountryCodeBean;
import lu.maxmind.geoip.Country;
import lu.globalepic.util.MailSender;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import lu.globalepic.dao.BlogDAO;
import lu.globalepic.dao.LiferayDAO;

/**
 * <a href="EPICBlogsPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jose Miguel Trinchan
 *
 */

public class EPICBlogsPortlet extends MVCPortlet  {
	
	public void init() throws PortletException {
		editJSP = getInitParameter("edit-jsp");
		helpJSP = getInitParameter("help-jsp");
		viewJSP = getInitParameter("view-jsp");
	}

	public void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		String jspPage = renderRequest.getParameter("jspPage");

		if (jspPage != null) {
			include(jspPage, renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	public void doEdit(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
		
		_log.info(" ################2221112  Action performed ");

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
		_log.info(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		_log.info(" !!!!!!!!!!!!!!!!!!!EPICBlogsPortlet doView START !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		
		PortletSession portletSession = renderRequest.getPortletSession();		
		String userId = renderRequest.getRemoteUser();
		List<BlogDAO> daoList = null;
		String code=null;
		if( userId!=null && userId!="0")
		{
			//daoList = LiferayDAO.getAllBlogs();
			_log.info(" daoList "+ daoList );
			
		}
			//renderRequest.getPortletSession().setAttribute("City-"+user.getUserId(),"Dubai" , PortletSession.APPLICATION_SCOPE);
		
		include(viewJSP, renderRequest, renderResponse);
		
		_log.info(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		_log.info(" !!!!!!!!!!!!!!!!!!! END doView EPICBlogsPortlet   END !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		_log.info(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		
	}
	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {
		
		_log.info(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		_log.info(" !!!!!!!!!!!!!!!!!!!EPICBlogs Portlet  processAction START !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		_log.info(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		try
		{
		PortletSession portletSession = actionRequest.getPortletSession();

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);		
		
		String userId = actionRequest.getRemoteUser();
		Object sessionObj = portletSession.getAttribute("ip-"+userId,  PortletSession.APPLICATION_SCOPE );
		
		_log.info(" !!sessionObj"+sessionObj+"!!!!!!!!!!!!cmd    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! "+ cmd );

		
		
		}catch(Exception e){e.printStackTrace(); }
		
		_log.info(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		_log.info(" !!!!!!!!!!!!!!!!!!!EPICBlogs PORTLET  processAction END !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		_log.info(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
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

	private static Log _log = LogFactoryUtil.getLog(EPICBlogsPortlet.class);
	
	

	
}
