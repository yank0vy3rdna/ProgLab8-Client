package net.yank0vy3rdna_and_Iuribabalin;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Dragon implements StoredType, Serializable {
    private long owner_id;

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    static final long serialVersionUID = -7588980448693010399L;
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long age; //Значение поля должно быть больше 0, Поле не может быть null
    private long weight; //Значение поля должно быть больше 0
    private DragonType type; //Поле может быть null
    private DragonCharacter character; //Поле не может быть null
    private Person killer; //Поле может быть null
    private transient String killerName;
    private transient Long killer_weight;
    private transient Long killer_height;
    private transient String killer_birthday;

    public long getId() {
        return id;
    }

    public Long getOwner_id(){
        return owner_id;
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

    public Long getAge() {
        return age;
    }

    public long getWeight() {
        return weight;
    }

    public DragonType getType() {
        return type;
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public Person getKiller() {
        return killer;
    }

    public Dragon(long id, String name, Coordinates coordinates, LocalDateTime creationDate, Long age, long weight, DragonType type, DragonCharacter character, Person killer, long owner_id) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.weight = weight;
        this.type = type;
        this.character = character;
        this.killer = killer;
        this.owner_id = owner_id;
    }

    public Dragon(String name, Coordinates coordinates,Long age, long weight, DragonType type, DragonCharacter character, Person killer, long owner_id) throws IllegalArgumentException, NullPointerException {
        long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.owner_id = owner_id;
        LocalDateTime creationDate = LocalDateTime.now();
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }
        if (age == null || age <= 0) {
            throw new IllegalArgumentException("Invalid age");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Invalid weight");
        }
        this.id = id;
        this.name = name;
        this.coordinates = Objects.requireNonNull(coordinates, "invalid coordinates");
        this.creationDate = creationDate;
        this.age = age;
        this.weight = weight;
        this.type = type;
        this.character = Objects.requireNonNull(character, "invalid character");
        this.killer = killer;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = name.hashCode()
                * creationDate.hashCode()
                * coordinates.hashCode()
                * age.hashCode()
                * (Long.valueOf(weight)).hashCode()
                * character.hashCode();
        if (killer != null)
            hash *= killer.hashCode();
        return hash;
    }

    @Override
    public boolean equals(StoredType obj) {
        return this.compareTo(obj) == 0;
    }
    //Сравнение по параметрам имени, возраста, веса, характера и типа, ЭТОГО ДОСТАТОЧНО, чтобы определить одинаковых драконов

    @Override
    public int compareTo(StoredType obj) {
        if (obj instanceof Dragon) {
            if (age.equals(((Dragon) obj).age)) {
                if (weight == ((Dragon) obj).weight) {
                    if (name.equals(obj.getName())) {
                        if (character.equals(obj.getCharacter())) {
                            if (type.equals(obj.getType())) {
                                return type.compareTo(((Dragon) obj).type);
                            }
                        }
                        return character.compareTo(((Dragon) obj).character);
                    }
                    return name.compareTo(((Dragon) obj).name);
                }
                return Long.compare(weight, ((Dragon) obj).weight);
            }
            return age.compareTo(((Dragon) obj).age);
        }
        return 0;
    }

    @Override

    public String toString(Workerable worker) {
        return worker.writeValue(this);
    }
}
