import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    static final int GAME_WIDTH = 800;
    static final int GAME_HEIGHT = 600;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Paddle player1, player2;
    Ball ball;

    public GamePanel() {
        player1 = new Paddle(0, (GAME_HEIGHT / 2) - 50);
        player2 = new Paddle(GAME_WIDTH - 20, (GAME_HEIGHT / 2) - 50);
        ball = new Ball((GAME_WIDTH / 2) - 15, (GAME_HEIGHT / 2) - 15);

        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new AL());

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        player1.draw(g);
        player2.draw(g);
        ball.draw(g);
    }

    public void move() {
        player1.move();
        player2.move();
        ball.move();

        // Cek tabrakan bola dengan paddle
        if (ball.x <= player1.x + player1.width &&
            ball.y + ball.diameter >= player1.y &&
            ball.y <= player1.y + player1.height) {
            ball.xVelocity = -ball.xVelocity;
        }

        if (ball.x + ball.diameter >= player2.x &&
            ball.y + ball.diameter >= player2.y &&
            ball.y <= player2.y + player2.height) {
            ball.xVelocity = -ball.xVelocity;
        }
    }

    public void run() {
        double nsPerFrame = 1000000000 / 60.0;
        long lastTime = System.nanoTime();
        double delta = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerFrame;
            lastTime = now;

            while (delta >= 1) {
                move();
                repaint();
                delta--;
            }
        }
    }

    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            player1.keyPressed(e);
            player2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            player1.keyReleased(e);
            player2.keyReleased(e);
        }
    }
}