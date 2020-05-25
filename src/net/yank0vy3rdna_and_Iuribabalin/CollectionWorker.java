package net.yank0vy3rdna_and_Iuribabalin;

import java.util.Date;
import java.util.Set;

public class CollectionWorker{

    public Set<StoredType> collection;
    private Date creationDate;

    public CollectionWorker(Set<StoredType> list){
        collection = list;
        creationDate = new Date();
    }



}
