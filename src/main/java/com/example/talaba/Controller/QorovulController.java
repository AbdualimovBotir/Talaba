package com.example.talaba.Controller;

import com.example.talaba.Dto.QorovulDto;
import com.example.talaba.Entity.ManzilBase;
import com.example.talaba.Entity.QorovulBase;
import com.example.talaba.Repository.ManzilRepository;
import com.example.talaba.Repository.QorovulRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/qorovul")
public class QorovulController {
    @Autowired
    QorovulRepository qorovulRepository;
    @Autowired
    ManzilRepository manzilRepository;
    @RequestMapping("/oqish")
    @GetMapping
    public List<QorovulBase> QorovulOqish(){
        return qorovulRepository.findAll();
    }

    @RequestMapping("/oqish/{id}")
    @GetMapping
    public QorovulBase OqishId(@PathVariable Integer id) {
        List<QorovulBase> list=qorovulRepository.findAll();
        for (QorovulBase i:list){
            if (i.getId().equals(id)){
                return i;
            }
        }
        return null;
    }

    @RequestMapping("/joylash")
    @PostMapping
    public String QorovulJoylash(@RequestBody QorovulDto dto){
//        boolean b = qorovulRepository.existsById(dto);
        int uzunlik=dto.getIsmi().length();
        if(uzunlik>5){
            return "ism uzunligi 5 harfdan katta";
        }
        QorovulBase qorovulBase=new QorovulBase();
        qorovulBase.setIsmi(dto.getIsmi());
        qorovulBase.setFamilya(dto.getFamilya());
        qorovulBase.setTelraqam((dto.getTelNomer()));
        ManzilBase manzilBase=new ManzilBase();
        manzilBase.setViloyat(dto.getViloyat());
        manzilBase.setTuman(dto.getTuman());
        manzilBase.setKocha(dto.getKocha());
        manzilRepository.save(manzilBase);
        qorovulBase.setManzilBase(manzilBase);
        qorovulRepository.save(qorovulBase);
        return "Bazaga ma'lumotlar saqlandi";
    }
    @RequestMapping("/ochirish/{id}")
    @DeleteMapping
    public String QorovulOchirish(@PathVariable Integer id){
        Optional<QorovulBase> optional=qorovulRepository.findById(id);
        if (!optional.isPresent()){
            return "Bazada bunday ma'lumot topilmadi";
        }
        int manzilid=optional.get().getManzilBase().getId();
        qorovulRepository.deleteById(id);
        manzilRepository.deleteById(manzilid);
        return "Bazadan ma'lumot ochirildi";
    }

    @RequestMapping("/tahrirlash/{id}")
    @PutMapping
    public String QorovulTahrirlash(@PathVariable Integer id, @RequestBody QorovulDto dto){
        Optional<QorovulBase> optional=qorovulRepository.findById(id);
        if (!optional.isPresent()){
            return "Bazada bunday ma'lumot topilmadi";
        }
        QorovulBase base=optional.get();
        base.setIsmi(dto.getIsmi());
        base.setFamilya(dto.getFamilya());
        base.setTelraqam(dto.getTelNomer());
        Optional<ManzilBase> optionalManzilBase=manzilRepository.findById(optional.get().getManzilBase().getId());
        ManzilBase manzilBase=optionalManzilBase.get();
        manzilBase.setViloyat(dto.getViloyat());
        manzilBase.setTuman(dto.getTuman());
        manzilBase.setKocha(dto.getKocha());
        manzilRepository.save(manzilBase);
        qorovulRepository.save(base);
        return "Bazadagi ma'lumot tahrirlandi";
    }
}
