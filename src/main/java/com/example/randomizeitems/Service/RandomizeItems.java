package com.example.randomizeitems.Service;

import com.example.randomizeitems.Entity.ItemEntity;
import com.example.randomizeitems.Repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RandomizeItems implements ItemServices{
    private final ItemRepository itemRepository;

    public RandomizeItems(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public String getRandomItem(int amountOfItems, List<ItemEntity> items) {
        Random random = new Random();
        int randomIndex = random.nextInt(amountOfItems);
        return items.get(randomIndex).getName();
    }

    public boolean doesItemExist(String name, String category) {
        return itemRepository.findByName(name) != null && itemRepository.findByCategory(category) != null;
    }

    @Override
    public ItemEntity create(ItemEntity itemEntity) {
        return itemRepository.save(itemEntity);
    }

    @Override
    public List<ItemEntity> getAllIds(List<Long> ids) {
        return itemRepository.findAllById(ids);
    }

    @Override
    public Long findItemIdByNameAndCategory(String itemName, String category) {
        return itemRepository.findItemIdByNameAndCategory(itemName, category);
    }

    @Override
    public List<ItemEntity> getAllItems() {
        return itemRepository.findAll();
    }
}
