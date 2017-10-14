<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jq" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>客户流失管理</title>
</head>
<body>

	<div class="page_title">
		客户流失管理
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctp}/drain/list" method="post"> 
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					客户名称
				</th>
				<td>
					<input type="text" name="search_LIKE_customerName" />
				</td>
				<th>
					客户经理
				</th>
				<td>
					<input type="text" name="search_LIKE_managerName" />
				</td>
				<th>
					&nbsp;
				</th>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
		
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						编号
					</th>
					<th>
						客户名称
					</th>
					<th>
						客户经理
					</th>
					<th>
						上次下单时间
					</th>
					<th>
						确认流失时间
					</th>
					<th>
						流失原因
					</th>
					<th>
						状态
					</th>
					<th>
						操作
					</th>
				</tr>
				
		<c:if test="${page.totalElements==0 }">
			明月几时有，把酒问青天。
		</c:if>
		<c:if test="${page.totalElements>0 }">		
			<c:forEach items="${page.content }" var="item">
					<tr>
						<td class="list_data_number">
							${item.id }
						</td>
						<td class="list_data_ltext">
							${item.customer.name }
						</td>
						<td class="list_data_text">
							${item.customer.manager.name }
						</td>
						<td class="list_data_text">
							<fmt:formatDate value="${item.lastOrderDate }"/>
						</td>
						<td class="list_data_text">
							<fmt:formatDate value="${item.drainDate }"/>
						</td>
						<td class="list_data_ltext">
							${item.reason }
						</td>
						<td class="list_data_text">
							${item.status }
						</td>
						<td class="list_data_op">
							<c:if test="${item.status=='流失预警' }">
								<img onclick="window.location.href='${ctp}/drain/confirm/${item.id }'" 
									title="确认流失" src="${ctp}/static/images/bt_confirm.gif" class="op_button" />
								<img onclick="window.location.href='${ctp}/drain/delay/${item.id }'" 
									title="暂缓流失" src="${ctp}/static/images/bt_relay.gif" class="op_button" />
							</c:if>
							
						</td>
					</tr>
			</c:forEach>
				
		</c:if>
					
				
				
				
			</table>
			
<jq:pagetag page="${page }" params="${params }"></jq:pagetag>








		
		
	</form>
</body>
</html>