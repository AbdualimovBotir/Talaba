package com.example.talaba.Controller;

import com.example.talaba.Dto.FakultetDto;
import com.example.talaba.Entity.FakultetBase;
import com.example.talaba.Entity.UniversitetBase;
import com.example.talaba.Repository.FakultetRepository;
import com.example.talaba.Repository.UniversitetRepository;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class    FakultetContoller {
    @Autowired
    FakultetRepository frepo;
    @Autowired
    UniversitetRepository urepo;

    @RequestMapping(value = "/fakultetoylash",method = RequestMethod.POST)
    public String Fjoylash(@RequestBody FakultetDto fakultet){
        Optional<UniversitetBase> option=urepo.findById(fakultet.getUid());
        if (!option.isPresent()){
            return "Bazada bunday id Universitet mavjud emas!";
        }
//        boolean b=frepo.existsByNomi(fakultet.getNomi());
        boolean b= frepo.existsByNomiAndUniversitetBase_Id(fakultet.getNomi(), fakultet.getUid());
        if (b){
            return "Bazada bunday nomda fakultet bor";
        }
        FakultetBase base=new FakultetBase();
        base.setNomi(fakultet.getNomi());
        base.setUniversitetBase(urepo.findById(fakultet.getUid()).get());
        frepo.save(base);
        return "Bazaga ma'lumotlar saqlandi";
    }
}
