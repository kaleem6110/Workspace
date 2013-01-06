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

package com.wfp.slider.portlet;

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
import com.liferay.portal.kernel.portlet.BaseConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import javax.portlet.ResourceRequest;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.theme.PortletDisplay;
import javax.portlet.WindowState;

import com.wfp.slider.util.AppConstants;
import com.wfp.slider.service.SliderService;
import com.wfp.slider.util.Slide;
/**
 * <a href="EPICResponsiveSliderPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author MOHAMMED KALEEM
 *
 */

public class EPICResponsiveSliderPortlet extends MVCPortlet  {

	public void init() throws PortletException {
		editJSP = getInitParameter("edit-jsp");
		helpJSP = getInitParameter("help-jsp");
		viewJSP = getInitParameter("view-jsp");
	}

	public void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		String jspPage = renderRequest.getParameter("jspPage");

		/*if (jspPage != null) {
			include(jspPage, renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}*/
		_log.info(" jspPage :"+ jspPage+" : hhhh"+renderRequest.getPortletMode().toString() );
		if (renderRequest.getPortletMode().toString().equalsIgnoreCase("edit"))
		{
			
			include(editJSP, renderRequest, renderResponse);
		}
		else
		{
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	public void doEdit(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
		
		_log.info(" ################2221112  Action performed ");
			
			include(editJSP, renderRequest, renderResponse);
		
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
		_log.info(" !!!!!!!!!!!!!!!!!!!EPICResponsiveSliderPortlet  doView START !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		
		String userId = renderRequest.getRemoteUser();
		String code=null;
		PortletPreferences preferences = renderRequest.getPreferences();
		String portletResource = ParamUtil.getString(renderRequest, "portletResource");
		try{
		if (Validator.isNotNull(portletResource)) {
		preferences = PortletPreferencesFactoryUtil.getPortletSetup(renderRequest, portletResource);
		}
		}catch(Exception e){e.printStackTrace(); }
		String slider_pref = preferences.getValue(AppConstants.PREFERENCE_KEY, null );
		if( slider_pref ==null )
		{
			include(helpJSP, renderRequest, renderResponse);
		}
		else include(viewJSP, renderRequest, renderResponse);
		
		_log.info(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		_log.info(" !!!!!!!!!!!!!!!!!!!EPICResponsiveSliderPortlet  doView END !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! "+slider_pref);
		_log.info(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		
	}
	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {
		
		_log.info(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		_log.info(" !!!!!!!!!!!!!!!!!!!EPICResponsiveSliderPortlet  processAction START !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		_log.info(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
		
		System.out.println(" cmd "+ cmd);
		try
		{
			
			String userId = actionRequest.getRemoteUser();
			PortletPreferences preferences = actionRequest.getPreferences();
			ThemeDisplay themeDisplay= (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			PortletDisplay portletDisplay= themeDisplay.getPortletDisplay();
			String portletResource = ParamUtil.getString(actionRequest, AppConstants.PORTLET_RESOURCE );

			if (Validator.isNotNull(portletResource)) 
			{
				preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
			}
			if(cmd.equals( AppConstants.SAVE_DISPLAY_SETTING_PREF ))
			{	preferences.setValue("pref-imgWidth", ParamUtil.getString(actionRequest, AppConstants.IMG_WIDTH ) );	
				preferences.setValue("pref-imgHeight", ParamUtil.getString(actionRequest, AppConstants.IMG_HEIGHT ));
				preferences.setValue("pref-imgALT", ParamUtil.getString(actionRequest, AppConstants.IMG_ALT ));	
				preferences.setValue("pref-imgCSS", ParamUtil.getString(actionRequest, AppConstants.IMG_CSS ));						
				preferences.setValue("pref-sliderWidth", ParamUtil.getString(actionRequest, AppConstants.SLIDER_WIDTH ));	
				preferences.setValue("pref-sliderHeight", ParamUtil.getString(actionRequest, AppConstants.SLIDER_HEIGHT ));	
				preferences.store();
				System.out.println(" Preferences stored imgWidth ");
			}
			else if(cmd.equals("saveSliderConfig"))
			{			
				String imageURL =ParamUtil.getString(actionRequest, "imageURL");				
				preferences.setValue("pref-imageURL", imageURL);
				preferences.store();
			}
			else if(cmd.equals( AppConstants.ADD_NEW_SLIDE ))
			{	
				String sliderPreferenceValue = getPreference( actionRequest, preferences );
				preferences.setValue(AppConstants.PREFERENCE_KEY, sliderPreferenceValue);
				preferences.store();
				_log.info("Slide stored in preferences with key : "+AppConstants.PREFERENCE_KEY );
			}
			else if(cmd.equals( AppConstants.DEL_SLIDE ))
			{	String title =ParamUtil.getString(actionRequest, AppConstants.TITLE);			
				String newSliderPreferenceValue = SliderService.deleteSlide( preferences.getValue(AppConstants.PREFERENCE_KEY ,""), title.trim() );	
				if(newSliderPreferenceValue!=""&&newSliderPreferenceValue!=null)
				preferences.setValue(AppConstants.PREFERENCE_KEY, newSliderPreferenceValue);
				preferences.store();
				_log.info(title + "isDeleted  : "+newSliderPreferenceValue );
			}
			else if(cmd.equals( AppConstants.EDIT_SLIDE ))
			{	Slide slide = new Slide();
				slide.setTitle ( ParamUtil.getString(actionRequest, AppConstants.TITLE ) );
				slide.setText( ParamUtil.getString(actionRequest, AppConstants.TEXT ) );
				slide.setPrevTitle ( ParamUtil.getString(actionRequest, AppConstants.OLD_TITLE ) );
				slide.setImgUrl( ParamUtil.getString(actionRequest, AppConstants.IMG_SRC ) );
				String newPreference = SliderService.editSlide( preferences.getValue(AppConstants.PREFERENCE_KEY ,""), slide );
				if(newPreference!=""&&newPreference!=null){
					preferences.setValue(AppConstants.PREFERENCE_KEY, newPreference);
					preferences.store();
				}				
				_log.info(slide.getPrevTitle() + ": editSlide  : "+slide.getTitle() );
			}
			else if(cmd.equals( AppConstants.MOVE_SLIDE ))
			{	
				String newPreference = SliderService.moveSlide( preferences.getValue( AppConstants.PREFERENCE_KEY ,""),
																ParamUtil.getString( actionRequest, AppConstants.TITLE  ),
																ParamUtil.getString( actionRequest, AppConstants.DIRECTION ) );
				if(newPreference!=""&&newPreference!=null){
					preferences.setValue(AppConstants.PREFERENCE_KEY, newPreference);
					preferences.store();
				}				
				_log.info(AppConstants.MOVE_SLIDE + ": AppConstants.MOVE_SLIDE  : "+newPreference );
			}
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
		
		_log.info(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		_log.info(" !!!!!!!!!!!!!!!!!!!EPICResponsiveSliderPortlet  processAction END !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		_log.info(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
	}
	private String getPreference( ActionRequest actionRequest, PortletPreferences preferences)
	{
		_log.info(" !!!!!!START   EPICResponsiveSliderPortlet getPreference !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		String title =ParamUtil.getString(actionRequest, AppConstants.TITLE );
		String text =ParamUtil.getString(actionRequest, AppConstants.TEXT );
		String imgsrc =ParamUtil.getString(actionRequest, AppConstants.IMG_SRC );		
		String prefValue = preferences.getValue(AppConstants.PREFERENCE_KEY ,"");
		if(prefValue!="")
		{
			prefValue += AppConstants.SLIDE_DELIMITER ;
		}
		prefValue +=  title + AppConstants.FIELD_DELIMITER + text + AppConstants.FIELD_DELIMITER + imgsrc ;
		System.out.println( " prefValue "+ prefValue );
		
		_log.info(" !!!!!!END   EPICResponsiveSliderPortlet getPreference !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
			
		return prefValue;
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

	private static Log _log = LogFactoryUtil.getLog(EPICResponsiveSliderPortlet.class);
	

	
}
