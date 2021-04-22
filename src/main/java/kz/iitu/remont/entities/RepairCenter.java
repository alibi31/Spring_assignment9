package kz.iitu.remont.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class RepairCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;

    @OneToMany(mappedBy = "repairCenter", fetch = FetchType.LAZY)
    private List<Reparier> repariers;


}
