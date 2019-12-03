package ru.ok.heisenbugdemo.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "text")
    @NotBlank(message = "text is mandatory")
    private String text;

    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name="photo_id")
    private Photo photo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp")
    Date timestamp;

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

    public Photo getPhoto() {
        return photo;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
