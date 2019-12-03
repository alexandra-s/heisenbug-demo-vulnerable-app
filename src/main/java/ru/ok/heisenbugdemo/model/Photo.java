package ru.ok.heisenbugdemo.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    @NotBlank(message = "url is mandatory")
    private String url;

    @Column(name="author_id")
    private long authorId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp")
    private Date timestamp;

    @OneToMany(mappedBy = "photo")
    @OrderBy()
    private List<Comment> comments;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public long getAuthorId() {
        return authorId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
