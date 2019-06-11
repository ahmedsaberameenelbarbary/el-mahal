package mahal;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class CRUD implements DBmethodes {

    @Override
    public void insert(share c) {
        Session s = Util.getSessionFactory().openSession();
        try {
            s.beginTransaction();
            s.save(c);
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
    }

    @Override
    public void update(share c) {
        Session s = Util.getSessionFactory().openSession();
        try {
            s.beginTransaction();
            s.update(c);
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
    }

    @Override
    public List onecateg(String c) {

        Session s = Util.getSessionFactory().openSession();
        List<categ> cus = new ArrayList<categ>();
        try {
            s.beginTransaction();
            Query q = s.createQuery("from categ where categ_code =?");
            q.setParameter(0, c);
            cus = q.list();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }

    @Override
    public List allcateg() {

        Session s = Util.getSessionFactory().openSession();
        List<categ> cus = new ArrayList<categ>();
        try {
            s.beginTransaction();

            org.hibernate.Query q = s.createQuery("from categ");
            cus = q.list();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }

    @Override
    public void deletecustomer(String i) {
        Session s = Util.getSessionFactory().openSession();
        try {
            s.beginTransaction();
            categ c = (categ) s.load(categ.class, new Integer(Integer.parseInt(i)));
            s.delete(c);
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
    }

    public void deleteuser(int i) {
        Session s = Util.getSessionFactory().openSession();
        try {
            s.beginTransaction();
            useres c = (useres) s.load(useres.class, new Integer(i));
            s.delete(c);
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
    }

    public void deletecategclass(String i) {
        Session s = Util.getSessionFactory().openSession();
        List<categclass> cus = new ArrayList<categclass>();

        try {
            categclass c=new categclass();
            s.beginTransaction();
            Query q = s.createQuery("from categclass where categ_class =?");
            q.setParameter(0, i);
            cus = q.list();
            if (!cus.isEmpty()) {
                c = cus.get(0);
            }
            s.delete(c);
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
    }
    
     public void deletecategunite(String i) {
        Session s = Util.getSessionFactory().openSession();
        List<categunite> cus = new ArrayList<categunite>();

        try {
           categunite c=new categunite();
            s.beginTransaction();
            Query q = s.createQuery("from categunite where bigunite =?");
            q.setParameter(0, i);
            cus = q.list();
            if (!cus.isEmpty()) {
                c = cus.get(0);
            }
            s.delete(c);
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
    }
     public void deletecateg(String i) {
        Session s = Util.getSessionFactory().openSession();
        List<categ> cus = new ArrayList<categ>();

        try {
            categ c=new categ();
            s.beginTransaction();
            Query q = s.createQuery("from categ where categ_code =?");
            q.setParameter(0, i);
            cus = q.list();
            if (!cus.isEmpty()) {
                c = cus.get(0);
            }
            s.delete(c);
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
    }
      public void deletemovement(int i) {
        Session s = Util.getSessionFactory().openSession();
        List<themovement> cus = new ArrayList<themovement>();

        try {
            themovement c=new themovement();
            s.beginTransaction();
            Query q = s.createQuery("from themovement where mov_id =?");
            q.setParameter(0, i);
            cus = q.list();
            if (!cus.isEmpty()) {
                c = cus.get(0);
            }
            s.delete(c);
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
    }

    ///get number from string
    @Override
    public double getnumfromstr(String s) {
        String ss = "";
        if (s == null || s.isEmpty() || "[null]".equals(s)) {
            s = "0";
        }

        char ch[] = s.toCharArray();
        for (char h : ch) {
            if (h == '0' || h == '1' || h == '2' || h == '3' || h == '4' || h == '5' || h == '6' || h == '7' || h == '8' || h == '9') {
                ss += h;
            }
        }
        return Double.parseDouble(ss);
    }
     public void deletecustomer(int i) {
        Session s = Util.getSessionFactory().openSession();
        try {
            s.beginTransaction();
            useres c = (useres) s.load(useres.class, new Integer(i));
            s.delete(c);
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }        
    }
      public List allcustselect(share a) {
        Session s = Util.getSessionFactory().openSession();
        List<Customer> cus = new ArrayList<Customer>();
        try {
            s.beginTransaction();
            
            org.hibernate.Query q = s.createQuery("from Customer");
            cus = q.list();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }
      public List customselect(int c) {
        Session s = Util.getSessionFactory().openSession();
        List<Customer> cus = new ArrayList<Customer>();
        try {
            s.beginTransaction();
            Query q = s.createQuery("from Customer where customer_Account =?");
            q.setParameter(0, c);
            cus = q.list();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }

}
