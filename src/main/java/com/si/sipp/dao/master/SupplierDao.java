package com.si.sipp.dao.master;

import com.si.sipp.entity.master.Supplier;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SupplierDao extends PagingAndSortingRepository<Supplier, String>{
    Supplier findByKdsupplier(String kdsupplier);
}

/*
public interface SesiDao extends PagingAndSortingRepository<Sesi, String>{
    public Page<Sesi> findByMateri(Materi m, Pageable page);
    
    @Query("select x from Sesi x where x.mulai >= :m "
            + "and x.mulai < :s "
            + "and x.materi.kode = :k "
            + "order by x.mulai desc ")
    public Page<Sesi> cariBerdasarkanTanggalMulaiDanKodeMateri(
            @Param("m") Date mulai, 
            @Param("s") Date sampai, 
            @Param("k") String kode,
            Pageable page);
}
*/