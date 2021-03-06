package com.allison.fresco.models;

// activeandroid is no longer supported? :(
/*
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name= "Ingredients")
public class Ingredient extends Model {

    public static long ingid = 0;

    // Unique id given by server
    @Column(name = "remote_id", unique = true)
    public long remoteId;

    // Regular fields
    @Column(name = "RecipeId")
    public int recipeId;
    @Column(name = "Name")
    public String name;
    @Column(name = "Amount")
    public int amount;
    @Column(name = "MeasurementType")
    public String measurementType;

    public Ingredient() {
    }

    public Ingredient(long remoteId, int recipeId, String name, int amount, String measurementType) {
        super();
        this.remoteId = remoteId;
        this.recipeId = recipeId;
        this.name = name;
        this.amount = amount;
        this.measurementType = measurementType;
    }

    public static List<Ingredient> getAllForRecipe(int recipeId) {
        return new Select()
                .from(Ingredient.class)
                .where("RecipeId = ?", recipeId)
                .orderBy("Name ASC")
                .execute();
    }

    @Override
    public String toString() {
        return name + ", " + amount + " " + measurementType;
    }


}

*/

import io.realm.RealmObject;

public class Ingredient extends RealmObject {

    public static long ingid = 0;

}