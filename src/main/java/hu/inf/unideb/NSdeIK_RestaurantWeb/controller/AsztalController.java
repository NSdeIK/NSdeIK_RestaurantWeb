package hu.inf.unideb.NSdeIK_RestaurantWeb.controller;

import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.AsztalDto;
import hu.inf.unideb.NSdeIK_RestaurantWeb.service.AsztalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
