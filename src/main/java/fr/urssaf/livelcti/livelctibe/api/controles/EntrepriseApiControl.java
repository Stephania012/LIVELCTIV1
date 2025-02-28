package fr.urssaf.livelcti.livelctibe.api.controles;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.urssaf.livelcti.livelctibe.api.livelcti.v3.EntreprisesApi;
import fr.urssaf.livelcti.livelctibe.dto.livelcti.v3.EntrepriseType;
//import fr.urssaf.livelcti.livelctibe.entity.EntrepriseEntity;
import fr.urssaf.livelcti.livelctibe.service.EntrepriseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EntrepriseApiControl implements EntreprisesApi {
    @Autowired
    private EntrepriseService entrepriseService;
    private LocalDateTime dtmaj;

    @Override
    public ResponseEntity<EntrepriseType> createEntreprises(final @Valid EntrepriseType entrepriseType) {
        return ResponseEntity.ok(entrepriseService.save(entrepriseType));
    }

    /*@Override
    public ResponseEntity<Void> deleteEntrepriseById(final Integer id) {
        entrepriseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }*/

    @Override
    public ResponseEntity<List<EntrepriseType>> getEntreprises() {
        return ResponseEntity.ok(entrepriseService.findAll());
    }

    /*@Override
    public ResponseEntity<EntrepriseType> saveEntrepriseById(final String id,
        final @Valid  EntrepriseType entrepriseType
    ) {
        return ResponseEntity.ok(entrepriseService.save(entrepriseType));
    }*/

    /*@Override
    public ResponseEntity<Void> getEntrepriseId(final Inte id) {
        ResponseEntity.ok(entrepriseService.findById(id));
        return ResponseEntity.noContent().build();
        //throw new UnsupportedOperationException("Unimplemented method 'getEntrepriseId'");
    }*/
    @PrePersist
    protected void onCreate() {
        dtmaj = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dtmaj = LocalDateTime.now();
    }

    @Override
    public ResponseEntity<Void> deleteEntrepriseById(final Integer id) {
        entrepriseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<EntrepriseType> getEntrepriseById(final Integer id) {
        return ResponseEntity.ok(entrepriseService.findById(id));
    }

    @Override
    public ResponseEntity<EntrepriseType> saveEntrepriseById(final Integer id,
            final @Valid EntrepriseType entrepriseType) {
                EntrepriseType entreprise = entrepriseService.findById(id);
                return ResponseEntity.ok(entrepriseService.save(entrepriseType));
    }

    /*@Override
    public ResponseEntity<EntrepriseType> saveEntrepriseById(final @Valid EntrepriseType entrepriseType) {
        return ResponseEntity.ok(entrepriseService.save(entrepriseType));
    }*/
}
/*
@RestController
@RequestMapping("/entreprise")
@RequiredArgsConstructor
public class EntrepriseApiControl implements EntreprisesApi {
    private final EntrepriseService entrepriseService;

    @Override
    @PostMapping
    public ResponseEntity<EntrepriseType> createEntreprises(@Valid @RequestBody EntrepriseType entrepriseType) {
        return ResponseEntity.ok(entrepriseService.save(entrepriseType));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntrepriseById(@PathVariable final String id) {
        entrepriseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EntrepriseType> getEntrepriseId(@PathVariable final String id) {
        return ResponseEntity.ok(entrepriseService.findById(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<EntrepriseType>> getEntreprises() {
        return ResponseEntity.ok(entrepriseService.findAll());
    }

    @Override
    @PostMapping("/{id}")
    public ResponseEntity<EntrepriseType> saveEntrepriseById(
        @PathVariable final String id,
        @Valid @RequestBody EntrepriseType entrepriseType
    ) {
        return ResponseEntity.ok(entrepriseService.save(entrepriseType));
    }
}

public class EntrepriseApiControl implements EntreprisesApi {
    private EntrepriseService entrepriseService;

    @Override
    @PostMapping
    public ResponseEntity<EntrepriseType> createEntreprises(final @Valid @RequestBody EntrepriseType entrepriseType) {
                return ResponseEntity.ok(entrepriseService.save(entrepriseType));
        //throw new UnsupportedOperationException("Unimplemented method 'createEntreprises'");
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntrepriseById(@PathVariable final String id) {
        entrepriseService.deleteById(id);
        return ResponseEntity.noContent().build();
        //throw new UnsupportedOperationException("Unimplemented method 'deleteEntrepriseById'");
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Void> getEntrepriseId(@PathVariable final String id) {
        ResponseEntity.ok(entrepriseService.findById(id));
        return ResponseEntity.noContent().build();
        //throw new UnsupportedOperationException("Unimplemented method 'getEntrepriseId'");
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<EntrepriseType>> getEntreprises() {
        return ResponseEntity.ok(entrepriseService.findAll());
        //throw new UnsupportedOperationException("Unimplemented method 'getEntreprises'");
    }

    @Override
    @PostMapping("/{id}")
    public ResponseEntity<EntrepriseType> saveEntrepriseById(@PathVariable final String id,
    final @Valid EntrepriseType entrepriseType) {
        EntrepriseType entreprise = new EntrepriseType();
        entreprise = entrepriseService.createEntreprises(entrepriseType);
        return ResponseEntity.ok(entrepriseService.save(entreprise));
        //throw new UnsupportedOperationException("Unimplemented method 'saveEntrepriseById'");
    }
}*/
