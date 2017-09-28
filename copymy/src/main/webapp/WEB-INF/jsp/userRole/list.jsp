<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<table id="dg_ur" title="用户列表" class="easyui-datagrid"
	style="width:auto;height:250px" url="userRole/getUserRoles.do"
	toolbar="#toolbar_ur" rownumbers="true" fitColumns="true"
	singleSelect="true">
	<thead>
		<tr>
			<th field="id" width="50">ID</th>
			<th field="username" width="50">用户名</th>
			<th field="roleName" width="50">角色</th>
		</tr>
	</thead>
</table>
<div id="toolbar_ur">
<a href="#" class="easyui-linkbutton"
		iconCls="icon-edit" plain="true" onclick="UserRoleModel.editUserRole()">分配</a>
</div>
<div id="dlg_ur" class="easyui-dialog"
	style="width:400px;height:280px;padding:15% 20px" closed="true"
	buttons="#dlg_ur-buttons">
	<form id="fm_ur" method="post">
		<table align="center">
			<tr>
				<td align="right">用户名:</td>
				<td><input name="username" class="easyui-validatebox" id="u_" readonly></td>
			</tr>
			<tr>
				<td align="right">角色</td>
				<td>
					<input id="c_r" class="easyui-combobox" name="ids" style="width:100%;" data-options="
					url:'role/getRoles.do',
					method:'get',
					valueField:'id',
					textField:'text',
					multiple:true,
					panelHeight:'auto',
					" required>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="dlg_ur-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
		onclick="UserRoleModel.saveUserRole()">保存</a> <a href="#" class="easyui-linkbutton"
		iconCls="icon-cancel" onclick="javascript:$('#dlg_ur').dialog('close')">取消</a>
</div>
<script>
	var UserRoleModel = {
	'editUserRole' : function() {
		var row = $('#dg_ur').datagrid('getSelected');
		if (row) {
			$('#dlg_ur').dialog('open').dialog('setTitle', '分配角色');
			$('#c_r').combobox('reload');
			var roleNames = ","+row.roleName;
			var roleId = [];
			var data = $('#c_r').combobox('getData');
			for(var i = 0; i < data.length; i++){
				if(roleNames.indexOf(","+data[i].text) > -1){
					roleId.push(data[i].id);
				}
			}
			$('#c_r').combobox('setValues',roleId);
			$('#fm_ur').form('load', row);			
			url = 'userRole/update.do?id=' + row.id;
		}
	},
	'saveUserRole' : function() {
		$('#fm_ur').form('submit', {
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
					$('#dlg_ur').dialog('close'); // close the dialog
					$('#dg_ur').datagrid('reload'); // reload the user data
				}
			}
		});
	}
}
</script>
