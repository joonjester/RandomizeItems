package com.example.randomizeitems.Controller;

import com.example.randomizeitems.Entity.ItemEntity;
import com.example.randomizeitems.Service.ItemServices;
import com.example.randomizeitems.Service.RandomizeItems;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemServices itemServices;
    private final RandomizeItems randomizeItems;

    public ItemController(ItemServices itemServices, RandomizeItems randomizeItems) {
        this.itemServices = itemServices;
        this.randomizeItems = randomizeItems;
    }

    @GetMapping("/allItems")
    public List<ItemEntity> getAllItems() {
        return itemServices.getAllItems();
    }

    @PostMapping("/createItem")
    public ItemEntity item(@RequestBody ItemEntity itemEntity){
        return itemServices.create(itemEntity);
    }

    @GetMapping("/randomize")
    public String randomizeItems(@RequestParam List<Long> ids){
        List<ItemEntity> items = itemServices.getAllIds(ids);
        int amountOfItems = items.size();
        return randomizeItems.getRandomItem(amountOfItems, items);
    }

    @PostMapping("/getIds")
    public Long receiveBox(@RequestBody ItemEntity item) {
        String nameOfBox = item.getName();
        String categoryOfBox = item.getCategory();
        return itemServices.findItemIdByNameAndCategory(nameOfBox, categoryOfBox);
    }
}
