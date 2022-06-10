package com.example.todolist.services.Interfaces;

import com.example.todolist.objects.dtos.ItemDto;
import com.example.todolist.objects.models.ItemModel;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ItemServiceI {

    boolean createItem(ItemDto item, Authentication auth);
    boolean delete(Long item, Authentication auth);

    ItemModel getItemById(Long item);

    List<ItemDto> getAllItems(Authentication auth);


}
