package com.example.tbankyomework.object;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private String slug;
    private Coords coords;
}
