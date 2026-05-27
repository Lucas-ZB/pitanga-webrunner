package br.com.lucas.pitanga.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class CircuitProject extends PanacheEntity {
    
    
    public String name;
    public String authorName;
    public String targetBoard; // Ex: TM1638, Arduino Uno
    public String verificationStatus; // Ex: PENDING, PASSED, FAILED

    
    public static CircuitProject findByName(String name){
        return find("name", name).firstResult();
    }
}
