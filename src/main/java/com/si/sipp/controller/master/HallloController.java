package com.si.sipp.controller.master;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HallloController {
    
    @RequestMapping("/halorest")
    @ResponseBody
    public Map<String, Object> haloRest(
            @RequestParam(value="nama", required = false) String nama, Model model){
        Map<String, Object> hasil = new HashMap<>();
        hasil.put("fullname", nama);
        hasil.put("waktu", new Date());
        return hasil;
    }
    
    @RequestMapping("/halo")
    public void haloHtml(@RequestParam(value="nama", required = false) String nama, 
            Model hasil){
        hasil.addAttribute("fullname", nama);
        hasil.addAttribute("waktu", new Date());
    }
}
