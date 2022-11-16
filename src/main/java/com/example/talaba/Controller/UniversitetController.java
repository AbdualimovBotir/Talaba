package com.example.talaba.Controller;

import com.example.talaba.Dto.UniversitetDto;
import com.example.talaba.Entity.ManzilBase;
import com.example.talaba.Entity.TalabaBase;
import com.example.talaba.Entity.UniversitetBase;
import com.example.talaba.Repository.ManzilRepository;
import com.example.talaba.Repository.UniversitetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversitetController{
    @Autowired
    UniversitetRepository Urepository;
    @Autowired
    ManzilRepository Mrepository;
    @RequestMapping(value = "/uyozish",method = RequestMethod.POST)
    public String Uyozish(@RequestBody UniversitetDto universitet){
        UniversitetBase base = new UniversitetBase();
        base.setNomi(universitet.getNomi());
        ManzilBase manzilBase = new ManzilBase();
        manzilBase.setViloyat(universitet.getViloyat());
        manzilBase.setTuman(universitet.getTuman());
        manzilBase.setKocha(universitet.getKocha());
        Mrepository.save(manzilBase);
        base.setManzil(manzilBase);
        Urepository.save(base);
        return "Bazaga ma'lumotlar muvoffaqiyatli saqlandi";
    }
    @RequestMapping(value = "/uoqish", method = RequestMethod.GET)
    public List<UniversitetBase> UOqish(){
        return Urepository.findAll();
    }
    @RequestMapping(value = "/utahrirlash/{id}", method = RequestMethod.PUT)
    public String UTahrirlash(@PathVariable Integer id, @RequestBody UniversitetDto base){
        Optional<UniversitetBase> Uoptional=Urepository.findById(id);
        if (!Uoptional.isPresent()){
            return "Bazada bunday idli ma'lumot mavjud emas";
        }
        Optional<ManzilBase> Moptional=Mrepository.findById(Uoptional.get().getId());
        UniversitetBase universitetBase=Uoptional.get();
        universitetBase.setNomi(base.getNomi());
        ManzilBase manzilBase=Moptional.get();
        manzilBase.setViloyat(base.getViloyat());
        Mrepository.save(manzilBase);
        Urepository.save(universitetBase);
        return "Tahrirlandi";
    }
    @RequestMapping(value = "/ochirish/{id}", method = RequestMethod.DELETE)
    public  String UOchirish(@PathVariable Integer id){
        Optional<UniversitetBase> Uoptional=Urepository.findById(id);
        if (!Uoptional.isPresent()){
            return "Bazada bunday idli ma'lumot mavjud emas";
        }
        Urepository.deleteById(id);
        int ID=Uoptional.get().getManzil().getId();
        Optional<ManzilBase> Moptional=Mrepository.findById(ID);
        Mrepository.deleteById(ID);
        return "O'chirildi";
    }
}
