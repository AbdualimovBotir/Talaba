package com.example.talaba.Repository;

import com.example.talaba.Entity.UniversitetBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversitetRepository extends JpaRepository<UniversitetBase, Integer> {
    boolean existsById(Integer id);
}
