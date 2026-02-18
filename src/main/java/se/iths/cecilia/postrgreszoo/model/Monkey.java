package se.iths.cecilia.postrgreszoo.model;

import jakarta.persistence.*;

@Entity
public class Monkey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monkey_name", nullable = false)
    private String name;

    @Column(name = "type_of_monkey", nullable = false)
    private String type;

    @Column(name = "country_of_origin", nullable = false)
    private String originCountry;

    @Column(name = "current_habitat", nullable = false)
    private String currentHabitat;

    public Monkey() {
    }

    public Monkey(Long id, String name, String type, String originCountry, String currentHabitat) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.originCountry = originCountry;
        this.currentHabitat = currentHabitat;
    }

    public Monkey(String name, String type, String originCountry, String currentHabitat) {
        this.name = name;
        this.type = type;
        this.originCountry = originCountry;
        this.currentHabitat = currentHabitat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getCurrentHabitat() {
        return currentHabitat;
    }

    public void setCurrentHabitat(String currentHabitat) {
        this.currentHabitat = currentHabitat;
    }
}
