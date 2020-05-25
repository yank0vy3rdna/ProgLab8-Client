package net.yank0vy3rdna_and_Iuribabalin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.io.*;

public class JSONWorker {
    private final Gson gson;
    public JSONWorker(JsonDeserializer<?> des) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(StoredType.class, des);
        gson = builder.create();
    }

    @SuppressWarnings("unchecked")
    public Object readValue(String json, Class cls){
        try {
            return (gson).fromJson(json, cls);
        }catch (NullPointerException | UnsupportedOperationException e){
            return "Файл битый";
        }
    }

    public String writeValue(Object object){
        return (new GsonBuilder().setPrettyPrinting().create()).toJson(object);
    }

    public Object load(String filename, Class cls){
        try {
            Object object;
            BufferedReader file = new BufferedReader(new FileReader(new File(filename)));
            StringBuilder jsonBuilder = new StringBuilder();
            int c;
            while(file.ready()){
                String string = Character.toString((char)(file.read()));
                jsonBuilder.append(string);
            }
            String json = jsonBuilder.toString();
            object =  cls.cast( readValue(json, cls));
            file.close();
            return object;
        }
        catch (FileNotFoundException e){
            return null;
        }
        catch (IOException e)
        {
            //ex.printStackTrace();
            System.out.println("Дяденька вы дурак");
        }
        //catch (ClassCastException e){
        //return "Файл битый";
        //}
        return null;
    }

    public void save(Storable objects, String filename) {
        try{
            BufferedOutputStream file = new BufferedOutputStream(new FileOutputStream(filename));
            String json;
            json = writeValue(objects);
            byte[] buffer = json.getBytes();
            file.write(buffer);
            file.close();
        } catch (FileNotFoundException ex){
            System.out.println("Ошибка при работе с файлом: " +ex.getLocalizedMessage());
        } catch(IOException ex){
            //ex.printStackTrace();
            System.out.println("Дяденька вы дурак");
        }
    }
}
