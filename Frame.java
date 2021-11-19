import java.util.Scanner;
import java.io.File;
public class Frame{
  //Handles the compositing of data from Player, Blocks, and user input in order
  //to update and 
  private Player player;
  private Blocks blocks;
  public Frame(String path){
    //File IO code copied from the consumer review lab 
    try{
      Scanner f = new Scanner(new File(path));
      String dimensions = f.nextLine();
      int boundIndex = dimensions.indexOf("*");
      int height = Integer.parseInt(dimensions.substring(0,boundIndex));
      int width = Integer.parseInt(dimensions.substring(boundIndex + 1, dimensions.length()));
      String grid = "";
      while(f.hasNext()){
        grid += f.nextLine();
      }
      this.blocks = new Blocks(grid, height, width);
      this.player = new Player(grid, height, width);
    }catch(Exception e){
      System.out.println("Error!");
    }
  }
  public Player getPlayer(){
    return this.player;
  }
  public void prepareNextFrame(String command){
    IntArray attemptedMove = player.attemptMove(command);
  }
  @Override
  public String toString() {
    String out = "";
    for(int i = 0; i < this.blocks.getHeight(); i++){
      for(int j = 0; j < this.blocks.getWidth(); j++){
        if(this.player.getX() == i && this.player.getY() == j){
          out += this.player;
        }else{
          out += this.blocks.getBlockDisplay(i, j);
        }
      }
      out += "\n";
    }
    return out;
  }
}