package com.example.todolist.controllers;


import com.example.todolist.objects.ItemDto;
import com.example.todolist.services.Interfaces.ItemServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class itemController {

    @Autowired
    ItemServiceI itemService;

    @PostMapping
    public boolean createItem(@RequestBody ItemDto itemDto) {
        return itemService.createItem(itemDto);
    }

    @GetMapping
    public List<ItemDto> getItens() {
        return itemService.getAllItems();
    }

    @DeleteMapping
    public Boolean deleteItem(@RequestParam Long id) {
        return itemService.delete(id);
    }
}
