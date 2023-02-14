import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private final SortingAlgosPanel sortsAlgos;

    public KeyboardInputs(SortingAlgosPanel sortsAlgos) {
        this.sortsAlgos = sortsAlgos;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R -> sortsAlgos.getSortingAlgos().getSorts().randomizeArraySame();
            case KeyEvent.VK_I -> sortsAlgos.getSortingAlgos().getSorts().setInsertionSort(true);
            case KeyEvent.VK_B -> sortsAlgos.getSortingAlgos().getSorts().setBubbleSort(true);
            case KeyEvent.VK_Q -> sortsAlgos.getSortingAlgos().getSorts().setQuickSort(true);
            case KeyEvent.VK_LEFT -> sortsAlgos.getSortingAlgos().getSorts().setLowerSize(true);
            case KeyEvent.VK_RIGHT -> sortsAlgos.getSortingAlgos().getSorts().setUpSize(true);
            case KeyEvent.VK_UP -> sortsAlgos.getSortingAlgos().getSorts().setFaster(true);
            case KeyEvent.VK_DOWN -> sortsAlgos.getSortingAlgos().getSorts().setSlower(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> sortsAlgos.getSortingAlgos().getSorts().setLowerSize(false);
            case KeyEvent.VK_RIGHT -> sortsAlgos.getSortingAlgos().getSorts().setUpSize(false);
            case KeyEvent.VK_UP -> sortsAlgos.getSortingAlgos().getSorts().setFaster(false);
            case KeyEvent.VK_DOWN -> sortsAlgos.getSortingAlgos().getSorts().setSlower(false);
        }
    }
}
