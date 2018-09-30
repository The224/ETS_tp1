package model;

import controller.FactoryController;

import java.awt.*;

public class Factory extends Building {

    Factory(Integer id, String output, Integer productionInterval) {
        super(id, output, productionInterval);
    }

    public void shipementReady() {
        FactoryController.sendingDispatcher.emit(new Shipment(this.id, null, Component.METAL));
    }

    public void arrival(String type) {

    }

    @Override
    public void subjectChange(String subjectId, Object newValue) {

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
