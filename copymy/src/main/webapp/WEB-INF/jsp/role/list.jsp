<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<table id="dg1" title="角色列表" class="easyui-datagrid"
	style="width:auto;height:250px" url="roleMenu/getRoleMenus.do"
	toolbar="#toolbar1" rownumbers="true" fitColumns="true"
	singleSelect="true">
	<thead>
		<tr>
			<th field="id" width="20">ID</th>
			<th field="roleName" width="20">角色名</th>
			<th field="menuName">权限</th>
		</tr>
	</thead>
</table>
<div id="toolbar1">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
		onclick="RoleModel.newRole()">新增</a> <a href="#" class="easyui-linkbutton"
		iconCls="icon-edit" plain="true" onclick="RoleModel.editRole()">修改</a> <a
		href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
		onclick="RoleModel.destroyRole()">删除</a>
</div>
<div id="dlg1" class="easyui-dialog"
	style="width:400px;height:280px;padding:15% 20px" closed="true"
	buttons="#dlg1-buttons">
	<form id="fm1" method="post">
		<table align="center">
			<tr>
				<td align="right">角色名</td>
				<td><input name="roleName" class="easyui-validatebox"></td>
			</tr>
		</table>
	</form>
</div>
<div id="dlg1-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
		onclick="RoleModel.saveRole()">保存</a> <a href="#" class="easyui-linkbutton"
		iconCls="icon-cancel" onclick="javascript:$('#dlg1').dialog('close')">取消</a>
</div>
<script>
	var RoleModel = {
	'newRole' : function() {
		$('#dlg1').dialog('open').dialog('setTitle', '添加角色');
		$('#fm1').form('clear');
		url = 'role/add.do';
	},
	'editRole' : function() {
		var row = $('#dg1').datagrid('getSelected');
		if (row) {
			$('#dlg1').dialog('open').dialog('setTitle', '编辑角色');
			$('#fm1').form('load', row);
			url = 'role/update.do?id=' + row.id;
		}
	},
	'saveRole' : function() {
		$('#fm1').form('submit', {
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
					$('#dlg1').dialog('close');
					$('#dg1').datagrid('reload');
				}
			}
		});
	},
	'destroyRole' : function(){
	var row = $('#dg1').datagrid('getSelected');
	if (row){
		$.messager.confirm('删除角色','你确定要删除这个角色吗?',function(r){
			if (r){
				$.post('role/delete.do',{id:row.id},function(result){
					if (result.success){
						$('#dg1').datagrid('reload');
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