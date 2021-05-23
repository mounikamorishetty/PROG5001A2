/**
 * Write a description of class Snake here.
 *
 * @author (mounikamorishetty)
 * @version ()
 */

public class VB_Snake
{
   // Stores the part of the snake
private final int[] x = new int[GameBoard.getTotalDots()];
private final int[] y = new int[GameBoard.getTotalDots()];

// Stores direction of our snake
private boolean mvingleft = false;
private boolean mvingRight = false;
private boolean mvingUp = false;
private boolean mvingDown = false;

private int joints = 0; 
//get X cordinate from the snake
public int getSnakeX(int index) {
    return x[index];
}
//get y cordinate of the snake
public int getSnakeY(int index) {
    return y[index];
}
//Seting snake x
public void setSnakeX(int i) {
    x[0] = i;
}
//setting snake Y
public void setSnakeY(int i) {
    y[0] = i;
}
//Checking movement functions are provided below for the left,right ,up ,down
public boolean ismvingleft() {
    return mvingleft;
}

public void setMvingLeft(boolean mvingleft) {
    this.mvingleft = mvingleft;
}

public boolean isMvingRight() {
    return mvingRight;
}

public void setMvingRight(boolean mvingRight) {
    this.mvingRight = mvingRight;
}

public boolean isMvingUp() {
    return mvingUp;
}

public void setMvingUp(boolean mvingUp) {
    this.mvingUp = mvingUp;
}

public boolean isMovingDwn() {
    return mvingDown;
}

public void setMovingDwn(boolean mvingDown) {
    this.mvingDown = mvingDown;
}

public int getJoint() {
    return joints;
}

public void setJoint(int j) {
    joints = j;
}
//Funcyion for the move
public void move() {
     for (int i = joints; i > 0; i--) {

        //moving the snake joints up and down'
       
        x[i] = x[(i - 1)];
        y[i] = y[(i - 1)];
    }

    // Moves snake to the left
    if (mvingleft) {
        x[0] -= GameBoard.getTotalSize();
    }
    // To the right
    if (mvingRight) {
        x[0] += GameBoard.getTotalSize();
    }
    //Moving Down 
    if (mvingDown) {
        y[0] += GameBoard.getTotalSize();
    }
    //Moving Upside
    if (mvingUp) {
        y[0] -= GameBoard.getTotalSize();
    }


}
 
   

}

