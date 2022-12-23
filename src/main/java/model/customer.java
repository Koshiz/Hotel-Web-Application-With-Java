package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name = "test1h.customer")
public class customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "email", nullable = false)
    protected String email;

    @Column(name = "password")
    protected String password;

    @Column(name = "first_name")
    protected String first_name;

    @Column(name = "last_name")
    protected String last_name;

    @Column(name = "phone1")
    protected String phone1;

    @Column(name = "phone2")
    protected String phone2;

    @Column(name = "address")
    protected String address;

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }
    public String getPhone1() {
        return phone1;
    }
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
    public String getPhone2() {
        return phone2;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public customer() {
    }

    public customer(String password , String first_name , String last_name , String phone1 , String phone2 , String address) {
        super();
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.address = address;
    }

    public customer( String email , String password , String first_name , String last_name , String phone1 , String phone2 , String address) {
        super();
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.address = address;
    }
}
