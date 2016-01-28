package com.si.sipp.dao.master;

import com.si.sipp.entity.master.Customer;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerDao extends PagingAndSortingRepository<Customer, String>{
//    @Query("select c from customer c " +
//        	     "where c.kdcustomer = :kdcustomer")
//    Customer findByKdcustomer(@Param("kdcustomer") String kdcustomer);    
        //List<Customer> findByKdcustomer(String kdcustomer);
    Customer findByKdcustomer(String kdcustomer);
}
