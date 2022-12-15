package hu.inf.unideb.NSdeIK_RestaurantWeb.service.impl;

import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.*;
import hu.inf.unideb.NSdeIK_RestaurantWeb.entity.*;
import hu.inf.unideb.NSdeIK_RestaurantWeb.repository.*;
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
    MegrendelesVarolistaRepository megrendelesVarolistaRepository;

    @Autowired
    MegrendelesRepository megrendelesRepository;

    @Autowired
    EtlapRepository etlapRepository;

    @Autowired
    VeglegesitesRepository veglegesitesRepository;

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
                    map.put("varolista", megrendelesVarolistaRepository.findAllByAsztalid(id));
                    map.put("megrendelesek", megrendelesRepository.findAllByAsztalid(id));
                    return map;
                }
            }
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
        osszesMegrendelesekTorlese(id);
    }

    protected void osszesMegrendelesekTorlese(String id){
        List<MegrendelesEntity> lst = megrendelesRepository.findAllByAsztalid(id);
        lst.forEach(item -> megrendelesRepository.deleteById(item.getId()));
        List<MegrendelesVarolistaEntity> lst2 = megrendelesVarolistaRepository.findAllByAsztalid(id);
        lst2.forEach(item -> megrendelesVarolistaRepository.deleteById(item.getId()));
        lefoglalRepository.deleteById(id);
    }

    @Override
    public void veglegesites(VeglegesitesDto veglegesitesDto){
        VeglegesitesEntity veglegesitesEntity = modelMapper.map(veglegesitesDto,VeglegesitesEntity.class);
        veglegesitesRepository.save(veglegesitesEntity);

        String id = veglegesitesDto.getAsztal_id();
        osszesMegrendelesekTorlese(id);
        Optional<AsztalEntity> asztal = asztalRepository.findById(id);
        if(asztal.isPresent()){
            asztal.get().setStatusz("szabad");
            asztalRepository.save(asztal.get());
        }
    }

    @Override
    public MegrendelesVarolistaDto ujMegrendelesVarolista(MegrendelesVarolistaDto megrendelesVarolistaDto) {
        MegrendelesVarolistaEntity megrendelesEntity = modelMapper.map(megrendelesVarolistaDto,MegrendelesVarolistaEntity.class);
        megrendelesEntity = megrendelesVarolistaRepository.save(megrendelesEntity);
        return modelMapper.map(megrendelesEntity, MegrendelesVarolistaDto.class);
    }

    @Override
    public MegrendelesDto ujMegrendeles(MegrendelesDto megrendelesDto) {
        MegrendelesEntity megrendelesEntity = modelMapper.map(megrendelesDto,MegrendelesEntity.class);
        megrendelesEntity = megrendelesRepository.save(megrendelesEntity);
        return modelMapper.map(megrendelesEntity, MegrendelesDto.class);
    }

    @Override
    public List<MegrendelesVarolistaDto> osszesMegrendelesek(){
        List<MegrendelesVarolistaDto> megrendelesDtos = new ArrayList<>();
        for(MegrendelesVarolistaEntity megrendelesEntity : megrendelesVarolistaRepository.findAll())
        {
            megrendelesDtos.add(modelMapper.map(megrendelesEntity,MegrendelesVarolistaDto.class));
        }
        return megrendelesDtos;
    }

    @Override
    public MegrendelesEntity hozzaadMegrendelesekhez(String id){
        Optional<MegrendelesVarolistaEntity> entity = megrendelesVarolistaRepository.findById(id);
        if(entity.isPresent()){
            Optional<EtlapEntity> etlapEntity = etlapRepository.findById(entity.get().getMegrendeles_id());
            if(etlapEntity.isPresent()){
                MegrendelesEntity megrendelesEntity  = MegrendelesEntity.builder().
                        asztalid(entity.get().getAsztalid()).
                        megrendeles_neve(entity.get().getMegrendeles_neve()).
                        megrendeles_db(entity.get().getMegrendeles_db()).
                        megrendeles_ara(etlapEntity.get().getEtlap_ara()).build();

                return megrendelesRepository.save(megrendelesEntity);
            }
        }
        return null;
    }

    @Override
    public void torlesMegrendelesVarolista(String id){
        megrendelesVarolistaRepository.deleteById(id);
    }

}
