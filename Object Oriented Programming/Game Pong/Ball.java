import java.awt.*;

public class Ball {

    int x, y;
    int xVelocity = 3;
    int yVelocity = 3;
    int diameter = 30;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, diameter, diameter);
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;

        if (y <= 0 || y >= GamePanel.GAME_HEIGHT - diameter)
            yVelocity = -yVelocity;

        if (x <= 0 || x >= GamePanel.GAME_WIDTH - diameter) {
            // Reset bola ke tengah jika keluar (skor)
            x = GamePanel.GAME_WIDTH / 2;
            y = GamePanel.GAME_HEIGHT / 2;
        }
    }
}