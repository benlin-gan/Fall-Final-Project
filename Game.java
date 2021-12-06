import java.util.Scanner;
public class Game {
  //represents a Game;
  private boolean done;
  private Frame frame;
  private LevelPack collection;
  public Game(String path) {
    this.collection = new LevelPack(path);
    this.done = false;
    this.frame = new Frame(this.collection.nextLevel());
  }
  public void loop(){
    Scanner stdin = new Scanner(System.in);
    while (!this.done){ 
      clearScreen();
      System.out.println("SOKOBAN - Console Edition");
      System.out.println(collection.getMetadata()); 
      System.out.println(this.frame);
      System.out.println("up[w]\nleft[a]\ndown[s]\nright[d]\nrestart[r]");
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
        this.frame.prepareNextFrame(command.trim());
      }
    }
    stdin.close();
    clearScreen();
    System.out.println("CONGRATULATIONS! YOU WIN!");
  }
  private void nextLevel(){
    Tag newLevel = this.collection.nextLevel();
    if(newLevel == null){
      this.done = true;
    }else{
      frame = new Frame(newLevel);
    }
  }
  private void clearScreen(){
    //Uses ANSI escape codes to clear standard output
    //taken from the Mastermind Lab
    System.out.print("\033[H\033[2J");  
    System.out.flush();
  }
}
