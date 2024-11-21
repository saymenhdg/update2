package smweb.chillana.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import smweb.chillana.Service.PostService;
import smweb.chillana.Service.UserProfileService;
import smweb.chillana.Service.UserService;
import smweb.chillana.model.PostModel;
import smweb.chillana.model.UserProfileModel;

import java.util.List;

@Controller
public class visitProfileController {
    private final UserService userService;
    private final UserProfileService userProfileService;
    private final PostService postService;

    public visitProfileController(UserService userService, UserProfileService userProfileService, PostService postService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.postService = postService;
    }



    @GetMapping("/visitProfile/{username}")
    public String visitProfile(@PathVariable String username, Model model, HttpSession session) {
        String sessionUsername = (String) session.getAttribute("username");
        UserProfileModel userProfile = userProfileService.getProfileByUsername(username);
        if (userProfile == null) {
            model.addAttribute("message", "User not found");
            return "redirect:/homefeed/" +sessionUsername;
        }
        List<PostModel> userPosts = postService.getAllPostsByUsername(username);
        model.addAttribute("userProfile", userProfile);
        System.out.println("User Posts: " + userPosts);
        model.addAttribute("userPosts", userPosts);
        return "visitProfile";

    }


}
