<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<table id="dg" title="用户列表" class="easyui-datagrid"
	style="width:auto;height:250px" url="userRole/getUserRoles.do"
	toolbar="#toolbar" rownumbers="true" fitColumns="true"
	singleSelect="true">
	<thead>
		<tr>
			<th field="id" width="50">ID</th>
			<th field="username" width="50">用户名</th>
			<th field="password" width="50">密码</th>
			<th field="roleName" width="auto">角色</th>
		</tr>
	</thead>
</table>
<div id="toolbar">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
		onclick="UserModel.newUser()">新增</a> <a href="#" class="easyui-linkbutton"
		iconCls="icon-edit" plain="true" onclick="UserModel.editUser()">修改</a> <a
		href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
		onclick="UserModel.destroyUser()">删除</a>
</div>
<div id="dlg" class="easyui-dialog"
	style="width:400px;height:280px;padding:15% 20px" closed="true"
	buttons="#dlg-buttons">
	<form id="fm" method="post">
		<table align="center">
			<tr>
				<td align="right">用户名:</td>
				<td><input name="username" class="easyui-validatebox" id="u_" required></td>
			</tr>
			<tr>
				<td align="right">密码:</td>
				<td><input name="password" type="password"
					class="easyui-validatebox" required></td>
			</tr>
		</table>
	</form>
</div>
<div id="dlg-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
		onclick="UserModel.saveUser()">保存</a> <a href="#" class="easyui-linkbutton"
		iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>
<script>
	var UserModel = {
	'newUser' : function() {
		$('#u_').attr("readonly",false); 
		$('#dlg').dialog('open').dialog('setTitle', '添加用户');
		$('#fm').form('clear');
		url = 'user/add.do';
	},
	'editUser' : function() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#u_').attr("readonly",true); 
			$('#dlg').dialog('open').dialog('setTitle', '编辑用户');
			$('#fm').form('load', row);
			url = 'user/update.do?id=' + row.id;
		}
	},
	'saveUser' : function() {
		$('#fm').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.errorMsg) {
					$.messager.show({
						title : '错误',
						msg : result.errorMsg
					});
				} else {
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				}
			}
		});
	},
	'destroyUser' : function(){
	var row = $('#dg').datagrid('getSelected');
	if (row){
		$.messager.confirm('删除用户','你确定要删除这个用户吗?',function(r){
			if (r){
				$.post('user/delete.do',{id:row.id},function(result){
					if (result.success){
						$('#dg').datagrid('reload');
					} else {
						$.messager.show({
							title: '错误',
							msg: result.errorMsg
						});
					}
				},'json');
			}
		});
	}
}
}
</script>
