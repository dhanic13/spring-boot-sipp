package com.si.sipp.controller.master;

import com.si.sipp.dao.master.ItemDao;
import com.si.sipp.entity.master.Item;
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
public class ItemController {
    @Autowired private ItemDao itemDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public Page<Item> daftarItem(Pageable page){
        return itemDao.findAll(page);
    }
    
    @RequestMapping(value="/item/{id}/{sts}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Item getItem(@PathVariable("id") String id, @PathVariable("sts") String sts){
        Item x = itemDao.findOne(id);
        if (x == null) {
           throw new IllegalStateException();
        }   
        return x;
    }
    
    @RequestMapping(value="/item/{kditem}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Item getKdItem(@PathVariable("kditem") String kditem){
        logger.debug("proses Pencarian.....");
        Item x = itemDao.findByKditem(kditem);
        if (x == null) {
            logger.debug("proses Pencarian tidak di temukan.....");
            throw new IllegalStateException();
        }   
        return x;
    }
    
    @RequestMapping(value="/item/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void hapusItem(@PathVariable("id") String id){
        itemDao.delete(id);
    }
    
    @RequestMapping(value="/item", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertItemBaru(@RequestBody @Valid Item s){
        itemDao.save(s);
    }
    
    @RequestMapping(value="/item/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateItem(@PathVariable("id") String id, @RequestBody @Valid Item m){
        m.setId(id);
        itemDao.save(m);
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({IllegalStateException.class})
    public void handle() {
        logger.debug("Resource dengan URI tersebut tidak ditemukan");
    }
    
}
