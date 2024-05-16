package ru.itmo.general.models;

import ru.itmo.general.exceptions.InvalidFormException;
import ru.itmo.general.utils.console.InputParser;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

public class SpaceMarineReader { //todo добавить валидацию while(true)
    private static final AtomicLong idGenerator = new AtomicLong(1);  // Статический генератор для ID
    private long id;

    private String name;
    private Coordinates coordinates;
    private LocalDate creationDate;
    private long health;
    private boolean loyal;
    private Weapon weaponType;
    private MeleeWeapon meleeWeapon;
    private Chapter chapter;
    private InputParser inputParser;

    public SpaceMarineReader() {
        this.id = idGenerator.getAndIncrement(); // Присваивание и инкрементация ID
        this.inputParser = new InputParser();
    }

    public SpaceMarineReader setName() {
        this.name = inputParser.requestString("Enter name: ");
        return this;
    }

    public SpaceMarineReader setCoordinates() {
        double x = inputParser.requestDouble("Enter coordinate X: ");
        double y = inputParser.requestDouble("Enter coordinate Y: ");
        this.coordinates = new Coordinates.Builder()
                .setX(x)
                .setY(y)
                .build();
        return this;
    }

    public SpaceMarineReader setHealth() {
        this.health = inputParser.requestLong("Enter health: ");
        return this;
    }

    public SpaceMarineReader setLoyal() {
        this.loyal = inputParser.requestBoolean("Is loyal? ");
        return this;
    }


    public SpaceMarineReader setWeaponType() {
        // Assume Weapon is an enum
        this.weaponType = Weapon.valueOf(inputParser.requestString("Enter weapon type: ").toUpperCase());
        return this;
    }

    public SpaceMarineReader setMeleeWeapon() {
        // Assume MeleeWeapon is an enum
        this.meleeWeapon = MeleeWeapon.valueOf(inputParser.requestString("Enter melee weapon type: ").toUpperCase());
        return this;
    }

    public SpaceMarineReader setChapter() {
        String chapterName = inputParser.requestString("Enter chapter name: ");
        String parentLegion = inputParser.requestString("Enter parent legion: ");
        this.chapter = new Chapter.Builder()
                .setName(chapterName)
                .setParentLegion(parentLegion).build();
        return this;
    }

    public SpaceMarine build() throws InvalidFormException {
        return new SpaceMarine.Builder()
                .setId(id)
                .setName(name)
                .setChapter(chapter)
                .setCoordinates(coordinates)
                .setHealth(health)
                .setLoyal(loyal)
                .setMeleeWeapon(meleeWeapon)
                .setWeaponType(weaponType)
                .build();

    }
}
