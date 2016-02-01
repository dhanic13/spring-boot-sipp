
package com.si.sipp.entity.transaksi;

import com.si.sipp.entity.master.Item;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;


/**
 *
 * @author AdminIT
 */


@Entity
@Table(name = "trs_pembeliandetail")  
public class PembelianDetail  {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
	@JsonBackReference
    @ManyToOne
    @JoinColumn(name="idpo", nullable = false, insertable = true, updatable = true)
    private Pembelian pembelian;
    
    @ManyToOne
    @JoinColumn(name="iditem", nullable = false, insertable = true, updatable = true)
    private Item item;
    
    @NotNull
    @NotEmpty
    @Column(name="qty", nullable=false)
    private BigDecimal qty;
    
    @NotNull
    @NotEmpty
    @Column(name="harga", nullable=false)
    private BigDecimal harga;
    
    @NotNull
    @NotEmpty
    @Min(0)
    @Column(name="bph", nullable=false)    
    private BigDecimal bph = BigDecimal.ZERO;
    
    @NotNull
    @NotEmpty
    @Min(0)
    @Column(name = "pph")
    private BigDecimal pph = BigDecimal.ZERO;
    
    @NotNull
    @NotEmpty
    @Min(0)
    @Column(name = "ppntrans")
    private BigDecimal ppntrans = BigDecimal.ZERO;
    
    @NotNull
    @NotEmpty
    @Min(0)
    @Column(name = "pph22")
    private BigDecimal pph22 = BigDecimal.ZERO;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public BigDecimal getBph() {
        return bph;
    }

    public void setBph(BigDecimal bph) {
        this.bph = bph;
    }

    public BigDecimal getPph() {
        return pph;
    }

    public void setPph(BigDecimal pph) {
        this.pph = pph;
    }

    public BigDecimal getPpntrans() {
        return ppntrans;
    }

    public void setPpntrans(BigDecimal ppntrans) {
        this.ppntrans = ppntrans;
    }

    public BigDecimal getPph22() {
        return pph22;
    }

    public void setPph22(BigDecimal pph22) {
        this.pph22 = pph22;
    }

    public Pembelian getPembelian() {
        return pembelian;
    }

    public void setPembelian(Pembelian pembelian) {
        this.pembelian = pembelian;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
}
