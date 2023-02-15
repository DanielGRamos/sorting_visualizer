// AUTHOR: DANIEL RAMOS

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class SortingAlgosWindow extends JFrame {

    SortingAlgosWindow(SortingAlgosPanel sortingAlgosPanel) {
        new JFrame();
        setTitle("Sorting Algorithms - By Daniel Ramos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(sortingAlgosPanel);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
//                gamePanel.getGame().windowsFocusLost();
            }
        });
    }
}

