package com.si.sipp.dao.transaksi;

import com.si.sipp.entity.transaksi.Pembelian;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PembelianDao extends PagingAndSortingRepository<Pembelian, String>{
    Pembelian findByNopo(String nopo);
}
