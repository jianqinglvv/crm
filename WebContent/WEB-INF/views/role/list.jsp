<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="jq" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>角色管理</title>
</head>

<body class="main">

	<div class="page_title">
		角色管理
	</div>
	
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${ctp}/role/'">
			新建
		</button>
	</div>
	
	<form action="role/list">

		<!-- 列表数据 -->
		<br />
		
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th class="data_title" >
						编号
					</th>
					<th class="data_title" >
						角色名
					</th>
					<th class="data_title" >
						角色描述
					</th>
					<th class="data_title">
						状态
					</th>
					<th class="data_title">
						操作
					</th>
				</tr>
				
				<c:forEach items="${page.content }" var="item">
				
					<tr>
						<td class="data_cell" style="text-align:right;padding:0 10px;">${item.id }</td>
						<td class="data_cell" style="text-align:center;">${item.name }</td>
						<td class="data_cell" style="text-align:left;">${item.description }</td>
						<td class="data_cell" style="text-align:center;">${item.enabled==true ?'有效':'无效'}</td>
						<td class="data_cell">
							<img onclick="window.location.href='assign/${item.id}'" title="分配权限" src="${ctp}/static/images/bt_linkman.gif" class="op_button" />
							<img onclick="window.location.href='delete/${item.id}'" title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" />
						</td>
					</tr>
				</c:forEach>

			</table>
			

<jq:pagination paginationSize="1" page="${page }" condition="${params }"></jq:pagination>






		
		
		
	</form>
</body>
</html>