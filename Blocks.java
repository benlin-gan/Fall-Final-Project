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
  public Blocks (String grid, int height, int width){   
    this.height = height;
    this.width = width;
    this.blocking = "";
    this.moveable = "";
    this.destination = "";
    //Today I learned that java implictly casts null into "null" when you concatenate a String to it;
    for(int i = 0; i < this.height; i++){
      for(int j = 0; j < this.width; j++){
        int k = getUnifiedIndex(i, j);
        String curr = grid.substring(k, k+1);
        //System.out.print(curr);
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
        }else if(curr.equals("d")){
          blocking += "1";
          moveable += "1";
          destination += "1";
        }else{
          blocking += "0";
          moveable += "0";
          destination += "0";
        }
      }
      //System.out.println();
    }
  }
  public String getBlockDisplay(int x, int y){
    int i = getUnifiedIndex(x, y);
    //given the properties of a block, return a unicode character to display;
    boolean blocking = this.blocking.substring(i, i+1).equals("1");
    boolean moveable = this.moveable.substring(i, i+1).equals("1");
    boolean destination = this.destination.substring(i, i+1).equals("1");
    if(blocking && moveable && !destination){
      return "\u264a"; //gemini
    }else if(blocking && moveable && destination){
      return "\u2705"; //checkmark
    }else if(blocking && !moveable){
      return "\u26dd "; //wall
    }else if(destination && !blocking && !moveable){
      return "\u26d4"; //one-way sign
    }else{
      return "  "; //empty space
    }
  }
  public int getWidth(){
    return this.width;
  }
  public int getHeight(){
    return this.height;
  }
  public boolean checkValidMovement(int oldX, int oldY, int deltaX, int deltaY){
    //System.out.println(oldX + " " + oldY + " " + deltaX + " " + deltaY);
    int newC = getUnifiedIndex(oldX + deltaX, oldY + deltaY);
    if(blocking.substring(newC, newC+1).equals("0")){
      return true;
    }else if(moveable.substring(newC, newC+1).equals("1")){
      int farC = getUnifiedIndex(oldX + deltaX + deltaX, oldY + deltaY + deltaY);
      if(blocking.substring(farC, farC+1).equals("0")){
        blocking = Util.swapSubstringsAtIndexes(blocking, newC, newC+1, farC, farC+1);
        moveable = Util.swapSubstringsAtIndexes(moveable, newC, newC+1, farC, farC+1);
      return true;
      }
      return false;
    }else{
      return false;
    }
  }
}