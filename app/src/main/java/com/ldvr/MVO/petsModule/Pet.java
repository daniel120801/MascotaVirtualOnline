package com.ldvr.MVO.petsModule;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import java.util.List;

public abstract class Pet {
    public List<Action> actions;
    public String id;
    public int hunger;
    public int fun;
    public int thirst;

    public Pet(String id){
        this.id = id;
    }
    public abstract void onMove();

    public abstract Action onChangeAction();


    @NonNull
    @Override
    public String toString() {
        return "Pet{" +
                "actions=" + actions +
                ", id='" + id + '\'' +
                ", hunger=" + hunger +
                '}';
    }

    public static class Action {
        public String name = "actionA";
        public List<Drawable> frames;

        public Action(String name, List<Drawable> frames){
            this.name = name;
            this.frames = frames;
        }
    }
}
