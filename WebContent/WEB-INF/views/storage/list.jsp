<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jq" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>库存查询</title>
</head>
<body>
	<div class="page_title">
		库存管理
	</div>
	<div class="button_bar">
		<button class="common_button"
			onclick="window.location.href='${ctp}/storage/create'">
			库存添加
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctp}/storage/list" method="POST">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					产品
				</th>
				<td>
					<input type="text" name="search_LIKE_product.name" />
				</td>
				<th>
					仓库
				</th>
				<td>
					<input type="text" name="search_LIKE_wareHouse" />
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
					产品
				</th>
				<th>
					仓库
				</th>
				<th>
					货位
				</th>
				<th>
					件数
				</th>
				<th>
					备注
				</th>
				<th>
					操作
				</th>
			</tr>
			<c:forEach items="${page.content }" var="item">
			
				<tr>
					<td class="list_data_number">
						${item.id }
					</td>
					<td class="list_data_ltext">
						${item.product.name }
					</td>
					<td class="list_data_ltext">
						${item.wareHouse }
					</td>
					<td class="list_data_text">
						${item.stockWare }
					</td>

					<td class="list_data_number">
						${item.stockCount }
					</td>
					<td class="list_data_ltext">
						${item.memo }
					</td>
					<td class="list_data_op">
						<img onclick="window.location.href='${ctp}/storage/create?id=1'" 
							title="修改" src="${ctp}/static/images/bt_edit.gif" class="op_button" />
						<img onclick="window.location.href='${ctp}/storage/delete?id=1'" 
							title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" />

					</td>
				</tr>
			</c:forEach>
			
				
			
		</table>
		<!-- 
		 -->
		 <jq:pagination paginationSize="3" page="${page }"></jq:pagination>
		
	</form>
</body>
</html>