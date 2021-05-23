
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import static javafx.scene.paint.Color.color;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel implements ActionListener {
    int score=0;
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 500;
    private final static int SIZEPIXEL = 15;

    private final static int TOTALPIXELS = (WIDTH * HEIGHT)
            / (SIZEPIXEL * SIZEPIXEL);

    private boolean inGame = true;
    private Timer timerr;

    private static int speed = 50;

    private VB_Snake snake = new VB_Snake();
    private Prey prey = new Prey();

    public GameBoard() {

        addKeyListener(new keywords());
        setBackground(Color.DARK_GRAY);
        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        initializeGame();
    }

//Used for the painting the components in the game
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);
    }

// Draw our Snake & Prey (Called on repaint()).
    void draw(Graphics g) {
        // This will only draw if the game is running or not
        if (inGame == true) {
            g.setColor(Color.YELLOW);
            g.fillRect(prey.getPreyX(), prey.getPreyY(), SIZEPIXEL, SIZEPIXEL); //Prey

            // Creating the snake
            for (int i = 0; i < snake.getJoint(); i++) {
                // snake head creation
                if (i == 0) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i),
                            SIZEPIXEL, SIZEPIXEL);
                    // Body of snake
                } else {
                    g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i),
                            SIZEPIXEL, SIZEPIXEL);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        } else {

            endGame(g);
        }
    }

    void initializeGame() {
        snake.setJoint(3); // set our snake's initial size

        // Create our snake's body
        for (int i = 0; i < snake.getJoint(); i++) {
            snake.setSnakeX(WIDTH / 2);
            snake.setSnakeY(HEIGHT / 2);
        }
        //Start moving snake right side
        snake.setMvingRight(true);

        // Creates Prey
        prey.createPrey();

        //Setting up the timer in the game
        timerr = new Timer(speed, this);
        timerr.start();
        
    }

// Check for the collision
    void checkPreyCollision() {
        int score=0;
        if ((approximity(snake.getSnakeX(0), prey.getPreyX(), 20)) && (approximity(snake.getSnakeY(0), prey.getPreyY(), 20))) {

            System.out.println("Crossed");
            score+=10;
            this.score+=score;
            // add joints to created snake
            snake.setJoint(snake.getJoint() + 1);
            // Create new Prey
            prey.createPrey();
            
        }
       
    }

//used to check the collision of the snake by itself
    void checkCollision() {
        //if the snake hits the joint
        for (int i = snake.getJoint(); i > 0; i--) {

            // snake will not able to intersect itself if it is not longer than 4
            if ((i > 4)
                    && (snake.getSnakeX(0) == snake.getSnakeX(i) && (snake
                    .getSnakeY(0) == snake.getSnakeY(i)))) {
                inGame = false; // then the game ends
            }
        }
        // If the snake intersects with the board edges..
        if (snake.getSnakeY(0) >= HEIGHT) {
            inGame = false;
        }
        if (snake.getSnakeY(0) < 0) {
            inGame = false;
        }
        if (snake.getSnakeX(0) >= WIDTH) {
            inGame = false;
        }
        if (snake.getSnakeX(0) < 0) {
            inGame = false;
        }
        // if the game is terminated will stop the timer
        if (!inGame) {
            timerr.stop();
        }
    }

    void endGame(Graphics g) {
    String message = "Upps,Game Over Try Again Score "+score;

    // Create a new font instance
    Font font = new Font("Times New Roman", Font.BOLD, 28);
    FontMetrics metrice = getFontMetrics(font);

    // Setting Up colour in the Frames
    g.setColor(Color.red);
    g.setFont(font);

    // Drawing Message to in the Frame
    g.drawString(message, (WIDTH - metrice.stringWidth(message)) / 2,
            HEIGHT / 2);

    System.out.println("Game COmpleted");
    }

// Run constantly as long as we're in game.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame == true) {

            checkPreyCollision();
            checkCollision();
            snake.move();

            System.out.println(snake.getSnakeX(0) + " " + snake.getSnakeY(0)
                    + " " + prey.getPreyX() + ", " + prey.getPreyY());
           
        }
        //Repainting the frame
        repaint();
        
    }

    private class keywords extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            int keyword = e.getKeyCode();

            if ((keyword == KeyEvent.VK_LEFT) && (!snake.isMvingRight())) {
                snake.setMvingLeft(true);
                snake.setMvingUp(false);
                snake.setMovingDwn(false);
            }

            if ((keyword == KeyEvent.VK_RIGHT) && (!snake.ismvingleft())) {
                snake.setMvingRight(true);
                snake.setMvingUp(false);
                snake.setMovingDwn(false);
            }

            if ((keyword == KeyEvent.VK_UP) && (!snake.isMovingDwn())) {
                snake.setMvingUp(true);
                snake.setMvingRight(false);
                snake.setMvingLeft(false);
            }

            if ((keyword == KeyEvent.VK_DOWN) && (!snake.isMvingUp())) {
                snake.setMovingDwn(true);
                snake.setMvingRight(false);
                snake.setMvingLeft(false);
            }

            if ((keyword == KeyEvent.VK_ENTER) && (inGame == false)) {

                inGame = true;
                snake.setMovingDwn(false);
                snake.setMvingRight(false);
                snake.setMvingLeft(false);
                snake.setMvingUp(false);

                initializeGame();
            }
        }
    }

    private boolean approximity(int alpha, int beta, int closeness) {
        return Math.abs((long) alpha - beta) <= closeness;
    }

    public static int getTotalDots() {
        return TOTALPIXELS;
    }

    public static int getTotalSize() {
        return SIZEPIXEL;
    }
}

