import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements IEngine, ActionListener {
    private IWorldMap map;
    DataManager dataManager;
    List<Animal> animalList;
    MapPanel mapa1;
    MapPanel mapa2;
    MainWindow m;

    public SimulationEngine(){
        this.dataManager = new DataManager();
        this.map = new WorldMap(dataManager.mapWidth, dataManager.mapHeight, dataManager.jungleRatio);
        //m = new MainWindow(this, dataManager, mapa1, mapa2);
        this.mapa1 = new MapPanel(dataManager.mapWidth, dataManager.mapHeight, map.getJungleBegin(), map.getJungleEnd());
        this.mapa2 = new MapPanel(dataManager.mapWidth, dataManager.mapHeight, map.getJungleBegin(), map.getJungleEnd());
        m = new MainWindow(this, dataManager, mapa1, mapa2);

        initialize();
    }
    public void initialize() {
        this.map = new WorldMap(dataManager.mapWidth, dataManager.mapHeight, dataManager.jungleRatio);
        animalList = new ArrayList<>();
        //TODO
        TreeMap<Vector2D, List<Animal>> animals = map.getAnimals();
        for (int i = 0; i < map.getMAP_HEIGHT(); i++) {
            for (int j = 0; j < map.getMAP_WIDTH(); j++) {
                if (animals.get(new Vector2D(i, j)) != null) {
                    List<Animal> anims = animals.get(new Vector2D(i, j));
                    //Iterator value = anims.iterator();
                    for (int k = 0; k < anims.size(); k++) {
                        //anims.get(i).move();
                        animalList.add(anims.get(k));
                        //System.out.print(anims.get(i) + " ");
                    }
                }
            }
        }
    }

    @Override
    public void run(DrawAnimalsOld zoo){
        //TODO
        JFrame frame = new JFrame("App");
        frame.setContentPane(zoo.table1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        TreeMap<Vector2D, List<Animal>> animals = map.getAnimals();
        GrassMap grassMap = map.getGrassMap();
        System.out.println(map);
        int ages = 3;
        while(ages-- > 0) {

          for(int i = 0; i < this.animalList.size(); i++) {
               this.animalList.get(i).move();
           }





            System.out.println(map);



            for (int i = 0; i < zoo.height; i++) {
                for (int j = 0; j < zoo.width; j++) {

                    if (animals.get(new Vector2D(i, j)) != null) {
                        zoo.drawAnimal(new Vector2D(i, j), String.valueOf(animals.get(new Vector2D(i, j)).size()));
                    } else if (grassMap.objectAt(new Vector2D(i, j)) != null) {
                        zoo.drawAnimal(new Vector2D(i, j), "*");
                    } else {
                        zoo.drawAnimal(new Vector2D(i, j), "");

                    }
                }
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //zoo.drawAnimal(ani
        /*for (int i = 0; i < directions.length; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            animals.get(i % animals.size()).move(this.directions[i]);
            zoo.drawAnimal(animals);
        }*/
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case "Apply Changes":
                initialize();
                mapa1.resizeMap(dataManager.mapWidth, dataManager.mapHeight, map.getJungleBegin(), map.getJungleEnd());
                mapa2.resizeMap(dataManager.mapWidth, dataManager.mapHeight, map.getJungleBegin(), map.getJungleEnd());
                m.steerSecondMap(dataManager.twoMaps);

                m.pack();
                //
                break;
            case "Reset Default":
                dataManager.resetData();
                initialize();
                //initialize();
                mapa1.resizeMap(dataManager.mapWidth, dataManager.mapHeight, map.getJungleBegin(), map.getJungleEnd());
                mapa2.resizeMap(dataManager.mapWidth, dataManager.mapHeight, map.getJungleBegin(), map.getJungleEnd());
                m.steerSecondMap(dataManager.twoMaps);

                m.pack();
            default:
                break;
        }
    }



    public void runSimulation() {
        //DataManager dataManager = new DataManager();

        System.out.println("Hello");

        Genotype g1 = new Genotype();
        Genotype g2 = new Genotype();
        Genotype g3 = new Genotype(g1.getGenes(), g2.getGenes());
        System.out.println(g1);
        System.out.println(g2);
        System.out.println(g3);
        //for(int i = 0; i < 10; i++) System.out.println(g3.getRandomGene());


        //WorldMap mapa = new WorldMap(5, 5);
        map.place(new Animal(new Vector2D(2, 3), g1, 1, (IPositionChangeObserver) map));
        map.place(new Animal(new Vector2D(1, 1), g1, 2, (IPositionChangeObserver) map));
        map.place(new Animal(new Vector2D(1, 1), g1, 3, (IPositionChangeObserver) map));
        //map.addGrass();
        System.out.println(map.objectAt(new Vector2D(1, 1)).get(0));
        //System.out.println(mapa);


        //MapPanel mapa1 = new MapPanel(dataManager.mapWidth, dataManager.mapHeight);
        //MapPanel mapa2 = new MapPanel(dataManager.mapWidth, dataManager.mapHeight);



        DrawAnimalsOld zoo = new DrawAnimalsOld(5, 5);

        //SimulationEngine simulationEngine = new SimulationEngine(map, dataManager, mapa1, mapa2);
        //SimulationEngine simulationEngine = new SimulationEngine(mapa);
        //simulationEngine.run(zoo);


        //MainWindow m = new MainWindow(this, dataManager, mapa1, mapa2);
        m.setVisible(true);

    }
}
