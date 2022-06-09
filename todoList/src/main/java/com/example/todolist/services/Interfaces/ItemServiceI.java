package com.example.todolist.services.Interfaces;

import com.example.todolist.objects.dtos.ItemDto;
import com.example.todolist.objects.models.ItemModel;

import java.util.List;

public interface ItemServiceI {

    boolean createItem(ItemDto item);
    boolean delete(Long item);

    ItemModel getItemById(Long item);

    List<ItemDto> getAllItems();


}
