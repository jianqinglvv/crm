<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jq" tagdir="/WEB-INF/tags" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>客户服务分析</title>
  <body>
	<div class="page_title">客户服务分析</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">查询</button>  
	</div>
  	<form action="${ctp}/report/service">
		<div id="listView" style="display:block;">
			<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th>
						日期
					</th>
					<td>
						<input type="text" name="search_GTE_createDate" size="10" />
						-
						<input type="text" name="search_LTE_createDate" size="10" />
					</td>
					<th>&nbsp;</th>
					<td>&nbsp;</td>
				</tr>
			</table>
			<!-- 列表数据 -->
			<br />
			
			
				<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th>序号</th>
					<th>条目</th>
					<th>客户数量</th>
				</tr>
					
						<tr>
							<td class="list_data_number">1</td>
							<td class="list_data_ltext">合作伙伴</td>
							<td class="list_data_number">1</td>
						</tr>			
					
								
					
				</table>
			

<jq:pagination paginationSize="2" page="${page }" condition="${params }"></jq:pagination>





			
			
		</div>
	</form>
</body>
</html>