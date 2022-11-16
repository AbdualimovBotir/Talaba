package com.example.talaba.Repository;

import com.example.talaba.Entity.FanBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FanRepository extends JpaRepository<FanBase,Integer> {
    boolean existsById(Integer integer);
    boolean existsByNomi(String nomi);
}
