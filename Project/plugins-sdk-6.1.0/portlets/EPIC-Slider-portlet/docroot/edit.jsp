
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt" %>

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="javax.portlet.RenderRequest" %>
<%@ page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@ page import="com.liferay.portal.theme.PortletDisplay"%>
<%@ page import="com.liferay.portal.kernel.util.WebKeys"%> 
<%@ page import="javax.portlet.WindowState"%>
<%@ page import="com.wfp.slider.util.AppConstants"%>
<%@ page import="com.wfp.slider.util.Slide"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<%@page import="com.liferay.portal.kernel.util.HttpUtil"%>
<portlet:defineObjects />
<script src="<%=renderRequest.getContextPath()%>/js/jquery-1.7.1.min.js" type="text/javascript"></script>



<script src="/html/themes/classic/js/tabcontent.js" type="text/javascript"></script>

<link href="/html/themes/classic/css/tabcontent.css" rel="stylesheet" type="text/css" />

<%@ include file="/AjaxUtils.jsp" %>

<%

	ThemeDisplay themeDisplay= (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	PortletDisplay portletDisplay= themeDisplay.getPortletDisplay();
	String portletId= renderRequest.getAttribute(WebKeys.PORTLET_ID).toString();
	long plid = themeDisplay.getPlid();
	String doAsUserId = String.valueOf(themeDisplay.getUserId());
	String doAsGroupId = String.valueOf(themeDisplay.getScopeGroupId());
	
String connectorURL = themeDisplay.getURLPortal()
+ "/html/js/editor/ckeditor/editor/filemanager/browser/liferay/browser.html?Connector=";
String resourceSelectorParam = "/c/portal/fckeditor?p_l_id=" + plid 
+ "&p_p_id=" + HttpUtil.encodeURL(portletId)
+ "&userId=" + HttpUtil.encodeURL(doAsUserId)
+ "&doAsGroupId=" + HttpUtil.encodeURL(doAsGroupId);

connectorURL += HttpUtil.encodeURL(resourceSelectorParam);

	String slideImage = "";	
	List<Slide> slideList = new ArrayList<Slide>();

	PortletPreferences preferences = renderRequest.getPreferences();
	
	String portletResource = ParamUtil.getString(request, "portletResource");
	
	if (Validator.isNotNull(portletResource)) {
		preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
	}
	int urlArrayLength =0;
	String slider_pref = preferences.getValue(AppConstants.PREFERENCE_KEY, null );
	String urlArray[] = null;
	if( slider_pref !=null )
	{
		String slideArray[] = slider_pref.split( AppConstants.SLIDE_DELIMITER );
		for(int i=0;i<slideArray.length;i++)
		{
			Slide slide = new Slide();
			String tempp = slideArray[i];
			urlArray = tempp.split( AppConstants.FIELD_DELIMITER  );
			if(urlArray.length>2){
			slide.setTitle( urlArray [0] );
			slide.setText( urlArray [1] );
			slide.setImgUrl( urlArray [2] );
			slideList.add( slide );
			System.out.println( "row :"+ urlArray [0]   );}
		}
		urlArrayLength =slideArray.length;
	}

	String param_URL = preferences.getValue("pref-imageURL","");
	String param_imgWidth = preferences.getValue("pref-imgWidth","600");
	String param_imgHeight = preferences.getValue("pref-imgHeight","250");
	String param_imgALT = preferences.getValue("pref-imgALT","This is alt text");
	String param_imgCSS = preferences.getValue("pref-imgCSS","dummyclass");
	
	String param_sliderWidth = preferences.getValue("pref-sliderWidth","850");
	String param_sliderHeight = preferences.getValue("pref-sliderHeight","250");
	
	System.out.println(" param_imgWidth "+ param_imgWidth );
	
	
	
	
	
	System.out.println( " urlArray : " +urlArray );
%>

<div class="htabs">
	<ul class="tabs" persist="true">
		<li><a href="#" title="view1">Slider Configuration</a></li>
		<li><a href="#" title="view2">Display Settings</a></li>
		
</ul>


<div class="tabcontents portlet-blogs-aggregator portlet-blogs">
<%@ include file="/styles.jsp" %>
<%@ include file="/sliderConfiguration.jsp" %> 
<%@ include file="/displaySetting.jsp" %>


</div>

<%@ include file="/scripts.jsp" %>
