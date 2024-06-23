package com.example.randomizeitems.Service;

import com.example.randomizeitems.Entity.ItemEntity;

import java.util.List;

public interface ItemServices{
    ItemEntity create(ItemEntity itemEntity);

    List<ItemEntity> getAllIds(List<Long> ids);

    Long findItemIdByNameAndCategory(String itemName, String category);

    List<ItemEntity> getAllItems();
}
