import java.awt.*;
import java.awt.event.*;

public class Paddle {

    int x, y;
    int yVelocity = 0;
    int speed = 5;
    int width = 20;
    int height = 100;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public void move() {
        y += yVelocity;
    }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W: yVelocity = -speed; break;
            case KeyEvent.VK_S: yVelocity = speed; break;
            case KeyEvent.VK_UP: yVelocity = -speed; break;
            case KeyEvent.VK_DOWN: yVelocity = speed; break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                yVelocity = 0;
                break;
        }
    }
}