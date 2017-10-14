<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>查看计划</title>
  </head>

  <body class="main">
	<span class="page_title">查看计划</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.back(-1);">返回</button>			
	</div>
  	<form action="/crm_/plan/execute" method="post">
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>编号</th>
				<td>173&nbsp;</td>
				
				<th>机会来源</th>
				<td>看电影&nbsp;</td>
			</tr>
			<tr>
				<th>客户名称</th>
				<td>朝桐光&nbsp;</td>
				
				<th>成功机率（%）</th>
				<td>99&nbsp;</td>
			</tr>
			
			<tr><th>概要</th>
				<td colspan="3">散发买&nbsp;</td>
			</tr>
			
			<tr>
				<th>联系人</th>
				<td>强人&nbsp;</td>
				<th>联系人电话</th>
				<td>22222&nbsp;</td>
			</tr>
			<tr>
				<th>机会描述</th>
				<td colspan="3">大牛&nbsp;</td>
			</tr>
			<tr>
				<th>创建人</th>
				<td>bcde&nbsp;</td>
				<th>创建时间</th>
				<td>2017-06-16&nbsp;</td>
			</tr>		
			<tr>					
				<th>指派给</th>
				<td>bcde&nbsp;</td>
			</tr>
		</table>
	
	<br />
	
		<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th width="200px">日期</th>
				<th>计划</th>
				<th>执行效果</th>
			</tr>
			
				<tr>
					<td class="list_data_text">
						1991-01-18&nbsp;
					</td>
					<td class="list_data_ltext">草草草哦啊哦从安次哦..&nbsp;</td>
					<td>草草草哦啊从啊&nbsp;</td>
				</tr>
			
				<tr>
					<td class="list_data_text">
						1991-01-18&nbsp;
					</td>
					<td class="list_data_ltext">111&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				
		</table>	
	
  </form>
  </body>
</html>