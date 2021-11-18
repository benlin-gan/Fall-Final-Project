public class Player{
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
  public void move(String command){
    if(command.equals("w")){
      this.x--;
    }else if(command.equals("a")){
      this.y--;
    }else if(command.equals("s")){
      this.x++;
    }else if(command.equals("d")){
      this.y++;
    }
  }
}