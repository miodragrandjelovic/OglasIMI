package com.example.Projekat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class indexkontroler {

    @GetMapping("/index")
    public String pocetna()
    {
        return "index";
    }

}
