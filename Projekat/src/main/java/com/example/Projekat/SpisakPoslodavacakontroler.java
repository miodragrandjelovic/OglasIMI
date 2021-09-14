package com.example.Projekat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SpisakPoslodavacakontroler {

    @GetMapping("/SpisakPoslodavaca")
    public String spisak()
    {
        return "SpisakPoslodavaca";
    }

}
