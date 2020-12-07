import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.jar.JarEntry;

public class MainWindow extends JFrame implements ComponentListener {

    private SimulationEngine simulationEngine;
    DataManager dataManager;
    DataPanel dataPanel;
    MapPanel mapa1;
    MapPanel mapa2;
    JPanel map1Panel;
    JPanel map2Panel;

    public MainWindow(SimulationEngine simulationEngine, DataManager dataManager, MapPanel mapa1, MapPanel mapa2) {
        this.simulationEngine = simulationEngine;
        this.dataManager = dataManager;
        this.mapa1 = mapa1;
        this.mapa2 = mapa2;
        dataPanel = new DataPanel(simulationEngine, dataManager);

        map1Panel = new JPanel();
        map1Panel.add(this.mapa1);

        map2Panel = new JPanel();
        map2Panel.add(mapa2);
        //if(dataManager.twoMaps) map2Panel.add(mapa2);

        this.setTitle("Evolution Simulator");
        this.add(dataPanel, BorderLayout.WEST);
        this.add(map1Panel, BorderLayout.CENTER);
        //this.add(map2Panel, BorderLayout.EAST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1000, 800));
        this.setVisible(true);
        this.pack();
    }

    public void steerSecondMap(boolean is){
        if(is) this.add(map2Panel, BorderLayout.EAST);
        else this.remove(map2Panel);
    }


    @Override
    public void componentResized(ComponentEvent e) {
        int W = 1;
        int H = 1;
        Rectangle b = e.getComponent().getBounds();
        e.getComponent().setBounds(b.x, b.y, b.width, b.width*H/W);

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
