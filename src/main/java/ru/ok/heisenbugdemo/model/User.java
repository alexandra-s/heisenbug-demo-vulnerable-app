package ru.ok.heisenbugdemo.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username")
    @NotBlank(message = "username is mandatory")
    private String username;

    @Column(name = "name")
    @NotBlank(message = "name is mandatory")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "followers",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "being_followed_id"))
    private Set<User> follows;

    @ManyToMany
    @JoinTable(
            name = "followers",
            joinColumns = @JoinColumn(name = "being_followed_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private Set<User> followedBy;

    @OneToMany(mappedBy = "authorId")
    private List<Photo> photos;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public Set<User> getFollows() {
        return follows;
    }

    public Set<User> getFollowedBy() {
        return followedBy;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUsername());
    }
}