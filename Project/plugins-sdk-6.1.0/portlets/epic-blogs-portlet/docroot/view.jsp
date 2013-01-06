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
<%@ page import="lu.globalepic.dao.BlogDAO"%>
<%@ page import="lu.globalepic.dao.LiferayDAO"%>
<%@ page import="lu.globalepic.util.BlogsWrapper"%>
<%@ page import="java.util.List"%>

<portlet:defineObjects />
<script src="http://www-dev.globalepic.lu/html/themes/classic/js/tabcontent.js" type="text/javascript"></script>
<link href="http://www-dev.globalepic.lu/html/themes/classic/css/tabcontent.css" rel="stylesheet" type="text/css" />

<div class="htabs">
	<ul class="tabs" persist="true">
		<li><a href="#" rel="view1">Most Popular</a></li>
		<li><a href="#" rel="view2">Most Recent</a></li>
		
	</ul>


<div class="tabcontents portlet-blogs-aggregator portlet-blogs">
<div class="tabcontent" id="view1">
<%

	BlogsWrapper blogWrapper =  LiferayDAO.getAllBlogs();
	List<BlogDAO> daoList = blogWrapper.getPopularBlogsList();
	int MAX_SIZE=3;
	int i=0;
if( daoList!=null )
{

   for( BlogDAO dao : daoList )
   {
		if(i<MAX_SIZE){
	   String url = "/web/apps/news/-/blogs/"+dao.getUrlTitle();  
	   %> <div class="entry-info">
	   <a href="<%=url %>" >
	   <span class="entry-title"><%=dao.getTitle()%> </span> </a>
	     <div class="authorSpan"> By <span class="entry-author" style="float:none;"><%=dao.getUserName()%> </span></div></div><br>
	<%
		}
		i++;
   }
   
}
i=0;
%>
</div>
<div class="tabcontent" id="view2">
<%

	 daoList = blogWrapper.getRecentBlogsList();
if( daoList!=null )
{

   for( BlogDAO dao : daoList )
   {
	   if(i<MAX_SIZE){
		String url = "/web/apps/news/-/blogs/"+dao.getUrlTitle();  
	   %> <div class="entry-info">
	   <a href="<%=url %>" ><span class="entry-title"><%=dao.getTitle()%> </span> </a> 
	   <div class="authorSpan">
	   		By <span class="entry-author" style="float:none;"><%=dao.getUserName()%> </span>
	   		</div>
	   		</div>		
	   		<br>
	<%
	   }
		i++;
   }
   
}

%>
</div>

</div>
</div>
