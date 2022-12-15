package hu.inf.unideb.NSdeIK_RestaurantWeb.controller;

import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.*;
import hu.inf.unideb.NSdeIK_RestaurantWeb.service.AsztalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AsztalController {

    @Autowired
    private AsztalService asztalService;

    @GetMapping("/asztalok")
    public ResponseEntity<List<AsztalDto>> getAsztalok(){
        return new ResponseEntity<>(asztalService.osszes(), HttpStatus.OK);
    }

    @GetMapping("/asztal/{id}")
    public ResponseEntity<?> getAsztal(@PathVariable("id") String id){
        return new ResponseEntity<>(asztalService.getAsztal(id),HttpStatus.OK);
    }

    @PostMapping("/asztal_lefoglalas")
    public ResponseEntity<?> LefoglalAsztal(@RequestBody AsztalLefoglal asztalLefoglal){
        asztalService.lefoglalAsztal(asztalLefoglal);
        return new ResponseEntity<>(asztalService.getAsztal(asztalLefoglal.getAsztal_id()), HttpStatus.OK);
    }

    @PostMapping("/admin/ujAsztal")
    public ResponseEntity<?> ujAsztal(@RequestBody AsztalDto asztalDto)
    {
        return new ResponseEntity<>(asztalService.ujAsztal(asztalDto), HttpStatus.OK);
    }

    @DeleteMapping("/admin/asztalTorles/{id}")
    public ResponseEntity<?> asztalTorles(@PathVariable("id") String id) {
        asztalService.asztalTorles(id);
        return new ResponseEntity<>("",HttpStatus.ACCEPTED);
    }

    @PostMapping("/asztal_ujmegrendelesvarolista")
    public ResponseEntity<?> ujMegrendelesVarolista(@RequestBody MegrendelesVarolistaDto megrendelesVarolistaDto)
    {
        return new ResponseEntity<>(asztalService.ujMegrendelesVarolista(megrendelesVarolistaDto), HttpStatus.OK);
    }

    @PostMapping("/asztal_ujmegrendeles")
    public ResponseEntity<?> ujMegrendeles(@RequestBody MegrendelesDto megrendelesDto)
    {
        return new ResponseEntity<>(asztalService.ujMegrendeles(megrendelesDto), HttpStatus.OK);
    }

    @PostMapping("/veglegesites")
    public ResponseEntity<?> veglegesites(@RequestBody VeglegesitesDto veglegesitesDto){
        asztalService.veglegesites(veglegesitesDto);


        //*2022-12-15*//
        //Instant inst = Instant.parse(veglegesitesDto.getMikor_veglegesitette());
        //LocalDate date = inst.atZone(ZoneId.systemDefault()).toLocalDate();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/osszesmegrendelesek")
    public ResponseEntity<List<MegrendelesVarolistaDto>> osszesMegrendelesek(){
        return new ResponseEntity<>(asztalService.osszesMegrendelesek(), HttpStatus.OK);
    }

    @DeleteMapping("/asztal_torlesmegrendelesvarolista/{id}")
    public ResponseEntity<?> torlesMegrendelesVarolista(@PathVariable("id") String id) {
        asztalService.hozzaadMegrendelesekhez(id);
        asztalService.torlesMegrendelesVarolista(id);
        return new ResponseEntity<>("",HttpStatus.ACCEPTED);
    }

}
