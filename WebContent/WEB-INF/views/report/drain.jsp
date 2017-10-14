<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jq" tagdir="/WEB-INF/tags" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>客户流失分析</title>
  </head>  
  <body>
  	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">查询</button>  
	</div>
	
  	<form action="${ctp}/report/drain">
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>
					客户名称
				</th>
				<td>
					<input type="text" name="search_LIKE_customer.name" />
				</td>
				<th>
					客户经理
				</th>
				<td>
					<input type="text" name="search_LIKE_customer.manager.name" />
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
			
			<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>			
				<th>序号</th>
				<th>确认流失时间</th>			
				<th>客户</th>
				<th>客户经理</th>
				<th>流失原因</th>
			</tr>
				
					<tr>
						<td class="list_data_number">1</td>				
						<td class="list_data_text">2017-06-29</td>
						<td class="list_data_text">联想移动1</td>				
						<td class="list_data_text">杨倩</td>
						<td class="list_data_ltext">我曹还是走了</td>				
					</tr>			
				
				
			</table>
			




				

<jq:pagination paginationSize="2" page="${page }" condition="${params }"></jq:pagination>





			
			
		</div>
	</form>
</body>
</html>