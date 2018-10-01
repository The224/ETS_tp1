package model.building;

import model.Component;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class BuildingConfig {
    public String type;
    public ArrayList<BufferedImage> icons;
    public Integer productionInterval;
    public Component output;
    public HashMap<Component, Integer> productionRequirement;
}
