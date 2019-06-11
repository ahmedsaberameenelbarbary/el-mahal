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
import org.apache.derby.vti.Restriction;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

@ManagedBean(name = "un")
@SessionScoped
@Entity
public class categunite implements share, Serializable {

    private String bigunite;
    private String smallunite;
    private int rate;
    private String msg;
    private List<categunite> c;

    //methodes
    public List allclass() {

        Session s = Util.getSessionFactory().openSession();
        List<categunite> cus = new ArrayList<categunite>();
        try {
            s.beginTransaction();

            org.hibernate.Query q = s.createQuery("from categunite s order by bigunite asc");
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
        this.bigunite = "";
        this.smallunite = "";
        this.rate = 0;
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
        c.deletecategunite(bigunite);
        msg = "تم الحفظ بنجاح";
        clearAll();
    }

    public void deleteall() {
        Session s = Util.getSessionFactory().openSession();
        List<categunite> cus = new ArrayList<categunite>();

        try {
            categunite c = new categunite();
            s.beginTransaction();
            Query q = s.createQuery("from categunite");
            cus = q.list();
            for (categunite cc : cus) {
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
            org.hibernate.Query q = s.createQuery(" select count(*)from categunite");

            cus = q.list().toString();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }

    public int therate(String i) {
        Session s = Util.getSessionFactory().openSession();
        List<categunite> cus = new ArrayList<categunite>();
        int r = 0;
        try {
            categunite c = new categunite();
            s.beginTransaction();
            Query q = s.createQuery("from categunite where bigunite =?");
            q.setParameter(0, i);
            cus = q.list();
            if (!cus.isEmpty()) {
                c = cus.get(0);
            }
            r = c.rate;
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return r;
    }

    

//getter and setter
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getBigunite() {
        return bigunite;
    }

    public void setBigunite(String bigunite) {
        this.bigunite = bigunite;
    }

    @Id
    @Column(name = "categ_unite", nullable = false, unique = true, length = 255)
    public String getSmallunite() {
        return smallunite;
    }

    public void setSmallunite(String smallunite) {
        this.smallunite = smallunite;
    }

    @Transient
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Transient
    public List<categunite> getC() {
        return c = allclass();
    }

    public void setC(List<categunite> c) {
        this.c = c;
    }

}
