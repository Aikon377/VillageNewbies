package sample;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Date;
import java.util.Optional;


public class Controller extends dbConnect{


    public TextField hakuField;
    public Button selaaVarauksiaBtn;
    public Button haeBtn;
    public RadioButton AsiakasRadio;
    public TableColumn tb_col2;
    public TableColumn tb_col3;
    public TableColumn tb_col4;
    public TableColumn tb_col5;
    public TableColumn tb_col6;
    public TableColumn tb_col7;
    public TextField asiakasNimi;
    public TextField varausMökki;
    public DatePicker varattuAlk;
    public DatePicker varattuPäättyy;
    @FXML
    private TextField mokkiAlueField;

    @FXML
    private TextField postinumeroField;

    @FXML
    private TextField katuosoiteField;

    @FXML
    private TextField henkilomaaraField;

    @FXML
    private TextField vuokraField;

    @FXML
    private TextArea varusteluField;

    @FXML
    private TextField mokkiNimiField;
    @FXML
    private TextArea kuvausField;


    @FXML
    private TextField etunimiField;
    @FXML
    private TextField sukunimiField;
    @FXML
    private TextField asiakasOsoiteField;
    @FXML
    private TextField asiakasPostinumeroField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField puhField;
    @FXML
    private TableColumn tb_col1;
    @FXML

    private RadioButton asiakasRadio;
    @FXML
    private RadioButton laskuRbtn;
    @FXML
    private RadioButton varausRadio;
    @FXML
    private RadioButton mokkiRadio;
    private String rButtonSelected(){

        String rButtonSelected = "";
        if (AsiakasRadio.isSelected()){
            System.out.println("asiakas");
            rButtonSelected = "asiakas";
            tb_col1.setText("Nimi");
            tb_col2.setText("Osoite");
            tb_col3.setText("Puh.");
            tb_col4.setText("Email");


        }
        else if (laskuRbtn.isSelected()){
            System.out.println("lasku");
            rButtonSelected = "lasku";
            tb_col1.setText("Laskun ID");
            tb_col2.setText("Varauksen ID");
            tb_col3.setText("Summa");
            tb_col4.setText("Maksettu");
            tb_col5.setText("Eräpäivä");

        }
        else if (varausRadio.isSelected()){
            System.out.println("varaus");
            rButtonSelected = "varaus";
            if (hakuField.getText().equals("")){
                System.out.println("null");
                //hae kaikki
            }
            else{
                System.out.println("Haussa on tekstiä.");

            }
            tb_col1.setText("Varauksen ID");
            tb_col2.setText("Asiakas");
            tb_col3.setText("Kohde");
            tb_col4.setText("Alkaen");
            tb_col5.setText("Päättyy");
            tb_col6.setText("Summa");
        }
        else if (mokkiRadio.isSelected()){
            System.out.println("mökki");
            rButtonSelected = "mökki";
            tb_col1.setText("Mökin nimi");
            tb_col2.setText("Osoite");
            tb_col3.setText("Hinta");
            tb_col4.setText("Mökin ID");
            tb_col5.setText("");

        }

        return rButtonSelected;
    }

    public void searchBtnPressed(ActionEvent event){
        rButtonSelected();
        ObservableList tableViewLista = loadFromDb(rButtonSelected(), hakuField.getText());
        System.out.println(tableViewLista);



    }


    @FXML
    void addMokkiPressed(ActionEvent event){
        try {

            createMökki(createID(), Integer.parseInt(mokkiAlueField.getText()), postinumeroField.getText(), mokkiNimiField.getText(), katuosoiteField.getText(), Double.parseDouble(vuokraField.getText()), kuvausField.getText(), Integer.parseInt(henkilomaaraField.getText()), varusteluField.getText());

        } catch (NumberFormatException e) {
            Dialog error = new Dialog();
            error.setContentText("Virhe Mökkiä lisätessä!");
            error.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            Node closeButton = error.getDialogPane().lookupButton(ButtonType.CLOSE);
            closeButton.managedProperty().bind(closeButton.visibleProperty());
            closeButton.setVisible(false);
            error.showAndWait();

            e.printStackTrace();
        }

    }

    @FXML
    void addAsiakasPressed(ActionEvent event){

        String password = null;

        TextInputDialog TxtDia = new TextInputDialog();
        TxtDia.setTitle("Search");
        TxtDia.getDialogPane().setContentText("Search");
        TxtDia.setHeaderText("Etsi nimellä - (asiakas_id)");
        TxtDia.setGraphic(null);

        Optional<String> result = TxtDia.showAndWait();
        TextField input = TxtDia.getEditor();
        if(input.getText() != null && input.getText().toString().length() != 0)
        // AND SOMETHING WAS FOUND IN SEARCH !!!!!!!!!
        {
            password = input.getText();
        }
        else System.out.println("null");

        try{
            createAsiakas(password, createID(), etunimiField.getText(), sukunimiField.getText(), asiakasOsoiteField.getText(), emailField.getText(), puhField.getText(), asiakasPostinumeroField.getText());
        } catch (Exception e) {
            Dialog error = new Dialog();
            error.setContentText("Virhe Asiakasta lisätessä!");
            error.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            Node closeButton = error.getDialogPane().lookupButton(ButtonType.CLOSE);
            closeButton.managedProperty().bind(closeButton.visibleProperty());
            closeButton.setVisible(false);
            error.showAndWait();

        }


    }
    @FXML
    void addPalveluPressed(ActionEvent event){


    }

    @FXML
    void setSelaaVarauksiaBtn(ActionEvent event){
        System.out.println("Moi");
        Dialog selaaVarauksiaDlg = new Dialog();
        TableView searchResultsTbVw = new TableView();
        GridPane searchGridPane = new GridPane();
        searchGridPane.setMinSize(50,50);
        searchGridPane.setGridLinesVisible(true);
        //searchGridPane.setMinSize(350, 500);

        TextField hakuTF = new TextField("Varauksen ID ...");
        Button hakuBtn = new Button("Hae");
        hakuBtn.setMinSize(100,30);
        Pane pane = new Pane();
        Boolean haettu = false;
        searchGridPane.add(searchResultsTbVw,2,1);
        searchGridPane.setVisible(haettu);



        searchGridPane.add(hakuTF,1,1);

        searchGridPane.add(hakuBtn, 1, 3);

        searchGridPane.getChildren().addAll();








        selaaVarauksiaDlg.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        selaaVarauksiaDlg.getDialogPane().setMinSize(500,500);
        selaaVarauksiaDlg.getDialogPane().setContent(searchGridPane);


        Node closeButton = selaaVarauksiaDlg.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        selaaVarauksiaDlg.showAndWait();


        selaaVarauksiaDlg.getDialogPane().getChildren().addAll();

    }

    public void addVarausPressed(ActionEvent event) {
        createVaraus(createID(), 123, varausMökki.getText(),varattuAlk.toString(), varattuPäättyy.toString());
    }
}

