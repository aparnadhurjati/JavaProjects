package com.aparna.java.hello_springai.functioncalling.stock;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private String name;
    private String symbol;
    private List<String> prices;
}
