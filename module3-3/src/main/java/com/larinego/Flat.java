package com.larinego;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.HashSet;
import java.util.Set;

@Data
public class Flat {

    @Inject
    @KitchenAnnotation
    private Room kitchen;

    @Value("#{bedroom}")
    private Room bathroom;

    @Inject
    private Set<Room> rooms;

    public Flat(Provider<Room> roomProvider){
        rooms = new HashSet<Room>();
        /*rooms.add(roomProvider.get());*/
    }



}
