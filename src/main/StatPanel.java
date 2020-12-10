import javax.swing.*;
import java.awt.*;

public class StatPanel extends JList {
    DataManager dataManager;
    JLabel animals;
    JLabel grasees;
    JLabel age;
    JLabel avEnergy;

    public StatPanel(DataManager dataManager){
        super(
        );
        //String str[] = ;

        this.dataManager = dataManager;
        refreshStats();
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //this.setLayout(new FlowLayout());

        /*animals = new JLabel("Animals alive: " + dataManager.animals);
        this.add(animals);
        grasees = new JLabel("Grasses: " + dataManager.grasses);
        this.add(grasees);
        age = new JLabel("Ages: " + dataManager.age);
        this.add(age);
        avEnergy = new JLabel("Average Energy: " + dataManager.averageEnergy);
        this.add(avEnergy);*/

    }

    public void refreshStats() {
        //this.setSelectedIndex(("Animals alive: " + dataManager.animals));
        //grasees.setText("Grasses: " + dataManager.grasses);
        //age.setText("Ages: " + dataManager.age);
    }
}
