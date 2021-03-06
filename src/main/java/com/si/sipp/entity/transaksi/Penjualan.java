package com.si.sipp.entity.transaksi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.si.sipp.entity.master.Customer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "trs_penjualan")
public class Penjualan {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 20)
    @Column(name = "noso", unique=true)
    private String noso;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date tgl;
    
    @NotNull
    @Min(0)
    @Column(name = "ppn")
    private BigDecimal ppn;
    
    @NotNull
    @Min(0)
    @Column(name = "pbbkb")
    private BigDecimal pbbkb;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "idcustomer", nullable = false)
    private Customer customer;
    
    @JsonIgnore
    @OneToMany(mappedBy = "penjualan", cascade = CascadeType.ALL)
    private List<PenjualanDetail> detailPenjualan = new ArrayList<>();

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoso() {
        return noso;
    }

    public void setNoso(String noso) {
        this.noso = noso;
    }

    public Date getTgl() {
        return tgl;
    }

    public void setTgl(Date tgl) {
        this.tgl = tgl;
    }

    public BigDecimal getPpn() {
        return ppn;
    }

    public void setPpn(BigDecimal ppn) {
        this.ppn = ppn;
    }

    public BigDecimal getPbbkb() {
        return pbbkb;
    }

    public void setPbbkb(BigDecimal pbbkb) {
        this.pbbkb = pbbkb;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<PenjualanDetail> getDetailPenjualan() {
        return detailPenjualan;
    }

    public void setDetailPenjualan(List<PenjualanDetail> detailPenjualan) {
        this.detailPenjualan = detailPenjualan;
    }

       
}
