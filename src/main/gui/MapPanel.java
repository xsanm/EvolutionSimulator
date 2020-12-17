package gui;

import data.DataManager;
import map.IWorldMap;
import objects.Animal;
import objects.MapElement;
import objects.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MapPanel extends JPanel implements ActionListener {

    MainWindow mainWindow;
    List<Animal> animals;
    JDialog following;
    Animal animalToFollow;
    private DrawMap[][] panels;
    private DataManager dataManager;
    private IWorldMap mapa;


    public MapPanel(DataManager dataManager, IWorldMap mapa) {
        this.dataManager = dataManager;
        this.mapa = mapa;
        int rows = dataManager.getMapHeight();
        int cols = dataManager.getMapWidth();
        panels = new DrawMap[rows][cols];
    }

    public void resizeMap(DataManager dataManager, IWorldMap mapa) {
        this.dataManager = dataManager;
        this.mapa = mapa;
        int rows = dataManager.getMapHeight();
        int cols = dataManager.getMapWidth();
        Vector2D begin = mapa.getJungleBegin();
        Vector2D end = mapa.getJungleEnd();
        MapPanel.this.removeAll();

        setLayout(new GridLayout(rows, cols));
        panels = new DrawMap[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                DrawMap panel = new DrawMap();

                panel.setBorder(BorderFactory.createLineBorder(Color.black));
                panel.setBackground(new Color(153, 255, 153));
                if (begin.precedes(new Vector2D(i, j)) && end.follows(new Vector2D(i, j)))
                    panel.setBackground(new Color(0, 153, 0));
                panel.setPreferredSize(new Dimension(30, 30));
                panel.addActionListener(this);
                panels[i][j] = panel;
                MapPanel.this.add(panel);
            }
        }
    }

    public void drawAnimal(Animal animal) {
        int y = dataManager.getMapHeight() - 1 - animal.getPosition().y;
        DrawMap panel = panels[y][animal.getPosition().x];
        panel.setDrawable(animal);
        if (animal == animalToFollow) refreshFollowing();
    }

    public void eraseAnimal(Animal animal) {
        int y = dataManager.getMapHeight() - 1 - animal.getPosition().y;
        DrawMap panel = panels[y][animal.getPosition().x];
        panel.setDrawable(null);
    }

    public void eraseGrass(MapElement element) {
        int y = dataManager.getMapHeight() - 1 - element.getPosition().y;
        DrawMap panel = panels[y][element.getPosition().x];
        panel.setDrawable(null);
    }

    public void drawGrass(MapElement element) {
        int y = dataManager.getMapHeight() - 1 - element.getPosition().y;
        DrawMap panel = panels[y][element.getPosition().x];
        panel.setDrawable(element);
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        DrawMap btn = (DrawMap) e.getSource();
        Vector2D vec = new Vector2D(btn.getX() / 30, dataManager.getMapHeight() - 1 - btn.getY() / 30);
        mapa.getObjectsAtPosition(vec);
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.PAGE_AXIS));
        dialogPanel.add(new JLabel("Postion: " + vec));

        animals = mapa.objectAt(vec);
        int id = 1;
        if (animals != null) {
            dialogPanel.add(new JLabel("Animals:"));
            for (Animal a : animals) {
                if(a == null) return;
                DefaultListModel<String> l1 = new DefaultListModel<>();
                l1.addElement("Animal postion: " + a.getPosition());
                l1.addElement("Animal energy: " + a.getEnergy());
                l1.addElement("Animal children: " + a.getChildren());
                l1.addElement("Animal years: " + a.getYears());
                l1.addElement("Animal genotype: " + a.getGenotype());

                JList lista = new JList();
                lista.setModel(l1);
                dialogPanel.add(lista);

                //dialogPanel.add(new JLabel("objects.Animal energy: " + a.getEnergy() + ", genotype: " + a.getGenotype()));
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
        d.pack();
        d.setVisible(true);
        d.setLocation(100, 100);
    }

    private void followAnimal(ActionEvent actionEvent) {
        int id = Integer.parseInt(actionEvent.getActionCommand()) - 1;
        if(animals == null || animals.isEmpty()) return;
        animalToFollow = animals.get(id);
        following = new JDialog(mainWindow, "following animal");
        refreshFollowing();
        following.setVisible(true);
        following.setLocation(100, 100);
    }

    private void refreshFollowing() {
        //.removeAll();
        if(animalToFollow.getEnergy() <= 0) {
            following.add(new JLabel("ANIMAL IS DEAD"));
            following.pack();
            return;
        }
        DefaultListModel<String> l1 = new DefaultListModel<>();
        l1.addElement("Animal postion: " + animalToFollow.getPosition());
        l1.addElement("Animal energy: " + animalToFollow.getEnergy());
        l1.addElement("Animal children: " + animalToFollow.getChildren());
        l1.addElement("Animal years: " + animalToFollow.getYears());
        l1.addElement("Animal genotype: " + animalToFollow.getGenotype());

        JList lista = new JList();
        lista.setModel(l1);


        following.add(lista);
        following.pack();
    }
}
