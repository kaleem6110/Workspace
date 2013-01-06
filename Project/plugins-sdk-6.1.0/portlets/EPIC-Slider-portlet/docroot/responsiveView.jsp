<%@ include file="/init.jsp"%>
<portlet:defineObjects />
<%

	ThemeDisplay themeDisplay= (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	PortletDisplay portletDisplay= themeDisplay.getPortletDisplay();
	String portletId= renderRequest.getAttribute(WebKeys.PORTLET_ID).toString();
	List<Slide> slideList = new ArrayList<Slide>();
	PortletPreferences preferences = renderRequest.getPreferences();
	String portletResource = ParamUtil.getString(request, "portletResource");

	if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
	}
	String urlArray[]=null;
	String slider_pref = preferences.getValue(AppConstants.PREFERENCE_KEY, null );
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
	}
	int size = slideList.size();
	//pageContext.setAttribute("slideList", slideList);
	String param_imgWidth = preferences.getValue("pref-imgWidth","600");
	String param_imgHeight = preferences.getValue("pref-imgHeight","250");
	
	String param_imgALT = preferences.getValue("pref-imgALT","");
	String param_imgCSS = preferences.getValue("pref-imgCSS","");

	
	String param_sliderWidth = preferences.getValue("pref-sliderWidth","850");
	String param_sliderHeight = preferences.getValue("pref-sliderHeight","250");	
	
	
	//String videoW = ""+ ( Integer.parseInt( param_imgWidth ) - 275 );
	
	//int totalWidth= Integer.parseInt( param_imgWidth )+275;
%>


<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta content="width=device-width" name="viewport">

<link type="text/css" rel="stylesheet" media="screen, projection" href="<%=renderRequest.getContextPath()%>/css/main.css" />
<link rel="stylesheet" type="text/css" href="resource://mytube-mytube.css/" />
<script src="<%=renderRequest.getContextPath()%>/js/iosLabelFix.js" type="text/javascript"></script>
<script src="<%=renderRequest.getContextPath()%>/js/mytube-mytube.js" type="text/javascript"></script>


	<!-- Enable MaxWidth Switching -->
<input type="radio" id="desktop" name="respond" checked="">
	<input type="radio" id="tablet" name="respond">
	<input type="radio" id="mobile" name="respond">
	
	<article id="slider">		
		

		<!-- MaxWidth Options		
		<div class="respond">		
			<label for="desktop" class="desktop"></label>
			<label for="tablet" class="tablet"></label>
			<label for="mobile" class="mobile"></label>		
		</div> <!-- .respond -->		
	
		<!-- Slider Setup -->
		<c:forEach var="i" begin="1" end="<%=slideList.size()%>" step="1">
				<input type="radio" id="slide<c:out value="${i}" />" name="slider" >
		</c:forEach>	
	
		<!-- The Slider -->		
		<div id="slides">		
			<div id="overflow">			
				<div class="inner">
					<% for( Slide slid : slideList){%>
					<article>
						<div class="info"><h3><%=slid.getTitle()%><</h3>  <a href="/projects/"><%=slid.getText()%>   </a></div>
						<img alt="<%=param_imgALT%>" src="<%=slid.getImgUrl()%>" class="<%=param_imgCSS%>" title="">
					</article>
				   <%}%>					
				</div> <!-- .inner -->				
			</div> <!-- #overflow -->		
		</div> <!-- #slides -->
	
	
		<!-- Controls and Active Slide Display -->	
		<div id="controls">
            <c:forEach var="i" begin="1" end="<%=slideList.size()%>" step="1">
			<label for="slide<c:out value="${i}" />" ></label>			
			</c:forEach>		
		</div> <!-- #controls -->
		
		<div id="active">
			<c:forEach var="i" begin="1" end="<%=slideList.size()%>" step="1">
				<label for="slide<c:out value="${i}" />" ></label>			
			</c:forEach>			
		</div> <!-- #active -->
	
	</article> <!-- #slider -->
		
	

