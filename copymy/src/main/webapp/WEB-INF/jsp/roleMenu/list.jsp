<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<table id="dg_rm" title="角色权限" class="easyui-datagrid"
	style="width:auto;height:250px" url="roleMenu/getRoleMenus.do"
	toolbar="#toolbar_rm" rownumbers="true" fitColumns="true"
	singleSelect="true">
	<thead>
		<tr>
			<th field="id" width="50">ID</th>
			<th field="roleName" width="50">角色</th>
			<th field="menuName" width="auto">权限</th>
		</tr>
	</thead>
</table>
<div id="toolbar_rm">
<a href="#" class="easyui-linkbutton"
		iconCls="icon-edit" plain="true" onclick="RoleMenuModel.editRoleMenu()">分配</a>
</div>
<div id="dlg_rm" class="easyui-dialog"
	style="width:400px;height:280px;padding:15% 20px" closed="true"
	buttons="#dlg_rm-buttons">
	<form id="fm_rm" method="post">
		<table align="center">
			<tr>
				<td align="right">角色名</td>
				<td><input name="roleName" class="easyui-validatebox" id="u_" readonly></td>
			</tr>
			<tr>
				<td align="right">权限</td>
				<td>
					<input id="c_m" class="easyui-combobox" name="ids" style="width:100%;" data-options="
					url:'menu/getMenus.do',
					method:'get',
					valueField:'id',
					textField:'text',
					multiple:true,
					panelHeight:'auto',
					">
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="dlg_rm-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
		onclick="RoleMenuModel.saveRoleMenu()">保存</a> <a href="#" class="easyui-linkbutton"
		iconCls="icon-cancel" onclick="javascript:$('#dlg_rm').dialog('close')">取消</a>
</div>
<script>
	var RoleMenuModel = {
	'editRoleMenu' : function() {
		var row = $('#dg_rm').datagrid('getSelected');
		if (row) {
			$('#dlg_rm').dialog('open').dialog('setTitle', '分配权限');
			$('#c_r').combobox('reload');
			//由串获得数据
			var menuNames = ","+row.menuName;
			var menuId = [];
			var data = $('#c_m').combobox('getData');
			for(var i = 0; i < data.length; i++){
				if(menuNames.indexOf(","+data[i].text) > -1){
					menuId.push(data[i].id);
				}
			}
			$('#c_m').combobox('setValues',menuId);
			$('#fm_rm').form('load', row);			
			url = 'roleMenu/update.do?id=' + row.id;
		}
	},
	'saveRoleMenu' : function() {
		$('#fm_rm').form('submit', {
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
					$('#dlg_rm').dialog('close'); // close the dialog
					$('#dg_rm').datagrid('reload'); // reload the user data
				}
			}
		});
	}
}
</script>