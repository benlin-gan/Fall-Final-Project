public class Player{
  //Represents the data 
  private int x;
  private int y;
  public Player(String grid, int height, int width){
    int index = grid.indexOf("p");
    this.x = index/height;
    this.y = index % width;
  }
  @Override
  public String toString() {
    return "x";
  }
  public int getX(){
    return this.x;
  }
  public int getY(){
    return this.y;
  }
  public IntArray attemptMove(String command){
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
}