package com.example.randomizeitems.Repositories;

import com.example.randomizeitems.Entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    ItemEntity findByName(String name);

    ItemEntity findByCategory(String category);

    @Query("SELECT i.id FROM ItemEntity i WHERE i.name = :itemName AND i.category = :category")
    Long findItemIdByNameAndCategory(String itemName, String category);
}
