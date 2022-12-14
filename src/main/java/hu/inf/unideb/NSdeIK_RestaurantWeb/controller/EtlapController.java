package hu.inf.unideb.NSdeIK_RestaurantWeb.controller;

import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.EtlapDto;
import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.SzemelyDto;
import hu.inf.unideb.NSdeIK_RestaurantWeb.enums.EtlapTipusok;
import hu.inf.unideb.NSdeIK_RestaurantWeb.enums.SzemelyPosztok;
import hu.inf.unideb.NSdeIK_RestaurantWeb.service.EtlapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EtlapController {

    @Autowired
    private EtlapService etlapService;

    @PostMapping("/admin/addEtel")
    public ResponseEntity<?> saveEtel(@RequestBody EtlapDto etlapDto)
    {
        etlapDto.setEtlap_tipus(EtlapTipusok.ETEL);
        return ResponseEntity.ok(etlapService.etlapMentes(etlapDto));
    }

    @PostMapping("/admin/addItal")
    public ResponseEntity<?> saveItal(@RequestBody EtlapDto etlapDto){
        etlapDto.setEtlap_tipus(EtlapTipusok.ITAL);
        return ResponseEntity.ok(etlapService.etlapMentes(etlapDto));
    }

    @GetMapping("/osszesEtlap")
    public ResponseEntity<List<EtlapDto>> osszesEtlap(){
        return new ResponseEntity<>(etlapService.osszesEtlap(), HttpStatus.OK);
    }

}
