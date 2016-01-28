package com.si.sipp.controller.master;

import com.si.sipp.dao.master.ItemDao;
import com.si.sipp.entity.master.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ItemReportController {
    
    @Autowired private ItemDao md;
    
    @RequestMapping("/item")
    public ModelAndView generateReportMateri(ModelAndView m, 
            @RequestParam(value = "format", required = false) String format){
        Iterable<Item> data = md.findAll();
        m.addObject("dataSource", data);
        //m.addObject("tanggalUpdate", new Date());
        m.addObject("format", "pdf");
        
        if(format != null && !format.isEmpty()){
            m.addObject("format", format);
        }
        
        m.setViewName("report_item");
        return m;
    }
}
