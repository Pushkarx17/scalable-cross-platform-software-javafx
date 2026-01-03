package cw3;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import mathutils.*;
import statutils.*;
import binmethod.*;

import org.apache.commons.math3.fitting.WeightedObservedPoints;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.IOException;

public class FXMLDocumentController {

    @FXML private Label meanLabel;
    @FXML private Label varianceLabel;
    @FXML private Label medianLabel;
    @FXML private Label stdLabel;

    @FXML private ChoiceBox<String> binChoice;
    @FXML private Label alphaLabel;
    @FXML private Label muLabel;
    @FXML private Label sigmaLabel;

    @FXML private BarChart<String, Number> histogramChart;
    @FXML private Label kLabel;
    @FXML private LineChart<Number, Number> pdfChart;

    private List<Double> data;

    
    
    @FXML
    public void initialize() {

    binChoice.getItems().addAll(
            "Square Root",
            "Sturges",
            "Rice Rule"
    );
    binChoice.setValue("Square Root");
    }

    // ===== LOAD FILE =====
    @FXML
    private void loadFile() {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Data File");

        File file = chooser.showOpenDialog(new Stage());
        if (file == null) return;

        data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(Double.parseDouble(line.trim()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        updateStatistics();
    }

    // ===== UPDATE STATS =====
    private void updateStatistics() {

        meanLabel.setText(String.valueOf(BasicStats.mean(data)));
        varianceLabel.setText(String.valueOf(BasicStats.variance(data)));
        medianLabel.setText(String.valueOf(
                BasicStats.median(new ArrayList<>(data))
        ));
        stdLabel.setText(String.valueOf(
                BasicStats.standardDeviation(data)
        ));
    }

    // ===== PLOT HISTOGRAM =====
    @FXML
    private void plotHistogram() {

        if (data == null || data.isEmpty()) return;

        // ---- Bin selection ----
        int k;

        switch (binChoice.getValue()) {

            case "Sturges": {
                SturgesFormula sturges = new SturgesFormula(data);
                sturges.calculateNumberOfBins();
                k = sturges.getNumberOfBins();
                break;
            }

            case "Rice Rule": {
                RiceRule rice = new RiceRule(data);
                rice.calculateNumberOfBins();
                k = rice.getNumberOfBins();
                break;
            }

            default: {
                SquareRootChoice sqrt = new SquareRootChoice(data);
                sqrt.calculateNumberOfBins();
                k = sqrt.getNumberOfBins();
                break;
            }
        }       
        
        kLabel.setText(String.valueOf(k));
        
        
        int[] bins = HistogramUtils.countBins(data, k);

        double min = BasicStats.min(data);
        double max = BasicStats.max(data);
        if (min == max) return;

        double binWidth = (max - min) / k;

        histogramChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Normalised Histogram");

        WeightedObservedPoints obs = new WeightedObservedPoints();

        // ---- Create bin ranges (compact labels) ----
        for (int i = 0; i < bins.length; i++) {

            double binStart = min + i * binWidth;
            double binEnd   = binStart + binWidth;
            double density  = bins[i] / (data.size() * binWidth);

            String category = String.format("%.6f", (binStart + binEnd )/ 2);

            series.getData().add(
                    new XYChart.Data<>(category, density)
            );

            // Gaussian fitting still uses numeric values
            double binCenter = (binStart + binEnd) / 2.0;
            obs.add(binCenter, density);
        }

        histogramChart.getData().add(series);
        

        // ---- Gaussian fit ----
        Gaussianfitting.calculateFitting(obs);
    
        alphaLabel.setText(String.valueOf(Gaussianfitting.alpha));
        muLabel.setText(String.valueOf(Gaussianfitting.mu));
        sigmaLabel.setText(String.valueOf(Gaussianfitting.sigma));
    
    
    // ===== PDF GRAPH (SEPARATE) =====
pdfChart.getData().clear();

XYChart.Series<Number, Number> pdfSeries = new XYChart.Series<>();
pdfSeries.setName("Gaussian PDF");

double alpha = Gaussianfitting.alpha;
double mu    = Gaussianfitting.mu;
double sigma = Gaussianfitting.sigma;

// Smooth curve
int points = 300;
double step = (max - min) / points;

for (int i = 0; i <= points; i++) {
    double x = min + i * step;

    double pdf =
            (alpha) *
            Math.exp(-Math.pow(x - mu, 2)
                     / (2 * sigma * sigma));

    pdfSeries.getData().add(new XYChart.Data<>(x, pdf));
}

pdfChart.getData().add(pdfSeries);
    
    
    }
    @FXML
private void savePNG() {

    FileChooser chooser = new FileChooser();
    chooser.setTitle("Save Charts as PNG");
    chooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("PNG Image", "*.png")
    );

    File file = chooser.showSaveDialog(new Stage());
    if (file == null) return;

    try {
        // ===== HISTOGRAM =====
        WritableImage histImage =
                histogramChart.snapshot(new SnapshotParameters(), null);

        File histFile = new File(
                file.getParent(),
                file.getName().replace(".png", "_histogram.png")
        );

        ImageIO.write(
                SwingFXUtils.fromFXImage(histImage, null),
                "png",
                histFile
        );

        // ===== PDF =====
        WritableImage pdfImage =
                pdfChart.snapshot(new SnapshotParameters(), null);

        File pdfFile = new File(
                file.getParent(),
                file.getName().replace(".png", "_pdf.png")
        );

        ImageIO.write(
                SwingFXUtils.fromFXImage(pdfImage, null),
                "png",
                pdfFile
        );

    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
}