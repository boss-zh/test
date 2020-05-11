<%@ page language="java" import="java.util.*,com.vo.*,com.service.impl.Category2ServiceImpl" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<base href="<%=basePath%>">
<title>修改</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">


<link rel="stylesheet" type="text/css" href="css/bootstrap/css/bootstrap.css"/>
<style type="text/css">

body {
		background-color:#F9F9F9;
}

div.categoryClass {
	margin-bottom: 15px;
}

#categoryFieldset {
	width: 400px;
	height:360px;
	padding: 30px 50px;
	position:relative;
	top:10px;
	left:200px;
}

fieldset legend {
	font-size:20px;
	font: 16px/100% Arial, Verdana, "宋体";
}

fieldset label {
	margin-right: 50px;
	font: 16px/100% Arial, Verdana, "宋体";
}

/*bootstrap 按钮样式*/
.btn {
  display: inline-block;
  padding: 6px 12px;
  margin-bottom: 0;
  font-size: 14px;
  font-weight: normal;
  line-height: 1.42857143;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  -ms-touch-action: manipulation;
      touch-action: manipulation;
  cursor: pointer;
  -webkit-user-select: none;
     -moz-user-select: none;
      -ms-user-select: none;
          user-select: none;
  background-image: none;
  border: 1px solid transparent;
  border-radius: 4px;
}

.btn-success {
  color: #fff;
  background-color: #5cb85c;
  border-color: #4cae4c;
}

.addCategoryBtn {
	position:absolute;
	right:50px;
	bottom:50px;
}

.categoryLabel
 {
  
  padding: 0px 10px;
  font-size: 16px;
  border-radius: 3px;
}
</style>



</head>

<body>
	
	<%
		Product product = (Product)request.getAttribute("product");
	 %>
	<div>
			<ul class="breadcrumb">
				<li><a href="#">首页</a></li>
				<li><a href="#">商品种类管理</a></li>
				<li><a href="#">修改一级商品种类</a></li>
			</ul>
	</div>			
	<form action="ProductServlet" method="post">
		<input type="hidden" name="action" value="update"/>
		<input type="hidden" name="pid" value="<%=product.getPid()%>"/>
		<input type="hidden" name="target" value="<%=request.getAttribute("target")%>"/>
		<input type="hidden" name="requestPage" value="<%=request.getAttribute("requestPage")%>"/>
		<input type="hidden" name="searchCondition" value="${requestScope.searchCondition}"/>
		
		<fieldset id="categoryFieldset">
			<legend>修改商品</legend>
			
			<div class="categoryClass">
				<label for="cname">商品编号</label>
				<%=product.getPid()%>
			</div>
			
			<div class="categoryClass">
				<label for="pname">商品名称</label><input type="text"  name="pname" class="categoryLabel"
					id="pname" value="<%=product.getPname()%>"/>
			</div>

			<div class="categoryClass">
				<label for="price">商品价格</label><input type="text"  name="price" class="categoryLabel"
					id="price" value="<%=product.getPrice()%>"/>
			</div>
			
			<div class="categoryClass">
				<label for="productSum">商品数量</label><input type="text"  name="productSum" class="categoryLabel"
					id="productSum" value="<%=product.getProductSum()%>"/>
			</div>
			
			<div class="categoryClass">
				<label for="dianpuName">店铺名称</label><input type="text"  name="dianpuName" class="categoryLabel"
					id="dianpuName" value="<%=product.getDianpuName()%>"/>
			</div>
			
			<div class="categoryClass">
				<label for="pdesc">商品描述</label><input type="text"  name="pdesc" class="categoryLabel"
					id="pdesc" value="<%=product.getPdesc()%>"/>
			</div>

			<div class="categoryClass">
				<label for="fid">所属种类</label>
				<select id="category1" name="category1">
					<%
						List<Category> categoryList = (List<Category>)request.getAttribute("categoryList");
						int parentCid = (Integer)request.getAttribute("parentCid");
						Iterator<Category> it = categoryList.iterator();
						while(it.hasNext()){
							Category category = it.next();
							
							if(category.getCid()==parentCid){
							
					%>
								<option value='<%=category.getCid() %>' selected='selected'><%=category.getCname() %></option>
					<%
							}else{
					%>	
								<option value='<%=category.getCid() %>'><%=category.getCname() %></option>
					<%	
							}
			
						}
					%>
					
					
				</select>
				
				<select name="cid" id="cid">
					<%
						int productCid = product.getCid();
						
						Category2ServiceImpl service = new Category2ServiceImpl();
						List<Category2> category2List = service.getAllCategory2ByCategory1(parentCid);
						for(Category2 temp:category2List){
					
							if(temp.getCid()==productCid){
					%>
								<option value='<%=temp.getCid()%>' selected='selected'><%=temp.getCname() %></option>
					<%
							}else{
					%>
								<option value='<%=temp.getCid()%>'><%=temp.getCname() %></option>
					<%
							}
						
					
						}
					
					%>
				</select>
			</div>
			
			<div class="categoryClass">
				<input type="submit" class="btn btn-success addCategoryBtn" value="修改商品" />
			</div>
		</fieldset>
	</form>

<script src="css/bootstrap/js/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
<script src="css/bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	
		$(function(){
			//给 id为 category1 的 下拉列表框 绑定 onchange事件
			$("#category1").change(function(){
				var category1Select = $("#category1")[0];
				
				var selectedIndex = category1Select.selectedIndex;
				var cid = category1Select.options[selectedIndex].value;
				
				//发送ajax请求   发给 ProductServlet
				$.post("ProductServlet",{
					"action":"getCategory2OfCategory1",
					"cid":cid
				},function(data,status){
					
				
					//使用dom技术来更新 创建出一个 二级产品种类的下拉列表框   把以前的替换就可以了
					var select = "<select name='cid' id='cid'>";
					for(var i=0;i<data.length;i++){
						
						select = select + "<option value='" + data[i].cid + "'>" + data[i].cname + "</option>";
					}
					
					select = select + "</select>";
					
					//替换以前的
					$("#cid").replaceWith(select);

				},"json");
				
				
  
			});
		});
</script>
</body>
</html>
