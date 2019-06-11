package mahal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.*;
import javax.persistence.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

@ManagedBean(name = "mv")
@SessionScoped
@Entity
public class themovement implements share, Serializable {

    private double categ_buy_price;
    private double categ_sell_price;
    private double categ_buy_pricebig;
    private double categ_sell_pricebig;
    private double the_amount;
    private int mov_id;
    private int autonum;
    private String categ_code;
    private String categ_name;
    private String categ_class;
    private String bigunite;
    private String smallunite;
    private String movetye;
    private String msg;
    private Date date;
    private Date date1;
    private Date date2;
    private Date time;
    private double price;
    private double thesum;
    private double earn;
    private double earn2;
    private int rowcount;
    private int rate;
    private int salary;
    private int adds;
    private List<themovement> allmoves;
    private List<themovement> allmoves2;
    private List<themovement> mareport;
    private List<themovement> ya;
    private List<themovement> alm = new ArrayList<themovement>();

    public themovement() {
        this.categ_code = "";
        this.categ_buy_price = 0;
        this.categ_sell_price = 0;
        this.categ_buy_pricebig = 0;
        this.categ_sell_pricebig = 0;
        this.price = 0;
        this.thesum = 0;
        this.the_amount = 0;
        this.categ_name = "";
        this.smallunite = "";
        this.bigunite = "";
        this.categ_name = "";
        this.categ_class = "الفئة";
        this.smallunite = "الوحدة";
        this.movetye="";
        this.date = new Date();
        this.time = new Date();
    }

    //function
    public void setmotype(String s){
        this.movetye=s;
        
    }
    private void clearAll() {
        this.categ_code = "";
        this.categ_buy_price = 0;
        this.categ_sell_price = 0;
        this.categ_buy_pricebig = 0;
        this.categ_sell_pricebig = 0;
        this.price = 0;        
        this.the_amount = 0;
        this.categ_name = "";
        this.smallunite = "";
        this.bigunite = "";
        this.categ_name = "";
        this.categ_class = "الفئة";
        this.smallunite = "الوحدة";
       
        

    }

    public void clear() {
        this.price = 0;
        this.thesum = 0;
    }

    public void clearall() {
        clearAll();
        msg = "";

    }

    public void save(String s) {
        rate=rr(bigunite);
        setmotype(s);
        thesum += price;
        CRUD c = new CRUD();
        categ ca = new categ();
        List<categ> cat = c.onecateg(categ_code);
        if (!cat.isEmpty()) {ca = cat.get(0);}
        switch(s){
            case "1":ca.setThe_amount(ca.getThe_amount() - the_amount);break;
            case "2":  setThesum(-thesum); ca.setThe_amount(ca.getThe_amount() + the_amount);break;
            case "3":   ca.setThe_amount(ca.getThe_amount() - the_amount);break;
            default:  setThesum(-thesum); ca.setThe_amount(ca.getThe_amount() + the_amount);break;
        }
        c.insert(this);
        update(ca);
        msg = "تم الحفظ بنجاح";
        clearAll();
        if(s.equals("2")||s.equals("4"))this.thesum *= -1;
    }


public void emptytable(){
    alm= new ArrayList<themovement>();
    clear();
    clearall();
    this.rowcount=0;
}
    public void settable() {
        rate=rr(bigunite);
        switch(movetye){  
            case "3": setThe_amount(the_amount*rate);  break;
            case "4": setThe_amount(the_amount*rate); break;
        }
        themovement mo = new themovement();
        mo.setCateg_buy_price(categ_buy_price);
         mo.setCateg_sell_price(categ_sell_price);
          mo.setCateg_buy_pricebig(categ_buy_pricebig);
           mo.setCateg_sell_pricebig(categ_sell_pricebig);
        mo.setThe_amount(the_amount);
        mo.setCateg_name(categ_name);
        mo.setCateg_code(categ_code);
        mo.setSmallunite(smallunite);
        alm.add(mo);
        rowcount=alm.size();

    }

    public void update(share s) {
        CRUD c = new CRUD();
        c.update(s);

    }

    public void delete() {
        CRUD c = new CRUD();
        c.deletemovement(mov_id);
        msg = "تم المسح بنجاح";
        clearAll();
    }

    public void getcateg(String a) {
        CRUD c = new CRUD();          
        List<categ> ca = c.onecateg(categ_code);
        if (!ca.isEmpty()) {
            categ e = ca.get(0);
            this.categ_code = e.getCateg_code();
            this.smallunite = e.getSmallunite();
            this.bigunite = e.getBigunite();
            this.categ_buy_price = e.getCateg_buy_price();
            this.categ_sell_price = e.getCateg_sell_price();
            this.categ_buy_pricebig = e.getCateg_buy_pricebig();
            this.categ_sell_pricebig = e.getCateg_sell_pricebig();
            this.categ_name = e.getCateg_name();
            this.the_amount=e.getThe_amount();

        }
        setmotype(a);
    }

