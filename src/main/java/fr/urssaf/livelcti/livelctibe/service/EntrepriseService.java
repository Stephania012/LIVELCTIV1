package fr.urssaf.livelcti.livelctibe.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

//import org.springframework.stereotype.Service;

import fr.urssaf.livelcti.livelctibe.dto.livelcti.v3.EntrepriseType;
//import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;


public interface EntrepriseService {

    EntrepriseType createEntreprises(EntrepriseType entrepriseType);

    EntrepriseType save(EntrepriseType entrepriseType);
    List<EntrepriseType> getEntreprise();

    EntrepriseType findById(Integer id);

    List<EntrepriseType> findAll();

    void deleteEntrepriseById(Integer id);

    void deleteById(Integer id);

    ResponseEntity<EntrepriseType> saveEntrepriseById(Integer id,
            @Valid EntrepriseType entrepriseType);

    //List<EntrepriseType>
}
