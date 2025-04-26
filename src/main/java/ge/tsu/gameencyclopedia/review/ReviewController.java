package ge.tsu.gameencyclopedia.review;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review/add")
    public String addReview(@Valid @ModelAttribute("reviewForm") ReviewForm form,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("reviewForm", form);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.reviewForm", result);
            return "redirect:/game/" + form.getGameId();
        }

        try {
            reviewService.saveReview(
                    form.getAuthor(),
                    form.getRating(),
                    form.getContent(),
                    form.getGameId()
            );

            redirectAttributes.addFlashAttribute("successMessage", "Your review has been added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "An error occurred: " + e.getMessage());
        }

        return "redirect:/game/" + form.getGameId();
    }
}