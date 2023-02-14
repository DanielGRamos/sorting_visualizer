import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

// AUTHOR: DANIEL RAMOS
// DATE: 14/02/2023

public class SortingAlgosWindow extends JFrame {

    SortingAlgosWindow(SortingAlgosPanel sortingAlgosPanel) {
        new JFrame();
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