    public void deleteall() {
        Session s = Util.getSessionFactory().openSession();
        List<themovement> cus = new ArrayList<themovement>();

        try {
            themovement c = new themovement();
            s.beginTransaction();
            org.hibernate.Query q = s.createQuery("from themovement");
            cus = q.list();
            for (themovement cc : cus) {
                s.delete(cc);
            }
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        msg = "تم المسح بنجاح";
        clearAll();
    }

    public List allmovements() {
        Session s = Util.getSessionFactory().openSession();
        List<themovement> cus = new ArrayList<themovement>();
        try {
            s.beginTransaction();
            Date d = new Date();
            org.hibernate.Query q = s.createQuery("from themovement where date=? ");
            q.setParameter(0, d);
            cus = q.list();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }
public List allmovements2() {
        Session s = Util.getSessionFactory().openSession();
        List<themovement> cus = new ArrayList<themovement>();
        try {
            s.beginTransaction();
            Date d = new Date();
            org.hibernate.Query q = s.createQuery("from themovement where date between ? and ? ");
            q.setParameter(0, date1);
            q.setParameter(1, date2);
            cus = q.list();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }
public String money() {
        Session s = Util.getSessionFactory().openSession();
      String cus ="";
        try {
            s.beginTransaction();
            Date d = new Date();
            org.hibernate.Query q = s.createQuery("select Sum(thesum)from themovement where date between ? and ? ");
            q.setParameter(0, date1);
            q.setParameter(1, date2);
            cus = q.list().toString();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }
    

    //count methode
    public String count() {
        Session s = Util.getSessionFactory().openSession();
        String cus = "";
        try {
            s.beginTransaction();
            org.hibernate.Query q = s.createQuery(" select count(*)from themovement");

            cus = q.list().toString();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }
      public String salary() {
        Session s = Util.getSessionFactory().openSession();
        String cus = "";
        try {
            s.beginTransaction();
            org.hibernate.Query q = s.createQuery(" select Sum(cus_balance)from Customer");
            cus = q.list().toString();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }

    public String theearn() {
        Session s = Util.getSessionFactory().openSession();
        String cus = "";
        Date d = new Date();
        try {
            s.beginTransaction();
            org.hibernate.Query q = s.createQuery(" select Sum(thesum)from themovement where date=?");
            q.setParameter(0, d);
            cus = q.list().toString();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }

   
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
            cus = cr.add(Restrictions.ilike("bigunite", i, MatchMode.ANYWHERE)).list();
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

    ///get number from string
    private double getnumfromstr(String s) {
        CRUD cr = new CRUD();
        return cr.getnumfromstr(s);

    }

    //getter and setter
    @Id
    @TableGenerator(name = "de", pkColumnName = "pk", pkColumnValue = "pkv", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "de")
    @Column(name = "mov_Id", nullable = false, unique = true, length = 255)
    public int getMov_id() {
        return mov_id;
    }

    public void setMov_id(int mov_id) {
        this.mov_id = mov_id;
    }

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

    public double getThe_amount() {
        return the_amount;
    }

    public void setThe_amount(double the_amount) {
        this.the_amount = the_amount;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Temporal(javax.persistence.TemporalType.TIME)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getCateg_buy_pricebig() {
        return categ_buy_pricebig;
    }

    public void setCateg_buy_pricebig(double categ_buy_pricebig) {
        this.categ_buy_pricebig = categ_buy_pricebig;
    }

    public double getCateg_sell_pricebig() {
        return categ_sell_pricebig;
    }

    public void setCateg_sell_pricebig(double categ_sell_pricebig) {
        this.categ_sell_pricebig = categ_sell_pricebig;
    }

    public String getBigunite() {
        return bigunite;
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

    @Transient
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Transient
    public int getAutonum() {
        return autonum = (int)getnumfromstr(count()) + 1;
    }

    public void setAutonum(int autonum) {
        this.autonum = autonum;
    }

    @Transient
    public List<themovement> getAllmoves() {
        return allmoves = allmovements();
    }

    public void setAllmoves(List<themovement> allmoves) {
        this.allmoves = allmoves;
    }

    public double getPrice() {
       
       switch(this.movetye) {
           case "1":price = categ_sell_price * the_amount;break;
           case "2":price = categ_buy_price * the_amount;break;
           case "3":price = categ_sell_pricebig * the_amount;break;
          default:price = categ_buy_pricebig * the_amount;break;
       }
        return price ;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public double getThesum() {
        return thesum;
    }

    public void setThesum(double thesum) {
        this.thesum = thesum;
    }

    @Transient
    public List<themovement> getAlm() {
        return alm;
    }

    public void setAlm(List<themovement> alm) {
        this.alm = alm;
    }

    @Transient
    public double getEarn() {
        return earn = getnumfromstr(theearn());
    }

    public void setEarn(double earn) {
        this.earn = earn;
    }

   @Transient
    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }
@Transient
    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    @Transient
    public double getEarn2() {
        return earn2 = getnumfromstr(money())-(adds+salary);
    }

    public void setEarn2(double earn2) {
        this.earn2 = earn2;
    }

    @Transient
    public String getMovetye() {
        return movetye;
    }

    public void setMovetye(String movetye) {
        this.movetye = movetye;
    }

    @Transient
    public List<themovement> getAllmoves2() {
        return allmoves2=allmovements2();
    }

    public void setAllmoves2(List<themovement> allmoves2) {
        this.allmoves2 = allmoves2;
    }
@Transient
    public int getRowcount() {
        return rowcount;
    }

    public void setRowcount(int rowcount) {
        this.rowcount = rowcount;
    }
@Transient
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
 @Transient
    public int getSalary() {
        return salary= (int)getnumfromstr(salary());
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
@Transient
    public int getAdds() {
        return adds;
    }

    public void setAdds(int adds) {
        this.adds = adds;
    }

    

}
