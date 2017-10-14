<%@tag import="com.atguigu.crm.orm.Page"%>
<%@tag import="java.util.Date"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="page" required="false" rtexprvalue="true" type="com.atguigu.crm.orm.Page" %>
			
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
		