package com.example.korera.dto;

import com.example.korera.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProject   {
    private Integer id;
    private String name;
}
