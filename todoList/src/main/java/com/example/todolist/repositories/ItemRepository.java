package com.example.todolist.repositories;

import com.example.todolist.objects.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, Long> {

    @Query("SELECT item from ItemModel item where item.id = ?1")
    ItemModel getById(Long id);

    @Query("SELECT item from ItemModel item where item.name = ?1")
    List <ItemModel> getByName(String name);

}
