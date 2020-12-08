import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel{

    private DrawMap[][] panels;

    public MapPanel(int rows, int cols, Vector2D begin, Vector2D end){
        panels = new DrawMap[rows][cols];
        resizeMap(rows, cols, begin, end);
    }

    public void resizeMap(int rows, int cols, Vector2D begin, Vector2D end){

        //System.out.println(begin);
        //System.out.println(end);
        MapPanel.this.removeAll();

        setLayout(new GridLayout(rows, cols));
        panels = new DrawMap[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                DrawMap panel = new DrawMap();
                panel.setBorder(BorderFactory.createLineBorder(Color.black));
                panel.setBackground(new Color(153, 255, 153));
                if(begin.precedes(new Vector2D(i ,j)) && end.follows(new Vector2D(i ,j)))
                    panel.setBackground(new Color(0, 153, 0));
                panel.setPreferredSize(new Dimension(30, 30));
                panels[i][j] = panel;
                MapPanel.this.add(panel);
            }
        }
    }

    public void drawAnimal(Animal animal){
        //System.out.println(animal);
        DrawMap panel = panels[animal.getPosition().x][animal.getPosition().y];
        panel.setDrawable(animal);
    }
    public void eraseAnimal(Animal animal){
        //System.out.println(animal);
        DrawMap panel = panels[animal.getPosition().x][animal.getPosition().y];
        panel.setDrawable(null);
    }


    public void drawSomething(int x, int y){
        DrawMap panel = panels[x][y];
        panel.setDrawable(new Animal(new Vector2D(2, 3)));
    }



}
