package com.itemservice.service;

import java.util.List;

import com.itemservice.model.Item;

public interface ItemService {

    public List<Item> getItems();
    public Item getItemsById(Long id);
    public Boolean addItem(Item item);
    public Boolean updateItemById(Long id, Item item);
    public Boolean deleteItemById(Long id);
}