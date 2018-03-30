package me.paulhobbel.engineOld;

import me.paulhobbel.engineOld.window.WindowManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Engine implements ActionListener {

    public static final float PPM = 10;

    private static Engine instance;

    private Timer gameLoop;
    private GameWorld world;

    private long lastTime;

    Engine() {
        gameLoop = new Timer(1, this);
        world = new GameWorld();
    }

    public static Engine getInstance() {
        if(instance == null) instance = new Engine();
        return instance;
    }

    public GameWorld getWorld() {
        return world;
    }

    public void start() {
        lastTime = System.nanoTime();
        gameLoop.start();
    }

    public void stop() {
        gameLoop.stop();

        // TODO: More cleanup

        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // First check if all our windows are closed
        if(!WindowManager.getInstance().isWindowActive()) {
            gameLoop.stop();
            return;
        }

        long time = System.nanoTime();
        double elapsedTime = (time-lastTime)/1e9;
        lastTime = time;

        world.update(elapsedTime);

        // TODO: Write a renderer
        WindowManager.getInstance().getWindow().update();
    }
}