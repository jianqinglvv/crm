<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="jq" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>添加角色</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>

	<body>

		<div class="page_title">
			系统角色添加
		</div>
		<form:form action="${ctp }/role/" method="post" modelAttribute="role">
			<div class="button_bar">
				<button class="common_button" onclick="javascript:history.back(-1);">
					返回
				</button>
				<button class="common_button" onclick="document.forms[0].submit();">
					保存
				</button>
			</div>
			
			<table class="query_form_table">
				<tr>
					<th>
						角色名称
					</th>
					<td>
						<form:input path="name"/>
					</td>
					<th>
						角色描述
					</th>
					<td>
						<form:input path="description"/>
					</td>
				</tr>
				<tr>
					<th>
						状态
					</th>
					<td>
						<form:select path="enabled">
							<form:option value="1">有效</form:option>
							<form:option value="0">无效</form:option>
						</form:select>
						<span class="red_star">*</span>
					</td>
					
				</tr>
			</table>
		</form:form>
	</body>
</html>