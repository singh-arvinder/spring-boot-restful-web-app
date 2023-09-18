package com.example.notinthehighstreetapp.controller;

import com.example.notinthehighstreetapp.model.Item;
import com.example.notinthehighstreetapp.model.ItemRepository;
import com.example.notinthehighstreetapp.model.ItemRequest;
import com.example.notinthehighstreetapp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

//    Constructor Injection
//    @Autowired
//    public ItemController(ItemService itemService) {
//        ItemService = itemService;
//    }

    @PostMapping("/create")
    ResponseEntity<Item> addItem(@RequestBody ItemRequest itemRequest) {
        Item item = itemService.createItem(itemRequest.getName(), itemRequest.getPrice());

        return ResponseEntity.ok(item);
    }

    @GetMapping
    ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemRepository.findAll());
    }

    @GetMapping("/id/{id}")
    ResponseEntity<Item> findItemById(@PathVariable("id") Long id) {
        Optional<Item> itemOpt = itemRepository.findById(id);
        return ResponseEntity.ok(itemOpt.get());
    }
}
