package com.TejaITB2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TejaITB2.Model.Items;

@Repository
public interface ItemDao extends JpaRepository<Items, Integer> {

}
