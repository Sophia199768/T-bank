package com.example.tbankyomework.object;


import com.example.tbankyomework.object.Coords;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private String slug;
    private Coords coords;
}
