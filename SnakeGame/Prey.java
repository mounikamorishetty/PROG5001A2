/**
 * Class Prey represents any prey object.
 *
 * @author mounikamorishetty
/

 * @version
 */

public class Prey {
private VB_Snake vbSnake = new VB_Snake();
private int PreyX; // storing Prey x position
private int PreyY; // storing Prey y position

// Used to determine randomPosition of prey
private final int RANDOMPOSITION = 30;
//Creating Prey 
public void createPrey() {
  //Creating Prey in the random location in the game using the gameBoard
  int randomPlace = (int) (Math.random() * RANDOMPOSITION);
    PreyX = ((randomPlace * GameBoard.getTotalSize()));

    randomPlace = (int) (Math.random() * RANDOMPOSITION);
    PreyY = ((randomPlace * GameBoard.getTotalSize()));

    if ((PreyX == vbSnake.getSnakeX(0)) && (PreyY == vbSnake.getSnakeY(0))) {
        createPrey();
    }
}

public int getPreyX() {

    return PreyX;
}

public int getPreyY() {
    return PreyY;
}
}


