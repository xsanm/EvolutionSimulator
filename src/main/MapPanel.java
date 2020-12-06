import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MapPanel extends JPanel{

    private DrawMap[][] panels;

    public MapPanel(int rows, int cols){
        setLayout(new GridLayout(rows, cols));
        panels = new DrawMap[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                DrawMap panel = new DrawMap();
                panel.setBorder(BorderFactory.createLineBorder(Color.black));
                panel.setBackground(Color.GREEN);
                panel.setPreferredSize(new Dimension(25, 25));
                panels[i][j] = panel;
                MapPanel.this.add(panel);
            }
        }
        DrawMap panel = panels[3][4];
        panel.setDrawable(new Animal(new Vector2D(2, 3)));
    }



}
