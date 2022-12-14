package hu.inf.unideb.NSdeIK_RestaurantWeb.service.impl;

import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.EtlapDto;
import hu.inf.unideb.NSdeIK_RestaurantWeb.entity.EtlapEntity;
import hu.inf.unideb.NSdeIK_RestaurantWeb.repository.EtlapRepository;
import hu.inf.unideb.NSdeIK_RestaurantWeb.service.EtlapService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EtlapServiceImpl implements EtlapService {

    @Autowired
    EtlapRepository etlapRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public EtlapDto etlapMentes(EtlapDto etlapDto) {
        EtlapEntity etlapEntity = modelMapper.map(etlapDto,EtlapEntity.class);
        etlapEntity = etlapRepository.save(etlapEntity);
        return modelMapper.map(etlapEntity, EtlapDto.class);
    }

    @Override
    public List<EtlapDto> osszesEtlap() {
        List<EtlapDto> etlapDtos = new ArrayList<>();
        for(EtlapEntity etlapEntity : etlapRepository.findAll())
        {
            etlapDtos.add(modelMapper.map(etlapEntity,EtlapDto.class));
        }
        return etlapDtos;
    }
}
