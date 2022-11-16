package com.example.talaba.Controller;

import com.example.talaba.Dto.GuruhDto;
import com.example.talaba.Entity.FakultetBase;
import com.example.talaba.Entity.FanBase;
import com.example.talaba.Entity.GuruhBase;
import com.example.talaba.Entity.TalabaBase;
import com.example.talaba.Repository.FakultetRepository;
import com.example.talaba.Repository.FanRepository;
import com.example.talaba.Repository.GuruhRepository;
import com.example.talaba.Repository.TalabaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class GuruhController {
    @Autowired
    GuruhRepository guruhRepository;
    @Autowired
    FanRepository fanRepository;
    @Autowired
    TalabaRepository talabaRepository;
    @Autowired
    FakultetRepository fakultetRepository;

    @RequestMapping(value = "/guruhjoylash", method = RequestMethod.POST)
    public String GruhJoyash(@RequestBody GuruhDto guruhDto){
        boolean b = guruhRepository.existsByNomi(guruhDto.getNomi());
        if(b){
            return "Bazada bunday guruh oldindan mavjud";
        }
        Optional<FakultetBase> op=fakultetRepository.findById(guruhDto.getFid());
        if (!op.isPresent()){
            return "Bazada bunday fakultet mavjud emas";
        }

        GuruhBase guruh=new GuruhBase();
        guruh.setNomi(guruhDto.getNomi());
        guruh.setFakultet(fakultetRepository.findById(guruhDto.getFid()).get());

        List<FanBase> fanlar=new ArrayList<>();
        for (Integer i: guruhDto.getFanid()){
            fanlar.add(fanRepository.findById(i).get());
        }
        guruh.setFanlist(fanlar);
        List<TalabaBase> talabaList=new ArrayList<>();
        for (Integer i: guruhDto.getTalabaid()){
            talabaList.add(talabaRepository.findById(i).get());
        }
        guruh.setTalabalist(talabaList);
        guruhRepository.save(guruh);
        return "Bazaga ma'lumot saqlandi";
    }
    @RequestMapping(value = "/guruhoqish",method = RequestMethod.GET)
    public List<GuruhBase> GuruhOqish(){
        return guruhRepository.findAll();
    }


}
