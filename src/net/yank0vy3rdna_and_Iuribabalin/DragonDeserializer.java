package net.yank0vy3rdna_and_Iuribabalin;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class DragonDeserializer{
    private static final com.google.gson.JsonDeserializer<StoredType> des = (JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) -> {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        long id = jsonObject.get("id").getAsLong();
        long owner_id = jsonObject.get("owner_id").getAsLong();
        String name = jsonObject.get("name").getAsString();
        Coordinates coordinates = new Coordinates(
                jsonObject.get("coordinates").getAsJsonObject().get("x").getAsDouble(),
                jsonObject.get("coordinates").getAsJsonObject().get("y").getAsFloat()
        );
        JsonObject creationDateJson = jsonObject.get("creationDate").getAsJsonObject();
        JsonObject date = creationDateJson.get("date").getAsJsonObject();
        JsonObject time = creationDateJson.get("time").getAsJsonObject();
        LocalDateTime creationDate = LocalDateTime.of(
                date.get("year").getAsInt(),
                date.get("month").getAsInt(),
                date.get("day").getAsInt(),
                time.get("hour").getAsInt(),
                time.get("minute").getAsInt(),
                time.get("second").getAsInt(),
                time.get("nano").getAsInt());
        Long age = jsonObject.get("age").getAsLong();
        long weight = jsonObject.get("weight").getAsLong();
        DragonType dragonType;
        try{
            dragonType = DragonType.valueOf(jsonObject.get("type").getAsString());
        }catch (NullPointerException ex){
            dragonType = null;
        }
        DragonCharacter dragonCharacter = DragonCharacter.valueOf(jsonObject.get("character").getAsString());
        Person killer;
        try {
            JsonObject killerJson = jsonObject.get("killer").getAsJsonObject();
            SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy, hh:mm:ss a");
            Location location;
            Date birthday;
            try {
                birthday = format.parse(killerJson.get("birthday").getAsString());
            } catch (NullPointerException | ParseException ex) {
                birthday = null;
            }
            try {
                JsonObject locationJson = killerJson.get("location").getAsJsonObject();
                location = new Location(
                        locationJson.get("x").getAsDouble(),
                        locationJson.get("y").getAsFloat(),
                        locationJson.get("z").getAsLong(),
                        locationJson.get("name").getAsString());
            } catch (NullPointerException ex) {
                location = null;
            }
            killer = new Person( // Person(String name, Date birthday, long height, Long weight, Location location
                    killerJson.get("name").getAsString(),
                    birthday,
                    killerJson.get("height").getAsLong(),
                    killerJson.get("weight").getAsLong(),
                    location);
        }catch (NullPointerException ex){
            killer = null;
        }
        //Dragon(long id, String name, Coordinates coordinates, LocalDateTime creationDate, Long age, long weight, DragonType type, DragonCharacter character, Person killer)
        return (StoredType) new Dragon(id,name,coordinates,creationDate,age,weight,dragonType,dragonCharacter,killer,owner_id);
    };

    public static com.google.gson.JsonDeserializer<StoredType> getDeserializer(){
        return des;
    }
}
