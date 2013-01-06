<%@ include file="/init.jsp"%>

<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jquery.dualSlider.0.2.css" />
<script src="<%=request.getContextPath()%>/js/jquery.easing.1.3.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.timers-1.2.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.dualSlider.0.3.js" type="text/javascript"></script>



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



	<script type="text/javascript">
		
		
		jQuery(document).ready(function() 
		{
			var imgWidth ="<%=param_imgWidth%>";
			var imgHeight ="<%=param_imgHeight%>";
			var param_sliderWidth = "<%=param_sliderWidth%>";
			var param_sliderHeight = "<%=param_sliderHeight%>";
			var slider_pref = "<%=slider_pref%>";
			if( slider_pref== null)
			{
			  jQuery(".wrappers").css("display","none");
			  jQuery("#initText").css("display","block");
			}
			
			var stringDIV ="";
			var sText="";	
			var panelWidth = jQuery(".panel").width();	
			var diff = param_sliderWidth - imgWidth;
			if( diff > 250 )
			{
			 	jQuery(".carousel .panel").css("width",diff+"px");
			}
			jQuery(".carousel").css("width",param_sliderWidth+"px" );
			jQuery(".carousel").css("height",param_sliderHeight+"px" );
			jQuery(".carousel .backgrounds .item").css("width",param_sliderWidth+"px" );
			
			jQuery(".item img").bind('mouseover',function(){
				 jQuery(".pause", myobj).hide();
                jQuery(".play", myobj).show();
                jQuery("body").stopTime("autoScroll");
			});
			var mySlider=	jQuery(".carousel").dualSlider({
				auto:true,
				autoDelay: 6000,
				easingCarousel: "swing",
				easingDetails: "easeOutBack",
				durationCarousel: 1000,
				durationDetails: 600
			});
			
			
		});
		
		
		
	</script>
	
<%@ include file="/markupView.jsp"%>
	
<!-- <script type="application/javascript" src="http://jsonip.appspot.com/?callback=<portlet:namespace />getip"></script> -->

	