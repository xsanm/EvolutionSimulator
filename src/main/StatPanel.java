import javax.swing.*;
import java.awt.*;

public class StatPanel extends JPanel {
    DataManager dataManager;
    JLabel animals;
    JLabel grasees;
    JLabel age;

    public StatPanel(DataManager dataManager){
        this.dataManager = dataManager;

        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new FlowLayout());

        animals = new JLabel("Animals alive: " + dataManager.animals);
        this.add(animals);
        grasees = new JLabel("Grasses: " + dataManager.grasses);
        this.add(grasees);
        age = new JLabel("Ages: " + dataManager.age);
        this.add(age);
    }

    public void refreshStats() {
        animals.setText("Animals alive: " + dataManager.animals);
        grasees.setText("Grasses: " + dataManager.grasses);
        age.setText("Ages: " + dataManager.age);
    }
}
