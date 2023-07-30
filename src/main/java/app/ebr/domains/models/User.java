package app.ebr.domains.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(mappedBy = "user")
    private Bicycle bicycle;

    public User() {

    }

    public User(String email, String password) {
        this.email = email;
        // Hash password
        this.password = BCrypt.with(BCrypt.Version.VERSION_2X).hashToString(6, password.toCharArray()).toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = BCrypt.with(BCrypt.Version.VERSION_2X).hashToString(6, password.toCharArray()).toString();
    }

    public String encodeJwt() {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String jwtToken = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 24 * 60 * 60))
                .withClaim("uuid", this.id)
                .sign(algorithm);
        return jwtToken;
    }

}
