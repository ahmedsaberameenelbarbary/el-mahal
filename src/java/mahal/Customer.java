package mahal;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.*;
import javax.persistence.*;
import org.hibernate.Session;

@ManagedBean(name = "cus")
@SessionScoped
@Entity
public class Customer implements share, Serializable {

//    private int cus_id;
    private int cus_account_num;
    private int autonum;
    private int cus_balance;
    private String cus_name;
    private String cus_phone;
    private String cus_occupation;
    private String cus_address;
    private String cus_marital_state;
    private String cus_sex;
    private String msg;
    private String checkmsg;
    private String vis;
    private String vis1;
    private String count;
    private Date date;
    private List<Customer> c;
    private List<Customer> a;
    private Date time;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");

    //constructor
    public Customer() {
        this.cus_balance = 0;
        this.date = new Date();
        this.time = new Date();
    }

    //methodes
    private void clearAll() {
        cus_balance = 0;
        this.cus_name = "";
        this.cus_account_num = 0;
        cus_phone = "";
        cus_occupation = "";
        cus_address = "";
        cus_marital_state = "";
        cus_sex = "";

    }

    public void clearall() {
        clearAll();
        msg = "";
        checkmsg = "";
    }

    public void save() throws ParseException {
        CRUD c = new CRUD();
        c.insert(this);
        msg = "information saved successfully";
        clearAll();
    }

    public void update() {
        CRUD c = new CRUD();
        c.update(this);
        msg = "information updated successfully";
        clearAll();
    }

    public void delete() {
        CRUD c = new CRUD();
        c.deletecustomer(cus_account_num);
        msg = "information deleted successfully";
        clearAll();
    }
//one customer

    public void getcus() {
        CRUD cr = new CRUD();
        List<Customer> c = cr.customselect(cus_account_num);
        for (Customer s : c) {
            cus_balance = s.cus_balance;
            this.cus_name = s.cus_name;
            this.cus_account_num = s.cus_account_num;
            cus_phone = s.cus_phone;
            cus_occupation = s.cus_occupation;
            cus_address = s.cus_address;
            cus_marital_state = s.cus_marital_state;
            cus_sex = s.cus_sex;

        }
    }

    //check account
    public void checkaccount() {
        CRUD cr = new CRUD();
        List<Customer> c = cr.customselect(cus_account_num);
        if (c.isEmpty()) {
            checkmsg = "not founded!!!!";
        } else {
            checkmsg = "founded-->write a nother one!!";
        }
    }
//table all

    private List allcustselect() {
        Session s = Util.getSessionFactory().openSession();
        List<Customer> cus = new ArrayList<Customer>();
        try {
            s.beginTransaction();

            org.hibernate.Query q = s.createQuery("from Customer s order by s.cus_account_num asc");
            cus = q.list();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }

    //delete all
    public void deleteall() {

        Session s = Util.getSessionFactory().openSession();
        List<Customer> cus = new ArrayList<Customer>();
        try {
            s.beginTransaction();

            org.hibernate.Query q = s.createQuery("from Customer s order by s.cus_account_num asc");
            cus = q.list();
            for (Customer c : cus) {
                s.delete(c);
            }
            msg = "information deleted successfully";
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }

    }
//table one

    private List onecustselect() {
        Session s = Util.getSessionFactory().openSession();
        List<Customer> cus = new ArrayList<Customer>();
        try {
            s.beginTransaction();

            org.hibernate.Query q = s.createQuery("from Customer where customer_Account =?");
            q.setParameter(0, cus_account_num);
            cus = q.list();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }

    //count methode
    public String customselect() {
        Session s = Util.getSessionFactory().openSession();
        String cus = "";
        try {
            s.beginTransaction();
            org.hibernate.Query q = s.createQuery(" select count(customer_Name)from Customer");

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
///getter and setter

    @Column(name = "customer_Name", length = 255)
    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    @Id
    @Column(name = "mov_Id", nullable = false, unique = true, length = 255)
    public int getCus_account_num() {
        return cus_account_num;
    }

    public void setCus_account_num(int cus_account_num) {
        this.cus_account_num = cus_account_num;
    }

    @Column(name = "customer_phone", length = 255)
    public String getCus_phone() {
        return cus_phone;
    }

    public void setCus_phone(String cus_phone) {
        this.cus_phone = cus_phone;
    }

    @Column(name = "customer_occupation", length = 255)
    public String getCus_occupation() {
        return cus_occupation;
    }

    public void setCus_occupation(String cus_occupation) {
        this.cus_occupation = cus_occupation;
    }

    @Column(name = "customer_Address", length = 255)
    public String getCus_address() {
        return cus_address;
    }

    public void setCus_address(String cus_address) {
        this.cus_address = cus_address;
    }

    @Column(name = "customer_Marital", length = 255)
    public String getCus_marital_state() {
        return cus_marital_state;
    }

    public void setCus_marital_state(String cus_marital_state) {
        this.cus_marital_state = cus_marital_state;
    }

    @Column(name = "customer_Sex", length = 255)
    public String getCus_sex() {
        return cus_sex;
    }

    public void setCus_sex(String cus_sex) {
        this.cus_sex = cus_sex;
    }

    @Column(name = "customer_Balance", length = 255)
    public int getCus_balance() {

        return this.cus_balance;

    }

    public void setCus_balance(int cus_balance) {
        this.cus_balance = cus_balance;
    }

    @Column(name = "customer_Time", length = 255)
    @Temporal(TemporalType.TIME)
    public Date getTime() {
        return time ;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
    public List<Customer> getC() {
        return c = allcustselect();
    }

    public void setC(List<Customer> c) {
        this.c = c;
    }

    @Transient
    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }
//table one

    @Transient
    public List<Customer> getA() {

        return a = onecustselect();
    }

    public void setA(List<Customer> a) {
        this.a = a;
    }

    @Transient
    public String getVis1() {
        return vis1;
    }

    public void setVis1(String vis1) {
        this.vis1 = vis1;
    }

    @Transient
    public String getCheckmsg() {
        return checkmsg;
    }

    public void setCheckmsg(String checkmsg) {
        this.checkmsg = checkmsg;
    }

    @Transient
    public String getCount() {
        return count = customselect();

    }

    public void setCount(String count) {
        this.count = count;
    }

    @Transient
    public int getAutonum() {

        return autonum = (int)getnumfromstr(customselect()) + 1;
    }

    public void setAutonum(int autonum) {
        this.autonum = autonum;
    }

}
