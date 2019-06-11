package mahal;

import java.util.List;

public interface DBmethodes {

    public void insert(share s);

    public void update(share s);

    public void deletecustomer(String i);
    
    public List onecateg(String s);

    public List allcateg();

    public double getnumfromstr(String s);

}
