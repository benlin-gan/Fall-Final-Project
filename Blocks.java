import java.util.Scanner;
import java.io.File;
public class Blocks{
  private int width;
  private int height;
  private String blocking;
  private String moveable;
  private String destination;
  private int getUnifiedIndex(int x, int y){
    //function that converts a coordinate pair into a single integer
    //representing the index of the String we actually want;
    return x * this.width + y;
  }
  public Blocks (String path){
    //File IO code copied from the consumer review lab 
    try{
      Scanner f = new Scanner(new File(path));
      String dimensions = f.nextLine();
      //String dimensions = "4*3";
      int boundIndex = dimensions.indexOf("*");
      this.height = Integer.parseInt(dimensions.substring(0,boundIndex));
      this.width = Integer.parseInt(dimensions.substring(boundIndex + 1, dimensions.length()));
      this.blocking = "";
      this.moveable = "";
      this.destination = "";
      //Today I learned that java implictly casts null into "null" when you concatenate a String to it;
      for(int i = 0; i < this.height; i++){
        String line = f.nextLine();
        for(int j = 0; j < this.width; j++){
          String curr = line.substring(j, j+1);
          System.out.print(curr);
          if(curr.equals("b")){
            blocking += "1";
            moveable += "1";
            destination += "0";
          }else if(curr.equals("x")){
            blocking += "1";
            moveable += "0";
            destination += "0";
          }else if(curr.equals("o")){
            blocking += "0";
            moveable += "0";
            destination += "1";
          }else{
            blocking += "0";
            moveable += "0";
            destination += "0";
          }
        }
        System.out.println();
      }
      System.out.println(blocking.length());
    }catch(Exception e){
      System.out.println(e);
    }
  }
  private String getBlockProperties(int x, int y){
    //look in all three string arrays to determine the properties
    //of the block at coordinates (x, y);
    int i = getUnifiedIndex(x, y);
    return blocking.substring(i, i + 1) + moveable.substring(i, i+1) + destination.substring(i, i+1);
  }
  private String getBlockDisplay(int x, int y){
    String properties = getBlockProperties(x, y);
    //given the properties of a block, return a unicode character to display;
    boolean blocking = properties.substring(0, 1).equals("1");
    boolean moveable = properties.substring(1, 2).equals("1");
    boolean destination = properties.substring(2, 3).equals("1");
    if(blocking && moveable){
      return "\u2327"; //box
    }else if(blocking && !moveable){
      return "\u2593"; //wall
    }else if(destination && !blocking && !moveable){
      return "\u272a"; //star
    }else{
      return " "; //empty space
    }
  }
  @Override
  public String toString(){
    String out = "";
    for(int i = 0; i < this.height; i++){
      for(int j = 0; j < this.width; j++){
        out += getBlockDisplay(i, j);
      }
      out += "\n";
    }
    return out;
  }
  public int getWidth(){
    return this.width;
  }
  public int getHeight(){
    return this.height;
  }
}