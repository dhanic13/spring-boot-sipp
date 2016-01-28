package com.si.sipp.dao.master;

import com.si.sipp.entity.master.HargaPertamina;
import java.util.Date;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PertaminaDao extends PagingAndSortingRepository<HargaPertamina, String>{
    HargaPertamina findByTgl(Date tgl);
}
