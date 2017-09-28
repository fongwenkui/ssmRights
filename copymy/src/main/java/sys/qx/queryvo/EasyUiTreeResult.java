package sys.qx.queryvo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EasyUiTreeResult {
	private String id;
	private String text;
	/**
	 * 表示该节点是否被选中
	 */
	private String checked;
	/**
	 * 节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点
	 */
	private String state;
	/**
	 * 自定义的tree节点属性
	 */
	private Map<String, Object> attributes = new HashMap<String, Object>();
	/**
	 * 如果不是使用异步访问，可以在children中添加子节点
	 */
	private List<EasyUiTreeResult> children = new ArrayList<EasyUiTreeResult>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public List<EasyUiTreeResult> getChildren() {
		return children;
	}
	public void setChildren(List<EasyUiTreeResult> children) {
		this.children = children;
	}
	
	
}
