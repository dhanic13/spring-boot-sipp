/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.si.sipp.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author AdminIT
 */

@Entity
@Table(name = "mst_customer")
public class Customer {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 5)
    @Column(name = "kdcustomer", unique=true)
    private String kdcustomer;
    
    @NotNull
    @NotEmpty
    @Column(name = "customer")
    private String customer;
    
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
    
    @NotNull
    @Min(0)
    @Column(name = "pbbkb")
    private Integer pbbkb=0;

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKdcustomer() {
        return kdcustomer;
    }

    public void setKdcustomer(String kdcustomer) {
        this.kdcustomer = kdcustomer;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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

    public Integer getPbbkb() {
        return pbbkb;
    }

    public void setPbbkb(Integer pbbkb) {
        this.pbbkb = pbbkb;
    }


    
    
    
    
}
