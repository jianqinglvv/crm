<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>制定计划</title>
<script type="text/javascript">
var update = function(node){
	var todo = $(node).prev().prev().val();
	var url = $(node).attr("url");
	var id = $(node).prev(":hidden").val();
	var param = {'id':id,'todo':todo,'_method':'put'};
	
	$.post(url,param,function(data){
		alert("更新成功!!");
	})
};
var  del = function(node){
	var url = $(node).attr("url");
	var param = {'_method':'delete'};
	
	alert(url);
	var flag = confirm("确定删除？");
	if(flag){
		$.post(url,param);
		$(node).parent().parent().remove();;
	}
	
}

	$(function(){
		
		$("#execute").click(function(){
			window.location.href="${ctp}/plan/execute/${chance.id }";
		});
		
		$("#new").click(function(){
			var chanceId = $("#chanceId").val();
			var date = $("#date").val();
			var todo = $("#todo").val();
			var url = "${ctp}/plan/make";
			var param = {'chance.id':chanceId,'date':date,'todo':todo};
			
		
	//	var s = "<tr><td>"+new Date(data.date).format("yyyy-MM-dd")+"</td><td>"+data.todo+"</td><td>"+data.result==null?data.result:'[保存]'+"</td></tr>";
		
			//	 $("#tb").append(s);
			$.post(url,param,function(data){
				
				var $input = $("<input type='text' value='"+data.todo+"'/><input type='hidden' value='"+data.id+"'/>");
				var $upbtn = $("<button class='common_button update' url='${ctp }/plan/make/"+data.id+"' method='put'>修改</button>");
				var $debtn = $("<button class='common_button delete' url='${ctp }/plan/make/"+data.id+"' method='delete'>删除</button>");
				var $td = $("<td></td>");
				
				$upbtn.click(function(){
					update(this);
				});
				$debtn.click(function(){
					del(this);
				});
					$td.append($input).append($upbtn).append($debtn);
					var $tr = $("<tr></tr>");
					$tr.append("<td>"+new Date(data.date).format("yyyy-MM-dd")+"</td>")
	    				.append($td);	
					
					$("#tb").append($tr);
				 	alert("添加成功");
			});
		});
		
		
		
		$(".update").click(function(){
			update(this);
		});
		
		$(".delete").click(function(){
			del(this);
		});
		
	})
</script>
</head>

<body class="main">
	<span class="page_title">制定计划</span>
	<div class="button_bar">
		<button class="common_button" id="execute">
			执行计划
		</button>
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
	</div>
		<form action="${ctp}/plan/make" method="post">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>

				<td>
					${chance.id }
				</td>
				<th>
					机会来源
				</th>

				<td>
					${chance.source }
				</td>
			</tr>
			<tr>
				<th>
					客户名称
				</th>
				<td>
					${chance.custName }
				</td>
				<th>
					成功机率（%）
				</th>

				<td>
					${chance.rate }
				</td>
			</tr>
			<tr>
				<th>
					概要
				</th>
				<td colspan="3">
					${chance.title }
				</td>
			</tr>
			<tr>
				<th>
					联系人
				</th>

				<td>
					${chance.contact }
				</td>
				<th>
					联系人电话
				</th>

				<td>
					${chance.contactTel }
				</td>
			</tr>
			<tr>
				<th>
					机会描述
				</th>
				<td colspan="3">
					${chance.description }
				</td>
			</tr>
			<tr>
				<th>
					创建人
				</th>
				<td>
					${chance.createBy.name }
				</td>
				<th>
					创建时间
				</th>
				<td>
					<fmt:formatDate value="${chance.createDate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<th>
					指派给
				</th>
				<td>
					${chance.designee.name }
				</td>

			</tr>
		</table>

		<br />
		
		<table class="data_list_table" border="0" cellPadding="3" id="tb"
			cellSpacing="0">
			<tr>
				<th width="200px">
					日期
				</th>
				<th>
					计划项
				</th>
			</tr>
			<c:forEach items="${plans }" var="item">
				<tr>
					<td width="30%">
						<fmt:formatDate value="${item.date }" pattern="yyyy-MM-dd" />
					</td>
					<td>
						<c:if test="${item.result!=null }">
							<input type="text" value="${item.todo }" readonly="readonly"/>
							${item.result }
						</c:if>
					
						<c:if test="${item.result==null }">
							<input type="text" value="${item.todo }"/>
							<input type="hidden" value="${item.id }"/>
							<button class="common_button update" url="${ctp }/plan/make/${item.id}" method="put">修改</button>
							<button class="common_button delete" url="${ctp }/plan/make/${item.id}" method="delete">删除</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div class="button_bar">
			<button class="common_button" id="new">
				新建
			</button>
		</div>
		<input type="hidden" name="chance.id" value="${chance.id }" id="chanceId"/>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					日期
					<br />
					(格式: yyyy-mm-dd)
				</th>
				<td>
					<input type="text" name="date" id="date" />
					&nbsp;
				</td>
				<th>
					计划项
				</th>
				<td>
					<input type="text" name="todo" size="50" id="todo" />
					&nbsp;
				</td>
			</tr>
		</table>
	</form>
</body>
</html>