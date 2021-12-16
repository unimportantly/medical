package fr.java.infirmiers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {

    /*
    don't have the need to give it an ID because the address is strongly tied to the infirmier class
    so since it can't exist without the other class, and since it wouldn't exist without the other class
    the id is inferred, kinda.
    IF we used JPA here we could use the @Embedded & @EmbeddedId annotations to achieve the same thing
     */
    private String numero; // -> gère les bis & ter etc...
    private String rue;
    private int cp;
    private String ville;
}
