package hu.inf.unideb.NSdeIK_RestaurantWeb.service.impl;

import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.AsztalDto;
import hu.inf.unideb.NSdeIK_RestaurantWeb.entity.AsztalEntity;
import hu.inf.unideb.NSdeIK_RestaurantWeb.repository.AsztalRepository;
import hu.inf.unideb.NSdeIK_RestaurantWeb.service.AsztalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsztalServiceImpl implements AsztalService {

    @Autowired
    AsztalRepository asztalRepository;

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
    public void asztalTorles(String id){
        asztalRepository.deleteById(id);
    }

}
