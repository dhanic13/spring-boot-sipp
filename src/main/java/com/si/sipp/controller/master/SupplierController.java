package com.si.sipp.controller.master;

import com.si.sipp.dao.master.SupplierDao;
import com.si.sipp.entity.master.Customer;
import com.si.sipp.entity.master.Supplier;
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
public class SupplierController {
    @Autowired private SupplierDao supplierDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping(value = "/supplier", method = RequestMethod.GET)
    public Page<Supplier> daftarSupplier(Pageable page){
        return supplierDao.findAll(page);
    }
    
    @RequestMapping(value="/supplier/{id}/{sts}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Supplier getSupplier(@PathVariable("id") String id, @PathVariable("sts") String sts){
/*        if (sts == "S") {
           Supplier x = supplierDao.findByKdsupplier(id);
           if (x == null) {
           throw new IllegalStateException();
           }   
           return x;
        } 
        else {  */
        Supplier x = supplierDao.findOne(id);
        if (x == null) {
           throw new IllegalStateException();
        }   
        return x;
        
    }
    
    @RequestMapping(value="/supplier/{kdsupplier}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Supplier getKdSupplier(@PathVariable("kdsupplier") String kdsupplier){
        logger.debug("proses Pencarian.....");
        Supplier x = supplierDao.findByKdsupplier(kdsupplier);
        if (x == null) {
            logger.debug("proses Pencarian tidak di temukan.....");
            throw new IllegalStateException();
        }   
        return x;
    }
    
    @RequestMapping(value="/supplier/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void hapusSupplier(@PathVariable("id") String id){
        supplierDao.delete(id);
    }
    
    @RequestMapping(value="/supplier", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertSupplierBaru(@RequestBody @Valid Supplier s){
        supplierDao.save(s);
    }
    
    @RequestMapping(value="/supplier/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateSupplier(@PathVariable("id") String id, @RequestBody @Valid Supplier m){
        m.setId(id);
        supplierDao.save(m);
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({IllegalStateException.class})
    public void handle() {
        logger.debug("Resource dengan URI tersebut tidak ditemukan");
    }
}
