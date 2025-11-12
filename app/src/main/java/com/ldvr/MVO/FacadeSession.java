package com.ldvr.MVO;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ldvr.MVO.petsModule.Factory;
import com.ldvr.MVO.petsModule.Pet;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FacadeSession {

    public interface OnAuthListener {
        void onSuccess();

        void onError(Exception e);
    }

    public interface OnGetPetsListener {
        void onSuccess(List<Pet> pets);

        void onError(Exception e);
    }

    public static void signIn(@NotNull String email, @NotNull String password, @NotNull FacadeSession.OnAuthListener onAuthListener) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener((result) -> {

            if (result.isSuccessful()) {
                onAuthListener.onSuccess();
            } else {
                onAuthListener.onError(result.getException());
            }
        });
    }

    public static void createAccount(@NotNull String email, @NotNull String password, @NotNull FacadeSession.OnAuthListener onAuthListener) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener((result) -> {

            if (result.isSuccessful()) {
                onAuthListener.onSuccess();
            } else onAuthListener.onError(result.getException());
        });
    }

    public static void getPets(OnGetPetsListener onGetPetsListener) {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            onGetPetsListener.onError(new Exception("User not logged in"));
            return;
        }
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(uid).get().addOnCompleteListener(
                task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        List<String> petIds = new ArrayList<>();
                        petIds.addAll ((List<String>) task.getResult().get("ownedPets"));

                        petIds.addAll( (List<String>) task.getResult().get("sharedPets"));
                        if (!petIds.isEmpty()) {
                            List<Pet> pets = new ArrayList<>();
                            AtomicInteger tasksCompleted = new AtomicInteger(0);
                            Factory factory = new Factory();

                            for (String petId : petIds) {
                                db.collection("pets").document(petId).get().addOnCompleteListener(petTask -> {
                                    if (petTask.isSuccessful() && petTask.getResult() != null && petTask.getResult().exists()) {
                                        DocumentSnapshot petSnapshot = petTask.getResult();
                                        String type = petSnapshot.getString("typePet");
                                        if (type != null) {
                                            Factory.PetBuilder builder = factory.createBuilder(type, petId);
                                            Long hunger = petSnapshot.getLong("hunger");
                                            if (hunger != null) {
                                                builder.withHunger(hunger.intValue());
                                            }
                                            pets.add(builder.build());
                                        }
                                    } else {
                                        Log.e("FIRESTORE", "Error fetching pet: " + petId, petTask.getException());
                                    }

                                    if (tasksCompleted.incrementAndGet() == petIds.size()) {
                                        onGetPetsListener.onSuccess(pets);
                                    }
                                });
                            }
                        } else {
                            onGetPetsListener.onSuccess(new ArrayList<>());
                        }
                    } else {
                        onGetPetsListener.onError(task.getException());
                    }
                }
        );
    }

    public static void feedPet(@NonNull Pet pet, int value) {
        FirebaseFirestore.getInstance().collection("pets").document(pet.id)
                .update("hunger", FieldValue.increment(value))
                .addOnSuccessListener(aVoid -> Log.d("FIRESTORE", "Hunger updated successfully for pet: " + pet.id))
                .addOnFailureListener(e -> Log.e("FIRESTORE", "Error updating hunger for pet: " + pet.id, e));
    }
}
