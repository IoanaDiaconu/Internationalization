package tutorial.internationalization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tutorial.internationalization.configuration.LocalizedText;
import tutorial.internationalization.configuration.SpringConfiguration;
import tutorial.internationalization.model.User;
import tutorial.internationalization.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ioana on 2/28/2016.
 */
@Controller
@RequestMapping(value = "/app")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserFormValidator userFormValidator;

    @Autowired
    private MessageSource ms;

    private static final List<Locale> supportedLocale = Arrays.asList(Locale.ENGLISH, Locale.FRANCE);

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(userFormValidator);
    }

    // list page
    @RequestMapping(value = "/users/{lang}", method = RequestMethod.GET)
    public String showAllUsers(Model model, @PathVariable String lang) {
        LocalizedText localizedText = getLocalizedText(lang);

        model.addAttribute("users", userService.findAll());
        model.addAttribute("nameLabel", localizedText.getLocalizedText("user.name"));
        model.addAttribute("countryLabel", localizedText.getLocalizedText("user.country"));
        return "/list";
    }

    @RequestMapping(value = "/addUser/{lang}", method = RequestMethod.GET)
    public String add(Model model, @PathVariable String lang) {
        LocalizedText localizedText = getLocalizedText(lang);
        model.addAttribute("nameLabel", localizedText.getLocalizedText("user.name"));
        model.addAttribute("countryLabel", localizedText.getLocalizedText("user.country"));
        return "/addUser";
    }

    private LocalizedText getLocalizedText(@PathVariable String lang) {
        Locale locale = null;

        switch (lang) {
            case "en":
                locale = supportedLocale.get(0);
                break;
            case "fr":
                locale = supportedLocale.get(1);
                break;
            default:
                locale = supportedLocale.get(0);
                break;
        }
        return new LocalizedText(locale);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") @Validated User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(bindingResult.getAllErrors());
            return "views/userForm";
        }

        userService.saveOrUpdate(user);
        return "redirect:/app/users?lang=en";
    }

}

