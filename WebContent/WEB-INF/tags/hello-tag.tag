<%@tag import="java.util.Date"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute  name="count" required="false" rtexprvalue="true" type="java.lang.Long" description="必须为正整数" %>

<% 
	if(count!=null){
	for(int i=0;i<count;i++){
	%>
	-----<%=new Date() %><br>
	<%	
	}
}

	%>