package kz.iitu.remont.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String year;
    private String manufacturer;
    private String explanation;
    private Boolean isDone = false;
    private String deviceOwnerPhone;
    private String deviceOwnerName;


    @OneToOne(mappedBy = "device", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Getter(AccessLevel.NONE)
    private FinishedDevices finishedDevices;
}
