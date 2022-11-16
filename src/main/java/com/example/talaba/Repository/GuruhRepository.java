package com.example.talaba.Repository;

import com.example.talaba.Entity.GuruhBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuruhRepository extends JpaRepository<GuruhBase,Integer> {
    boolean existsById(Integer id);
    boolean existsByNomi(String nomi);
}
