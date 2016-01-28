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
@Table(name = "mst_item")
public class Item {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty
    @Column(name = "kditem", unique=true)
    private String kditem;
    
    @NotNull
    @NotEmpty
    @Column(name = "nmitem")
    private String nmitem;
    

        
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKditem() {
        return kditem;
    }

    public void setKditem(String kditem) {
        this.kditem = kditem;
    }

    public String getNmitem() {
        return nmitem;
    }

    public void setNmitem(String nmitem) {
        this.nmitem = nmitem;
    }

         
    
    
    
}
