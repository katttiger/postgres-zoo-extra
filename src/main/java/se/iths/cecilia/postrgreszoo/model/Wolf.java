package se.iths.cecilia.postrgreszoo.model;

import jakarta.persistence.*;

@Entity
public class Wolf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "fur_color", nullable = false)
    private String furColor;

    @Column(name = "howl_key", nullable = false)
    private String howlKey;

    public Wolf() {
    }

    public Wolf(Long id, int age, String name, String furColor, String howlKey) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.furColor = furColor;
        this.howlKey = howlKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFurColor() {
        return furColor;
    }

    public void setFurColor(String furColor) {
        this.furColor = furColor;
    }

    public String getHowlKey() {
        return howlKey;
    }

    public void setHowlKey(String howlKey) {
        this.howlKey = howlKey;
    }
}
