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
    public String item(@RequestBody ItemEntity itemEntity) {
        if (randomizeItems.doesItemExist(itemEntity.getName(), itemEntity.getCategory())) {
            return ("Item already exists");
        }
        itemServices.create(itemEntity);
        return ("Item created successfully");
    }

    @GetMapping("/randomize")
    public String randomizeItems(@RequestParam List<Long> ids){
        List<ItemEntity> items = itemServices.getAllIds(ids);
        int amountOfItems = items.size();
        return randomizeItems.getRandomItem(amountOfItems, items);
    }

    @GetMapping("/getIds")
    public Long receiveBox(@RequestParam String nameOfBox, @RequestParam String categoryOfBox) {

        return randomizeItems.findItemIdByNameAndCategory(nameOfBox, categoryOfBox);
    }
}
