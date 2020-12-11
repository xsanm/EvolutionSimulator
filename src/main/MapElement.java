import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapElement implements Comparable<MapElement>, Drawable {
    BufferedImage image;
    private Vector2D position;

    public MapElement(Vector2D position) {
        this.position = position;
        try {
            String path = new File("src/main/grass.png").getAbsolutePath();
            //System.out.println(path);
            image = ImageIO.read(new File(path));


            //image = ImageIO.read(new File("C:\\Users\\xsan\\Desktop\\grass.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Vector2D getPosition() {
        return position;
    }


    @Override
    public int compareTo(MapElement ob) {
        Vector2D o = ob.getPosition();
        if (this.equals(o))
            return 0;
        if (this.getPosition().x < o.x)
            return -1;
        else if (this.getPosition().x == o.x)
            return this.getPosition().y - o.y;
        else
            return 1;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}
