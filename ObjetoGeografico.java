import java.lang.reflect.Method;

public class ObjetoGeografico {
    // Attributes
    private String name;
    private int id;
    private String town;
    // Methods
        // Constructor
    public ObjetoGeografico() {
    }

    public ObjetoGeografico(String name, int id, String town) {
        this.name = name;
        this.id = id;
        this.town = town;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }    
}
