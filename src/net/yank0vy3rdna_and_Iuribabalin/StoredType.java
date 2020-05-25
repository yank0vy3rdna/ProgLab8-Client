package net.yank0vy3rdna_and_Iuribabalin;

public interface StoredType extends Comparable<StoredType> {
    boolean equals(StoredType obj);
    int compareTo(StoredType obj);

    String toString(Workerable worker);

    Long getOwner_id();

    void setOwner_id(long owner_id);

    Long getAge();

    String getName();

    long getId();

    void setId(long id);

    public DragonType getType();

    public Coordinates getCoordinates();

    public long getWeight();

    public DragonCharacter getCharacter();

    public Person getKiller();
}
