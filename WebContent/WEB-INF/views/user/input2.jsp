<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>增加用户</title>
</head>

<body class="main">

	<form:form action="${ctp}/user/" method="post" modelAttribute="nuser">
		<c:if test="${nuser.id!=null }">
			<input type="hidden" name="_method" value="put"/>
		</c:if>

		<span class="page_title">用户管理 &gt; 新建用户</span>


		<div class="button_bar">
			<button class="common_button" onclick="javascript:history.go(-1);">返回</button>
			<button class="common_button" onclick="document.forms[0].submit()">保存</button>
		</div>

		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">用户名</th>
				<td class="input_content">
				<form:input path="name"/>
					<div id='divCheck'></div></td>

				<th class="input_title">密码</th>
				<td class="input_content">
					<form:password path="password"/>
			</tr>
			<tr>
				<th class="input_title">角色</th>
				<td class="input_content">
				<form:select path="role.id" items="roles" itemValue="id" itemLabel="name"></form:select>
						</td>
				<th class="input_title">状态</th>
				<td class="input_content">
				
				<span>
					<form:radiobuttons path="enabled" items="#{'1':有效,'2':无效 }" />
					<input id="enabled1" name="enabled" type="radio" value="0" checked="checked" />
					<label for="enabled1">无效</label>
				</span>
				<span>
					<input id="enabled2" name="enabled" type="radio" value="1" />
					<label for="enabled2">有效</label>
				</span>
				</td>
			</tr>
		</table>
	</form:form>

</body>
</html>