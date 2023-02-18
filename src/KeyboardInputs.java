// AUTHOR: DANIEL RAMOS

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
            case KeyEvent.VK_I -> sortsAlgos.getSortingAlgos().getSorts().setInsertionSortBol(true);
            case KeyEvent.VK_B -> sortsAlgos.getSortingAlgos().getSorts().setBubbleSortBol(true);
            case KeyEvent.VK_Q -> sortsAlgos.getSortingAlgos().getSorts().setQuickSortBol(true);
            case KeyEvent.VK_N -> sortsAlgos.getSortingAlgos().getSorts().setMergeSortInPlaceBol(true);
            case KeyEvent.VK_P -> sortsAlgos.getSortingAlgos().resetUPS();
            case KeyEvent.VK_M -> sortsAlgos.getSortingAlgos().getSorts().setMergeSortBol(true);
            case KeyEvent.VK_S -> sortsAlgos.getSortingAlgos().getSorts().setShellSort(true);
            case KeyEvent.VK_LEFT -> sortsAlgos.getSortingAlgos().getSorts().setLowerSize(true);
            case KeyEvent.VK_RIGHT -> sortsAlgos.getSortingAlgos().getSorts().setUpSize(true);
            case KeyEvent.VK_UP -> sortsAlgos.getSortingAlgos().getSorts().setFaster(true);
            case KeyEvent.VK_DOWN -> sortsAlgos.getSortingAlgos().getSorts().setSlower(true);
            case KeyEvent.VK_O -> sortsAlgos.getSortingAlgos().getSorts().setOld(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> sortsAlgos.getSortingAlgos().getSorts().setLowerSize(false);
            case KeyEvent.VK_RIGHT -> sortsAlgos.getSortingAlgos().getSorts().setUpSize(false);
            case KeyEvent.VK_UP -> sortsAlgos.getSortingAlgos().getSorts().setFaster(false);
            case KeyEvent.VK_DOWN -> sortsAlgos.getSortingAlgos().getSorts().setSlower(false);
            case KeyEvent.VK_O -> sortsAlgos.getSortingAlgos().getSorts().setOld(false);
        }
    }
}
