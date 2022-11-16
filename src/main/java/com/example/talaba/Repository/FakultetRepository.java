package com.example.talaba.Repository;

import com.example.talaba.Entity.FakultetBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FakultetRepository extends JpaRepository<FakultetBase  ,Integer> {
    boolean existsById(Integer id);
//    boolean existsByNomi(String nomi);
    boolean existsByNomiAndUniversitetBase_Id(String nomi, Integer universitetBase_id);
}
