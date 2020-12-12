package gui;

import objects.Drawable;

import javax.swing.*;
import java.awt.*;

public class DrawMap extends JButton {
    private Drawable drawable;

    public DrawMap() {
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (drawable != null) {
            drawable.draw(g);
        }
    }

}
