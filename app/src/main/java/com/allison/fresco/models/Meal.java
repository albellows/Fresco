package com.allison.fresco.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Meals")
public class Meal extends Model {

    public static int mealid = 0;

    // Unique id given by server
    @Column(name = "remote_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public long remoteId;

    // Regular fields
    @Column(name = "Day")
    public String day;
    @Column(name = "MealTime")
    public String mealtime;
    @Column(name = "ServingSize")
    public int servingSize;

    // Association to Recipe activeandroid model
    @Column(name = "Recipe", index = true, onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Recipe recipe;

    // default constructor
    public Meal() {
        super();
    }

    public Meal(int remoteId, String day, String mealtime, int servingSize, Recipe recipe) {
        super();
        this.remoteId = remoteId;
        this.day = day;
        this.mealtime = mealtime;
        this.servingSize = servingSize;
        this.recipe = recipe;
    }

    // Method to get all Meals
    // TODO move this above class?
    public static List<Meal> getAll() {
        return new Select()
                .from(Meal.class)
                .execute();
    }

    @Override
    public String toString() {
        return this.recipe.title;
    }
}
