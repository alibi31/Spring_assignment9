package kz.iitu.remont.service.impl;


import javassist.NotFoundException;
import kz.iitu.remont.entities.Reparier;
import kz.iitu.remont.repository.ReparierRepository;
import kz.iitu.remont.service.ReparierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReparierServiceImpl implements ReparierService, UserDetailsService {

    @Autowired
    private ReparierRepository reparierRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Reparier reparier = reparierRepository.findByUsername(s);
        if (reparier == null){
            throw new UsernameNotFoundException("Not found such " + s);
        }
        return reparier;
    }

    @Override
    public List<Reparier> getAllRepariers() {
        return reparierRepository.findAll();
    }

    @Override
    public Reparier getReparierByPhone(String phone) {
        Reparier reparier = reparierRepository.findByPhone(phone);
        if (reparier == null){
            throw new RuntimeException("Not found owner with " + phone);
        }
        return reparier;
    }

    @Override
    public void deletReparier(Long id) {
        Reparier reparier = reparierRepository.findById(id).get();
        reparierRepository.delete(reparier);
    }

    @Override
    public Reparier findReparierById(Long id) {
        Reparier reparier = reparierRepository.findById(id).get();
        return reparier;

    }

    @Override
    public void newReparier(Reparier reparier) {
        reparier.setPassword(passwordEncoder.encode(reparier.getPassword()));
        reparierRepository.save(reparier);
    }

    @Override
    public Reparier getByUsername(String username) {
        return reparierRepository.findByUsername(username);
    }
}
