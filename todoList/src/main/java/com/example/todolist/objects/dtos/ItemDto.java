package com.example.todolist.objects.dtos;


import com.example.todolist.objects.models.ItemModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    String name;
    String description;
    String date;
    Boolean status = false;

    Long id;


    public ItemDto(ItemModel itemModel) {
        this.name = itemModel.getName();
        this.description = itemModel.getDescription();
        this.date = itemModel.getDate();
        this.status = itemModel.getStatus();
        this.id = itemModel.getId();
    }

}
