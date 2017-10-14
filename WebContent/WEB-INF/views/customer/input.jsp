<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>编辑客户</title>
</head>

<body class="main">

	<span class="page_title">编辑客户</span>
	
	<div class="button_bar">
		<button class="common_button" onclick="">
			联系人
		</button>
		<button class="common_button" onclick="">
			交往记录
		</button>
		<button class="common_button" onclick="">
			历史订单
		</button>
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			保存
		</button>
	</div>
	
	
	<form:form  id="customer" action="${ctp}/customer/${customer.id }" method="post" modelAttribute="customer">
		<input type="hidden" name="_method" value="put" />
		<form:hidden path="id"/>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					客户编号
				</th>
				<td>
					${customer.no }
					<form:hidden path="no"/>
				</td>
				<th>
					客户名称
				</th>
				<td>
					<form:input path="name"/>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					地区
				</th>
				<td>
					<form:select path="region">
						<option value="">未指定</option>
						<form:options items="${regions }"/>
					</form:select>
					<!-- 
					<select id="region" name="region">
						<option value="">未指定</option>
						<option value="北京" selected="selected">北京</option><option value="上海">上海</option><option value="广州">广州</option><option value="深圳">深圳</option><option value="香港">香港</option><option value="辽宁">辽宁</option>
					</select>
					 -->
					<span class="red_star">*</span>
				</td>
				<th>
					客户经理
				</th>
				<td>
					<form:select path="manager.id">
						<option value="">未指定</option>
						<form:options items="${customer.contacts }" itemLabel="name" itemValue="id"/>
					</form:select>
				<!-- 
					<select id="manager.id" name="manager.id">
						<option value="">未指定</option>
						<option value="142" selected="selected">杨倩</option>
					</select>
				 -->
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					客户等级
				</th>
				<td>
					<form:select path="level">
						<option value="">未指定</option>
						<form:options items="${levels }" />
					</form:select>
					<!-- 
					<select id="level" name="level">
						<option value="">未指定</option>
						<option value="普通客户">普通客户</option><option value="大客户">大客户</option><option value="重点开发客户">重点开发客户</option><option value="合作伙伴">合作伙伴</option><option value="战略合作伙伴" selected="selected">战略合作伙伴</option>
					</select>
					 -->
					<span class="red_star">*</span>
				</td>
				<th>
					客户状态
				</th>
				<td>
					<form:select path="state">
						<form:option value="流失预警">流失预警</form:option>
						<form:option value="正常">正常</form:option>
						<form:option value="流失">流失</form:option>
						<form:option value="删除" >删除</form:option>
					</form:select>
				</td>
			</tr>
			<tr>
				<th>
					满意度
				</th>
				<td>
					<form:select path="satify">
						<option value="">未指定</option>
						<form:options items="${satify }" />
					</form:select>
					<!-- 
					<select id="satify" name="satify">
						<option value="">未指定</option>
						<option value="☆">☆</option><option value="☆☆">☆☆</option><option value="☆☆☆">☆☆☆</option><option value="☆☆☆☆">☆☆☆☆</option><option value="☆☆☆☆☆" selected="selected">☆☆☆☆☆</option>
					</select>
					 -->
					<span class="red_star">*</span>
				</td>
				<th>
					信用度
				</th>
				<td>
					<form:select path="credit">
						<option value="">未指定</option>
						<form:options items="${credits }" />
					</form:select>
					<!-- 
					<select id="credit" name="credit">
						<option value="">未指定</option>
						<option value="☆">☆</option><option value="☆☆">☆☆</option><option value="☆☆☆">☆☆☆</option><option value="☆☆☆☆">☆☆☆☆</option><option value="☆☆☆☆☆" selected="selected">☆☆☆☆☆</option>
					</select>
					 -->
					<span class="red_star">*</span>
				</td>
			</tr>
		</table>
		<br />
		
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					地址
				</th>
				<td>
					
					<form:input path="address"/>
					<span class="red_star">*</span>
				</td>
				<th>
					邮政编码
				</th>
				<td>
					<form:input path="zip"/>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					电话
				</th>
				<td>
					<form:input path="tel"/>
					<span class="red_star">*</span>
				</td>
				<th>
					传真
				</th>
				<td>
					<form:input path="fax"/>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					网址
				</th>
				<td>
					<form:input path="websit"/>
					<span class="red_star">*</span>
				</td>
				<th>
					&nbsp;
				</th>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
		<br />
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					营业执照注册号
				</th>
				<td>	
					<form:input path="licenceNo"/>
				</td>
				<th>
					法人
				</th>
				<td>
					<form:input path="chief"/>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					注册资金(万元)
				</th>
				<td>
					<form:input path="bankroll"/>
				</td>
				<th>
					年营业额
				</th>
				<td>
					<form:input path="turnover"/>
				</td>
			</tr>
			<tr>
				<th>
					开户银行
				</th>
				<td>
					<form:input path="bank"/>
					<span class="red_star">*</span>
				</td>
				<th>
					银行账号
				</th>
				<td>
					<form:input path="bankAccount"/>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					地税登记号
				</th>
				<td>
					<form:input path="localTaxNo"/>
				</td>
				<th>
					国税登记号
				</th>
				<td>
					<form:input path="nationalTaxNo"/>
				</td>
			</tr>
		</table>
	</form:form>
	
</body>
</html>