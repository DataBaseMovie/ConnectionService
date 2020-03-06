package com.connexion.controller;

import com.connexion.entities.User;
import com.connexion.enums.RoleEnum;
import com.connexion.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private UserService uService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public User connect(@Valid User user ) {
        RedirectView redirect = new RedirectView();
        redirect.setContextRelative(true);

        List<RoleEnum> userRole = Collections.singletonList(RoleEnum.USER);
        System.out.println(user);
        user.setRoles(userRole);
        uService.addUser(user);
        System.out.println(user);

        return user;
    }


    @RequestMapping(value = "/connUser", method = RequestMethod.POST)
    public User loginGet(String username, String password) {
        System.out.println("Login");
        User u = uService.verifUser(username, password);
        if (u.getUsername() != null) {
            return u;
        } else {
            return null;
        }
    }

    /**
     * Rest template instance.
     *
     * @return the rest template
     */
    @Autowired
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home(@RequestParam(defaultValue = "1") String page, Model model) {

        return "index";
    }
    @RequestMapping(value = {"/auth"}, method = RequestMethod.GET)
    public String auth(@RequestParam(defaultValue = "1") String page, Model model) {

        return "auth";
    }

}