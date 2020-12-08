import javax.swing.*;
import java.awt.*;

public class DrawMap  extends JButton {
    private Drawable drawable;

    public DrawMap() {}
    public DrawMap(String s) {super(s);}

    public DrawMap(Drawable drawable) {
        this.drawable = drawable;
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
