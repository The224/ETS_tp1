package model;

public abstract class Building implements IDessinable {

    Integer id;
    Component output;
    Integer productionInterval;

    public Building(Integer id, Component output, Integer productionInterval) {
        this.id = id;
        this.output = output;
        this.productionInterval = productionInterval;
    }

    abstract void estimateTime();
    abstract void process();

}

