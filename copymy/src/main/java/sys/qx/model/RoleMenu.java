package sys.qx.model;

public class RoleMenu {
    private String id;

    private String menid;

    private String roleid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMenid() {
        return menid;
    }

    public void setMenid(String menid) {
        this.menid = menid == null ? null : menid.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }
}