package com.si.sipp.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author AdminIT
 */

@Entity
@Table(name = "mst_supplier")
public class Supplier {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty
    @Column(name = "kdsupplier", unique=true)
    private String kdsupplier;
    
    @NotNull
    @NotEmpty
    @Column(name = "nmsupplier")
    private String nmsupplier;
    
    @NotNull
    @NotEmpty
    @Column(name = "alamat")
    private String alamat;
    
    @NotNull
    @NotEmpty
    @Column(name = "kota")
    private String kota;
    
    @NotNull
    @NotEmpty
    @Column(name = "cperson")
    private String cperson;
    
    @NotNull
    @NotEmpty
    @Column(name = "tlp")
    private String tlp;

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKdsupplier() {
        return kdsupplier;
    }

    public void setKdsupplier(String kdsupplier) {
        this.kdsupplier = kdsupplier;
    }

    public String getNmsupplier() {
        return nmsupplier;
    }

    public void setNmsupplier(String nmsupplier) {
        this.nmsupplier = nmsupplier;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getCperson() {
        return cperson;
    }

    public void setCperson(String cperson) {
        this.cperson = cperson;
    }

    public String getTlp() {
        return tlp;
    }

    public void setTlp(String tlp) {
        this.tlp = tlp;
    }
       
    
    
    
}
