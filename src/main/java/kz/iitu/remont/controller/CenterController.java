package kz.iitu.remont.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.iitu.remont.entities.RepairCenter;
import kz.iitu.remont.repository.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
@Api(value = "RepairCenter Controller class", description = "This class allows to interact with RepairCenter object")
public class CenterController {

    @Autowired
    private CenterRepository centerRepository;

    @ApiOperation(value = "LIst of all repairing centers")
    @GetMapping("")
    public List<RepairCenter> repairCenterList(){
        return centerRepository.findAll();
    }


    @ApiOperation(value = "Add new repairing center")
    @PostMapping("")
    public void newCenter(@RequestBody RepairCenter repairCenter){
        if(repairCenter.getLocation() == null || repairCenter.getName() == null){
            throw new RuntimeException("all fields in repair center should be filled (location|name)");
        }
        centerRepository.save(repairCenter);
    }

    @ApiOperation(value = "Update repairing center location")
    @PatchMapping("/{id}/location")
    public void updateCenterLocation (@PathVariable Long id,
                                     @RequestParam String location){
        if (id == null || location.equals("")){
            throw new RuntimeException("id or location should not be empty");
        }
        RepairCenter repairCenter = centerRepository.findById(id).get();
        repairCenter.setLocation(location);
        centerRepository.save(repairCenter);
    }

    @ApiOperation(value = "Update repairing center name")
    @PatchMapping("/{id}/name")
    public void updateCenterName(@PathVariable Long id,
                                     @RequestParam String name){
        if (id == null || name.equals("")){
            throw new RuntimeException("id or location should not be empty");
        }
        RepairCenter repairCenter = centerRepository.findById(id).get();
        repairCenter.setName(name);
        centerRepository.save(repairCenter);
    }

}
