package ge.tsu.gameencyclopedia.game;

import ge.tsu.gameencyclopedia.review.ReviewForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/game/{id}")
    public String viewGame(@PathVariable Long id, Model model) {
        model.addAttribute("game", gameService.findGameById(id));

        if (!model.containsAttribute("reviewForm")) {
            ReviewForm reviewForm = new ReviewForm();
            reviewForm.setGameId(id);
            model.addAttribute("reviewForm", reviewForm);
        }

        return "game/game";
    }

    @GetMapping("/game/new")
    public String createNewGame(Model model) {
        if (!model.containsAttribute("gameForm")) {
            model.addAttribute("gameForm", new GameForm());
        }
        return "game/game_new";
    }

    @PostMapping("/game/new")
    public String createNewGame(@Valid @ModelAttribute("gameForm") GameForm form,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("gameForm", form);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.gameForm", result);
            return "redirect:/game/new";
        }

        try {
            GameDTO game = gameService.saveGame(
                    form.getTitle(),
                    form.getDeveloper(),
                    form.getReleaseYear(),
                    form.getGenre(),
                    form.getDescription(),
                    form.getImages()
            );

            String redirectUrl = UriComponentsBuilder.fromPath("/game/{id}")
                    .buildAndExpand(game.getId())
                    .toUriString();

            return "redirect:" + redirectUrl;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "An error occurred: " + e.getMessage());
            return "redirect:/game/new";
        }
    }
}