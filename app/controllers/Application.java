package controllers;
 
import java.util.*;
 
import play.*;
import play.mvc.*;
 
import models.*;
 
public class Application extends Controller {
 
    public static void index() {
        Post frontPost = Post.find("order by postedAt desc").first();
        List<Post> olderPosts = Post.find(
            "order by postedAt desc"
        ).from(1).fetch(10);
        render(frontPost, olderPosts);
    }
    
    @Before  //execute the same code for each action of a controller (or a hierarchy of controllers) 
    static void addDefaults() {
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
    }
 
}