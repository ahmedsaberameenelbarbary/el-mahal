package mahal;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import org.hibernate.Session;

@ManagedBean(name = "i")
@SessionScoped
@Entity
public class importers implements share, Serializable {

    private int impo_id;
    private int autonum;
    private String impi_name;
    private String impo_phone;
    private String impo_add;
    private String msg;
    private List<importers> table;

    private void clearAll() {
        this.impi_name = "";
        this.impo_add = "";
        this.impo_phone = "";

    }

    public void clearall() {
        clearAll();
        msg = "";

    }

    public void save() throws ParseException {
        CRUD c = new CRUD();
        c.insert(this);
        msg = "information saved successfully";
        clearAll();
    }

    private List allimporters() {
        Session s = Util.getSessionFactory().openSession();
        List<importers> cus = new ArrayList<importers>();
        try {
            s.beginTransaction();

            org.hibernate.Query q = s.createQuery("from importers ");
            cus = q.list();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }
    public String customselect() {
        Session s = Util.getSessionFactory().openSession();
        String cus = "";
        try {
            s.beginTransaction();
            org.hibernate.Query q = s.createQuery(" select count(*)from importers");

            cus = q.list().toString();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }
     private double getnumfromstr(String s) {
        CRUD cr = new CRUD();
        return cr.getnumfromstr(s);

    }

    @Id
    @TableGenerator(name = "de", pkColumnName = "pk", pkColumnValue = "pkv", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "de")
    @Column(name = "user_Id", nullable = false, unique = true, length = 255)
    public int getImpo_id() {
        return impo_id;
    }

    public void setImpo_id(int impo_id) {
        this.impo_id = impo_id;
    }

    public String getImpi_name() {
        return impi_name;
    }

    public void setImpi_name(String impi_name) {
        this.impi_name = impi_name;
    }

    public String getImpo_phone() {
        return impo_phone;
    }

    public void setImpo_phone(String impo_phone) {
        this.impo_phone = impo_phone;
    }

    public String getImpo_add() {
        return impo_add;
    }

    public void setImpo_add(String impo_add) {
        this.impo_add = impo_add;
    }

    @Transient
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Transient
    public List<importers> getTable() {
        return table =allimporters();
    }

    public void setTable(List<importers> table) {
        this.table = table;
    }
     @Transient
    public int getAutonum() {

        return autonum = (int)getnumfromstr(customselect()) + 1;
    }

    public void setAutonum(int autonum) {
        this.autonum = autonum;
    }
}
