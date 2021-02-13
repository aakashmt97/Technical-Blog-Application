package technicalblog.repository;

import org.springframework.stereotype.Repository;
import technicalblog.model.Post;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PostRepository {

    @PersistenceUnit(unitName = "techblog")
    private EntityManagerFactory emf;

    public List<Post> getAllPosts() throws ClassNotFoundException, SQLException {

        EntityManager em = emf.createEntityManager();
        TypedQuery<Post> query = em.createQuery("SELECT p FROM Post p", Post.class);
        List<Post> resulList = query.getResultList();

        return resulList;
    }

    public Post getLatestPost() {
        EntityManager em = emf.createEntityManager();
        return em.find(Post.class, 3);
    }

    public Post createPost(Post newPost) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newPost);
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }

        return newPost;
    }

    public Post getPostEdit(Integer id) {

        EntityManager em = emf.createEntityManager();
        return em.find(Post.class, id);
    }

    public void updatePost(Post update_post) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(update_post);
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
    }

    public void deletePost(Integer postId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Post pt = em.find(Post.class, postId);
            em.remove(pt);
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
    }
}
