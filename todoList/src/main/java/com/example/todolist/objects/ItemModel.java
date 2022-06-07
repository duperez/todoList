package com.example.todolist.objects;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    Long id;

    String name;
    String description;
    String date;
    Boolean status;

    public ItemModel(ItemDto itemDto) {
        this.name = itemDto.getName();
        this.description = itemDto.getDescription();
        this.date = itemDto.getDate();
        this.status = itemDto.getStatus();
        this.id = itemDto.getId();
    }
}
