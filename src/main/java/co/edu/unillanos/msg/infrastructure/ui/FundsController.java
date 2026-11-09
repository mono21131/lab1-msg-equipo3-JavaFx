package co.edu.unillanos.msg.infrastructure.ui;

import co.edu.unillanos.msg.application.usecase.EstimateFundsForWeek;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class FundsController {

    @FXML 
    private Label totalLabel;
    
    private final EstimateFundsForWeek estimateFunds;

    // Constructor que recibe el caso de uso (cumpliendo el contrato)
    public FundsController(EstimateFundsForWeek estimateFunds) {
        this.estimateFunds = estimateFunds;
    }

    @FXML
    private void onEstimate() {
        try {
            // Se llama al caso de uso y se formatea el resultado usando MoneyFormat
            double total = estimateFunds.execute();
            totalLabel.setText(MoneyFormat.format(total));
        } catch (IllegalStateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }
}