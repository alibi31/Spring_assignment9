package kz.iitu.remont.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.iitu.remont.entities.Device;
import kz.iitu.remont.entities.FinishedDevices;
import kz.iitu.remont.entities.Reparier;
import kz.iitu.remont.repository.DeviceRepository;
import kz.iitu.remont.repository.FinishedDeviceRepository;
import kz.iitu.remont.repository.ReparierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/finishedDevices")
@Api(value = "FinishedDevice Controller class", description = "This class allows to interact with FinishedDevice object")
public class FinishedDeviceController {

    @Autowired
    private FinishedDeviceRepository finishedDeviceRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ReparierRepository reparierRepository;

    @ApiOperation(value = "List of all finished devices")
    @GetMapping("")
    public List<FinishedDevices> repairCenterList(){
        return finishedDeviceRepository.findAll();
    }


    @ApiOperation(value = "End of repairing")
    @PostMapping("/{deviceId}/{repId}")
    public void endOfRepairing( @PathVariable Long deviceId, @PathVariable Long repId){
        LocalDate date = LocalDate.now();
        Device device = deviceRepository.findById(deviceId).get();
        Reparier reparier = reparierRepository.findById(repId).get();
        FinishedDevices finishedDevices = new FinishedDevices();
        device.setIsDone(true);
        finishedDevices.setDevice(device);
        finishedDevices.setDate(date);
        finishedDevices.setIsTaken(false);
        finishedDevices.setReparier(reparier);
        finishedDeviceRepository.save(finishedDevices);
        deviceRepository.save(device);
    }

    @ApiOperation(value = "Take finished device")
    @PatchMapping("/{id}")
    public void takeFinishedDevice(@PathVariable Long id){
        FinishedDevices finishedDevices = finishedDeviceRepository.findById(id).get();
        finishedDevices.setIsTaken(true);
        finishedDeviceRepository.save(finishedDevices);
    }


}
