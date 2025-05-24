package ge.tsu.gameencyclopedia.search;

import ge.tsu.gameencyclopedia.game.GameDTO;
import ge.tsu.gameencyclopedia.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private GameService gameService;

    @GetMapping("/search")
    public String searchForm() {
        return "search/search_form";
    }

    @PostMapping("/search")
    public String searchResults(@RequestParam("query") String query,
                                @RequestParam("searchType") String searchType,
                                Model model) {

        List<GameDTO> results = gameService.searchGames(query, searchType);
        model.addAttribute("results", results);
        model.addAttribute("query", query);
        model.addAttribute("searchType", searchType);
        return "search/search_results";
    }
}