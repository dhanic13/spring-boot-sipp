package com.si.sipp.controller.master;

import com.si.sipp.dao.master.CustomerDao;
import com.si.sipp.entity.master.Customer;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired private CustomerDao cs;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public Page<Customer> daftarCustomer(Pageable page){
        return cs.findAll(page);
    }
    
    @RequestMapping(value="/customer/{cari}/{sts}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable("cari") String cari, @PathVariable("sts") String sts){
        logger.debug("proses Pencarian Editing....."); 
        Customer x = cs.findOne(cari);
        if (x == null) {
            throw new IllegalStateException();
        }  
        return x;
    } 
    
    @RequestMapping(value="/customer/{kdcustomer}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getKdCustomer(@PathVariable("kdcustomer") String kdcustomer){
        logger.debug("proses Pencarian.....");
        Customer x = cs.findByKdcustomer(kdcustomer);
        if (x == null) {
            logger.debug("proses Pencarian tidak di temukan.....");
            throw new IllegalStateException();
        }   
        return x;
    }
    
    @RequestMapping(value="/customer/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void hapusCustomer(@PathVariable("id") String id){
        cs.delete(id);
    }
    
    @RequestMapping(value="/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertCustomerBaru(@RequestBody @Valid Customer m){
        cs.save(m);
    }
    
    @RequestMapping(value="/customer/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@PathVariable("id") String id, @RequestBody @Valid Customer m){
        m.setId(id);
        cs.save(m);
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({IllegalStateException.class})
    public void handle() {
        logger.debug("Resource dengan URI tersebut tidak ditemukan");
    }
    /*
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        // prepare responseEntity
        return responseEntity;
    }*/
    
}
