package model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test1h.hotel_admin")
public class hotel_admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "username", nullable = false)
    protected String username;

    @Column(name = "password")
    protected String password;

    @Column(name = "name")
    protected String name;


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public hotel_admin() {
    }
    public hotel_admin(String password, String name) {
        super();
        this.password = password;
        this.name = name;
    }

    public hotel_admin(String username, String password, String name) {
        super();

        this.username = username;
        this.password = password;
        this.name = name;
    }

}
