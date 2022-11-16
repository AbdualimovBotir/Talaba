package com.example.talaba.Controller;

import com.example.talaba.Dto.TalabaDto;
import com.example.talaba.Entity.ManzilBase;
import com.example.talaba.Entity.TalabaBase;
import com.example.talaba.Repository.ManzilRepository;
import com.example.talaba.Repository.TalabaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TalabaController {
    @Autowired
    TalabaRepository repository;
    @Autowired
    ManzilRepository manzilRepository;

    @RequestMapping(value = "/yozish", method = RequestMethod.POST)
    public String Yozish(@RequestBody TalabaDto talaba){
        boolean b = repository.existsByEmail(talaba.getEmail());
        if (b){
            return "Bazada bunday email addres mavjud";
        }
        TalabaBase base=new TalabaBase();
        base.setIsm(talaba.getIsm());
        base.setFamilya(talaba.getFamilya());
        base.setTelNomer(talaba.getTelNomer());
        base.setEmail(talaba.getEmail());
        ManzilBase manzilBase=new ManzilBase();
        manzilBase.setViloyat(talaba.getViloyat());
        manzilBase.setTuman(talaba.getTuman());
        manzilBase.setKocha(talaba.getKocha());
        manzilRepository.save(manzilBase);
        base.setManzil(manzilBase);
        repository.save(base);
        return "Bazaga ma'lumotlar muvoffaqiyatli saqlandi";
    }

    @RequestMapping(value = "/oqish", method = RequestMethod.GET)
    public List<TalabaBase> Oqish(){
        return  repository.findAll();
    }

    @RequestMapping(value = "/tahrirlash/{id}", method = RequestMethod.PUT)
    public String Tahrirlash(@PathVariable Integer id, @RequestBody TalabaBase base){
        Optional<TalabaBase> optional=repository.findById(id);
        if (!optional.isPresent()){
            return "Bazada bunday "+id+" idli talaba mavjud emas";
        }
        TalabaBase talaba=optional.get();
        talaba.setIsm(base.getIsm());
        talaba.setFamilya(base.getFamilya());
        repository.save(talaba);
        return "Bazadagi "+id+" li talabani ma'lumotlari tahrirlandi";
    }
    @RequestMapping(value = "/talabaochirish/{id}",method = RequestMethod.DELETE)
    public String TalabaOchirish(@PathVariable Integer id){
        Optional<TalabaBase> optional=repository.findById(id);
        if (!optional.isPresent()){
            return "Bazada bunday idli ma'lumot mavjud emas";
        }
        repository.deleteById(id);
        manzilRepository.deleteById(optional.get().getManzil().getId());
        return "Bazadagi ma'lumot o'chirildi";
    }
}
