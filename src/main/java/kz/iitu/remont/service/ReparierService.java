package kz.iitu.remont.service;



import kz.iitu.remont.entities.Reparier;

import java.util.List;

public interface ReparierService {
    List<Reparier> getAllRepariers();
    Reparier getReparierByPhone(String phone);
    void deletReparier(Long id);
    Reparier findReparierById(Long id);
    void newReparier(Reparier reparier);
    Reparier getByUsername(String usernmae);
}
