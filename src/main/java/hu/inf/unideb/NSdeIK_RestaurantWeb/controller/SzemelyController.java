package hu.inf.unideb.NSdeIK_RestaurantWeb.controller;

import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.SzemelyDto;
import hu.inf.unideb.NSdeIK_RestaurantWeb.enums.SzemelyPosztok;
import hu.inf.unideb.NSdeIK_RestaurantWeb.security.JWT.JWTUserDetailsService;
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
    private SzemelyService szemelyService;

    @Autowired
    private JWTUserDetailsService jwtUserDetailsService;

    @GetMapping("/szemelyek")
    public ResponseEntity<List<SzemelyDto>> getSzemelyek(){
        return new ResponseEntity<>(szemelyService.osszes(), HttpStatus.OK);
    }

    @GetMapping("/szemely")
    public ResponseEntity<SzemelyDto> getSzemely(@RequestParam(required = true) String id){
        return new ResponseEntity<>(szemelyService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/addPincer")
    public ResponseEntity<?> savePincer(@RequestBody SzemelyDto szemelyDto)
    {
        szemelyDto.setSzemely_poszt(SzemelyPosztok.PINCER);
        return ResponseEntity.ok(jwtUserDetailsService.save(szemelyDto));
    }

    @PostMapping("/addSzakacs")
    public ResponseEntity<?> saveSzakacs(@RequestBody SzemelyDto szemelyDto){
        szemelyDto.setSzemely_poszt(SzemelyPosztok.SZAKACS);
        return ResponseEntity.ok(jwtUserDetailsService.save(szemelyDto));
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
