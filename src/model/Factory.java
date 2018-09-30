package model;

import controller.FactoryController;

import java.awt.*;

public class Factory extends Building {

    private Boolean isInProduction = false;

    public Factory(Integer id, Component output, Integer productionInterval) {
        super(id, output, productionInterval);
        FactoryController.isInProduction.subscribe( (isInProduction) -> this.isInProduction = (Boolean)isInProduction);
    }

    public void shipmentReady() {
        FactoryController.sendingDispatcher.emit(new Shipment(this.id, null, Component.METAL));
    }

    public void arrival(String type) {

    }

    public Boolean productionChange( Boolean isProductionStop) {

        return true;
    }

    public void subjectChange(String subjectName, Shipment shipment) {

    }

    @Override
    public void estimateTime() {

    }

    @Override
    public void process() {

    }

    @Override
    public void draw(Graphics graphics) {

    }
}
