import java.util.Scanner;
public class Game {
  //represents a Game;
  private int level;
  private int maxLevel;
  private boolean done;
  private Frame frame;
  public Game(int maxLevel) {
    this.level = 1;
    this.maxLevel = maxLevel;
    this.done = false;
    this.frame = new Frame("level" + this.level + ".txt");
  }
  public void loop(){
    Scanner stdin = new Scanner(System.in);
    while (!this.done){ 
      clearScreen();
      System.out.println("SOKOBAN - TTY Edition");
      System.out.println("Level: " + this.level); 
      System.out.println(this.frame);
      System.out.println("up[w]\nleft[a]\ndown[s]\nright[d]\nrestart[r]");
      if(this.frame.checkVictory()){
        nextLevel();
      }else{
        //if no victory yet, ask for a command; 
        String command = stdin.next();
        if(command.trim().equals("r")){
          //restarts the level, by restoring the level data from file;
          this.frame = new Frame("level" + this.level + ".txt");
        }
        //using the command, update the frame;
        frame.prepareNextFrame(command.trim());
      }
    }
    stdin.close();
    clearScreen();
    System.out.println("CONGRATULATIONS! YOU WIN!");
  }
  private void nextLevel(){
    if(level < maxLevel){
      //generates next level if there is one
      this.frame = new Frame("level" + ++this.level + ".txt");
    }else{
      //else signals for the loop to halt;
      this.done = true;
    }
  }
  private void clearScreen(){
    //Uses ANSI escape codes to clear standard output
    //taken from the Mastermind Lab
    System.out.print("\033[H\033[2J");  
    System.out.flush();
  }
}
