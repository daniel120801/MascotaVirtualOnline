package com.ldvr.MVO.petsModule.pets;

import android.util.Log;

import com.ldvr.MVO.petsModule.Pet;

import java.util.ArrayList;

public class Cat extends Pet {
    public Cat(String id){
        super(id);
        actions = new ArrayList<>();
        actions.add(new Action("walk",null));
        actions.add(new Action("miau",null));
        actions.add(new Action("sleep",null));
    }


    @Override
    public void onMove() {
    Log.d("CAT", "Cat is moving");
    }

    @Override
    public Action onChangeAction() {
        int nRandom = (int) (Math.random() * actions.size());
        Log.d("CAT", "Cat is changing action to " + actions.get(nRandom).name);
        return actions.get(nRandom);
    }
}
