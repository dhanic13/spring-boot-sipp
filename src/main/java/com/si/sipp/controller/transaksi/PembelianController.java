package com.si.sipp.controller.transaksi;

import com.si.sipp.dao.transaksi.PembelianDao;
import com.si.sipp.entity.transaksi.Pembelian;
import com.si.sipp.entity.transaksi.PembelianDetail;
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
public class PembelianController {
    @Autowired private PembelianDao pembelianDao;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping(value = "/pembelian_list", method = RequestMethod.GET)
    public Page<Pembelian> daftarPembelian(Pageable page){
        return pembelianDao.findAll(page);
    }
    
    @RequestMapping(value="/pembelian_form/{cari}/{sts}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Pembelian getPembelian(@PathVariable("cari") String cari, @PathVariable("sts") String sts){
        logger.debug("proses Pencarian Editing....."); 
        Pembelian x = pembelianDao.findOne(cari);
        if (x == null) {
            throw new IllegalStateException();
        }  
        return x;
    } 
    
    @RequestMapping(value="/pembelian_form/{nopo}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Pembelian getKdPembelian(@PathVariable("nopo") String nopo){
        logger.debug("proses Pencarian.....");
        Pembelian x = pembelianDao.findByNopo(nopo);
        if (x == null) {
            logger.debug("proses Pencarian tidak di temukan.....");
            throw new IllegalStateException();
        }   
        return x;
    }
    
    @RequestMapping(value="/pembelian_list/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void hapusPembelian(@PathVariable("id") String id){
        pembelianDao.delete(id);
    }
    
    @RequestMapping(value="/pembelian_form", method = RequestMethod.POST, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody public void addPembelian(@RequestBody @Valid Pembelian pembelian){
        Pembelian m = new Pembelian();
        m.setNopo(pembelian.getNopo());
        m.setTgl(pembelian.getTgl());
        m.setPpn(pembelian.getPpn());
        m.setPbbkb(pembelian.getPbbkb());
        m.setSupplier(pembelian.getSupplier());
        for(PembelianDetail detail : pembelian.getDetailPembelian()) {
            detail.setPembelian(m);
            m.getDetailPembelian().add(detail); 
        } 
        pembelianDao.save(m);
    }
    
    @RequestMapping(value="/pembelian_form/{id}", method = RequestMethod.PUT, method = RequestMethod.POST, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody public void updatePembelian(@PathVariable("id") String id, @RequestBody @Valid Pembelian data){
        data.setId(id);
        /*
        Pembelian m = new Pembelian();
        m.setId(id);
        m.setNopo(data.getNopo());
        m.setTgl(data.getTgl());
        m.setPpn(data.getPpn());
        m.setPbbkb(data.getPbbkb());
        m.setSupplier(data.getSupplier());
        for(PembelianDetail detail : data.getDetailPembelian()) {
            detail.setPembelian(m);
            m.getDetailPembelian().add(detail); 
        } */
        
        pembelianDao.save(data);
        
        
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
