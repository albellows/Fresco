package com.allison.fresco.models;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Table(name = "Recipes")
public class Recipe extends Model {
    public static int recid = 0;

    // Unique id given by server
    @Column(name = "remote_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public long remoteId;

    // Regular fields
    @Column(name = "Title")
    public String title;
    @Column(name = "Description")
    public String description;
    @Column(name = "ServingSize")
    public int servingSize;

    // Association to Ingredients activeandroid model
    @Column(name = "IngredientsList", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public String ingredients;

    // String image_path;

    // default constructor
    public Recipe() {
        super();
    }
    
    public Recipe(int remoteId, String title, String description, int servingSize, String ingredients) {
        super();
        this.remoteId = remoteId;
        this.title = title;
        this.description = description;
        this.servingSize = servingSize;
        this.ingredients = ingredients;
    }

    // Method to get all Recipes
    // TODO move this above class?
    public static List<Recipe> getAll() {
        return new Select()
                .from(Recipe.class)
                .execute();
    }

    public String getIngredients() {
        return this.ingredients;
    }

    @Override
    public String toString() {
        return this.title;
    }

    public String toString(boolean verbose) {
        String result = this.title;
        if (verbose) {
            result += ": " + this.description;
            if (this.ingredients != null) {
                // Convert from Json to List
                Type listType = new TypeToken<List<String>>() {}.getType();
                List<String> listJsonIngredients = new Gson().fromJson(this.ingredients, listType);
                List<Ingredient> listIngredients = new ArrayList<>();
                for (int i = 0; i < listJsonIngredients.size(); i++) {
                    listIngredients.add(i, new Gson().fromJson(listJsonIngredients.get(i), Ingredient.class));

                }
                for (String i : listJsonIngredients) {
                    result += i;
                }
            }
        }

        return result;
    }
}
