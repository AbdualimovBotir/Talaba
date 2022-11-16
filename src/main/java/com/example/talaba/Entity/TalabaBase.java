package com.example.talaba.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TalabaBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String ism;
    @Column(nullable = false)
    private String familya;
    @Column(nullable = false, unique = true)
    private String telNomer;
    @Column(nullable = false, unique = true)
    private String email;

}
