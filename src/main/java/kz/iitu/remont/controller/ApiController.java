package kz.iitu.remont.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.iitu.remont.entities.RepairCenter;
import kz.iitu.remont.repository.CenterRepository;
import kz.iitu.remont.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ExceptionService exceptionService;

    @GetMapping
    public ResponseEntity<?> nullPointerException(){
        return ResponseEntity.ok(exceptionService.testException());
    }



}
