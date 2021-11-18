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
  public String move(String command){
    int deltaX = 0;
    int deltaY = 0;
    if(command.equals("w")){
      deltaX = -1; deltaY = 0;
    }else if(command.equals("a")){
      deltaX = 0; deltaY = -1;
    }else if(command.equals("s")){
      deltaX = 1; deltaY = 0;
    }else if(command.equals("d")){
      deltaX = 0; deltaY = 1;
    }
    return this.x + "" + this.y  + "" + deltaX + "" + deltaY; 
  }
}