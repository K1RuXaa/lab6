package ru.itmo.general.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import ru.itmo.general.exceptions.InvalidFormException;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SpaceMarine implements Serializable {
    /**
     * Главный класс объекта
     */

    private long id; // Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; // Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm-ss")
    private LocalDateTime creationDate; // Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long health; // Значение поля должно быть больше 0
    private boolean loyal;
    private Weapon weaponType; // Поле может быть null
    private MeleeWeapon meleeWeapon; // Поле может быть null
    private Chapter chapter; // Поле может быть null

    private SpaceMarine(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.coordinates = builder.coordinates;
        this.creationDate = builder.creationDate;
        this.health = builder.health;
        this.loyal = builder.loyal;
        this.weaponType = builder.weaponType;
        this.meleeWeapon = builder.meleeWeapon;
        this.chapter = builder.chapter;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public long getHealth() {
        return health;
    }

    public boolean isLoyal() {
        return loyal;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public Chapter getChapter() {
        return chapter;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nid: " + id + "\nКоординаты: " + coordinates + "\nhealth: " + health +
                "\nweaponType: " + weaponType + "\nmeleeWeapon: " + meleeWeapon + "\nChapter: " + chapter +
                "\nloyal: " + loyal + "\nДата: " + creationDate;
    }

    public static class Builder {
        private long id; // Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
        private String name; // Поле не может быть null, Строка не может быть пустой
        private Coordinates coordinates; // Поле не может быть null
        private LocalDateTime creationDate; // Поле не может быть null, Значение этого поля должно генерироваться автоматически
        private long health; // Значение поля должно быть больше 0
        private boolean loyal;
        private Weapon weaponType; // Поле может быть null
        private MeleeWeapon meleeWeapon; // Поле может быть null
        private Chapter chapter; // Поле может быть null

        public Builder() {
            this.creationDate = LocalDateTime.now(); // Генерация текущей даты и времени
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("name cannot be null or empty");
            }
            this.name = name;
            return this;
        }

        public Builder setCoordinates(Coordinates coordinates) {
            if (coordinates == null) {
                throw new IllegalArgumentException("coordinates cannot be null");
            }
            this.coordinates = coordinates;
            return this;
        }

        public Builder setHealth(long health) {
            if (health <= 0) {
                throw new IllegalArgumentException("health must be greater than 0");
            }
            this.health = health;
            return this;
        }

        public Builder setLoyal(boolean loyal) {
            this.loyal = loyal;
            return this;
        }

        public Builder setWeaponType(Weapon weaponType) {
            this.weaponType = weaponType;
            return this;
        }

        public Builder setMeleeWeapon(MeleeWeapon meleeWeapon) {
            this.meleeWeapon = meleeWeapon;
            return this;
        }

        public Builder setChapter(Chapter chapter) {
            this.chapter = chapter;
            return this;
        }

        public SpaceMarine build() throws InvalidFormException {
            if (this.name == null || this.name.isEmpty()) {
                throw new InvalidFormException("name cannot be null or empty");
            }
            if (this.coordinates == null) {
                throw new InvalidFormException("coordinates cannot be null");
            }
            if (this.health <= 0) {
                throw new InvalidFormException("health must be greater than 0");
            }
            return new SpaceMarine(this);
        }
    }
}
