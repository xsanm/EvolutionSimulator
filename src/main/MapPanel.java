import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MapPanel extends JPanel implements ActionListener {

    private DrawMap[][] panels;
    private DataManager dataManager;
    private IWorldMap mapa;
    MainWindow mainWindow;
    List<Animal> animals;
    JDialog following;
    Animal animalToFollow;


    public MapPanel(DataManager dataManager, IWorldMap mapa) {
        this.dataManager = dataManager;
        this.mapa = mapa;
        int rows = dataManager.mapHeight;
        int cols = dataManager.mapWidth;
         //Vector2D begin = mapa.getJungleBegin();
        //Vector2D end = mapa.getJungleEnd();
        panels = new DrawMap[rows][cols];
        //resizeMap(dataManager, mapa);
    }

    public void resizeMap(DataManager dataManager, IWorldMap mapa){
        this.dataManager = dataManager;
        this.mapa = mapa;
        int rows = dataManager.mapHeight;
        int cols = dataManager.mapWidth;
        Vector2D begin = mapa.getJungleBegin();
        Vector2D end = mapa.getJungleEnd();
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
                panel.addActionListener(this);
                panels[i][j] = panel;
                MapPanel.this.add(panel);
            }
        }
    }

    public void drawAnimal(Animal animal){
        //System.out.println(animal);
        //int x = dataManager.mapHeight - 1 - animal.getPosition().x;
        int y = dataManager.mapHeight - 1 - animal.getPosition().y;
        DrawMap panel = panels[y][animal.getPosition().x];
        panel.setDrawable(animal);
        if(animal == animalToFollow) refreshFollowing();
    }

    public void eraseAnimal(Animal animal){
        //System.out.println(animal);
        int y = dataManager.mapHeight - 1 - animal.getPosition().y;
        DrawMap panel = panels[y][animal.getPosition().x];
        panel.setDrawable(null);
        //if(animal == animalToFollow) refreshFollowing();
    }
    public void eraseGrass(MapElement element){
        //System.out.println(animal);
        int y = dataManager.mapHeight - 1 - element.getPosition().y;
        DrawMap panel = panels[y][element.getPosition().x];
        panel.setDrawable(null);
    }

    public void drawGrass(MapElement element){
        //System.out.println(animal);
        int y = dataManager.mapHeight - 1 - element.getPosition().y;
        DrawMap panel = panels[y][element.getPosition().x];
        panel.setDrawable(element);
    }


    public void drawSomething(int x, int y){
        DrawMap panel = panels[x][y];
        panel.setDrawable(new Animal(new Vector2D(2, 3)));
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        DrawMap btn = (DrawMap)e.getSource();
        Vector2D vec = new Vector2D(btn.getX() / 30, dataManager.mapHeight -1 - btn.getY() / 30);
        System.out.println(vec);
        mapa.getObjectsAtPosition(vec);
        //e.getSource();
        //System.out.println(e.getSource());
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.PAGE_AXIS));
        dialogPanel.add(new JLabel("Postion: " + vec ));

        animals = mapa.objectAt(vec);
        int id = 1;
        if(animals != null) {
            dialogPanel.add(new JLabel("Animals:"));
            for (Animal a : animals) {
                dialogPanel.add(new JLabel("Animal energy: " + a.getEnergy() + ", genotype: " + a.getGenotype()));
                JPanel foll = new JPanel();
                foll.add(new JLabel("click here to follow this animal ->"));
                JButton jb = new JButton(String.valueOf(id));
                jb.addActionListener(this::followAnimal);
                foll.add(jb);
                dialogPanel.add(foll);
                id++;
            }
        } else {
            dialogPanel.add(new JLabel("Animals: NONE"));
        }

        JDialog d = new JDialog(mainWindow, "dialog Box");
        d.setTitle("Objects at");
        d.add(dialogPanel);
        //d.setLocationRelativeTo(null);
        d.pack();
        d.setVisible(true);
        d.setLocation(100, 100);
    }

    private void followAnimal(ActionEvent actionEvent) {
        int id = Integer.parseInt(actionEvent.getActionCommand()) - 1;
        animalToFollow = animals.get(id);
        following = new JDialog(mainWindow, "following animal");
        refreshFollowing();
        following.setVisible(true);
        //System.out.println(actionEvent.getActionCommand());
        following.setLocation(100, 100);
    }

    private void refreshFollowing() {
        //.removeAll();
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        jp.add(new JLabel(animalToFollow.toString()));
        jp.add(new JLabel("Animal Energy: " + animalToFollow.getEnergy()));
        jp.add(new JLabel("Chlidren: " + animalToFollow.getChildren()));
        jp.add(new JLabel("Age: " + animalToFollow.getYears()));

        following.add(jp);
        following.pack();
    }
}
