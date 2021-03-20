package gokisoft.j2010g.model;

/**
 * Created by Diep.Tran on 3/13/21.
 */

public class User {
    String fullname, email, address, pwd;

    public User() {
    }

    public User(String fullname, String email, String address, String pwd) {
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.pwd = pwd;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
