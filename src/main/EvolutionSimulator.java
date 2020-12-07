import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvolutionSimulator {

    public static void main(String[] args) {
        SimulationEngine simulationEngine = new SimulationEngine();
        simulationEngine.runSimulation();
        simulationEngine.run();
    }
}
