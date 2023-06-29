package com.tezine.appquarkus.services;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.tezine.appquarkus.beans.Fruit;
import com.tezine.appquarkus.codes.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FruitsService {

    @Inject
    com.mongodb.client.MongoClient mongoClient;

    public FruitsService() {
    }

    private MongoCollection<Fruit> getCollection() {
        return mongoClient.getDatabase("testmongo").getCollection("fruits", Fruit.class);
    }

    public List<Fruit> getFruits() {
        List<Fruit> list = new ArrayList<>();
        MongoCursor<Fruit> cursor = getCollection().find().iterator();
        try {
            while (cursor.hasNext()) {
                Fruit fruit = cursor.next();
                list.add(fruit);
            }
        } catch (Exception e) {
            Logger.logError(e);
        } finally {
            cursor.close();
        }
        return list;
    }

    public Fruit getFruit(String id) {
        try {
            var result = getCollection().find(Filters.eq("_id", new ObjectId(id))).first();
            return result;
        } catch (Exception e) {
            Logger.logError(e);
        }
        return null;
    }

    public String addFruit(Fruit fruit) {
        try {
            var result = getCollection().insertOne(fruit);
            result.getInsertedId().toString();
        } catch (Exception e) {
            Logger.logError(e);
        }
        return null;
    }

    public String updateFruit(Fruit fruit) {
        try {
            var updateFruit = getCollection().find(Filters.eq("_id", fruit.id)).first();
            updateFruit.name=fruit.name;
            updateFruit.description=fruit.description;
            getCollection().replaceOne((Filters.eq("_id", fruit.getId())),updateFruit);
        } catch (Exception e) {
            Logger.logError(e);
        }
        return null;
    }

    public boolean deleteFruit(Fruit fruit) { 
        try {
            var result = getCollection().deleteOne(Filters.eq("_id", fruit.getId()));
            return result.getDeletedCount() > 0;
        } catch (Exception e) {
            Logger.logError(e);
        }
        return false;
    }

    public boolean deleteFruitByID(String id) {
        try {
            var result = getCollection().deleteOne(Filters.eq("_id", new ObjectId(id)));
            return result.getDeletedCount() > 0;
        } catch (Exception e) {
            Logger.logError(e);
        }
        return false;
    }
}
