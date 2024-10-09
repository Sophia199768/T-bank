package com.example.tbanks.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GetResponse {
    private String currency;
    private String rate;
}
