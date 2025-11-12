package com.ldvr.MVO;

import java.util.HashMap;

public class Settings {
    private static Settings instance;
    public static Settings getInstance(){
        if(instance == null)
            instance = new Settings();
        return instance;
    }

    private double scaleImage = 1.0;
    private HashMap<String,Boolean> actionsEnabled = new HashMap<>();
    private WalkMode walkMode = WalkMode.ALL_WINDOW;

    public boolean isActionEnabled(String value){
        return Boolean.TRUE.equals(actionsEnabled.get(value));
    }
    public void addAction(String name,boolean state){
        actionsEnabled.put(name,state);
    }
    public void removeAction(String name){
        actionsEnabled.remove(name);
    }
    public void clearActions(){
        actionsEnabled.clear();
    }

    public  double getScaleImage() {
        return scaleImage;
    }
    public  void setScaleImage(double scaleImage) {
        this.scaleImage = scaleImage;
    }

    public WalkMode getWalkMode() {
        return walkMode;
    }

    public void setWalkMode(WalkMode walkMode) {
        this.walkMode = walkMode;
    }

    // Declaración correcta de un enum
    public enum WalkMode {
        ALL_WINDOW,
        ONLY_BOTTOM
    }
}
