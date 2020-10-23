package nl.brouwer.guitarshop.ui.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class StyledScene extends Scene {
    // uses JMetro for styling
    public StyledScene(Parent parent) {
        super(parent);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(this);
        jMetro.setAutomaticallyColorPanes(true);

        this.getStylesheets().add("resources/css/Style.css");
    }
}
