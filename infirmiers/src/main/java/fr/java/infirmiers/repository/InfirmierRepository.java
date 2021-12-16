package fr.java.infirmiers.repository;

import fr.java.infirmiers.entity.Infirmier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfirmierRepository extends MongoRepository<Infirmier, String> {
}
