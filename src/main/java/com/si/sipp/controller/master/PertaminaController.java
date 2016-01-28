package com.si.sipp.controller.master;

import com.si.sipp.dao.master.PertaminaDao;
import com.si.sipp.entity.master.HargaPertamina;
import java.util.Date;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PertaminaController {
    @Autowired private PertaminaDao pertaminaDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping(value = "/pertamina_list", method = RequestMethod.GET)
    public Page<HargaPertamina> daftarHargaPertamina(Pageable page){
        return pertaminaDao.findAll(page);
    }
    
    @RequestMapping(value="/pertamina_form/{cari}/{sts}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public HargaPertamina getPertamina(@PathVariable("cari") String cari, @PathVariable("sts") String sts){
        logger.debug("proses Pencarian Editing....."); 
        HargaPertamina x = pertaminaDao.findOne(cari);
        if (x == null) {
            throw new IllegalStateException();
        }  
        return x;
    } 
    
    @RequestMapping(value="/pertamina_form/{tgl}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public HargaPertamina getTanggal(@PathVariable("tgl") Date tgl){
    //public HargaPertamina getTanggal(@PathVariable @DateTimeFormat(iso=ISO.DATE) Date tgl){        
        logger.debug("proses Pencarian.....");
        HargaPertamina x = pertaminaDao.findByTgl(tgl);
        if (x == null) {
            logger.debug("proses Pencarian tidak di temukan.....");
            throw new IllegalStateException();
        }   
        return x;
    }
    
    @RequestMapping(value="/pertamina_list/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void hapusHargaPertamina(@PathVariable("id") String id){
        pertaminaDao.delete(id);
    }
    
    @RequestMapping(value="/pertamina_form", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertHargaPertaminaBaru(@RequestBody @Valid HargaPertamina s){
        pertaminaDao.save(s);
    }
    
    @RequestMapping(value="/pertamina_form/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateHargaPertamina(@PathVariable("id") String id, @RequestBody @Valid HargaPertamina u){
        u.setId(id);
        pertaminaDao.save(u);
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({IllegalStateException.class})
    public void handle() {
        logger.debug("Resource dengan URI tersebut tidak ditemukan");
    }
}
