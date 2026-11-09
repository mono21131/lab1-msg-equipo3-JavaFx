package co.edu.unillanos.msg.infrastructure.ui;

import co.edu.unillanos.msg.application.usecase.EstimateFundsForWeek;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class FundsController {
    
    @FXML 
    private Label totalLabel;
    
    private final EstimateFundsForWeek estimateFunds;
    
    public FundsController(EstimateFundsForWeek estimateFunds) {
        this.estimateFunds = estimateFunds;
    }
    
    @FXML
    private void onEstimate() {
        try {
            totalLabel.setText(MoneyFormat.format(estimateFunds.execute()));
        } catch (IllegalStateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }
}