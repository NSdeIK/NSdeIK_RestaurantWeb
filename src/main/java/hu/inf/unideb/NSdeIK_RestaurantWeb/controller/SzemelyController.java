package hu.inf.unideb.NSdeIK_RestaurantWeb.controller;

import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.SzemelyDto;
import hu.inf.unideb.NSdeIK_RestaurantWeb.enums.SzemelyPosztok;
import hu.inf.unideb.NSdeIK_RestaurantWeb.service.SzemelyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SzemelyController
{
    @Autowired
    SzemelyService szemelyService;

    @GetMapping("/initDb")
    public ResponseEntity initDb(){
        try{
            szemelyService.szemelyMentes(
                    SzemelyDto.builder()
                            .szemely_nev("Tulajdonos Neve")
                            .szemely_poszt(SzemelyPosztok.TULAJDONOS)
                            .build()
            );
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/szemelyek")
    public ResponseEntity<List<SzemelyDto>> getSzemelyek(){
        return new ResponseEntity<>(szemelyService.osszes(), HttpStatus.OK);
    }

    @GetMapping("/szemely")
    public ResponseEntity<SzemelyDto> getSzemely(@RequestParam(required = true) String id){
        return new ResponseEntity<>(szemelyService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/addPincer")
    public ResponseEntity<SzemelyDto> savePincer(@RequestBody(required = true) SzemelyDto szemelyDto){
        if(!szemelyDto.getSzemely_nev().isEmpty())
        {
            szemelyDto.setSzemely_poszt(SzemelyPosztok.PINCER);
            return new ResponseEntity<>(szemelyService.szemelyMentes(szemelyDto), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/addSzakacs")
    public ResponseEntity<SzemelyDto> saveSzakacs(@RequestParam(required = true) SzemelyDto szemelyDto){
        if(!szemelyDto.getSzemely_nev().isEmpty())
        {
            szemelyDto.setSzemely_poszt(SzemelyPosztok.SZAKACS);
            return new ResponseEntity<>(szemelyService.szemelyMentes(szemelyDto), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/szemelyTorles")
    public ResponseEntity deleteSzemely(@RequestParam(required = true) String id) {
        szemelyService.szemelyTorles(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/szemelyFrissites")
    public ResponseEntity<SzemelyDto> frissitesSzemely(@RequestBody SzemelyDto szemelyDto, @RequestParam String id) {
        SzemelyDto szemely = szemelyService.getById(id);
        szemely.setSzemely_nev(szemelyDto.getSzemely_nev());
        szemely.setSzemely_poszt(szemelyDto.getSzemely_poszt());
        return new ResponseEntity<>(szemelyService.szemelyMentes(szemely), HttpStatus.CREATED);
    }

}
