package org.gbdpiparserfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.gbdpiparserfx.model.Model;

public class Controller {
    Model model = new Model();
    @FXML
    private TextArea outputResult;
    @FXML
    private Label textField;
    @FXML
    private Label chosenFile;
    @FXML
    private Button start;
    @FXML
    private CheckBox checkBox;
    @FXML
    private TextField impBox;

    @FXML
    protected void onExitButtonClick() {
        model.exit();
    }
    @FXML
    protected void onFileChooserButtonClick() {
        chosenFile.setText(model.fileChooser(start));
    }
    @FXML
    private void checkBoxButtonClick() {
        if (checkBox.isSelected()) {
            impBox.setVisible(true);
            textField.setVisible(true);
        } else {
            impBox.setVisible(false);
            textField.setVisible(false);
        }
    }
    @FXML
    protected void textFieldShow() {
        //test.setText(impBox.getText());
    }
    @FXML
    protected void startButtonClick() {
        if (checkBox.isSelected()) {
            model.setUserSettings(impBox.getText());
        } else model.setUserSettings(false);

        model.searchBetterVariable();
        model.showResult(outputResult);
    }
}