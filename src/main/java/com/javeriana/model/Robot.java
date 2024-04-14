package com.javeriana.model;

import java.util.ArrayList;
import java.util.List;

public class Robot {

    private String code;
    private double maxWeight;
    private List<Component> components;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public List<Component> getComponents() {
        return this.components;
    }

    public Robot(String code, double maxWeight) {
        this.code = code;
        this.maxWeight = maxWeight;
        this.components = new ArrayList<>();
    }

    public double getComponentsWeight(){
        double totalWeight = 0.0;
        for(int i = 0; i < components.size(); i++){
            totalWeight += components.get(i).getWeight();
        }
        return totalWeight;
    }

    public boolean addComponent (int id, String name, double weight){

        if (maxWeight >= getComponentsWeight() + weight) {
            Component newComponent = new Component(id, name, weight);
            components.add(newComponent);
            return true;
        }
        else {
            return false;
        }
    }

    public List<String> getComponentsNames() {
        List<String> componentsNames = new ArrayList<>();
        for (int i = 0; i < components.size(); i++) {
            if (!componentsNames.contains(components.get(i).getName())) {
                componentsNames.add(components.get(i).getName());
            }
        }
        return componentsNames;
    }
}
