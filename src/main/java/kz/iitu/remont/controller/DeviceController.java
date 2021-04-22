package kz.iitu.remont.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.iitu.remont.entities.Device;
import kz.iitu.remont.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
@Api(value = "Device Controller class", description = "This class allows to interact with Device object")
public class DeviceController {
    @Autowired
    private DeviceRepository deviceRepository;

    @ApiOperation(value = "LIst of all devices")
    @GetMapping("")
    public List<Device> deviceList(){
        return deviceRepository.findAll();
    }

    @ApiOperation(value = "LIst of all repaired devices")
    @GetMapping("/repairedDevices")
    public List<Device> repairedDevices(){
        return deviceRepository.findByIsDoneTrue();
    }

    @ApiOperation(value = "LIst of all devices that waiting repairing")
    @GetMapping("/devicesBeforeRepairing")
    public List<Device> devicesBeforeRepairing()
    {
        return deviceRepository.findByIsDoneFalse();
    }

    @ApiOperation(value = "Add new device")
    @PostMapping("")
    public Device newDevice(@RequestBody Device device){
        if (device.getDeviceOwnerName().isEmpty() || device.getExplanation().isEmpty()){
            throw new RuntimeException("fields of device must be filled");
        }
        deviceRepository.save(device);
        return device;
    }

}
