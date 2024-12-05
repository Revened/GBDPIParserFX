package org.gbdpiparserfx.model;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private static final List<ArrayList<String>> list = new ArrayList<>();
    private List<String> arrayWithResults;
    private static boolean isCountWritten = false;
    private static int searchThatAndBigger = 0;

    private String path;
    private File file;
    public void setUserSettings(String count) {
        try {
            int i = Integer.parseInt(count);
            searchThatAndBigger = i;
            isCountWritten = true;
        } catch (NumberFormatException e) {
            setUserSettings(false);
            //e.printStackTrace();
        }
    }
    public void setUserSettings(boolean trigger) {
        isCountWritten = trigger;
        searchThatAndBigger = 0;
    }

    public String fileChooser(Button start) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("null", "*.txt"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            this.file = file;
            path = file.getAbsolutePath();
            start.setDisable(false);
            return path;
        } else return "Файл не был выбран";
    }

    public void exit() {
        System.exit(0);
    }

    public void searchBetterVariable () {
        formatFile();

        arrayWithResults = new ArrayList<>();
        for (ArrayList<String> group : list) {
            String[] parser;
            String result = group.get(group.size() - 1);
            parser = result.split("[ /]");

            int count = Integer.parseInt(parser[parser.length - 2]);

            if (count > searchThatAndBigger) {
                if (!isCountWritten) {
                    searchThatAndBigger = count;
                    arrayWithResults = new ArrayList<String>();
                }
                arrayWithResults.add(group.get(group.size() - 1) + "\t" + group.get(2));

            } else if (count == searchThatAndBigger) {
                arrayWithResults.add(group.get(group.size() - 1) + "\t" + group.get(2));
            }
        }
    }
    public void showResult(TextArea textArea) {
        if (!arrayWithResults.isEmpty()) {
            textArea.setVisible(true);
            textArea.setPrefColumnCount(arrayWithResults.size());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arrayWithResults.size(); i++) {
                sb.append(arrayWithResults.get(i)).append("\n");
            }
            textArea.setText(sb.toString());
        }

    }
    private void formatFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            ArrayList<String> list2 = new ArrayList<>();
            while (br.ready()) {
                String line = br.readLine();
                if (!line.contains("Successes")) {
                    list2.add(line);
                } else {
                    list2.add(line);
                    list.add(list2);
                    list2 = new ArrayList<>();
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}
