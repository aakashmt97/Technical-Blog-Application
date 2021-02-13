package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import technicalblog.model.Post;
import technicalblog.service.PostService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("posts")
    public String getUserPosts(Model model) throws SQLException, ClassNotFoundException {

//        ArrayList<Post> localPosts = new ArrayList<>();
        List<Post> localPosts = postService.getAllPosts();
//        Post latestPost = postService.getOnePosts();
//        localPosts.add(latestPost);
        model.addAttribute("posts", localPosts);

        return "posts";
    }

    @RequestMapping("/posts/newpost")
    public String newPost() {
        return "/posts/create";
    }

    @RequestMapping(value="/posts/create", method= RequestMethod.POST)
    public String createPost(Post new_post) {
        postService.createPost(new_post);
        return "redirect:/posts";
    }

    @RequestMapping(value="/editPost", method = RequestMethod.GET)
    public String editPost(@RequestParam(name = "postId") Integer postId, Model model) {
        Post pt = postService.getPostEdit(postId);
        model.addAttribute("post", pt);
        return "posts/edit";
    }

    @RequestMapping(value="/editPost", method = RequestMethod.PUT)
    public String editPostSubmit(@RequestParam(name = "postId") Integer postId, Post post) {
        post.setId(postId);
        postService.updatePost(post);
        return "redirect:/posts";
    }

    @RequestMapping(value="/deletePost", method = RequestMethod.DELETE)
    public String deletePostSubmit(@RequestParam(name = "postId") Integer postId) {
        postService.deletePost(postId);
        return "redirect:/posts";
    }

}
