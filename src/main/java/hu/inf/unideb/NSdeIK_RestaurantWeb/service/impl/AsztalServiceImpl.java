package hu.inf.unideb.NSdeIK_RestaurantWeb.service.impl;

import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.AsztalDto;
import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.AsztalLefoglal;
import hu.inf.unideb.NSdeIK_RestaurantWeb.entity.AsztalEntity;
import hu.inf.unideb.NSdeIK_RestaurantWeb.entity.AsztalLefoglalEntity;
import hu.inf.unideb.NSdeIK_RestaurantWeb.entity.SzemelyEntity;
import hu.inf.unideb.NSdeIK_RestaurantWeb.repository.AsztalLefoglalRepository;
import hu.inf.unideb.NSdeIK_RestaurantWeb.repository.AsztalRepository;
import hu.inf.unideb.NSdeIK_RestaurantWeb.repository.SzemelyRepository;
import hu.inf.unideb.NSdeIK_RestaurantWeb.service.AsztalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AsztalServiceImpl implements AsztalService {

    @Autowired
    AsztalRepository asztalRepository;

    @Autowired
    SzemelyRepository szemelyRepository;

    @Autowired
    AsztalLefoglalRepository lefoglalRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<AsztalDto> osszes(){
        List<AsztalDto> asztalDtos = new ArrayList<>();
        for(AsztalEntity asztalEntity : asztalRepository.findAll())
        {
            asztalDtos.add(modelMapper.map(asztalEntity,AsztalDto.class));
        }
        return asztalDtos;
    }

    @Override
    public AsztalDto ujAsztal(AsztalDto asztalDto){
        AsztalEntity asztalEntity = modelMapper.map(asztalDto,AsztalEntity.class);
        asztalEntity = asztalRepository.save(asztalEntity);
        return modelMapper.map(asztalEntity, AsztalDto.class);
    }

    @Override
    public Object getAsztal(String id){
        Optional<AsztalEntity> asztalEntity = asztalRepository.findById(id);
        AsztalDto asztalDto = modelMapper.map(asztalEntity.get(),AsztalDto.class);
        if(!asztalDto.getStatusz().equals("szabad"))
        {
            Optional<AsztalLefoglalEntity> lefoglaltAsztal = lefoglalRepository.findById(id);
            if(lefoglaltAsztal.isPresent())
            {
                Optional<SzemelyEntity> szemelynev = szemelyRepository.findById(lefoglaltAsztal.get().getKezelo_szemely_id());
                if(szemelynev.isPresent())
                {
                    List<Map<String , Object>> myMap  = new ArrayList<Map<String,Object>>();
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("asztal_id", asztalDto.getAsztal_id());
                    map.put("asztal_nev", asztalDto.getAsztal_nev());
                    map.put("kezelo_szemely_nev", szemelynev.get().getSzemely_nev());
                    map.put("vendegek_szama", lefoglaltAsztal.get().getVendegek_szama());
                    map.put("maxfo", asztalDto.getMaxfo());
                    map.put("statusz", asztalDto.getStatusz());
                    return map;
                }
            }
            return "Not Okay";
        }
        return asztalDto;
    }


    @Override
    public AsztalLefoglal lefoglalAsztal(AsztalLefoglal asztal) {
        AsztalLefoglalEntity asztalLefoglalEntity = modelMapper.map(asztal,AsztalLefoglalEntity.class);
        Optional<AsztalEntity> asztalDto = asztalRepository.findById(asztal.getAsztal_id());
        if(asztalDto.isPresent())
        {
            asztalDto.get().setStatusz("foglalt");
            asztalRepository.save(asztalDto.get());
        }
        asztalLefoglalEntity = lefoglalRepository.save(asztalLefoglalEntity);
        return modelMapper.map(asztalLefoglalEntity, AsztalLefoglal.class);
    }

    @Override
    public void asztalTorles(String id){
        asztalRepository.deleteById(id);
    }

}
