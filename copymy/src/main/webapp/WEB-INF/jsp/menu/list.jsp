<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<table id="dg2" title="权限列表" class="easyui-datagrid"
	style="width:auto;height:250px" url="menu/getSortMenus.do"
	toolbar="#toolbar2" rownumbers="true" fitColumns="true"
	singleSelect="true">
	<thead>
		<tr>
			<th field="id" width="50">ID</th>
			<th field="menuname" width="50">权限名</th>
			<th field="pid" width="100">父ID</th>
			<th field="code" width="100">权限码</th>
		</tr>
	</thead>
</table>
<div id="toolbar2">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
		onclick="MenuModel.newMenu()">新增</a> <a href="#" class="easyui-linkbutton"
		iconCls="icon-edit" plain="true" onclick="MenuModel.editMenu()">修改</a> <a
		href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
		onclick="MenuModel.destroyMenu()">删除</a>
</div>
<div id="dlg2" class="easyui-dialog"
	style="width:400px;height:280px;padding:15% 20px" closed="true"
	buttons="#dlg2-buttons">
	<form id="fm2" method="post">
		<table align="center">
			<tr>
				<td align="right">权限名</td>
				<td><input name="menuname" class="easyui-validatebox" required></td>
			</tr>
			<tr>
				<td align="right">权限码</td>
				<td><input class="easyui-validatebox" name="code" required></td>
			</tr>
			<tr>
				<td align="right">归属</td>
				<td><input id="c_" class="easyui-combobox" name="pid"
			data-options="url:'menu/getCombobox.do',valueField:'id',textField:'text',editable:false" required></td>
			</tr>
		</table>
	</form>
</div>
<div id="dlg2-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
		onclick="MenuModel.saveMenu()">保存</a> <a href="#" class="easyui-linkbutton"
		iconCls="icon-cancel" onclick="javascript:$('#dlg2').dialog('close')">取消</a>
</div>
<script>
	var MenuModel = {
	'newMenu' : function() {
		$('#dlg2').dialog('open').dialog('setTitle', '添加权限');
		$('#c_').combobox('reload');
		$('#fm2').form('clear');
		url = 'menu/add.do';
	},
	'editMenu' : function() {
		var row = $('#dg2').datagrid('getSelected');
		if (row) {
			
			$('#dlg2').dialog('open').dialog('setTitle', '编辑权限');
			$('#c_').combobox('reload');
			$('#fm2').form('load', row);
			url = 'menu/update.do?id=' + row.id;
		}
	},
	'saveMenu' : function() {
		$('#fm2').form('submit', {
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
					$('#dlg2').dialog('close');
					$('#dg2').datagrid('reload');		
				}
			}
		});
	},
	'destroyMenu' : function(){
	var row = $('#dg2').datagrid('getSelected');
	if (row){
		$.messager.confirm('删除权限','你确定要删除这个权限吗?',function(r){
			if (r){
				$.post('menu/delete.do',{id:row.id},function(result){
					if (result.success){
						$('#dg2').datagrid('reload');
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
},
'reload' : function(){
	$('#c_').combobox('reload');
	}
}
