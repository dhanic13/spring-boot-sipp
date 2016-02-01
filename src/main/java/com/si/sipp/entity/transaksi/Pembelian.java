package com.si.sipp.entity.transaksi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.si.sipp.entity.master.Supplier;
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
@Table(name = "trs_pembelian")
public class Pembelian {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 20)
    @Column(name = "nopo", unique=true)
    private String nopo;
    
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
    @JoinColumn(name = "idsupplier", nullable = false)
    private Supplier supplier;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "pembelian", cascade = CascadeType.ALL)
    private List<PembelianDetail> detailPembelian = new ArrayList<>();

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNopo() {
        return nopo;
    }

    public void setNopo(String nopo) {
        this.nopo = nopo;
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<PembelianDetail> getDetailPembelian() {
        return detailPembelian;
    }

    public void setDetailPembelian(List<PembelianDetail> detailPembelian) {
        this.detailPembelian = detailPembelian;
        //for(PembelianDetail p : this.detailPembelian){
        //    p.setPembelian(this);
        //}
    }

       
}
