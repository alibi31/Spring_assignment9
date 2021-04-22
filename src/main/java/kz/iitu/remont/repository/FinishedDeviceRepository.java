package kz.iitu.remont.repository;


import kz.iitu.remont.entities.Device;
import kz.iitu.remont.entities.FinishedDevices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FinishedDeviceRepository extends JpaRepository<FinishedDevices,Long> {

    FinishedDevices findByDevice(Device device);
}
