package dad.CambioDivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {

	private TextField cuadroTexto1;
	private TextField cuadroTexto2;
	private ComboBox<Divisa> botones1;
	private ComboBox<Divisa> botones2;
	private Button boton;

	Alert error = new Alert(AlertType.ERROR);

	@Override
	public void start(Stage primaryStage) throws Exception {

		cuadroTexto1 = new TextField();
		cuadroTexto1.setMaxWidth(150);
		cuadroTexto2 = new TextField();
		cuadroTexto2.setMaxWidth(150);
		cuadroTexto2.setEditable(false); 
		boton = new Button("Cambiar");
		boton.setOnAction(e -> onComprobarAction(e));
		
		
		
		Divisa euro = new Divisa("Euro", 1.0);
        Divisa libra = new Divisa("Libra", 0.8873);
        Divisa dolar = new Divisa("Dolar", 1.2007);
        Divisa yen = new Divisa("Yen", 133.59);
        
        botones1 = new ComboBox<>();
        botones1.getItems().addAll(euro, libra, dolar, yen); // desplegable
        botones1.getSelectionModel().select(euro); 
        
        botones2 = new ComboBox<>();
        botones2.getItems().addAll(euro, libra, dolar, yen);
        botones2.getSelectionModel().select(libra);

		HBox primera = new HBox(); //para poner los elementos de izq a der
		primera.getChildren().addAll(cuadroTexto1, botones1);
		primera.setAlignment(Pos.CENTER);
		HBox segunda = new HBox(); //para poner los elementos de izq a der
		segunda.getChildren().addAll(cuadroTexto2, botones2);
		segunda.setAlignment(Pos.CENTER);
        VBox root = new VBox(); // caja donde metemos todo
		root.getChildren().addAll(primera, segunda, boton);
		root.setAlignment(Pos.CENTER); // setAlignment es para poner las posiciones de cualquier elemento

		Scene scene = new Scene(root, 480, 320); // caja mas su tamaño

		primaryStage.setTitle("Cambio de divisa"); // nombre de arriba de la caja
		primaryStage.setScene(scene); // mostrar caja
		primaryStage.show();
	}

	public void onComprobarAction(ActionEvent e) {

		try {
			Divisa divisaEntrada = botones1.getSelectionModel().getSelectedItem();
            Divisa divisaSalida = botones2.getSelectionModel().getSelectedItem();
            double cantidad = Double.parseDouble(cuadroTexto1.getText());

            Divisa.fromTo(divisaEntrada, divisaSalida,cantidad);
            cuadroTexto2.setText(Divisa.fromTo(divisaEntrada, divisaSalida,cantidad).toString());
            
		} catch(Exception a) {
			error.setTitle("Error.");
            error.setHeaderText("Ha habido un error.");
            error.showAndWait();
		}

	}

	public static void main(String[] args) {
	
		launch(args);

	}

}
