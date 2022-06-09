package com.example.todolist.services.implementations;

import com.example.todolist.objects.dtos.ItemDto;
import com.example.todolist.objects.models.ItemModel;
import com.example.todolist.repositories.ItemRepository;
import com.example.todolist.services.Interfaces.ItemServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ItemServiceImplementation implements ItemServiceI {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public boolean createItem(ItemDto item) {
        try {
            itemRepository.save(new ItemModel(item));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Long item) {
        ItemModel itemModel = getItemById(item);
        if (itemModel == null)
            return false;
        itemRepository.delete(itemModel);
        return true;
    }

    @Override
    public ItemModel getItemById(Long item) {
        return itemRepository.getById(item);
    }

    @Override
    public List<ItemDto> getAllItems() {
        return itemRepository.findAll().stream().map(ItemDto::new).collect(Collectors.toList());
    }
}
