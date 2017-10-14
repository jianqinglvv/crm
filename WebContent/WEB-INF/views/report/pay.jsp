<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jq" tagdir="/WEB-INF/tags" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>客户贡献分析</title>
</head>
<body>

	<div class="page_title">
		客户贡献分析
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctp}/report/pay">
		<div id="listView" style="display:block;">
			<table class="query_form_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						客户名称
					</th>
					<td>
						<input type="text" name="search_name" />
					</td>
					<th>
						日期
					</th>
					<td>
						<input type="text" name="search_date1" size="10" />
						-
						<input type="text" name="search_date2" size="10" />
					</td>
				</tr>
			</table>
			<!-- 列表数据 -->
			<br />
			
			
				<table class="data_list_table" border="0" cellPadding="3"
					cellSpacing="0">
					<tr>
						
						<th>
							客户名称
						</th>
						<th>
							订单金额（元）
						</th>
					</tr>
					
					<c:forEach items="${page.content }" var="item">
					
						<tr>
							
							<td align="center">
								${item.name }
							</td>
							<td align="center">
								${item.orderMoney }
							</td>
		
						</tr>
					
					</c:forEach>

				</table>
				

<jq:pagination paginationSize="2" page="${page }" condition="${params }"></jq:pagination>





			
			
		</div>
	</form>
</body>
</html>