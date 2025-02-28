package fr.urssaf.livelcti.livelctibe.repository;





//import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.urssaf.livelcti.livelctibe.dto.livelcti.v3.EntrepriseType;
import fr.urssaf.livelcti.livelctibe.entity.EntrepriseEntity;
//import fr.urssaf.livelcti.livelctibe.mapper.GenericMapper;

@Repository
public interface EntrepriseRepository extends CrudRepository<EntrepriseEntity, Integer> {
   // List<EntrepriseType> getEntreprise();
    EntrepriseType save(EntrepriseType entrepriseType);
   // EntrepriseType findById(final String id);
    //List<EntrepriseType> findAll();
}

