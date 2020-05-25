package net.yank0vy3rdna_and_Iuribabalin;

public class DragonTable {
    private String name;
    private String age;
    private String weight;
    private String type;
    private String character;
    private String killerName;
    private String killer_weight;
    private String killer_height;
    private String killer_birthday;
    private String location_name;
    private long owner_id;
    private long id;


    public DragonTable(long id, String name, String age, String weight, String type, String character, String killerName, String killer_weight, String killer_height, String killer_birthday, String location_name, Long owner_id) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.weight = weight;
        this.type = type;
        this.character = character;
        this.killerName = killerName;
        this.killer_weight = killer_weight;
        this.killer_height = killer_height;
        this.killer_birthday = killer_birthday;
        this.location_name = location_name;
        this.owner_id = owner_id;
    }

    public long getId() {
        return id;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }

    public String getCharacter() {
        return character;
    }

    public String getKillerName() {
        return killerName;
    }

    public String getKiller_weight() {
        return killer_weight;
    }

    public String getKiller_height() {
        return killer_height;
    }

    public String getKiller_birthday() {
        return killer_birthday;
    }

    public String getLocation_name() {
        return location_name;
    }
}
