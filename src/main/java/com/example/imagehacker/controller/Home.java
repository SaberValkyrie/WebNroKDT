package com.example.imagehacker.controller;

import com.example.imagehacker.entity.Account;
import com.example.imagehacker.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Home.
 *
 * @author Nguyễn Hải
 * Created 27/11/2023
 */

@Controller
public class Home {

private UserService userService;

    public Home(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String get(HttpSession session,Model model) {
        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/users/login";
        }
        model.addAttribute("loggedInUser", loggedInUser);
        return "forum";
    }

    @GetMapping("check")
    public String check(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        // Lưu trang trước thứ 2 vào session
        session.setAttribute("previousPage", "http://" + UserController.urlIP +"/check");
        return "redirect:/users/code";
    }


    @PostMapping("/getCode")
    public String getCode(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/users/login";
        }
        String linkForAccount = userService.checkUrl(loggedInUser);
        return "redirect:" + linkForAccount;
    }

}
