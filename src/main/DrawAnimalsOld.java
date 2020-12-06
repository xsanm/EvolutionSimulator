import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.util.List;

public class DrawAnimalsOld {
    private JButton button1;
    public JPanel panel1;
    public JTable table1;
    final int width;
    final int height;

    public DrawAnimalsOld(int x, int y) {
        this.width = x;
        this.height = y;
        table1 = new JTable(height + 1, width + 1);

        table1.setRowHeight(20);
        TableColumnModel columnModel = table1.getColumnModel();
        for (int i = 0; i <= width; i++) {
            columnModel.getColumn(i).setPreferredWidth(20);
        }
        for (int i = 0; i < width; i++) {
            table1.setValueAt(i, 0, i + 1);
        }
        for (int i = 0; i < height; i++) {
            table1.setValueAt(i, y - i, 0);
        }

    }

    public void drawAnimal(Vector2D positon, String value) {
        //for (int i = 1; i <= height; i++) for (int j = 1; j <= width; j++) table1.setValueAt("", i, j);
        /*for (Animal a : animals) {
            table1.setValueAt(a.toString(), this.height - a.getPosition().y, a.getPosition().x + 1);
        }*/
        table1.setValueAt(value, this.height - positon.y, positon.x + 1);
    }
}
