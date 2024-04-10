package com.javeriana.model;

import java.util.ArrayList;
import java.util.List;

public class Catalog {

    private List<Robot> robots;
    public Catalog() {
        List<Robot> robots = new ArrayList<>();
    }
    public Robot searchRobotByCode(String code){
        for (Robot robot : robots) {
            if (robot.getCode().equals(code)) {
                return robot;
            }
        }
        return null;
    }

}
