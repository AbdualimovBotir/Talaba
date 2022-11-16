package com.example.talaba.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Qorovul")

public class QorovulBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,name = "name",columnDefinition = "TEXT")
    private String ismi;
    @Column(nullable = false,name = "firstname",length = 25)
    private String familya;
    @Column(nullable = false, unique = true,name = "phone",length = 13)
    private String telraqam;
    @OneToOne
    ManzilBase manzilBase;
}
