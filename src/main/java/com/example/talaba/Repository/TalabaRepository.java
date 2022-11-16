package com.example.talaba.Repository;

import com.example.talaba.Entity.TalabaBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalabaRepository extends JpaRepository<TalabaBase, Integer> {
    boolean existsByEmail(String email);
    boolean existsById(Integer id);
}
