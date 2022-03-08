package com.TejaITB2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TejaITB2.Model.Items;
import com.TejaITB2.Repository.ItemDao;

@Service
public class Itemservice {

	@Autowired
	ItemDao itemDao;

	public void saveItems(Items items) {
		itemDao.save(items);
	}

}
