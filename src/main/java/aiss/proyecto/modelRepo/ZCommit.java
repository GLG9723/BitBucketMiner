package aiss.proyecto.modelRepo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "commits")
public class ZCommit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "message")
    private String message;

    @Column(name = "author_email")
    private String author_email;

    @Column(name = "authored_date")
    private LocalDateTime authored_date;

    public ZCommit() {
    }

    public ZCommit(String title, String message, String author_email, LocalDateTime authored_date) {
        this.title = title;
        this.message = message;
        this.author_email = author_email;
        this.authored_date = authored_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor_email() {
        return author_email;
    }

    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }

    public LocalDateTime getAuthored_date() {
        return authored_date;
    }

    public void setAuthored_date(LocalDateTime authored_date) {
        this.authored_date = authored_date;
    }

}
