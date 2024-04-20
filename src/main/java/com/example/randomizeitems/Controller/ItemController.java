package com.example.randomizeitems.Controller;

import com.example.randomizeitems.Entity.ItemEntity;
import com.example.randomizeitems.Service.ItemServices;
import com.example.randomizeitems.Service.RandomizeItems;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemServices itemServices;
    private final RandomizeItems randomizeItems;

    public ItemController(ItemServices itemServices, RandomizeItems randomizeItems) {
        this.itemServices = itemServices;
        this.randomizeItems = randomizeItems;
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

}
