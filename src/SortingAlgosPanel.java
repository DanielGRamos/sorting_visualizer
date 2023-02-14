import javax.swing.*;
import java.awt.*;

// AUTHOR: DANIEL RAMOS
// DATE: 14/02/2023

public class SortingAlgosPanel extends JPanel {
    private final SortingAlgos sortingAlgos;

    public SortingAlgosPanel(SortingAlgos sortingAlgos, int screenWidth, int screenHeight) {
        this.sortingAlgos = sortingAlgos;
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        addKeyListener(new KeyboardInputs(this));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        sortingAlgos.render(g);
    }

    public SortingAlgos getSortingAlgos() {
        return sortingAlgos;
    }
}