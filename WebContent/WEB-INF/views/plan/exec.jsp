<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>执行计划</title>
   <script type="text/javascript">
   		$(function(){
   			
   			$(".update").click(function(){
   				var result = $(this).prev().val();
   				var id = $(this).siblings(":hidden").val();
   				var url = "${ctp}/plan/make/"+id;
   				var param = {'id':id,'result':result,"_method":"put"};
   				
   				var btn = $(this);
   				$.post(url,param,function(data){
   						alert("更新完毕！！！"); 
   						btn.prev().attr("disabled","disabled");
   						btn.remove();
					});
   			});
   			
   			$("#finish").click(function(){
   				var url = "${ctp}/chance/finish/${chance.id }";
   				
   				$("#hiddenForm").attr("action",url);
   				
   				$("#hiddenForm").submit();
   				
   				
   			});
   			
   			$("#stop").click(function(){
				var url = "${ctp}/chance/stop/${chance.id }";
   				
   				$("#hiddenForm").attr("action",url);
   				
   				$("#hiddenForm").submit();
   			});
   		});
   </script>
  </head>

  <body class="main">
	<span class="page_title">执行计划</span>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${ctp}/chance/stop/${chance.id }'" id="stop">终止开发</button>
		<button class="common_button" onclick="window.location.href='${ctp}/plan/make/${chance.id }'">制定计划</button>
		<button class="common_button" onclick="javascript:history.go(-1);">返回</button>			
		<button class="common_button"  id="finish">开发成功</button>
	</div>
  	<form action="${ctp}/plan/execute" method="post">
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>编号</th>
				<td>${chance.id }&nbsp;</td>
				
				<th>机会来源</th>
				<td>${chance.source }&nbsp;</td>
			</tr>
			<tr>
				<th>客户名称</th>
				<td>${chance.custName }&nbsp;</td>
				
				<th>成功机率（%）</th>
				<td>${chance.rate }&nbsp;</td>
			</tr>
			
			<tr><th>概要</th>
				<td colspan="3">${chance.title }&nbsp;</td>
			</tr>
			
			<tr>
				<th>联系人</th>
				<td>${chance.contact }&nbsp;</td>
				<th>联系人电话</th>
				<td>${chance.contactTel }&nbsp;</td>
			</tr>
			<tr>
				<th>机会描述</th>
				<td colspan="3">${chance.description }&nbsp;</td>
			</tr>
			<tr>
				<th>创建人</th>
				<td>${chance.createBy.name }&nbsp;</td>
				<th>创建时间</th>
				<td><fmt:formatDate value="${chance.createDate }" pattern="yyyy-MM-dd"/>&nbsp;</td>
			</tr>		
			<tr>					
				<th>指派给</th>
				<td>${chance.designee.name }&nbsp;</td>
			</tr>
		</table>
	
	<br />
	
		<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th width="200px">日期</th>
				<th>计划</th>
				<th>执行效果</th>
			</tr>
			
			<c:forEach items="${plans }" var="item">
				<tr>
				
					
					<td class="list_data_text">
						<fmt:formatDate value="${item.date }" pattern="yyyy-MM-dd"/>&nbsp;
					</td>
					<td class="list_data_ltext">${item.todo }&nbsp;</td>
					<td>
						<input type="hidden"  value="${item.id }"/>
						<c:if test="${item.result==null }">
							<input class="result" id="result-${item.id }" type="text" size="50" value="${item.result }" />
							<button class="common_button update" id="saveresult-${item.id }">保存</button>
						</c:if>
						<c:if test="${item.result!=null }">
							<input class="result" id="result-${item.id }" type="text" size="50" value="${item.result }" disabled="disabled" />
							<!-- 
							<button class="common_button update" id="saveresult-${item.id }" disabled="disabled">保存</button>
							 -->
							
						</c:if>
					</td>
				</tr>
			</c:forEach>
			
				
		</table>	
	
	
  </form>
  
  <form action="" method="post" id="hiddenForm">
  	<input type="hidden"  name="_method" value="put"/>
  </form>
  </body>
</html>