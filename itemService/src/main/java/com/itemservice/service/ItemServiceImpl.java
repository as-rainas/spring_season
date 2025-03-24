package com.itemservice.service;

import com.itemservice.model.Item;
import com.itemservice.model.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{

    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getItems() {
        List<Item> items = itemRepository.findAll();
        if(!items.isEmpty()){
            return items;
        }
        return null;
    }

    @Override
    public Item getItemsById(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.orElse(null);
    }

    @Override
    public Boolean addItem(Item item) {
        if (item != null){
            itemRepository.save(item);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateItemById(Long id, Item item) {
        Item existingItem = getItemsById(id);
        if(existingItem != null){
            existingItem.setName(item.getName());
            existingItem.setDescription(item.getDescription());
            existingItem.setPrice(item.getPrice());
            existingItem.setQuantity(item.getQuantity());

            itemRepository.save(existingItem);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteItemById(Long id) {
        Item item = getItemsById(id);
        if(item != null){
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
