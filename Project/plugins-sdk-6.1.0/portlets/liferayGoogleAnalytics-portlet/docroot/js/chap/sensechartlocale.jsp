<%@page import="com.liferay.portal.kernel.util.LocaleUtil"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    String languageId = request.getParameter("languageId");

    if(languageId==null || languageId== ""){
            languageId = "en_GB";
    }
    Locale locale = LocaleUtil.fromLanguageId(languageId);
    String language = locale.getLanguage();  
    String senseChartLocaleFile = "sense-chart-lang-" + language + ".js";
%>
<jsp:include page="<%=senseChartLocaleFile%>"  />
