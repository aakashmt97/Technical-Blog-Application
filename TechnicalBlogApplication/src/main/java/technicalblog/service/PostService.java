package technicalblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalblog.model.Post;
import technicalblog.repository.PostRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() throws ClassNotFoundException, SQLException {


//METHOD 1: Hard Code
//        ArrayList<Post> posts = new ArrayList<>();

//        Post post1 = new Post();
//        post1.setTitle("Post 1");
//        post1.setBody("Post Body 1");
//        post1.setDate(new Date());
//
//        Post post2 = new Post();
//        post2.setTitle("Post 2");
//        post2.setBody("Post Body 2");
//        post2.setDate(new Date());
//
//        Post post3 = new Post();
//        post3.setTitle("Post 3");
//        post3.setBody("Post Body 3");
//        post3.setDate(new Date());
//
//        posts.add(post1);
//        posts.add(post2);
//        posts.add(post3);

//        return posts



// METHOD 2: JDBC
//        ArrayList<Post> posts = new ArrayList<>();

//        // Loading Driver
//        Class.forName("org.postgresql.Driver");
//
//        // Establising Connection with DataBase
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechnicalBlog", "postgres", "namonamo");
//
//        // The following instruction creates a statement object that will
//        // allow the java program to execute a SQL query on the database.
//        Statement statement = connection.createStatement();
//
//        // Executing Queries
//        ResultSet rs =  statement.executeQuery("SELECT * FROM posts");
//
//        // The required data in the ResultSet type object which is in form of a tabular data.
//        // It is basically a table of data representing a database result set.
//        // You can access the data in a ResultSet type object by using a cursor,
//        // which is basically a pointer and points to one row in the ResultSet type object.
//        // Initially, the cursor is positioned at the first row, and next() method is used to
//        // move the cursor to the next row. This is how you can iterate all the results
//        // in the ResultSet type object until the cursor is positioned after the last row and returns false.
//        while (rs.next()) {
//            Post pst = new Post();
//            pst.setTitle(rs.getString("title"));
//            pst.setBody(rs.getString("body"));
//            posts.add(pst);
//        }
//        connection.close();

//        return posts;



// METHOD 3: JPA and Hibernate framework
//        NOTE: This code is shifted to package: repository/PostRepository
//        EntityManager em = emf.createEntityManager();
//        TypedQuery<Post> query = em.createQuery("SELECT p FROM Post p", Post.class);
//        List<Post> resulList = query.getResultList();

//        return resultList


        return postRepository.getAllPosts();
    }


    public Post getOnePosts() throws ClassNotFoundException, SQLException {

//METHOD 1: Hard Code
//        ArrayList<Post> posts = new ArrayList<>();

//        Post post = new Post();
//        post.setTitle("This is your post");
//        post.setBody("Your post has some valid content");
//        post.setDate(new Date());
//
//        posts.add(post);

//        return posts;



//        METHOD 2: JDBC
//        ArrayList<Post> posts = new ArrayList<>();
//
//        Class.forName("org.postgresql.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechnicalBlog", "postgres", "namonamo");
//        Statement statement = connection.createStatement();
//        ResultSet rs =  statement.executeQuery("SELECT * FROM posts WHERE id = 4");
//        while (rs.next()) {
//            Post pst = new Post();
//            pst.setTitle(rs.getString("title"));
//            pst.setBody(rs.getString("body"));
//            posts.add(pst);
//        }
//        connection.close();
//
//        return posts;



//        METHOD 3: JPA and Hibernate framework
//        NOTE: This code is shifted to package: repository/PostRepository
//        EntityManager em = emf.createEntityManager();
//        return em.find(Post.class, 3);


        return postRepository.getLatestPost();
    }

    public void createPost(Post newpost) {
        newpost.setDate(new Date());
        postRepository.createPost(newpost);
        System.out.println("New Post = " +  newpost);
    }

    public Post getPostEdit(Integer id) {
        return postRepository.getPostEdit(id);
    }

    public void updatePost(Post update_post) {
        update_post.setDate(new Date());
        postRepository.updatePost(update_post);
    }

    public void deletePost(Integer postId) {
        postRepository.deletePost(postId);
    }
}
