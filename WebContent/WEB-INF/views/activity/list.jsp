<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>交往记录管理</title>
</head>
<body>
	
	<div class="page_title">
		交往记录管理
	</div>
	<div class="button_bar">
		<button class="common_button"
			onclick="window.location.href='${ctp}/activity/create?customerId=${customer.id }'">
			新建
		</button>
		<button class="common_button" onclick="javascript:history.go(-1);"> 
			返回
		</button>
	</div>
	
	<form action="${ctp}/activity/list" method="POST">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					客户编号
				</th>
				<td>${customer.no }</td>
				<th>
					客户名称
				</th>
				<td>${customer.name }</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
		<c:if test="${page.totalElements==0 }">
					没数据哈哈
		</c:if>
		<c:if test="${page.totalElements>0 }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						时间
					</th>
					<th>
						地点
					</th>
					<th>
						概要
					</th>
					<th>
						详细信息
					</th>
					<th>
						操作
					</th>
				</tr>
				
			
					<c:forEach items="${page.content }" var="item">
					<tr>
						<td class="list_data_text">
							<fmt:formatDate value="${item.date }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_ltext">
							${item.place }
						</td>
						<td class="list_data_ltext">
							${item.title }
						</td>
						<td class="list_data_ltext">
							${item.description }
						</td>
						<td class="list_data_op">
							<img onclick="window.location.href='${ctp}/activity/${item.id }'" 
								title="编辑" src="${ctp}/static/images/bt_edit.gif" class="op_button" />
							<img onclick="window.location.href='${ctp}/activity/delete?id=${item.id }&customerId=${item.customer.id } '" 
								title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" />
						</td>
					</tr>
					</c:forEach>
		
				
			</table>	
		
		</c:if>







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
			if(pageNo2 < 1 || pageNo2 > parseInt("1")){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			//查询条件需要放入到 class='condition' 的隐藏域中. 
			window.location.href = window.location.pathname
				+ "?page=" + pageNo2;
			
		});
	})
</script>
		
		
	</form>
</body>
</html>