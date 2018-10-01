package controller;

import model.Component;
import model.ISimulatedObject;
import model.Road;
import model.building.Building;
import model.building.BuildingConfig;
import model.building.Factory;
import model.building.Warehouse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class ConfigurationReader {

    public static ArrayList<ISimulatedObject> createSimulationByConfiguration(String configurationPath) throws Exception {
        Element configuration = getDocumentConfiguration(configurationPath);
        Element metadonnees = (Element) configuration.getElementsByTagName("metadonnees").item(0);
        Element simulation = (Element) configuration.getElementsByTagName("simulation").item(0);

        ArrayList<BuildingConfig> buildingsConfig = generateBuildingsConfig(metadonnees);

        ArrayList<Building> buildings = generateBuildings(simulation, buildingsConfig);
        ArrayList<Road> roads = generateRoads(simulation, buildings);

        ArrayList<ISimulatedObject> test = new ArrayList<>();

        test.addAll(roads);
        test.addAll(buildings);

        System.out.println(buildings.size());
        System.out.println(roads.size());
        System.out.println(buildings.size());

        return test;
    }

    private static ArrayList<BuildingConfig> generateBuildingsConfig(Element metadata) throws Exception {
        ArrayList<BuildingConfig> buildingsConfig = new ArrayList<>();
        NodeList factories = metadata.getElementsByTagName("usine");

        for (int i = 0; i < factories.getLength(); i++) {
            Element factory = (Element) factories.item(i);
            BuildingConfig config = new BuildingConfig();

            Element output = (Element) factory.getElementsByTagName("sortie").item(0);
            Element productionInterval = (Element) factory.getElementsByTagName("interval-production").item(0);

            config.type = factory.getAttribute("type");
            config.icons = generateIcons(factory.getElementsByTagName("icons"));
            config.productionRequirement = generateInputs(factory.getElementsByTagName("entree"));

            if (!factory.getAttribute("type").equals("entrepot")) {
                config.productionInterval = Integer.parseInt(productionInterval.getChildNodes().item(0).getNodeValue());
                config.output = Component.getComponentFromFR(output.getAttribute("type"));
            }
            buildingsConfig.add(config);
        }
        return buildingsConfig;
    }

    private static ArrayList<Building> generateBuildings(Element simulation, ArrayList<BuildingConfig> buildingsConfig) {
        ArrayList<Building> buildings = new ArrayList<>();

        NodeList factories = simulation.getElementsByTagName("usine");

        for (int i = 0; i < factories.getLength(); i++) {
            Element factory = (Element) factories.item(i);

            BuildingConfig config = null;

            for (BuildingConfig bc:buildingsConfig) {
                if (bc.type.equals(factory.getAttribute("type"))) {
                    config = bc;
                }
            }

            switch (config.type) {
                case "entrepot":
                    buildings.add(new Warehouse(
                            Integer.parseInt(factory.getAttribute("id")),
                            config.type,
                            config.icons,
                            config.productionRequirement,
                            new Point2D.Double(
                                    Double.parseDouble(factory.getAttribute("id")),
                                    Double.parseDouble(factory.getAttribute("id"))
                            )
                    ));
                default:
                    buildings.add(new Factory(
                            Integer.parseInt(factory.getAttribute("id")),
                            config.type,
                            config.icons,
                            config.productionInterval,
                            config.output,
                            config.productionRequirement,
                            new Point2D.Double(
                                    Double.parseDouble(factory.getAttribute("id")),
                                    Double.parseDouble(factory.getAttribute("id"))
                            )
                    ));
            }
        }
        return buildings;
    }

    private static ArrayList<BufferedImage> generateIcons(NodeList iconList) throws Exception {
        ArrayList<BufferedImage> icons = new ArrayList<>(iconList.getLength());
        for (int i = 0; i < iconList.getLength(); i++) {
            Element icon = (Element) iconList.item(i);
            switch (icon.getAttribute("type")) {
                case "vide":
                    icons.add(0, ImageIO.read(new File(icon.getAttribute("path"))));
                case "un-tiers":
                    icons.add(1, ImageIO.read(new File(icon.getAttribute("path"))));
                case "deux-tiers":
                    icons.add(2, ImageIO.read(new File(icon.getAttribute("path"))));
                case "plein":
                    icons.add(3, ImageIO.read(new File(icon.getAttribute("path"))));
            }
        }
        return icons;
    }

    private static HashMap<Component, Integer> generateInputs(NodeList inputList) {
        HashMap<Component, Integer> productionRequirement = new HashMap<>();
        for (int i = 0; i < inputList.getLength(); i++) {
            Element input = (Element) inputList.item(i);
            Component component = Component.getComponentFromFR(input.getAttribute("type"));
            String quantity = input.getAttribute("quantite");

            if (!quantity.equals("")) {
                productionRequirement.put(component, Integer.parseInt(quantity));
            } else {
                productionRequirement.put(component, Integer.parseInt(input.getAttribute("capacite")));
            }
        }
        return productionRequirement;
    }

    private static ArrayList<Road> generateRoads(Element simulation, ArrayList<Building> buildings) {
        ArrayList<Road> roads = new ArrayList<>();
        NodeList roadList = ((Element) simulation.getElementsByTagName("chemins").item(0)).getElementsByTagName("chemin");
        for (int i = 0; i < roadList.getLength(); i++) {
            Element road = (Element) roadList.item(i);
            Building origin = null;
            Building destination = null;
            for (Building building : buildings) {
                if (building.getId().equals(Integer.parseInt(road.getAttribute("de")))) {
                    origin = building;
                } else if(building.getId().equals(Integer.parseInt(road.getAttribute("vers")))) {
                    destination = building;
                }
            }
            roads.add(new Road(origin, destination));
        }
        return roads;
    }

    private static Element getDocumentConfiguration(String configurationPath) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(configurationPath));
        doc.getDocumentElement().normalize();
        return doc.getDocumentElement();
    }

}
