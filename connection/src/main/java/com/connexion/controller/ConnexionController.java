package com.connexion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ConnexionController {

    @RequestMapping( value="/connexion", method = RequestMethod.GET )
    public String getConnexion(){

        return "index";
    }

}
