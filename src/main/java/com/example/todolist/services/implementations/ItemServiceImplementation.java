package com.example.todolist.services.implementations;

import com.example.todolist.objects.dtos.ItemDto;
import com.example.todolist.objects.models.ItemModel;
import com.example.todolist.objects.models.UserModel;
import com.example.todolist.repositories.ItemRepository;
import com.example.todolist.repositories.UserRepository;
import com.example.todolist.services.Interfaces.ItemServiceI;
import com.example.todolist.services.Interfaces.UserDetailsServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class ItemServiceImplementation implements ItemServiceI {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean createItem(ItemDto item, Authentication auth) {
        try {
            if (item.getId() != null) {
                throw new IllegalArgumentException("id should be empty");
            }
            UserModel userModel = userRepository.findByUsernameOrEmail(auth.getName(), auth.getName()).get();
            userModel.getItens().add(new ItemModel(item));
            userRepository.saveAndFlush(userModel);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateItem(ItemDto item, Authentication auth) {
        UserModel userModel = userRepository.findByUsernameOrEmail(auth.getName(), auth.getName()).get();
        List<ItemModel> itens = userModel.getItens().stream().filter(todo -> Objects.equals(todo.getId(), item.getId())).collect(Collectors.toList());
        if (!itens.isEmpty()) {
            itens.get(0).setDate(item.getDate());
            itens.get(0).setDescription(item.getDescription());
            itens.get(0).setName(item.getName());
            itens.get(0).setStatus(item.getStatus());
            userRepository.saveAndFlush(userModel);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long itemId, Authentication auth) {
        UserModel userModel = userRepository.findByUsernameOrEmail(auth.getName(), auth.getName()).get();
        List<ItemModel> itemModelList = userModel.getItens().stream().filter(item -> item.getId().equals(itemId)).collect(Collectors.toList());
        if (itemModelList.isEmpty())
            return false;
        userModel.getItens().removeAll(itemModelList);
        userRepository.saveAndFlush(userModel);
        itemRepository.delete(itemModelList.get(0));
        return true;
    }

    @Override
    public ItemDto getItemById(Long item) {
        return new ItemDto(itemRepository.getById(item));
    }

    @Override
    public List<ItemDto> getAllItems(Authentication auth) {
        UserModel userModel = userRepository.findByUsernameOrEmail(auth.getName(), auth.getName()).get();
        return userModel.getItens().stream().map(ItemDto::new).collect(Collectors.toList());
    }
}
