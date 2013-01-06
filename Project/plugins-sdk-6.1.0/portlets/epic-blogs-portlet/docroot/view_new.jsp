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
<%@ page import="java.util.List"%>

<portlet:defineObjects />
<script src="http://www-dev.globalepic.lu/html/themes/classic/js/tabcontent.js" type="text/javascript"></script>
<link href="http://www-dev.globalepic.lu/html/themes/classic/css/tabcontent.css" rel="stylesheet" type="text/css" />

<div class="htabs">
	<ul class="tabs" persist="true">
		<li><a href="#" rel="view1">Most Popular</a></li>
		<li><a href="#" rel="view2">Most Recent</a></li>
		<li><a href="#" rel="view3">Most Rated</a></li>
		<li><a href="#" rel="view4">Most Commented</a></li>
	</ul>


<div class="tabcontents portlet-blogs-aggregator portlet-blogs">
<div class="tabcontent" id="view1">
<%

	List<BlogDAO> daoList = LiferayDAO.getAllBlogs(true);
if( daoList!=null )
{

   for( BlogDAO dao : daoList )
   {
		String url = "/web/apps/news/-/blogs/"+dao.getUrlTitle();  
	   %> <div class="entry-info"><a href="<%=url %>" ><span class="entry-title"><%=dao.getTitle()%> </span> </a> By <span class="entry-author" style="float:none;"><%=dao.getUserName()%> </span></div><br>
	<%
   }
   
}

%>
</div>
<div class="tabcontent" id="view2">
<%

	 daoList = LiferayDAO.getAllBlogs(false);
if( daoList!=null )
{

   for( BlogDAO dao : daoList )
   {
		String url = "/web/apps/news/-/blogs/"+dao.getUrlTitle();  
	   %> <div class="entry-info"><a href="<%=url %>" ><span class="entry-title"><%=dao.getTitle()%> </span> </a> By <span class="entry-author" style="float:none;"><%=dao.getUserName()%> </span></div><br>
	<%
   }
   
}

%>
</div>

<div class="tabcontent" id="view3">
<%

	 
if( daoList!=null )
{

   for( BlogDAO dao : daoList )
   {
		String url = "/web/apps/news/-/blogs/"+dao.getUrlTitle();  
	   %> <div class="entry-info"><a href="<%=url %>" ><span class="entry-title"><%=dao.getTitle()%> </span> </a> By <span class="entry-author" style="float:none;"><%=dao.getUserName()%> </span></div><br>
	<%
   }
   
}

%>
</div>
<div class="tabcontent" id="view4">
<%

	 
if( daoList!=null )
{

   for( BlogDAO dao : daoList )
   {
		String url = "/web/apps/news/-/blogs/"+dao.getUrlTitle();  
	   %> <div class="entry-info"><a href="<%=url %>" ><span class="entry-title"><%=dao.getTitle()%> </span> </a> By <span class="entry-author" style="float:none;"><%=dao.getUserName()%> </span></div><br>
	<%
   }
   
}

%>
</div>
</div>
</div>
