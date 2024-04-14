package com.javeriana.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Catalog {

    private List<Robot> robots;
    private List<Robot> robotsCatalog;
    public Catalog() {
        this.robots = new ArrayList<>();
        this.robotsCatalog = new ArrayList<>();
    }

    public List<Robot> getRobotsCatalog() {
        return new ArrayList<>(robots);
    }

    public Robot searchRobotByCode(String code) {
        for (Robot robot : robots) {
            if (robot.getCode().equals(code)) {
                return robot;
            }
        }
        return null;
    }

    public boolean addRobot(String code, double maxWeight) {
        for (Robot robot : robots) {
            if (robot.getCode().equals(code)) {
                return false;
            }
        }
        Robot newRobot = new Robot(code, maxWeight);
        robots.add(newRobot);
        return true;
    }

    public boolean addComponentToRobot(String code, int id, String name, double weight) {
        for (Robot robot : robots) {
            if (robot.getCode().equals(code)) {
                if (robot.getMaxWeight() >= robot.getComponentsWeight() + weight) {
                    return robot.addComponent(id, name, weight);
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean removeRobot(String code) {
        for (Robot robot : robots) {
            if (robot.getCode().equals(code)) {
                robots.remove(robot);
                return true;
            }
        }
        return false;
    }
    public List<String> getComponentsNamesUsedInAllRobots() {
        List<String> allComponentNames = new ArrayList<>();
        for (Robot robot : robots) {
            List<String> robotComponentNames = robot.getComponentsNames();
            for (String name : robotComponentNames) {
                if (!allComponentNames.contains(name)) {
                    allComponentNames.add(name);
                }
            }
        }
        return allComponentNames;
    }
}


