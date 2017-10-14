<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jq" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>编辑数据字典项</title>
</head>

<body class="main">
	<span class="page_title">编辑/新建数据字典项</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			保存
		</button>
	</div>
	<form:form action="${ctp}/dict/" method="POST" modelAttribute="dict">
		<c:if test="${dict.id }!=null">
			<input type="hidden"  name="_method" value="put"/>
		</c:if>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>
				<td>
					<form:input path="id" readonly="true"/>
				</td>
				<th>
					类别
				</th>
				<td>
					<form:input path="type"/>
				</td>
			</tr>
			<tr>
				<th>
					条目
				</th>
				<td>
					<form:input path="item"/>
				</td>
				<th>
					值
				</th>
				<td>
					<form:input path="value"/>
				</td>
			</tr>
			<tr>
				<th>
					是否可编辑
				</th>
				<td>
					<form:select path="editable">
						<form:option value="0">否</form:option>
						<form:option value="1">是</form:option>
					</form:select>
				</td>
				<th>
					&nbsp;
				</th>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>