package hu.inf.unideb.NSdeIK_RestaurantWeb.service.impl;

import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.SzemelyDto;
import hu.inf.unideb.NSdeIK_RestaurantWeb.entity.SzemelyEntity;
import hu.inf.unideb.NSdeIK_RestaurantWeb.enums.SzemelyPosztok;
import hu.inf.unideb.NSdeIK_RestaurantWeb.repository.SzemelyRepository;
import hu.inf.unideb.NSdeIK_RestaurantWeb.service.SzemelyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SzemelyServiceImpl implements SzemelyService
{
    @Autowired
    SzemelyRepository szemelyRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<SzemelyDto> osszes(){
        List<SzemelyDto> szemelyDtos = new ArrayList<>();
        for(SzemelyEntity szemelyEntity : szemelyRepository.findAll())
        {

            szemelyDtos.add(modelMapper.map(szemelyEntity,SzemelyDto.class));
        }
        return szemelyDtos;
    }

    @Override
    public List<SzemelyDto> szemelyekLista(){
        List<SzemelyDto> szemelyek = new ArrayList<>();
        for(SzemelyEntity szemelyEntity : szemelyRepository.findAll())
        {
            if(szemelyEntity.getSzemely_poszt() != SzemelyPosztok.TULAJDONOS &&
                    szemelyEntity.getSzemely_poszt() != SzemelyPosztok.SZAKACS)
            {
                SzemelyDto szemely = SzemelyDto.builder().szemely_id(szemelyEntity.getSzemely_id()).szemely_nev(szemelyEntity.getSzemely_nev()).build();
                szemelyek.add(szemely);
            }
        }
        return szemelyek;
    }

    @Override
    public SzemelyDto getById(String id){
        Optional<SzemelyEntity> szemelyEntity = szemelyRepository.findById(id);
        SzemelyDto szemelyDto = modelMapper.map(szemelyEntity.get(),SzemelyDto.class);
        return szemelyDto;
    }

    @Override
    public SzemelyDto szemelyMentes(SzemelyDto szemelyDto){
        SzemelyEntity szemelyEntity = modelMapper.map(szemelyDto,SzemelyEntity.class);
        szemelyEntity = szemelyRepository.save(szemelyEntity);
        return modelMapper.map(szemelyEntity, SzemelyDto.class);
    }

    @Override
    public void szemelyTorles(String id){
        szemelyRepository.deleteById(id);
    }


}
