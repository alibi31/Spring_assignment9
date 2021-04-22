package kz.iitu.remont.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class FinishedDevices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Boolean isTaken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reparier_id")
    private Reparier reparier;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private Device device;
}
