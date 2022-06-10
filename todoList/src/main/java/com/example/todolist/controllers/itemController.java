package com.example.todolist.controllers;


import com.example.todolist.objects.dtos.ItemDto;
import com.example.todolist.services.Interfaces.ItemServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class itemController {

    @Autowired
    ItemServiceI itemService;

    @PostMapping
    public boolean createItem(@RequestBody ItemDto itemDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return itemService.createItem(itemDto, auth);
    }

    @GetMapping
    @RequestMapping("/all")
    public List<ItemDto> getItens() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return itemService.getAllItems(auth);
    }

    @DeleteMapping
    @RequestMapping("/delete")
    public Boolean deleteItem(@RequestParam Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return itemService.delete(id, auth);
    }
}
