package com.ldvr.MVO.petsModule;

import androidx.annotation.Nullable;

import com.ldvr.MVO.petsModule.pets.Cat;
import com.ldvr.MVO.petsModule.pets.Dog;

public class Factory {


    public Pet createPet(String type, String id) {
        TypePet petType = TypePet.fromString(type);
        if (petType == null) {
            return new Dog(id);
        }
        switch (petType) {
            case CAT:
                return new Cat(id);
            case DOG:
            default:
                return new Dog(id);
        }
    }
        public Pet createPet (TypePet petType, String id){

            switch (petType) {
                case CAT:
                    return new Cat(id);
                case DOG:
                default:
                    return new Dog(id);
            }
        }

        public PetBuilder createBuilder (TypePet petType, String id){
            return new PetBuilder(createPet(petType, id));
        }
        public PetBuilder createBuilder (String petType, String id){
            return new PetBuilder(createPet(petType, id));
        }

        public enum TypePet {
            DOG("dog"),
            CAT("cat"),
            BIRD("bird");

            private final String type;

            TypePet(String type) {
                this.type = type;
            }

            @Nullable
            public static TypePet fromString(String text) {
                for (TypePet b : TypePet.values()) {
                    if (b.type.equalsIgnoreCase(text)) {
                        return b;
                    }
                }
                return null;
            }
        }

        public static class PetBuilder {

            private final Pet pet;

            public PetBuilder(Pet basePet) {
                pet = basePet;

            }

            public PetBuilder withHunger(int hunger) {
                pet.hunger = hunger;
                return this;
            }
            public PetBuilder withFun(int fun) {
                pet.fun = fun;
                return this;
            }
            public PetBuilder withThirst(int thirst) {
                pet.thirst = thirst;
                return this;
            }


            public Pet build() {
                return pet;
            }


        }
    }

