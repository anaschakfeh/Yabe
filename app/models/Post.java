package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
 
@Entity
public class Post extends Model {
 
    public String title;
    public Date postedAt;
    
    @Lob//use a large text database type to store the post content. 
    public String content;
    
    @ManyToOne// each Post is authored by a single User, and that each User can author several Post instances.
    public User author;
    
    
    @OneToMany(mappedBy="post", cascade=CascadeType.ALL)
    public List<Comment> comments;
     
    public Post(User author, String title, String content) { 
        this.comments = new ArrayList<Comment>();
        this.author = author;
        this.title = title;
        this.content = content;
        this.postedAt = new Date();
    }
    
    public Post addComment(String author, String content) {
        Comment newComment = new Comment(this, author, content).save();
        this.comments.add(newComment);
        this.save();
        return this;
    }
 
}