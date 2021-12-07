public class Frame{
  //Handles the compositing of data from Player, Blocks, and user input in order
  //to update and display the graphics of a level.
  private String levelName;
  private Player player;
  private Blocks blocks;
  private static final String SECTION_BREAK = "-----------------------------------------------------------------";
  public Frame(Tag level){
    int height = Integer.parseInt(level.parseVariable("Height"));
    int width = Integer.parseInt(level.parseVariable("Width"));
    this.levelName = level.parseVariable("Id");
    String grid = "";
    for(int i = 0; i < height; i++){
      String line = level.nextChildTag("L").getBody();
      while(line.length() < width){
	      line += " ";
      }
      System.out.println(line);
      grid += line;
    }
    this.blocks = new Blocks(grid, height, width);
    this.player = new Player(grid, height, width);
  }
  public void restart(){
    this.blocks.restart();
    this.player.restart();
  }
  public Player getPlayer(){
    return this.player;
  }
  public void prepareNextFrame(String command){
    //Translate a movement command into a move;
    IntArray attemptedMove = this.player.attemptMove(command);
    int oldX = attemptedMove.at(0);
    int oldY = attemptedMove.at(1);
    int deltaX = attemptedMove.at(2);
    int deltaY = attemptedMove.at(3);
    if (this.blocks.checkValidMovement(oldX, oldY, deltaX, deltaY)){
      player.move(deltaX, deltaY);
    }
  }
  @Override
  public String toString() {
    //called to display the updated frame;
    String out = "";
    out += this.levelName + "\n";
    out += SECTION_BREAK + "\n";
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
    out += SECTION_BREAK;
    return out;
  }
  public boolean checkVictory(){
    //relays victory signal up;
    return this.blocks.checkVictory();
  }
}
