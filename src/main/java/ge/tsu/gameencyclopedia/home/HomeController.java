package ge.tsu.gameencyclopedia.home;

import ge.tsu.gameencyclopedia.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public String index(Pageable pageable, Model model) {
        Sort sort = Sort.by(Sort.Order.by("createTime").with(Sort.Direction.DESC));
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        model.addAttribute("games", gameService.getAllGames(pageable));
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("totalPages", gameService.totalNumberOfGames() / pageable.getPageSize());

        return "index";
    }
}