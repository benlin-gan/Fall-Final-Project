import java.util.Scanner;
public class Game {
  //represents a Game;
  private boolean done;
  private Frame frame;
  private LevelPack collection;
  private Scanner stdin;
  public Game(String path, Scanner stdin) {
    this.collection = new LevelPack(path);
    this.done = false;
    this.frame = new Frame(this.collection.nextLevel()); //load first level into frame
    this.stdin = stdin;
    //instead of making another scanner around System.in, inherit the Scanner from main
  }
  public Scanner loop(){
    while (!this.done){
      Util.clearScreen();
      System.out.println("SOKOBAN - Console Edition");
      System.out.println(collection.getMetadata()); //information about the level
      System.out.println(this.frame); //the level itself
      System.out.println("up[w]\nleft[a]\ndown[s]\nright[d]\nrestart[r]\nskip level[n]\nreturn to main menu[q]\n");
      if(this.frame.checkVictory()){
        nextLevel();
      }else{
        //if no victory yet, ask for a command;
        String command = stdin.next();
        if(command.trim().equals("r")){
          //restarts the level, by restoring the level data from file;
          this.frame.restart();
        }
        //using the command, update the frame;
        if(command.trim().equals("q")){
          this.done = true;
          //if the user calls, quit, then set the halt condition
        }else if(command.trim().equals("n")){
          nextLevel();
        }else{
          //if w. a, s, or d, let the frame handle it;
          this.frame.prepareNextFrame(command.trim());
        }
      }
    }
    Util.clearScreen();
    return stdin; //give stdin back to the main method
  }
  private void nextLevel(){
    //handler for moving onto next level
    Tag newLevel = this.collection.nextLevel();
    if(newLevel == null){
      this.done = true;
    }else{
      frame = new Frame(newLevel);
    }
  }
}
