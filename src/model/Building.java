package model;

import java.awt.geom.Point2D;
import java.util.HashMap;

public abstract class Building implements ISimulatedObject {

    private Integer id;
    private Component output;
    private Integer productionInterval;
    private Point2D position;
    private HashMap<Component, Integer> productionRequirement;

    public Building(Integer id, Component output, Integer productionInterval, Point2D position, HashMap<Component, Integer> productionRequirement) {
        this.id = id;
        this.output = output;
        this.productionInterval = productionInterval;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public Component getOutput() {
        return output;
    }

    public Integer getProductionInterval() {
        return productionInterval;
    }

    public Point2D getPosition() {
        return position;
    }

    public abstract void arrival(Shipment shipment);

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", output=" + output +
                ", productionInterval=" + productionInterval +
                ", position=" + position +
                '}';
    }
}

