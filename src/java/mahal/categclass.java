package mahal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.Query;
import org.hibernate.Session;

@ManagedBean(name = "a")
@SessionScoped
@Entity
public class categclass implements Serializable, share {

    private int autonum;
    private String categ_class;
    private String categ_c;
    private String msg;
    private String count;
    private List<categclass> c;

    public categclass() {
        categ_c = "";
        

    }

    //methodes
    public List allclass() {

        Session s = Util.getSessionFactory().openSession();
        List<categclass> cus = new ArrayList<categclass>();
        try {
            s.beginTransaction();

            org.hibernate.Query q = s.createQuery("from categclass s order by s.categ_class asc");
            cus = q.list();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }

    private void clearAll() {
        this.categ_c = "";
        
    }

    public void clearall() {
        clearAll();
        msg = "";

    }

    public void save() {
        CRUD c = new CRUD();
        c.insert(this);
        msg = "تم الحفظ بنجاح";
        clearAll();
    }

    public void delete() {
        CRUD c = new CRUD();
        c.deletecategclass(categ_class);
         msg = "تم الحفظ بنجاح";
        clearAll();
    }

    public void deleteall() {
        Session s = Util.getSessionFactory().openSession();
        List<categclass> cus = new ArrayList<categclass>();

        try {
            categclass c = new categclass();
            s.beginTransaction();
            Query q = s.createQuery("from categclass");
            cus = q.list();
            for (categclass cc : cus) {
                s.delete(cc);
            }
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
    }

    //count methode
    public String customselect() {
        Session s = Util.getSessionFactory().openSession();
        String cus = "";
        try {
            s.beginTransaction();
            org.hibernate.Query q = s.createQuery(" select count(categ_class)from categclass");

            cus = q.list().toString();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }

    ///get number from string
    private double getnumfromstr(String s) {
        CRUD cr = new CRUD();
        return cr.getnumfromstr(s);

    }

    @Id
    @Column(name = "categ_code", nullable = false, unique = true, length = 255)
    public String getCateg_class() {
        return categ_class = "*" + categ_c+"*";
    }

    public void setCateg_class(String categ_class) {
        this.categ_class = categ_class;
    }

    @Transient
    public int getAutonum() {

        return autonum = (int)getnumfromstr(customselect()) + 1;
    }

    public void setAutonum(int autonum) {
        this.autonum = autonum;
    }

    @Transient
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    //tabel all

    @Transient
    public List<categclass> getC() {
        return c = allclass();
    }

    public void setC(List<categclass> c) {
        this.c = c;
    }

    @Transient
    public String getCount() {
        return count = customselect();
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCateg_c() {
        return categ_c;
    }

    public void setCateg_c(String categ_c) {
        this.categ_c = categ_c;
    }

}
