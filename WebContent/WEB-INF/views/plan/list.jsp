<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>客户开发计划</title>
</head>

<body class="main">
	<form action="${ctp}/plan/chance/list" method="post">
	

		<div class="page_title">
			客户开发计划(${message })
		</div>
		<div class="button_bar">
			<button class="common_button" onclick="document.forms[0].submit();">
				查询
			</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					客户名称
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_custName" />
				</td>
				<th class="input_title">
					概要
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_title" />
				</td>
				<th class="input_title">
					联系人
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_contact" />
				</td>
			</tr>
		</table>
		<br />
		<c:if test="${page.content==null }">
			没有任何数据呵呵呵呵
		</c:if>
		<c:if test="${page.content!=null }">
		
		
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
						概要
					</th>
					<th>
						联系人
					</th>
					<th>
						联系人电话
					</th>
					<th>
						创建时间
					</th>
					<th>
						状态
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
							${item.custName }
						</td>
						<td class="list_data_text">
							${item.title }
						</td>
						<td class="list_data_text">
							${item.contact }
						</td>
						<td class="list_data_text">
							${item.contactTel }
						</td>
						<td class="list_data_text">
							<fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_text">
							${item.status==2?'开发中':item.status==3?'开发成功':'开发失败' }
							<script type="text/javascript">
							/*
								switch('${item.status }')
								{
									case '2':
										document.write('开发中');
										break;
									case '3':
										document.write('开发成功');
										break;
									case '4':
									    document.write('开发失败');
									   	break;
								}
								*/		
							</script>
						</td>
						<td class="list_data_op">
							<c:if test="${item.status==2 }">
							
								<img
									onclick="window.location.href='${ctp}/plan/make/${item.id }'"
									title="制定计划" src="${ctp}/static/images/bt_plan.gif" class="op_button" />
								<img
									onclick="window.location.href='${ctp}/plan/execution?id=1'"
									title="执行计划" src="${ctp}/static/images/bt_feedback.gif" class="op_button" />
								<img 
									onclick="window.location.href='${ctp}/chance/finish?id=1'"
									title="开发成功" src="${ctp}/static/images/bt_yes.gif" class="op_button" />
						
							</c:if>
							<c:if test="${item.status!=2 }">
								<img 
									onclick="window.location.href='${ctp}/chance/details?chanceId=${item.id }'"
									title="详细信息" src="${ctp}/static/images/bt_detail.gif" class="op_button" />
							</c:if>
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
	<a href="${ctp }/plan/chance/list?pageNo=1&${params}">首页</a>
	<c:if test="${!page.hasPrev }">
		上一页
	</c:if>
	<c:if test="${page.hasPrev }">
		<a href="${ctp }/plan/chance/list?pageNo=${page.prev}&${params}">上一页</a>
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
				+ "?page=" + pageNo2 + "&${params}";
			
		});
	})
</script>
		
		
		
		
		</form>
</body>
</html>