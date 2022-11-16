package com.example.talaba.Repository;

import com.example.talaba.Entity.QorovulBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QorovulRepository extends JpaRepository<QorovulBase,Integer> {
    boolean existsById(Integer id);
}
