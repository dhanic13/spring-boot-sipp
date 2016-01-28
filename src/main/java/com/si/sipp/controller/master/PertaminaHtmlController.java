package com.si.sipp.controller.master;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PertaminaHtmlController {

    @RequestMapping(value = "/pertamina", method = RequestMethod.GET)
    public String tampilkanForm(@RequestParam(value = "id", required = false) String id, Model m) {
        // defaultnya, isi dengan object baru
        return "redirect:pertamina_form";
    }

}
