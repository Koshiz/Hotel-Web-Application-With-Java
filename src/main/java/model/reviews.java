package model;

import org.hibernate.annotations.Type;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name = "test1h.reviews")
public class reviews implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    protected int id;

    @Column(name = "email")
    protected String email;

    @Column(name = "title")
    protected String title;

    @Column(name = "comment")
    protected String comment;

    @Column(name = "score")
    protected int score;

    @Column(name = "date")
    protected String date;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getComment() {
        return comment;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    public reviews() {
    }
    public reviews(String email,String title,String comment,int score,String date) {
        this.email = email;
        this.title = title;
        this.comment = comment;
        this.score = score;
        this.date = date;
    }

    public reviews(int id,String email,String title,String comment,int score,String date) {
        this.id = id;
        this.email = email;
        this.title = title;
        this.comment = comment;
        this.score = score;
        this.date = date;
    }


}
