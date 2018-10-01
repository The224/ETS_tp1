package model.building;

import model.Component;
import model.ISimulatedObject;
import model.Shipment;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Building implements ISimulatedObject {

    private Integer id;
    private String type;
    private model.Component output;
    private Integer productionInterval;
    private Point2D position;
    private HashMap<model.Component, Integer> productionRequirement;
    private HashMap<model.Component, Integer> inventory = new HashMap<>();
    private ArrayList<BufferedImage> icons;

    public Building(
            Integer id,
            String type,
            ArrayList<BufferedImage> icons,
            Integer productionInterval,
            Component output,
            HashMap<Component, Integer> productionRequirement,
            Point2D position) {
        this.id = id;
        this.type = type;
        this.output = output;
        this.productionInterval = productionInterval;
        this.icons = icons;
        this.productionRequirement = productionRequirement;
        this.position = position;
    }


    public void arrival(Shipment shipment) {
        System.out.println("Warehouse has received " + shipment.getComponent());
        model.Component component = shipment.getComponent();
        Integer quantity = inventory.get(component);

        if ( inventory.containsKey(component)) {
            inventory.put(component, quantity + 1);
        } else {
            inventory.put(component, 1);
        }

    }

    @Override
    public void draw(Graphics graphics) throws Exception {
        final BufferedImage image = ImageIO.read(new File("src/ressources/avion.png"));

        System.out.println(this.getPosition().getX());

        graphics.drawImage(image, (int) this.getPosition().getX(), (int) this.getPosition().getY(), null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public model.Component getOutput() {
        return output;
    }

    public void setOutput(model.Component output) {
        this.output = output;
    }

    public Integer getProductionInterval() {
        return productionInterval;
    }

    public void setProductionInterval(Integer productionInterval) {
        this.productionInterval = productionInterval;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public HashMap<model.Component, Integer> getProductionRequirement() {
        return productionRequirement;
    }

    public void setProductionRequirement(HashMap<model.Component, Integer> productionRequirement) {
        this.productionRequirement = productionRequirement;
    }

    public HashMap<model.Component, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<Component, Integer> inventory) {
        this.inventory = inventory;
    }

    public ArrayList<BufferedImage> getIcons() {
        return icons;
    }

    public void setIcons(ArrayList<BufferedImage> icons) {
        this.icons = icons;
    }

    @Override
    public String toString() {
        return "Building" +
                "id=" + id;
    }
}

