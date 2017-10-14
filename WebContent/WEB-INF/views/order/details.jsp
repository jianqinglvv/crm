<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>查看历史订单明细</title>
  </head>  
  <body>
  
  	<div class="page_title">查看历史订单明细</div>
  	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">返回</button>  
	</div>
	<br>
	
	<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
		<tr>
			<th>订单编号</th>
			<td>${order.no }</td>
			<th>日期</th>
			<td><fmt:formatDate value="${order.date }"/></td>
		</tr>
		<tr>
			<th>送货地址</th>
			<td>${order.address }</td>
			<th>状态</th>
			<td>${order.status }</td>
		</tr>
	</table>
	<!-- 列表数据 -->
	<br />
	<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
	<tr>
		<th>商品</th>
		<th>数量</th>
		<th>单位</th>
		<th>金额（元）</th>
	</tr>
		
	</table>
		
  </body>
</html>