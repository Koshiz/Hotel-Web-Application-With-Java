package model;

import controller.DeleteCustomer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test1h.booking")
public class booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    protected int id;

    @Column(name = "check_in")
    protected String check_in;

    @Column(name = "check_out")
    protected String check_out;

    @Column(name = "date")
    protected String date;

    @Column(name = "type")
    protected String type;

    @Column(name = "adults")
    protected String adults;

    @Column(name = "status")
    protected String status;

    @Column(name = "email")
    protected String email;

    @Column(name = "review")
    protected String review;

    public void setAdults(String adults) {
        this.adults = adults;
    }
    public String getAdults() {
        return adults;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }
    public String getCheck_in() {
        return check_in;
    }
    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }
    public String getCheck_out() {
        return check_out;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setReview(String review) {
        this.review = review;
    }
    public String getReview() {
        return review;
    }

    public booking() {
    }

    public booking(String check_in , String check_out , String date , String type,String adults , String status , String email , String review ) {
        super();
        this.check_in = check_in;
        this.check_out = check_out;
        this.date = date;
        this.type = type;
        this.adults = adults;
        this.status = status;
        this.email = email;
        this.review = review;

    }

    public booking( int id , String check_in , String check_out , String date , String type,String adults , String status , String email , String review ) {
        super();
        this.id = id;
        this.check_in = check_in;
        this.check_out = check_out;
        this.date = date;
        this.type = type;
        this.adults = adults;
        this.status = status;
        this.email = email;
        this.review = review;

    }

}
