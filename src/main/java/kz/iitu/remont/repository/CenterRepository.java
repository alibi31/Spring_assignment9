package kz.iitu.remont.repository;


import kz.iitu.remont.entities.RepairCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CenterRepository extends JpaRepository<RepairCenter,Long> {

}
