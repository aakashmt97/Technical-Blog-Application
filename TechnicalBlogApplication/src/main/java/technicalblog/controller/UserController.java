package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import technicalblog.model.Post;
import technicalblog.model.User;
import technicalblog.model.UserProfile;
import technicalblog.service.PostService;
import technicalblog.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @RequestMapping("users/login")
    public String login() {
        return "users/login";
    }

    @RequestMapping("users/registration")
    public String registration(Model model) {
        User user = new User();
        UserProfile profile = new UserProfile();
        user.setProfile(profile);
        model.addAttribute("User", user);
        return "users/registration";
    }

    @RequestMapping(value="users/login", method=RequestMethod.POST)
    public String loginUser(User user) {
        if (userService.login(user)) {
            return "redirect:/posts";
        }
        else {
            return "users/login";
        }
    }

    @RequestMapping(value="users/registration", method=RequestMethod.POST)
    public String registerUser(User user) {
        userService.registerUser(user);
        return "users/login";
    }

    @RequestMapping(value="users/logout", method=RequestMethod.POST)
    public String logout(Model model) throws SQLException, ClassNotFoundException {

        List<Post> post = postService.getAllPosts();
        model.addAttribute("posts", post);

        return "index";
    }
}
