package com.hw.cy.entity;
import  java.util.UUID;
public class CREATE_UUID {
    public static String getId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
     }
}
