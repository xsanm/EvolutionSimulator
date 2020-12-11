import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    DataManager dataManager;
    DataPanel dataPanel;
    MapPanel mapa1;
    MapPanel mapa2;
    JPanel map1Panel;
    JPanel map2Panel;
    JPanel leftPanel;
    StatPanel statPanel1;
    StatPanel statPanel2;
    Label l1;
    private SimulationEngine simulationEngine;

    public MainWindow(SimulationEngine simulationEngine, DataManager dataManager, MapPanel mapa1, MapPanel mapa2, StatPanel statPanel1, StatPanel statPanel2) {
        this.simulationEngine = simulationEngine;
        this.dataManager = dataManager;
        this.mapa1 = mapa1;
        this.mapa2 = mapa2;
        this.statPanel1 = statPanel1;
        this.statPanel2 = statPanel2;

        mapa1.setMainWindow(this);
        mapa2.setMainWindow(this);

        dataPanel = new DataPanel(simulationEngine, dataManager);

        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

        leftPanel.add(dataPanel);

        l1 = new Label("Map 2 Stats");

        leftPanel.add(new Label("Map 1 Stats"));
        leftPanel.add(this.statPanel1);


        map1Panel = new JPanel();
        map1Panel.add(this.mapa1);

        map2Panel = new JPanel();
        map2Panel.add(mapa2);

        this.setTitle("Evolution Simulator");
        this.add(leftPanel, BorderLayout.WEST);
        this.add(map1Panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setPreferredSize(new Dimension(800, 800));
        this.setMinimumSize(new Dimension(800, 800));
        //this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
    }

    public void steerSecondMap(boolean is) {
        if (is) {
            leftPanel.add(l1);
            leftPanel.add(this.statPanel2);
            this.add(map2Panel, BorderLayout.EAST);
            this.pack();
        } else {
            leftPanel.remove(statPanel2);
            leftPanel.remove(l1);
            this.remove(map2Panel);
        }


    }
}
