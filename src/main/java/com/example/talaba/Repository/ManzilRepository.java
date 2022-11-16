package com.example.talaba.Repository;

import com.example.talaba.Entity.ManzilBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManzilRepository extends JpaRepository<ManzilBase,Integer> {
    boolean existsById(Integer id);
}
