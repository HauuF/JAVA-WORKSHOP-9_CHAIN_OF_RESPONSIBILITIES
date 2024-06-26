package com.javeriana.model;

public class Component {

    private int id;
    private String name;
    private double weight;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Component(int id, String name, double weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
            }
}