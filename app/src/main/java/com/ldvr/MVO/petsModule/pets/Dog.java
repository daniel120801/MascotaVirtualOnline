package com.ldvr.MVO.petsModule.pets;

import android.util.Log;

import com.ldvr.MVO.petsModule.Pet;

import java.util.ArrayList;

public class Dog extends Pet {


    public Dog(String id){
        super(id);
        actions = new ArrayList<>();
        actions.add(new Action("walk",null));
        actions.add(new Action("bark",null));
        actions.add(new Action("sleep",null));
    }


    @Override
    public void onMove() {
        Log.d("DOG", "Dog is moving");
    }

    @Override
    public Action onChangeAction() {
        int nRandom = (int) (Math.random() * actions.size());
        Log.d("DOG", "Dog is changing action to " + actions.get(nRandom).name);
        return actions.get(nRandom);
    }
}
