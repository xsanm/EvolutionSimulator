import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements IEngine, ActionListener {
    private final IWorldMap map;
    List<Animal> animalList;

    public SimulationEngine(IWorldMap map){
        this.map = map;
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

    public SimulationEngine(WorldMap mapa, IPositionChangeObserver mapa1) {
        this.map = mapa;
    }

    @Override
    public void run() {
        ;
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

    }
}
