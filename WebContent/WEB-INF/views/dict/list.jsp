<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jq" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var url = $(this).attr("url");
			
			var flag = confirm("确定删除？");
			
			if(flag){
					window.location.href=url;
			}
			
			return false;
		});
	});
</script>
	<title>管理</title>
</head>
<body>
	<div class="page_title">
		基础数据管理
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${ctp}/dict/'">
			新建
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctp}/dict/list" method="POST">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					类别
				</th>
				<td>
					<input type="text" name="search_LIKE_type" />
				</td>
				<th>
					条目
				</th>
				<td>
					<input type="text" name="search_LIKE_item" />
				</td>
				<th>
					值
				</th>
				<td>
					<input type="text" name="search_LIKE_value" />
				</td>
			</tr>
		</table>
	</form>
	<!-- 列表数据 -->
	<br />
	
	
		<table class="data_list_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>
				<th>
					类别
				</th>
				<th>
					条目
				</th>
				<th>
					值
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
					<td class="list_data_text">
						${item.type }
					</td>
					<td class="list_data_text">
						${item.item }
					</td>
					<td class="list_data_text">
						${item.value }
					</td>
					<td class="list_data_op">
						<c:if test="${item.editable }">
						<img onclick="window.location.href='${ctp}/dict/${item.id }'" 
								title="编辑" src="${ctp }/static/images/bt_edit.gif" class="op_button" />
							<img url="${ctp}/dict/delete?id=${item.id }" 
								title="删除" src="${ctp }/static/images/bt_del.gif" class="op_button delete" />
						</c:if>
					</td>
				</tr>
				
			</c:forEach>
						
		</table>
		
<jq:pagination paginationSize="1" page="${page }" condition="${params }"></jq:pagination>
	
	
	
</body>
</html>