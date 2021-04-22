package kz.iitu.remont.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.iitu.remont.entities.Reparier;
import kz.iitu.remont.service.ExceptionService;
import kz.iitu.remont.service.impl.ReparierServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repariers")
@Api(value = "Reparier Controller class", description = "This class allows to interact with Reparier object")
@Slf4j
public class ReparierController {

    @Autowired
    private ReparierServiceImpl reparierService;


    @ApiOperation(value = "LIst of all device owners")
    @GetMapping("")
    public List<Reparier> reparierList(){
        return reparierService.getAllRepariers();
    }

    @ApiOperation(value = "Reparier by phone number")
    @GetMapping("/byPhone")
    public Reparier reparierByPhone(@RequestParam String phone){
        return reparierService.getReparierByPhone(phone);
    }

    @ApiOperation(value = "Reparier by id")
    @GetMapping("/byId")
    public Reparier reparierById(@RequestParam Long id){
        return reparierService.findReparierById(id);
    }

    @ApiOperation(value = "Delete reparier")
    @DeleteMapping("/{id}")
    public void deleteReparier(@PathVariable Long id) {
        if (id == null){
            throw new NullPointerException("id must not be null");
        }
        reparierService.deletReparier(id);
    }

    @ApiOperation(value = "Reparier by name")
    @GetMapping("/username/{name}")
    public Reparier reparierByName(@PathVariable String username){
        return reparierService.getByUsername(username);
    }

    @ApiOperation(value = "Add new reparier")
    @PostMapping("/signUp")
    public void newReparier(@RequestBody Reparier reparier) {
       Reparier reparier1 = reparierService.getByUsername(reparier.getUsername());
       if (reparier1 != null){
           throw new RuntimeException("With this " + reparier.getUsername() + " is exist");
       }
        if (reparier.getUsername().isEmpty() || reparier.getPassword().isEmpty()){
            throw new RuntimeException("username and password should not be empty");
        }

        reparierService.newReparier(reparier);
    }

}
