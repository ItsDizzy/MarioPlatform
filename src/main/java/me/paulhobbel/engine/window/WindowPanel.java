package me.paulhobbel.engine.window;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.graphics.renderer.DebugRenderer;
import me.paulhobbel.engine.graphics.renderer.DebugRendererV2;
import me.paulhobbel.engine.graphics.renderer.MapRenderer;
import me.paulhobbel.engine.graphics.renderer.SpriteRenderer;
import me.paulhobbel.engine.map.objects.RectangleMapObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WindowPanel extends JPanel {

    WindowPanel(Dimension dimension) {
        setPreferredSize(dimension);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setTransform(Engine.getInstance().getWorld().getCamera().getTransform());
        //g2d.scale(1, -1);
        MapRenderer.getInstance().render(g2d);
        SpriteRenderer.getInstance().render(g2d);

        //DebugRenderer.getInstance().render(g2d);

        DebugRendererV2.draw(g2d, Engine.getInstance().getWorld().getPhysicsWorld(), 100.0);
    }
}
