package com.example.talaba.Dto;

import lombok.Data;

import java.util.List;

@Data
public class GuruhDto {
    private String nomi;
    private Integer fid;
    private List<Integer> fanid;
    private List<Integer> talabaid;
}
