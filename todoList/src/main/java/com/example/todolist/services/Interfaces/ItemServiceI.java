package com.example.todolist.services.Interfaces;

import com.example.todolist.objects.ItemDto;
import com.example.todolist.objects.ItemModel;

import java.util.List;

public interface ItemServiceI {

    boolean createItem(ItemDto item);
    boolean delete(Long item);

    ItemModel getItemById(Long item);

    List<ItemDto> getAllItems();


}
