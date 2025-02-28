package fr.urssaf.livelcti.livelctibe.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.urssaf.livelcti.livelctibe.dto.livelcti.v3.EntrepriseType;
import fr.urssaf.livelcti.livelctibe.entity.EntrepriseEntity;
import fr.urssaf.livelcti.livelctibe.mapper.EntrepriseMapper;
import fr.urssaf.livelcti.livelctibe.service.EntrepriseService;
import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
import fr.urssaf.livelcti.livelctibe.repository.EntrepriseRepository;
//import fr.urssaf.livelcti.livelctibe.repository.Impl.EntrepriseRepositoryImpl;


@Service
@Transactional
public class EntrepriseServiceImpl implements EntrepriseService {
    @Autowired
    private final EntrepriseRepository entrepriseRepository;
    @Autowired
    private EntrepriseMapper entrepriseMapper;

    public EntrepriseServiceImpl(final EntrepriseRepository entrepriseRepository,
    final EntrepriseMapper entrepriseMapper) {
        this.entrepriseRepository = entrepriseRepository;
        this.entrepriseMapper = entrepriseMapper;
    }

    @Override
    public EntrepriseType createEntreprises(final EntrepriseType entrepriseType) {
        EntrepriseEntity entrepriseEntity = entrepriseMapper.toEntity(entrepriseType);
        var entrepriseToSave = entrepriseRepository.save(entrepriseEntity);
        return entrepriseMapper.toDTO(entrepriseToSave);
    }

    @Override
    public EntrepriseType save(final EntrepriseType entrepriseType) {
        EntrepriseEntity entity = entrepriseMapper.toEntity(entrepriseType);
        entity = entrepriseRepository.save(entity);
        return entrepriseMapper.toDTO(entity);
    }

    @Override
    public List<EntrepriseType> getEntreprise() {
        List<EntrepriseEntity> listeEntreprise = new ArrayList<>();
        entrepriseRepository.findAll().forEach(listeEntreprise::add);
        return listeEntreprise.stream().map(entrepriseMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public EntrepriseType findById(final Integer id) {
        return entrepriseRepository.findById(id)
        .map(entrepriseMapper::toDTO)
        .orElseThrow(() -> new RuntimeException("Entreprise introuvable"));
    }

    @Override
    public List<EntrepriseType> findAll() {
        return StreamSupport.stream(entrepriseRepository.findAll().spliterator(), false)
        .map(entrepriseMapper::toDTO)
        .collect(Collectors.toList());
    }

    @Override
    public void deleteEntrepriseById(final Integer id) {
        entrepriseRepository.deleteById(id);
        throw new UnsupportedOperationException(" Configuration Error! Check method 'deleteEntrepriseById'");
    }

    @Override
    public void deleteById(final Integer id) {
        entrepriseRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<EntrepriseType> saveEntrepriseById(final Integer id,
            final @Valid EntrepriseType entrepriseType) {
                Optional<EntrepriseEntity> entreprise = entrepriseRepository.findById(id);
                if (entreprise.isPresent()) {
                    EntrepriseEntity entity = entreprise.get();
                    entity = entrepriseMapper.modify(entity, entrepriseType);
                    entity = entrepriseRepository.save(entity);
                    return ResponseEntity.ok(entrepriseMapper.toDTO(entity));

                } else {
                    return ResponseEntity.notFound().build();
                }
            }
        }
