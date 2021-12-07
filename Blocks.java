public class Blocks{
  private int width; 
  private int height;
  private String blocking; 
  private String moveable;
  private String destination;
  private String originalState;
  private int getUnifiedIndex(int x, int y){
    //function that converts a coordinate pair into a single integer
    //representing the index of the String we actually want;
    return x * this.width + y;
  }
  public Blocks (String grid, int height, int width){   
    this.height = height;
    this.width = width;
    this.originalState = grid; //store inital state to reload it if restart is called
    initializeGrid(grid);
  }
  public void restart(){
    initializeGrid(this.originalState);
  }
  private void initializeGrid(String grid){
    this.blocking = "";
    this.moveable = "";
    this.destination = "";
    //Today I learned that java implictly casts null into "null" when you concatenate a String to it;
    //System.out.println(grid.length());
    for(int i = 0; i < this.height; i++){
      for(int j = 0; j < this.width; j++){
        int k = getUnifiedIndex(i, j);
        String curr = grid.substring(k, k+1);
        //System.out.print(curr);
        if(curr.equals("$")){
          //a box that blocks movement, is moveable, and is not on a destination 
          blocking += "1";
          moveable += "1";
          destination += "0";
        }else if(curr.equals("#")){
          //a wall that blocks movement, is not moveable, and is not a destination
          blocking += "1";
          moveable += "0";
          destination += "0";
        }else if(curr.equals(".") || curr.equals("+")){
          //A destination that currently has no box on it
          //+ is if the player is also on it;
          blocking += "0";
          moveable += "0";
          destination += "1";
        }else if(curr.equals("*")){
          //a moveable box that blocks movement and happens to already be on a destination
          blocking += "1";
          moveable += "1";
          destination += "1";
        }else{
          //empty space does not block movement, is nto moveable, and is not a destination
          blocking += "0";
          moveable += "0";
          destination += "0";
        }
      }
      //System.out.println();
    }
  }
  public String getBlockDisplay(int x, int y){
    //given the coordinates of a block return a unicode character to display;
    int i = getUnifiedIndex(x, y);
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
    int newC = getUnifiedIndex(oldX + deltaX, oldY + deltaY);
    if(blocking.substring(newC, newC+1).equals("0")){
      //if the new coordinates do not block, then movement is valid;
      return true;
    }else if(moveable.substring(newC, newC+1).equals("1")){
      //if the new coordinates have a moveable box:
      int farC = getUnifiedIndex(oldX + deltaX + deltaX, oldY + deltaY + deltaY);
      if(blocking.substring(farC, farC+1).equals("0")){
        //if the coordinates to where the block are going do not block, then movemnt is valid;
        blocking = Util.swapSubstringsAtIndexes(blocking, newC, newC+1, farC, farC+1);
        moveable = Util.swapSubstringsAtIndexes(moveable, newC, newC+1, farC, farC+1);
        //swap the data at the old and new coordinates of the block;
        return true;
      }
      //if box is not moveable, than you also cannot move;
      return false;
    }else{
      //in any other case, you cannot move;
      return false;
    }
  }
  public boolean checkVictory(){
    for(int i = 0; i < destination.length(); i++){
      if(this.destination.substring(i, i+1).equals("1")){
        if(this.moveable.substring(i, i+1).equals("0") || this.blocking.substring(i, i+1).equals("0")){
          //if any destination does not have a box on it, immediately halt the check and return false;
          //There is no grid iteration here, it just goes through every character; newlines are ignored because of the first if statement;
          return false;
        }
      } 
    }
    return true;
  }
}
