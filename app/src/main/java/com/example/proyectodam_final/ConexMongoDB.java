/*package com.example.proyectodam_final;

import android.annotation.SuppressLint;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.security.*;

import javax.net.ssl.SSLContext;

public class ConexMongoDB {
    public MongoCollection <org.bson.Document> colUsers, colTournaments, colBookings, colPrices;
    public MongoClient CrearConexion(){
        try {
            SSLContext ctx = SSLContext.getInstance("TLSv1.2");
            ctx.init(null, null, null);
            SSLContext.setDefault(ctx);
        } catch(KeyManagementException | NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }

        @SuppressLint("AuthLeak") ConnectionString connString = new ConnectionString("mongodb+srv://root:calovgamingcenter+12@hig55467e34rf3.bkehr.mongodb.net/");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();
        com.mongodb.client.MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase db = mongoClient.getDatabase("WebMasters");
        //create
        try{
            db.createCollection("tournaments");
            db.createCollection("bookings");
            db.createCollection("prices");
        } catch (Exception e){
            System.out.println("Programa ejecutado con exito");
        }


        //get
        colUsers = db.getCollection("users");
        colTournaments = db.getCollection("tournaments");
        colBookings = db.getCollection("bookings");
        colPrices = db.getCollection("prices");

        return null;
    }
}*/