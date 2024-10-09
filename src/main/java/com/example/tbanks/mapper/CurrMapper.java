package com.example.tbanks.mapper;

import com.example.tbanks.dto.GetResponse;
import com.example.tbanks.valute.Valute;

public class CurrMapper {

    public GetResponse toGetResponse(Valute valute) {
        return new GetResponse(valute.getCharCode(), valute.getValue());
    }


}
