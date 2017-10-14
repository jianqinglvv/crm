<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="jq" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>角色管理 - 分配权限</title>
	<script type="text/javascript">
		$(function(){
			//父标签点击事件函数
			$("[class^='parent']").click(function(){
					//alert($(this).is(":checked")); alert($(this).prop('checked'));
					var count = $(this).prev(":hidden").val();
					if($(this).is(":checked")){
						//$(this).children(":checkbox").attr("checked",true);
						$(this).nextAll(".child"+count).attr("checked",true);
					}
					if(!$(this).is(":checked")){
						//$(this).children(":checkbox").attr("checked",true);
						$(this).nextAll(".child"+count).attr("checked",false);
					}
				
				
			});
			//子标签点击事件函数
			$("[class^='child']").click(function(){
				var count = $(this).attr("class").substring(5);

				var flag = true;
				$("[class^='child"+count+"']").each(function(){
					if($(this).is(":checked")){
						flag = true;
						$(this).prevAll(".parent"+count).attr("checked",true);
						return false;
					}else{
						flag = false;
						}
				});
				if(!flag){
					$(this).prevAll(".parent"+count).attr("checked",false);
				}
			});
			
			
			$("[class^='child']").each(function(){
				var count = $(this).attr("class").substring(5);
				
				if($(this).is(":checked")){
					$(this).prevAll(".parent"+count).attr("checked",true);
					
					
				}
			});
			
			
		});
	</script>
</head>

<body class="main">
 	
 	
 	<form:form action="${ctp}/role/assign/${role.id }" method="post" modelAttribute="role">
		<input type="hidden" name="_method" value="put" />
 	
		<input type="hidden" name="id" value="${role.id }" />
		
		<div class="page_title">
			角色管理 &gt; 分配权限
		</div>
		
		<div class="button_bar">
			<button class="common_button" onclick="javascript:history.back(-1);">
				返回
			</button>
			<button class="common_button" onclick="document.forms[0].submit();">
				保存
			</button>
		</div>

		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title" width="10%">
					角色名
				</th>
				<td class="input_content" width="20%">
					${role.name }
				</td>
				<th class="input_title" width="10%">
					角色描述
				</th>
				<td class="input_content" width="20%">
					${role.description }
				</td>
				<th class="input_title" width="10%">
					状态
				</th>
				<td class="input_content" width="20%">
					${role.enabled?'有效':'无效' }
				</td>
			</tr>
			<tr>
				<th class="input_title">
					权限
				</th>
				<td class="input_content" colspan="5" valign="top">
				
					<c:forEach items="${parentAuthorities }" var="item" varStatus="status">
						<input type="hidden"  value="${status.count }" />

							 <input type="checkbox" value="${item.id }" 
							 	class="parent${status.count }"/>
							 ${item.displayName }
						
						<br>
						<c:forEach  items="${childAuthorities }" var="child">
							<c:if test="${child.parentAuthority.id==item.id }">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<form:checkbox path="authorities2" label="${child.displayName }" value="${child.id }"
									 class="child${status.count }"/>
								<br>
							</c:if>
						</c:forEach>
						
					
					</c:forEach>
					
					
					
				</td>
			</tr>
		</table>
 	</form:form>
	
</body>
</html>
