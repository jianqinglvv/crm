<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="true">
  <head>
    <title>查看历史订单</title>
  </head>  
  <body>
  	
  
  	<div class="page_title">查看历史订单</div>
  	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">返回</button>  
	</div>
		
	<br />
	<c:if test="${page.totalElements==0 }">
		no data
	</c:if>
	
		<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
		<tr>
			<th>订单编号</th>
			<th>日期</th>
			<th>送货地址</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
			<c:if test="${page.totalElements>0 }">
				<c:forEach items="${page.content }" var="item">
				<tr>
					<td class="list_data_number">${item.no }</td>
					<td class="list_data_text"><fmt:formatDate value="${item.date }"/></td>
					<td class="list_data_ltext">${item.address }</td>
					<td class="list_data_text">${item.status }</td>
					<td class="list_data_op">
						<img onclick="window.location.href='${ctp}/order/details?id=${item.id }'" 
						title="查看明细" src="${ctp}/static/images/bt_detail.gif" class="op_button" />
					</td>
				</tr>			
				</c:forEach>
			
			</c:if>
		</table>
		








<div style="text-align:right; padding:6px 6px 0 0;">

	

	共 ${page.totalElements } 条记录 
	&nbsp;&nbsp;
	
	当前第 ${page.pageNo } 页/共 ${page.pages } 页
	&nbsp;&nbsp;
	<a href="?pageNo=1&${params}">首页</a>
	<c:if test="${!page.hasPrev }">
		上一页
	</c:if>
	<c:if test="${page.hasPrev }">
		<a href="?pageNo=${page.prev}&${params}">上一页</a>
	</c:if>
	<c:if test="${!page.hasNext }">
		下一页
	</c:if><c:if test="${page.hasNext }">
		<a href="?pageNo=${page.next}&${params}">下一页</a>
	</c:if>
	<a href="?pageNo=${page.pages}&${params}">尾页</a>
	 
	
	
	转到 <input id="pageNo" size='1'/> 页
	&nbsp;&nbsp;

</div>

		
<script type="text/javascript" src="${ctp}/static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

	$(function(){
		
		$("#pageNo").change(function(){
			
			var pageNo = $(this).val();
			var reg = /^\d+$/;
			if(!reg.test(pageNo)){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			var pageNo2 = parseInt(pageNo);
			if(pageNo2 < 1 || pageNo2 > parseInt("${page.pages}")){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			//查询条件需要放入到 class='condition' 的隐藏域中. 
			window.location.href = window.location.pathname	+ "?pageNo=" + pageNo2+"&${params}" ;
			
		});
	})
</script>
	
	
  </body>
</html>