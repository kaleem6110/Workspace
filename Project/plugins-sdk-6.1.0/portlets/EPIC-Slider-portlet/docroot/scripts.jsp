
<script type="text/javascript">

function <portlet:namespace />appendSlide()
{	
	 

   jQuery("div#configSlider").css("display","none");
   jQuery("#addTitle").val("");
   jQuery("#addText").val("");
   jQuery("#imagePreview").attr("src","");
   jQuery("#tab2_1_statusMessage").css("display","none");
   jQuery("#tab2_2_statusMessage").css("display","none");
   jQuery("#updateSlideButton").css("display","none");
    jQuery("#saveSlideButton").css("display","inline");
    jQuery("#backButton").css("display","inline");
}
function <portlet:namespace />goBack()
{
jQuery("form#<portlet:namespace />fm2").submit();

}
function <portlet:namespace />validateDisplaySettings()
{
				var imgWidth  = jQuery("#imgWidth").val();
				var imgHeight  = jQuery("#imgHeight").val();
				var imgALT  = jQuery("#imgALT").val();
				var imgCSS  = jQuery("#imgCSS").val();
				var sliderWidth  = jQuery("#sliderWidth").val();
				var sliderHeight  = jQuery("#sliderHeight").val();
				
				if( imgWidth!="" && imgHeight!="" )<portlet:namespace />saveDisplaySettingPreferences();
				else alert("Please enter mandatory fields* ");


}
function <portlet:namespace />saveDisplaySettingPreferences()
{
				
				var imgWidth  = jQuery("#imgWidth").val();
				var imgHeight  = jQuery("#imgHeight").val();
				var imgALT  = jQuery("#imgALT").val();
				var imgCSS  = jQuery("#imgCSS").val();
				var sliderWidth  = jQuery("#sliderWidth").val();
				var sliderHeight  = jQuery("#sliderHeight").val();
												
				var <portlet:namespace />ajax = new AjaxUtils();
	  			var parameters = {} ;	  			
	  			parameters["cmd"]= "saveDisplaySettingPreferences";
	  			parameters["imgWidth"]= imgWidth;
	  			parameters["imgHeight"]= imgHeight;
	  			parameters["imgALT"]= imgALT;
	  			parameters["imgCSS"]= imgCSS;
	  			parameters["sliderWidth"]= sliderWidth;
	  			parameters["sliderHeight"]= sliderHeight;
	  			<portlet:namespace />ajax.parameters = parameters;
	  			<portlet:namespace />ajax.actionRequest("post" );
	  			
	  			jQuery("#tab1_statusMessage").css("display","block");
	  			
	  			
}
    

	var CKEDITOR={
			 tools : {
				callFunction: function(funcNum, fileUrl){
					$('#imagePreview').attr('src' ,fileUrl);
					$('#<portlet:namespace/>slideImage').val(fileUrl);
				}
			}
	};
	function selectImage(){
			window.open('<%=connectorURL%>',"mywindow","scroll=1,status=1,menubar=1,width=700,height=550");
	}	
	function <portlet:namespace />saveNewSlide(title,text,imgsrc )
	{
				var <portlet:namespace />ajax = new AjaxUtils();
	  			var parameters = {} ;	  			
	  			parameters["cmd"]= "addNewSlide";
	  			parameters["title"]= title;
	  			parameters["text"]= text;
	  			parameters["imgsrc"]= imgsrc;
	  			<portlet:namespace />ajax.parameters = parameters;
	  			<portlet:namespace />ajax.actionRequest("post" );
	  			jQuery("#tab2_2_statusMessage").css("display","block");
	  			setTimeout(function(){jQuery("form#<portlet:namespace />fm2").submit();}, 4000);
	  			
	
	}
	function <portlet:namespace />validateNewSlide()
	{
				var newTitle  = jQuery("#addTitle").val();
				var newText  = jQuery("#addText").val();
				var newImg = jQuery("#imagePreview").attr("src");
				
				if(	newTitle!="" &&  newText!==""  )
				{
				  if(newImg=="") alert("Please select image");
				  else <portlet:namespace />saveNewSlide(newTitle, newText, newImg);
				}else
				{
				 alert('Please enter valid Title and Text');
				}		
	}
	function <portlet:namespace />validateEditSlide()
	{
				var newTitle  = jQuery("#addTitle").val();
				var newText  = jQuery("#addText").val();
				var newImg = jQuery("#imagePreview").attr("src");
				
				if(	newTitle!="" &&  newText!==""  )
				{
				  if(newImg=="") alert("Please select image");
				  else <portlet:namespace />editSlideAJAX(newTitle, newText, newImg);
				}else
				{
				 alert('Please enter valid Title and Text');
				}		
	}
	function <portlet:namespace />editSlideAJAX(title,text,imgsrc )
	{
				var <portlet:namespace />ajax = new AjaxUtils();
				var oldTitle = jQuery("#cmd").val();
	  			var parameters = {} ;	  			
	  			parameters["cmd"]= "editSlide";
	  			parameters["title"]= title;
	  			parameters["oldTitle"]= oldTitle;
	  			parameters["text"]= text;
	  			parameters["imgsrc"]= imgsrc;
	  			<portlet:namespace />ajax.parameters = parameters;
	  			<portlet:namespace />ajax.actionRequest("post" );
	  			jQuery("#tab2_2_statusMessage").css("display","block");
	  			setTimeout(function(){jQuery("form#<portlet:namespace />fm2").submit();}, 2000);
	  			
	
	}
	function <portlet:namespace />editSlide(slideString)
	{
	   var slideArray =slideString.split(":DELIM:");
	   jQuery("#saveSlideButton").css("display","none");
	   jQuery("#updateSlideButton").css("display","block");
	   jQuery("#cmd").val(slideArray[0]+"" );
	  
	   jQuery("#addTitle").val(slideArray[0]+"" );
	   jQuery("#addText").val(slideArray[1]+"" );	 
	   jQuery('#imagePreview').attr('src' ,slideArray[2]+"");
	   jQuery("#tab2_2_statusMessage").css("display","block");
	}
	function <portlet:namespace />deleteSlide(title)
	{	
		var <portlet:namespace />ajax = new AjaxUtils();
	  	var parameters = {} ;	  			
	  	parameters["cmd"]= "deleteSlide";
	  	parameters["title"]= title;
	  	<portlet:namespace />ajax.parameters = parameters;
	  	<portlet:namespace />ajax.actionRequest("post" );
	  	
		jQuery("#tab2_1_statusMessage").css("display","block");
		setTimeout(function(){jQuery("form#<portlet:namespace />fm2").submit();}, 2000);
	}
	function <portlet:namespace />moveSlide(title, direction )
	{
		var <portlet:namespace />ajax = new AjaxUtils();
	  	var parameters = {} ;	  			
	  	parameters["cmd"]= "moveSlide";
	  	parameters["title"]= title;
	  	parameters["direction"]= direction;
	  	<portlet:namespace />ajax.parameters = parameters;
	  	<portlet:namespace />ajax.actionRequest("post" );
	  	setTimeout(function(){jQuery("form#<portlet:namespace />fm2").submit();}, 1000);
	}
</script>
