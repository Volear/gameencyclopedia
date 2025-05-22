package ge.tsu.gameencyclopedia.auth;

import ge.tsu.gameencyclopedia.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password");
        }
        if (logout != null) {
            model.addAttribute("successMessage", "You have been logged out successfully");
        }

        if (!model.containsAttribute("loginForm")) {
            model.addAttribute("loginForm", new LoginForm());
        }

        return "auth/login";
    }

    @GetMapping("/auth/register")
    public String registerForm(Model model) {
        if (!model.containsAttribute("registerForm")) {
            model.addAttribute("registerForm", new RegisterForm());
        }
        return "auth/register";
    }

    @PostMapping("/auth/register")
    public String register(@Valid @ModelAttribute("registerForm") RegisterForm form,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {

        if (!form.getPassword().equals(form.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");
        }

        if (userService.existsByUsername(form.getUsername())) {
            result.rejectValue("username", "error.username", "Username already exists");
        }

        if (userService.existsByEmail(form.getEmail())) {
            result.rejectValue("email", "error.email", "Email already exists");
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerForm", form);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerForm", result);
            return "redirect:/auth/register";
        }

        try {
            userService.registerUser(
                    form.getUsername(),
                    form.getEmail(),
                    form.getPassword(),
                    form.getFirstName(),
                    form.getLastName()
            );

            redirectAttributes.addFlashAttribute("successMessage",
                    "Registration successful! Please log in with your credentials.");
            return "redirect:/auth/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Registration failed: " + e.getMessage());
            redirectAttributes.addFlashAttribute("registerForm", form);
            return "redirect:/auth/register";
        }
    }
}