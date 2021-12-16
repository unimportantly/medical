package org.example.deplacements.repository;

import org.example.deplacements.entity.Deplacement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeplacementRepository extends MongoRepository<Deplacement, String> {
}
