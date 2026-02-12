package se.iths.cecilia.postrgreszoo.model;

import jakarta.persistence.*;

@Entity
public class Lion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "kills")
    private int kills;

    public Lion() {
    }

    public Lion(String name, int age, double weight, int kills) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.kills = kills;
    }

    public Lion(Long id, String name, int age, double weight, int kills) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.kills = kills;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }
}
