package net.yank0vy3rdna_and_Iuribabalin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CommandSerializer {

    public byte[] serializable(OutputCommand out) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeObject(out);
        objectOutputStream.flush();

        return byteArrayOutputStream.toByteArray();
    }
}
