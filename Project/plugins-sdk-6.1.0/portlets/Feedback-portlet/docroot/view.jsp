<%
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
<%@ include file="/AjaxUtils.jsp"%>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <title> New Document </title>
  <meta name="Generator" content="EditPlus">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta charset="utf-8">

	
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>

  <script charset="utf-8" type="text/javascript" src="/Feedback-portlet/js/jquery.lightbox_me.js"></script>
  <link href="/Feedback-portlet/css/main.css" charset="utf-8" rel="stylesheet" media="screen" type="text/css" ></link>
   <script charset="utf-8" type="text/javascript" src="/Feedback-portlet/js/main.js"></script>


 </head>
 <body>
 <!--  <a href="#" id="try-1" class="try">Try it!</a>--> 


  <div id="feedback" style="display: none; left: 50%; margin-left: -223px; z-index: 1002; position: fixed; top: 50%; margin-top: -159px;">
                <h3 class="" id="see_id">Feedback</h3>
                <span>Please provide your valuable feeback using the form below</span>
                <div id="feedback_form">
                 <!--   <label><strong>Email : </strong> <input class="sprited"></label>-->
                    <label><strong>Feedback Type : </strong> <br/> <select id="fType" style="width:202px;margin:5px;font-size:12px;height:24px;" >
                    												<option value="-1">Select Type </option>
                    												<option value="1">Suggestion </option>
                    												<option value="2">Request </option>
                    												<option value="3">Bug/Issue </option>
                    												 </select></label>
                    <label><strong>Title : </strong> <br/> <input  id="feedbackTitle" style="width:362px;margin:5px;height:24px;font-size:12px;" > </input></label>
                    <label><strong>Description : </strong> <br/> <textarea  id="desc" style="height:80px;width:362px;margin:5px;font-size:12px;" > </textarea></label>
                    <div id="actions">
                        <a href="#" id="cancel" style="display:none;" class="close form_button orange">Cancel</a>
                        <a href="#" id="log_in" class="form_button orange" >Submit</a>
                    </div>
                </div>
                <h3 class="" id="left_out">Thank you</h3>
                <span>EPIC support team</span>
                <a href="#" class="close sprited" id="close_x">close</a>
  </div>

<%
	String userName = user.getFirstName() +" "+user.getLastName(); 
%>
<script type="text/javascript">

function <portlet:namespace />resetForm()
{
	jQuery("#feedback").css("display","none"); 
					jQuery("#fType option[value='-1']").attr("selected","selected");
					jQuery("#desc").val("");
					jQuery("#feedbackTitle").val("");

}
jQuery("#close_x").click(function(){ <portlet:namespace />resetForm(); });

jQuery("#log_in").click(function()
{
			
				var fType = jQuery("#fType option:selected").val();
				if( fType!= '-1' )
				{
					var feedbackTitle = jQuery("#feedbackTitle").val();
					if( feedbackTitle!=null&& feedbackTitle!="" )
					{
						var desc = jQuery("#desc").val();
						if( desc!=null&& desc!="" ) <portlet:namespace />sendEmail();
						else alert(" Please enter description");
					}
					else alert(" Please enter title");	
						
				}
				else alert(" Please select feedback type");
				

	});
	

function <portlet:namespace />sendEmail()
{
				var feedbackType = jQuery("#fType option:selected").text();
				var feedbackTitle  = jQuery("#feedbackTitle").val();
				var description  = jQuery("#desc").val();
				var myurl = "<br> User's Page : "+window.location;
				var browserr = "<br>Browser Name : "+navigator.appName;
				browserr += "<br>Browser Version : " +navigator.appVersion;
				browserr += "<br>Cookies Enabled : " +navigator.cookieEnabled;
				browserr += "<br>Operating System : "+navigator.platform ;
												
				var <portlet:namespace />ajax = new AjaxUtils();
	  			var parameters = {} ;	  			
	  			parameters["cmd"]= "sendFeedback";
	  			parameters["feedbackType"]= feedbackType;
	  			parameters["feedbackTitle"]= feedbackTitle;
	  			parameters["description"]= description;
	  			parameters["browserr"]= browserr;
	  			parameters["myurl"]= myurl;
	  			parameters["userName"]= "<%=userName%>";
	  			<portlet:namespace />ajax.parameters = parameters;
	  			<portlet:namespace />ajax.actionRequest("get" );
	  			
	  			<portlet:namespace />resetForm();
	  			
	  			
}
    
</script>



 </body>
</html>
