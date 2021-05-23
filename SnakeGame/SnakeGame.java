import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SnakeGame extends JFrame{

    public SnakeGame() {    
        add(new GameBoard());
        setTitle("Snake Game");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();        
    }
    
    public static void main(String[] args) {        
        EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
           LoginForm frame=new LoginForm();
            frame.setVisible(true);
        }
    });
    }
}
