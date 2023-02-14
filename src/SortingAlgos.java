import java.awt.*;

// AUTHOR: DANIEL RAMOS
// DATE: 14/02/2023

public class SortingAlgos implements Runnable {
    private final SortingAlgosPanel sortingAlgosPanel;
    private final Sorts sorts;
    int screenWidth = 800;
    int screenHeight = 800;
    int upsSet = 200;

    public SortingAlgos() {
        sortingAlgosPanel = new SortingAlgosPanel(this, screenWidth, screenHeight);
        sorts = new Sorts(200, screenWidth, screenHeight, this);
        new SortingAlgosWindow(sortingAlgosPanel);
        sortingAlgosPanel.requestFocus();
        startLoop();
    }

    public int getUpsSet() {
        return upsSet;
    }

    public void setUpsSet(int upsSet) {
        if (upsSet < 10 || upsSet > 1000)
            return;

        this.upsSet = upsSet;
    }

    private void startLoop() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public Sorts getSorts() {
        return sorts;
    }

    private void update() {
        sorts.update();
    }

    public void render(Graphics g) {
        sorts.render(g);
    }

    @Override
    public void run() {
        final int fpsSet = 120;
        final double timePerFrame = 1000000000.0 / fpsSet;
        double timePerUpdate;

        long previousTime = System.nanoTime();

        int updates = 0;
        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        //noinspection InfiniteLoopStatement
        while (true) {
            long currentTime = System.nanoTime();
            timePerUpdate = 1000000000.0 / upsSet;


            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                sortingAlgosPanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println(frames + " | " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}