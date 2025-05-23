package ge.tsu.gameencyclopedia.config;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/debug")
public class DebugController {

    private final MessageSource messageSource;

    public DebugController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/locale")
    public String checkLocale() {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("nav.brand", null, currentLocale);
        return String.format("Current Locale: %s, Sample message: %s", currentLocale, message);
    }
}