package model.building;

import controller.FactoryController;
import model.Component;
import model.Shipment;
import model.building.Building;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Factory extends Building {

    private Boolean isInProduction = false;
    private Boolean producing = false;

    public Factory(
            Integer id,
            String type,
            ArrayList<BufferedImage> icons,
            Integer productionInterval,
            Component output,
            HashMap<Component, Integer> productionRequirement,
            Point2D position) {
        super(id, type, icons, productionInterval, output, productionRequirement, position );
        FactoryController.isInProduction.subscribe( (isInProduction) -> this.isInProduction = (Boolean)isInProduction);
    }

    @Override
    public void nextTurn() {
        if (getProductionRequirement().size() == 0) { // Producer Factory
            setProductionTick(getProductionTick() + 1);
            if(getProductionInterval().equals(getProductionTick())) {
                this.shipmentReady();
                setProductionTick(0);
            }
        } else { // Assembly Factory
            if (producing) {
                setProductionTick(getProductionTick() + 1);
                if(getProductionInterval().equals(getProductionTick())) {
                    this.shipmentReady();
                    setProductionTick(0);
                    this.producing = false;
                }
            } else {
                Integer produceNeeds = getProductionRequirement().size();
                Integer produceReady = 0;

                for(Map.Entry<Component, Integer> entry : getProductionRequirement().entrySet()) {
                    Component requirement = entry.getKey();
                    Integer nbRequire = entry.getValue();

                    Integer stock = getInventory().get(requirement);
                    if (stock != null && nbRequire <= stock) {
                        produceReady++;
                    }
                }
                if (produceNeeds.equals(produceReady)) {
                    producing = true;
                }
            }
        }
    }

    private void shipmentReady() {
        System.out.println("Building " + this.getId() + " has produced " + this.getOutput());
        FactoryController.sendingDispatcher.emit(new Shipment(this.getId(), null, this.getOutput()));
    }

}
