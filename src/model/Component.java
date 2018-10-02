package model;

public enum Component {
    METAL,
    WING,
    MOTOR,
    PLANE;

    // TODO Set urls
    public static String getURL(Component component) {
        switch (component){
            case METAL:
                return "src/ressources/metal.png";
            case WING:
                return "src/ressources/aile.png";
            case MOTOR:
                return "src/ressources/moteur.png";
            case PLANE:
                return "src/ressources/avion.png";
        }
        return "";
    }

    public static Component getComponentFromFR(String name) {
        switch (name){
            case "metal":
                return METAL;
            case "aile":
                return WING;
            case "moteur":
                return MOTOR;
            case "avion":
                return PLANE;
        }
        return null;
    }

}
