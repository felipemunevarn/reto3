public class CuerpoDeAgua extends ObjetoGeografico {
    // Attributes
    private String typeOfWater;
    private boolean sweet;
    private double irca;
    // Methods
        // Constructor

    public CuerpoDeAgua() {
    }

    public CuerpoDeAgua(String name, int id, String town, String typeOfWater, boolean sweet, double irca) {
        super(name, id, town);
        this.typeOfWater = typeOfWater;
        this.sweet = sweet;
        this.irca = irca;
    }

    public String nivel(double irca){
        if (irca >= 80.1 && irca <= 100){
            return "INVIABLE SANITARIAMENTE";
        } else if (irca >= 35.1 && irca <= 80){
            return "ALTO";
        } else if (irca >= 14.1 && irca <= 35){
            return "MEDIO";
        } else if (irca >= 5.1 && irca <= 14){
            return "BAJO";
        } else if (irca >= 0 && irca <= 5){
            return "SIN RIESGO";
        }
        return null;
    }

    public String getTypeOfWater() {
        return typeOfWater;
    }

    public void setTypeOfWater(String typeOfWater) {
        this.typeOfWater = typeOfWater;
    }

    public boolean isSweet() {
        return sweet;
    }

    public void setSweet(boolean sweet) {
        this.sweet = sweet;
    }

    public double getIrca() {
        return irca;
    }

    public void setIrca(double irca) {
        this.irca = irca;
    }
}
