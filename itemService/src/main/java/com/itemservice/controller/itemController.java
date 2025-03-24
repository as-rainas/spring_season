package com.itemservice.controller;

import com.itemservice.model.Item;
import com.itemservice.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class itemController {

    ItemService itemService;

    public itemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld(){
        return new ResponseEntity<>( "Hello world!!!", HttpStatus.OK);
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getItems(){
        List<Item> items = itemService.getItems();
        if(!items.isEmpty()){
            return new ResponseEntity<>(items, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = itemService.getItemsById(id);
        if (item != null){
            return new ResponseEntity<>(item, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/items")
    public ResponseEntity<String> addItem(@RequestBody Item item) {
        try {
            itemService.addItem(item);
            return new ResponseEntity<>("Added successful", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Not successful", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Long id, @RequestBody Item item) {
        if(itemService.updateItemById(id, item)){
            return new ResponseEntity<>("Updated successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("There is an issue in updating", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        if(itemService.deleteItemById(id)){
            return new ResponseEntity<>("Successfully deleted", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Deletion unsuccessful", HttpStatus.BAD_REQUEST);
    }
}
