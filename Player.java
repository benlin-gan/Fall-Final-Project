public class Player{
  //Represents the data associated with the player's avatar 
  private int x;
  private int y;
  private int originalX;
  private int originalY;
  public Player(String grid, int height, int width){
    //find the index of the player on the grid and translate it into a coordinate;
    int index = grid.indexOf("@");
    if(index == -1){
      index = grid.indexOf("+");
    }
    //System.out.println(index);
    this.x = index/width;
    this.y = index % width;
    this.originalX = this.x;
    this.originalY = this.y;
    //System.out.println(this.x + " " + this.y);
  }
  @Override
  public String toString() {
    return "\u26d1 "; //unicode road (looks like a person with a hardhat)
  }
  public void restart(){
    //mutator method resetting player to original location;
    this.x = this.originalX;
    this.y = this.originalY;
  }
  public IntArray attemptMove(String command){
    //accessor method that generates the representation of a move command
    int deltaX = 0;
    int deltaY = 0;
    IntArray attemptedMove = new IntArray();
    if(command.equals("w")){
      deltaX = -1; deltaY = 0;
    }else if(command.equals("a")){
      deltaX = 0; deltaY = -1;
    }else if(command.equals("s")){
      deltaX = 1; deltaY = 0;
    }else if(command.equals("d")){
      deltaX = 0; deltaY = 1;
    }
    attemptedMove.push(this.x);
    attemptedMove.push(this.y);
    attemptedMove.push(deltaX);
    attemptedMove.push(deltaY);
    return attemptedMove;
  }
  public void move(int deltaX, int deltaY){
    //The only mutator method.
    this.x += deltaX;
    this.y += deltaY;
  }
  public int getX() {
    //getter for x
    return this.x;
  }
  public int getY() {
    //getter for y
    return this.y;
  }
}