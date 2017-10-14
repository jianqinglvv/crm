<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jq" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>客户基本信息管理</title>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			
			var url = $(this).attr("url");
			var param = {'time':new Date()};
			var name = $(this).prev(":hidden").val();
			var flag = confirm("确定删除"+name+"吗？");
			
			var img = $(this);
			var td = $(this).parent();
			
			if(flag){
				$.post(url,param,function(data){
					if(data=='1'){
						td.prev("td").text("潮吹");
						img.remove();
					}else{
						alert("删除失败");
					}
				});
			}
			return false;
		});
	});
</script>
</head>
<body>

	<div class="page_title">客户基本信息管理</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">查询</button>
	</div>
	
	<form action="${ctp}/customer/list" method="POST">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>客户名称</th>
				<td>
					<input type="text" name="search_LIKE_name"/>
				</td>
				<th>地区</th>
				<td>
					<select name="search_EQ_region" >
							<option value="">全部</option>
						<c:forEach items="${regions }" var="item">
							<option value="${item }">${item }</option>	
						</c:forEach>
					</select>
					<!-- 
					<select name="search_EQ_region">
						<option value="">全部</option>
						
							<option value="北京">北京</option>
						
							<option value="上海">上海</option>
						
							<option value="广州">广州</option>
						
							<option value="深圳">深圳</option>
						
							<option value="香港">香港</option>
						
							<option value="辽宁">辽宁</option>
						
					</select>
					 -->
				</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>客户经理</th>
				<td><input type="text" name="search_LIKE_managerName" /></td>
				
				<th>客户等级</th>
				<td>
			
					<select name="search_EQ_level">
							<option value="">全部</option>
						<c:forEach items="${levels }" var="item">
							<option value="${item }">${item }</option>	
						</c:forEach>
					</select>
					<!-- 
					<select name="search_EQ_level">
						<option value="">全部</option>
						
						
							<option value="普通客户">普通客户</option>
						
							<option value="大客户">大客户</option>
						
							<option value="重点开发客户">重点开发客户</option>
						
							<option value="合作伙伴">合作伙伴</option>
						
							<option value="战略合作伙伴">战略合作伙伴</option>
						
					</select>
					 -->
				</td>
				
				<th>状态</th>
				<td>
					
					<select name="search_EQ_state">
						<option value="">全部</option>
						<option value="流失预警">流失预警</option>
						<option value="正常">正常</option>
						<option value="流失">流失</option>
						<option value="删除">删除</option>					
					</select>
				</td>
			</tr>
		</table>
		
		<!-- 列表数据 -->
		<br />
		
		<c:if test="${page.content!=null }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>客户编号</th>
					<th>客户名称</th>
					<th>地区</th>
					<th>客户经理</th>
					<th>客户等级</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				
		
				<c:forEach items="${page.content }" var="item">
					<tr>
						<td class="list_data_text">${item.no }&nbsp;</td>
						<td class="list_data_ltext">${item.name }&nbsp;</td>
						<td class="list_data_text">${item.region }&nbsp;</td>
						<td class="list_data_text">${item.manager.name }&nbsp;</td>
						<td class="list_data_text">${item.level }&nbsp;</td>
						<td class="list_data_text">${item.state }&nbsp;</td>
						<td class="list_data_op" nowrap="nowrap">
							<img onclick="window.location.href='${ctp}/customer/${item.id }'"
								title="编辑" src="${ctp}/static/images/bt_edit.gif" class="op_button" alt="" /> 
							<img onclick="window.location.href='${ctp}/contact/list/${item.id }'"
								title="联系人" src="${ctp}/static/images/bt_linkman.gif" class="op_button" alt="联系人信息" /> 
							<img onclick="window.location.href='${ctp}/activity/list/${item.id }'"
								title="交往记录" src="${ctp}/static/images/bt_acti.gif" class="op_button" alt="交往记录" /> 
							<img onclick="window.location.href='${ctp}/order/list/${item.id }'"
								title="历史订单" src="${ctp}/static/images/bt_orders.gif" class="op_button" alt="历史订单" /> 
								<c:if test="${item.state != '删除' }">
									<input type="hidden" value="${item.name }"/>
									<img url="${ctp}/customer/delete/${item.id }" 
									title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button delete" alt="删除" />
								</c:if>
								
						</td>					
					</tr>
				</c:forEach>
			</table>
				</c:if>

			<jq:pagetag page="${page }" params="${params }"></jq:pagetag>
		
	</form>
	
</body>
</html>