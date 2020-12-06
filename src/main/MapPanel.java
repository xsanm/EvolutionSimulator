import javax.swing.*;

public class MapPanel {
    public JPanel map;
    public JTable table;

    public MapPanel(){
        map = new JPanel();
        table = new JTable(10 + 1, 5 + 1);

    }

    public JPanel getMap() {
        return map;
    }
}
