package kz.iitu.remont.repository;


import kz.iitu.remont.entities.Reparier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparierRepository extends JpaRepository<Reparier,Long> {
    Reparier findByPhone(String phone);
    Reparier findByUsername(String username);
}
