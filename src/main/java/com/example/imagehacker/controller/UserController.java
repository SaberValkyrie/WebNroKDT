package com.example.imagehacker.controller;

/**
 * UserControler.
 *
 * @author Nguyễn Hải
 * Created 27/11/2023
 */
import com.example.imagehacker.Logger;
import com.example.imagehacker.entity.Account;
import com.example.imagehacker.entity.AccountUrl;
import com.example.imagehacker.entity.ListUrl;
import com.example.imagehacker.service.UrlService;
import com.example.imagehacker.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
//    public static String urlIP = "103.161.181.15:8080";
public static String urlIP = "localhost:8080";

    private UserService userService;
    private UrlService urlService;


    public UserController(UserService userService, UrlService urlService) {
        this.userService = userService;
        this.urlService = urlService;
    }

    @GetMapping("code")
    public String code(HttpSession session, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        // Kiểm tra xem có thông tin đăng nhập hay không
        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/users/login";
        }

        // Kiểm tra nếu truy cập trực tiếp từ URL thay vì qua trang "/check"
        String referer = request.getHeader("referer");
        String previousPage = (String) session.getAttribute("previousPage");

        if (!("http://" + urlIP + "/check").equals(previousPage)) {
            return "redirect:/";
        }

        // Kiểm tra thông điệp từ redirectAttributes
        if (redirectAttributes.containsAttribute("reloadError")) {
            session.removeAttribute("loggedInUser");
            return "redirect:/error";
        }

        // Lưu trang hiện tại vào session để sử dụng cho lần truy cập sau
        session.setAttribute("previousPage", request.getRequestURL().toString());

        AccountUrl accountUrl = userService.checkaccountUrl(loggedInUser);
        model.addAttribute("Account", accountUrl);
        return "home";
    }


    @PostMapping("code")
    public String handleCodeSubmission(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("reloadError", true);
        return "redirect:/users/code";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, HttpSession session, HttpServletRequest request) {
        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            return "redirect:/";
        }

        // Lưu trang hiện tại vào session để sử dụng sau khi đăng nhập
        session.setAttribute("prevPage", session.getAttribute("previousPage"));

        Account user = new Account();
        model.addAttribute("user", user);
        return "login-form";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") Account user,
                            Model model, HttpSession session) {
        // Xác thực người dùng và lấy thông tin từ cơ sở dữ liệu
        Account authenticatedUser = userService.authenticateUser(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            session.setAttribute("loggedInUser", authenticatedUser);
            Logger.success("Login thành công " + user.getUsername() + " /" + user.getPassword());
            String prevPage = (String) session.getAttribute("prevPage");

            if (prevPage != null) {
                session.removeAttribute("prevPage"); // Xóa URL trang trước đó khỏi session
                return "redirect:" + prevPage;
            } else {
                return "redirect:/";
            }
        } else {
            String errorMessage = "Tên đăng nhập hoặc mật khẩu không đúng.";
            model.addAttribute("error1", errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "login-form";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session,Model model) {
        // Xóa thông tin người dùng khỏi phiên làm việc
        session.removeAttribute("loggedInUser");
        return "redirect:/users/login";
    }



    @GetMapping("/change-password")
    public String showChangePasswordForm(HttpSession session,Model model) {
        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/users/login";
        }
        model.addAttribute("loggedInUser",loggedInUser);

        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        Account loggedInUser = (Account) session.getAttribute("loggedInUser");

        String passcu = userService.md5(oldPassword);
        if (!loggedInUser.getPassword().equals(passcu)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Mật Khẩu không chính xác");
            return "redirect:/users/change-password";
        }

        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu mới không trùng khớp");
            return "redirect:/users/change-password";
        }

        loggedInUser.setPassword(userService.md5(newPassword));
        userService.save(loggedInUser);

        return "redirect:/users/logout";
    }

    @GetMapping("/quanLyLienKet/{status}")
    public String quanli(@PathVariable byte status,
                         HttpSession session,
                         Model model) {
        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/users/login";
        }
        if (loggedInUser.role != 1) {
            return "redirect:/";
        }
        List<ListUrl> listUrl = urlService.getAlllink(status);

        model.addAttribute("listUrl",listUrl);
        model.addAttribute("loggedInUser",loggedInUser);

        return "linkindex";
    }

    @GetMapping("/xoaLienKet/{id}")
    public String xoaLienKet(@PathVariable Long id) {
        urlService.xoaLienKet(id);
        return "redirect:/users/quanLyLienKet/1";
    }

    @PostMapping("/suaLienKet/{id}")
    public String suaLienKet(@PathVariable Long id,
                             @RequestParam("linkRutGon") String link) {
        ListUrl lienKet = urlService.getLienKetById(id);
        lienKet.linkRutGon = link;
        urlService.save(lienKet);
        return "redirect:/users/quanLyLienKet/1";
    }


    @PostMapping("/themLienKet")
    public String themLienKet(@RequestParam("linkRutGon") String linkRutGon) {
        urlService.themLienKet(linkRutGon);
        return "redirect:/users/quanLyLienKet/1";
    }

}

