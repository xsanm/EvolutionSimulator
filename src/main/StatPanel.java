import javax.swing.*;
import java.awt.*;

public class StatPanel extends JList {
    DataManager dataManager;
    JLabel animals;
    JLabel grasees;
    JLabel age;
    JLabel avEnergy;

    public StatPanel(DataManager dataManager){
        //String str[] = ;
        super(new DefaultListModel<>());
        this.dataManager = dataManager;
        refreshStats();

    }

    public void refreshStats() {
        double scale = 10000;
        DefaultListModel<String> l1 = new DefaultListModel<>();
        l1.addElement("Animals alive: " + dataManager.animals);
        l1.addElement("Grasses: " + dataManager.grasses);
        l1.addElement("Ages: " + dataManager.age);
        l1.addElement("Average energy: " + Math.round(dataManager.averageEnergy * scale) / scale  );
        l1.addElement("Average years of dead animals: " + Math.round(dataManager.averageLife * scale) / scale);
        l1.addElement("Average childre of alive animals: " + Math.round(dataManager.averageChildren * scale) / scale);
        l1.addElement("Dominating genotype: " );
        l1.addElement(dataManager.dominatingGenotype.toString().substring(0, 18));
        l1.addElement(dataManager.dominatingGenotype.toString().substring(18, 18));
        this.setModel(l1);
    }
}
