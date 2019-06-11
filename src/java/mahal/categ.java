package mahal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.*;
import javax.persistence.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

@ManagedBean(name = "c")
@SessionScoped
@Entity
public class categ implements share, Serializable {

    private String categ_code;
    private String categ_name;
    private String categ_n;
    private String categ_class;
    private String count;
    private String categplace;
    private String bigunite;
    private String smallunite;
    private Date date;
    private Date time;
    private String msg;
    private List<categ> categvew;
    private categunite unite;
    private int rate;
    private double the_amount;
    private double bigamount;
    private double categ_buy_price;
    private double categ_sell_price;
    private double categ_buy_pricebig;
    private double categ_sell_pricebig;
    private int autonum;

    public categ() {
        this.categ_code = "";
        this.categ_class = "";
        this.categ_buy_price = 0;
        this.categ_sell_price = 0;
        this.categ_name = "";
        this.smallunite = "";
        this.categ_name = "";
        this.categplace = "";
        this.categ_class = "الفئة";
        this.smallunite = "الوحدة";
        this.categ_buy_pricebig = 0;
        this.categ_sell_pricebig = 0;
        this.the_amount = 0;
        this.date = new Date();
        this.time = new Date();
    }

    //methodes
    private void clearAll() {
        this.categ_code = "";
        this.categ_class = "";
        this.categ_buy_price = 0;
        this.categ_sell_price = 0;
        this.categ_name = "";
        this.smallunite = "";
        this.categ_name = "";
        this.categplace = "";
        this.categ_class = "الفئة";
        this.smallunite = "الوحدة";
        this.categ_buy_pricebig = 0;
        this.categ_sell_pricebig = 0;
        this.categ_n = "";
        this.the_amount = 0;
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

    public void update() {
        CRUD c = new CRUD();
        c.update(this);
        msg = "تم الحفظ بنجاح";
        clearAll();
    }

    public void delete() {
        CRUD c = new CRUD();
        c.deletecateg(categ_code);
        msg = "تم الحفظ بنجاح";
        clearAll();
    }

    public void getcateg() {
        CRUD c = new CRUD();
        List<categ> ca = c.onecateg(categ_code);
        if (!ca.isEmpty()) {
            categ e = ca.get(0);
            this.categ_code = e.categ_code;
            this.categ_class = e.getCateg_class();
            this.categ_buy_price = e.getCateg_buy_price();
            this.categ_sell_price = e.categ_sell_price;
            this.categ_n = e.categ_n;
        }
    }

    public void deleteall() {
        Session s = Util.getSessionFactory().openSession();
        List<categ> cus = new ArrayList<categ>();

        try {
            categ c = new categ();
            s.beginTransaction();
            Query q = s.createQuery("from categ");
            cus = q.list();
            for (categ cc : cus) {
                s.delete(cc);
            }
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        msg = "تم الحفظ بنجاح";
        clearAll();
    }

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
    private List<categclass> fe = allclass();

    private String[] thefe2a() {
        String ss[] = new String[fe.size()];
        int x = 0;
        if (!fe.isEmpty()) {
            for (categclass c : fe) {
                ss[x] = c.getCateg_class();
                x++;
            }
        }

        return ss;
    }

    private String fe2a[];

    ///////////////////////////////////
    public List allunite() {

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
    //////////////////////////////////

    private String[] thewehda() {
        List<categunite> we = allunite();
        String ss[] = new String[we.size()];
        int x = 0;
        if (!we.isEmpty()) {
            for (categunite c : we) {
                ss[x] = c.getSmallunite();
                x++;
            }
        }

        return ss;
    }

    private String wehda[];

    //count methode
    public String customselect() {
        Session s = Util.getSessionFactory().openSession();
        String cus = "";
        try {
            s.beginTransaction();
            org.hibernate.Query q = s.createQuery(" select count(categ_code)from categ");

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

//geter and setter
    @Id
    @Column(name = "categ_code", nullable = false, unique = true, length = 255)
    public String getCateg_code() {
        return categ_code;
    }

    public void setCateg_code(String categ_code) {
        this.categ_code = categ_code;
    }

    public String getCateg_name() {
        return categ_name;
    }

    public void setCateg_name(String categ_name) {
        this.categ_name = categ_name;
    }

    public String getCateg_class() {
        return categ_class;
    }

    public void setCateg_class(String categ_class) {
        this.categ_class = categ_class;
    }

    public double getCateg_buy_price() {
        return categ_buy_price;
    }

    public void setCateg_buy_price(double categ_buy_price) {
        this.categ_buy_price = categ_buy_price;
    }

    public double getCateg_sell_price() {
        return categ_sell_price;
    }

    public void setCateg_sell_price(double categ_sell_price) {
        this.categ_sell_price = categ_sell_price;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDate() {
        return date ;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Temporal(javax.persistence.TemporalType.TIME)
    public Date getTime() {
        return time ;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Transient
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Transient
    public int getAutonum() {

        return autonum = (int)getnumfromstr(customselect()) + 1;
    }

    public void setAutonum(int autonum) {
        this.autonum = autonum;
    }

    @Transient
    public String[] getFe2a() {
        return fe2a = thefe2a();
    }

    public void setFe2a(String[] fe2a) {
        this.fe2a = fe2a;
    }

    @Transient
    public String getCateg_n() {
        return categ_n;
    }

    public void setCateg_n(String categ_n) {
        this.categ_n = categ_n;
    }

    @Transient
    public List<categ> getCategvew() {
        CRUD c = new CRUD();

        return categvew = c.allcateg();
    }

    public void setCategvew(List<categ> categvew) {
        this.categvew = categvew;
    }

    @Transient
    public String getCount() {
        return count = customselect();
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCategplace() {
        return categplace;
    }

    public void setCategplace(String categplace) {
        this.categplace = categplace;
    }

    /////////////////////////////////////
    public String thebigunite(String i) {
        Session s = Util.getSessionFactory().openSession();
        List<categunite> cus = new ArrayList<categunite>();
        String ss = "";
        try {
            categunite c = new categunite();
            s.beginTransaction();
//            Query q = s.createQuery("from categunite where smallunite =?");
//            q.setParameter(0, i);
//            cus = q.list();
            Criteria cr = s.createCriteria(categunite.class);
            cus = cr.add(Restrictions.ilike("smallunite", i, MatchMode.ANYWHERE)).list();
            for (categunite ca : cus) {
                ss = ca.getBigunite();
            }

            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return ss;

    }

    ////////////////////////////
    /////////////////////////////////////
    public int rr(String i) {
        Session s = Util.getSessionFactory().openSession();
        List<categunite> cus = new ArrayList<categunite>();
        int ss = 0;
        try {
            categunite c = new categunite();
            s.beginTransaction();
//            Query q = s.createQuery("from categunite where smallunite =?");
//            q.setParameter(0, i);
//            cus = q.list();
            Criteria cr = s.createCriteria(categunite.class);
            cus = cr.add(Restrictions.ilike("smallunite", i, MatchMode.ANYWHERE)).list();
            for (categunite ca : cus) {
                ss = ca.getRate();
            }

            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return ss;

    }

    ////////////////////////////
    public String getBigunite() {

        return bigunite = thebigunite(smallunite);
    }

    public void setBigunite(String bigunite) {
        this.bigunite = bigunite;
    }

    public String getSmallunite() {
        return smallunite;
    }

    public void setSmallunite(String smallunite) {
        this.smallunite = smallunite;
    }

    ////////////////
    public int therate(String i) {
        Session s = Util.getSessionFactory().openSession();
        List<categunite> cus = new ArrayList<categunite>();
        int r = 0;
        try {
            categunite c = new categunite();
            s.beginTransaction();
//            Query q = s.createQuery("from categunite where smallunite =?");
//            q.setParameter(0, i);
//            cus = q.list();
            Criteria cr = s.createCriteria(categunite.class);
            cus = cr.add(Restrictions.ilike("smallunite", i, MatchMode.ANYWHERE)).list();
            if (!cus.isEmpty()) {
                c = cus.get(0);
            }
            r = c.getRate();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return r;
    }
    ////////////

    @Transient
    public int getRate() {
        return rate = rr(smallunite);
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Transient
    public double getCateg_buy_pricebig() {
        rate = rr(smallunite);
        return categ_buy_pricebig = categ_buy_price * rate;
    }

    public void setCateg_buy_pricebig(double categ_buy_pricebig) {
        this.categ_buy_pricebig = categ_buy_pricebig;
    }

    @Transient
    public double getCateg_sell_pricebig() {
        rate = rr(smallunite);
        return categ_sell_pricebig = categ_sell_price * rate;
    }

    public void setCateg_sell_pricebig(double categ_sell_pricebig) {
        this.categ_sell_pricebig = categ_sell_pricebig;
    }

    @Transient
    public String[] getWehda() {
        return wehda = thewehda();
    }

    public void setWehda(String[] wehda) {
        this.wehda = wehda;
    }

    public double getThe_amount() {
        return the_amount;
    }

    public void setThe_amount(double the_amount) {
        this.the_amount = the_amount;
    }

    @Transient
    public double getBigamount() {
       rate= rr(smallunite);
        if(rate!=0)
        return bigamount=the_amount/rate ;
        return bigamount ;
    }

    public void setBigamount(double bigamount) {
        this.bigamount = bigamount;
    }

}
