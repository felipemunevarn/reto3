import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class GUIreto3Controller {

    @FXML
    private AnchorPane parent;

    @FXML
    private ImageView misionimg;

    @FXML
    private ImageView uninorteimg;

    @FXML
    private Button modebtn;

    @FXML
    private ImageView modeimg;

    @FXML
    private Label modetxt;

    @FXML
    private TextField nametxt;

    @FXML
    private TextField idtxt;

    @FXML
    private TextField towntxt;

    @FXML
    private TextField ircatxt;

    @FXML
    private TextArea addareatxt;

    @FXML
    private TextArea outputtxt;

    @FXML
    private Label statustxt;

    @FXML
    private ComboBox<String> sweetcbx;

    @FXML
    private ComboBox<String> typewatercbx;

    ArrayList<CuerpoDeAgua> bodiesOfWater = new ArrayList<CuerpoDeAgua>();

    @FXML
    public void addClick(MouseEvent event) {
        outputtxt.setText("");
        String name = nametxt.getText().strip();
        String town = towntxt.getText().strip();
        String typewater = typewatercbx.getValue();
        String sweet = sweetcbx.getValue();
        String irca;
        String id;
        int next = 0;
        
        try {
            id = idtxt.getText().strip();
            next = Integer.parseInt(id) + 1;
        } catch (Exception e) {
            statustxt.setText("Id debe ser un numero entero");
            id = null;
            idtxt.setText("");
        }

        try {
            irca = ircatxt.getText().strip();
            double ircaTest = Double.parseDouble(irca);
        } catch (Exception e) {
            statustxt.setText("IRCA debe ser un numero real");
            irca = null;
            ircatxt.setText("");
        }

        if (name.isEmpty() || id.isEmpty() || town.isEmpty() || irca.isEmpty()) {
            statustxt.setText("Por favor, llene todos los campos del formulario");
        } else {
            addareatxt.setText(addareatxt.getText() + name + "-" + id + "-" + town + "-" + sweet + "-" + typewater + "-" + irca + "\n");
            CuerpoDeAgua tempBody = new CuerpoDeAgua(name, Integer.parseInt(id), town, typewater, sweetCheck(sweet), Double.parseDouble(irca));
            bodiesOfWater.add(tempBody);
            statustxt.setText("");
            nametxt.setText("");
            idtxt.setText(String.valueOf(next));
            towntxt.setText("");
            ircatxt.setText("");
        }
    }

    @FXML
    public void runClick(MouseEvent event) {
        outputtxt.setText("");
        float amountHighAndLow = 0;
        int amountMed = 0;
        double sum = 0;

        for (int i = 0; i < bodiesOfWater.size(); i++) {

            String catTemp = bodiesOfWater.get(i).nivel(bodiesOfWater.get(i).getIrca());
            if(catTemp == "ALTO" || catTemp == "MEDIO")
            amountHighAndLow++;
            sum += bodiesOfWater.get(i).getIrca();
        }
        
        for (int i = 0; i < bodiesOfWater.size(); i++)
            outputtxt.setText(outputtxt.getText() + bodiesOfWater.get(i).getName() + "\n");

        outputtxt.setText(outputtxt.getText() + String.format("%.2f", amountHighAndLow) + "\n");

        for (int i = 0; i < bodiesOfWater.size(); i++){
            String catTemp = bodiesOfWater.get(i).nivel(bodiesOfWater.get(i).getIrca());
            if(catTemp == "ALTO"){
                outputtxt.setText(outputtxt.getText() + bodiesOfWater.get(i).getName() + " ");
                amountMed++;
            }
        }
    
        if (amountMed == 0)
            outputtxt.setText(outputtxt.getText() + "NA");

        outputtxt.setText(outputtxt.getText() + "\n");
        outputtxt.setText(outputtxt.getText() + String.format("%.2f", sum / bodiesOfWater.size()));
    }

    public static boolean sweetCheck(String typeOfWater){
        return typeOfWater.toLowerCase().equals("dulce");
    }

    @FXML
    void deleteClick(MouseEvent event) {
        outputtxt.setText("");
        int lastChangeLine = addareatxt.getText().lastIndexOf("\n");
        int secondLast = addareatxt.getText().lastIndexOf("\n", lastChangeLine - 1);
        if (lastChangeLine == -1 || secondLast == -1) {
            addareatxt.setText("");
        } else {
            addareatxt.setText(addareatxt.getText().substring(0, secondLast + 1));
            bodiesOfWater.remove(bodiesOfWater.size() - 1);
        }
    }

    @FXML
    void resetClick(MouseEvent event) {
        addareatxt.setText("");
        outputtxt.setText("");
        idtxt.setText("0");
        for(int i = bodiesOfWater.size() - 1; i > -1; i--){
            bodiesOfWater.remove(i);
        }
    }

    private boolean isLightMode = true;

    public void changeMode(MouseEvent event) {
        isLightMode = !isLightMode;
        if (isLightMode) {
            setLightMode();
        } else {
            setDarkMode();
        }
    }

    private void setLightMode() {
        parent.getStylesheets().remove("styles/darkMode.css");
        parent.getStylesheets().add("styles/lightMode.css");
        Image modeImg = new Image("img/moon.png");
        modetxt.setText("Light Mode");
        modeimg.setImage(modeImg);
        Image misionImg = new Image("img/misionLight.png");
        misionimg.setImage(misionImg);
        Image uniImg = new Image("img/uninorteLight.png");
        uninorteimg.setImage(uniImg);
    }

    private void setDarkMode() {
        parent.getStylesheets().remove("styles/lightMode.css");
        parent.getStylesheets().add("styles/darkMode.css");
        Image modeImg = new Image("img/sun.png");
        modetxt.setText("Dark Mode");
        modeimg.setImage(modeImg);
        Image misionImg = new Image("img/misionDark.png");
        misionimg.setImage(misionImg);
        Image uniImg = new Image("img/uninorteDark.png");
        uninorteimg.setImage(uniImg);
    }

    @FXML
    void initialize() {
        sweetcbx.getItems().removeAll(sweetcbx.getItems());
        sweetcbx.getItems().addAll("Dulce", "Salada");
        sweetcbx.getSelectionModel().select("Dulce");

        idtxt.setText("0");

        typewatercbx.getItems().removeAll(typewatercbx.getItems());
        typewatercbx.getItems().addAll("Arroyo", "Caño", "Cienaga", "Lago", "Laguna", "Rio", "Oceano");
        typewatercbx.getSelectionModel().select("Arroyo");
    }
}
