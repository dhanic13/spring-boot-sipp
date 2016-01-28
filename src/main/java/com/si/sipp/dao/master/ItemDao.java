package com.si.sipp.dao.master;

import com.si.sipp.entity.master.Item;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemDao extends PagingAndSortingRepository<Item, String>{
    Item findByKditem(String kditem);
}
