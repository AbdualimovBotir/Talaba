package com.example.talaba.Controller;

import com.example.talaba.Dto.GuruhDto;
import com.example.talaba.Entity.FanBase;
import com.example.talaba.Repository.FakultetRepository;
import com.example.talaba.Repository.FanRepository;
import com.example.talaba.Repository.GuruhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FanController {
    @Autowired
    FanRepository fanRepository;
    @Autowired
    GuruhRepository guruhRepository;

    @RequestMapping(value = "/fanjoylash", method = RequestMethod.POST)
    public String FanJoylash(@RequestBody FanBase malumot){
        boolean b = fanRepository.existsByNomi(malumot.getNomi());
        if (b){
            return "Bazada bunday fan mavjud";
        }
        FanBase fanlar=new FanBase();
        fanlar.setNomi(malumot.getNomi());
        fanRepository.save(fanlar);
        return "Bazaga fan joylandi";
    }

    @RequestMapping(value = "/fanoqish", method = RequestMethod.GET)
    public List<FanBase> FanOqish(){
        return fanRepository.findAll();
    }

    @RequestMapping(value = "/fanochirish/{id}",method = RequestMethod.DELETE)
    public String FanOchirish(@PathVariable Integer id){
        Optional<FanBase> optional=fanRepository.findById(id);
        if (!optional.isPresent()){
            return "Bazada bunday idli malumot topilmadi";
        }
        fanRepository.deleteById(id);
        return "Bazadan ma'lumot o'chirildi.";
    }

    @RequestMapping(value = "/fantahrirlash/{id}", method = RequestMethod.PUT)
    public String FanTahrirlash(@PathVariable Integer id, @RequestBody FanBase fanBase){
        Optional<FanBase> optional=fanRepository.findById(id);
        if (!optional.isPresent()){
            return "Bazada bunday idli malumot topilmadi";
        }
        FanBase base=new FanBase();
        base.setNomi(fanBase.getNomi());
        fanRepository.save(base);
        return "Ma'lumotlar tahrirlandi";
    }
}
