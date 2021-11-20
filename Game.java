import java.util.Scanner;
public class Game {
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
        String command = stdin.next();
        if(command.trim().equals("r")){
          this.frame = new Frame("level" + this.level + ".txt");
        }
        frame.prepareNextFrame(command.trim());
      }
    }
    stdin.close();
    clearScreen();
    System.out.println("CONGRATULATIONS! YOU WIN!");
  }
  private void nextLevel(){
    if(level < maxLevel){
      this.frame = new Frame("level" + ++this.level + ".txt");
    }else{
      this.done = true;
    }
  }
  private void clearScreen(){
      System.out.print("\033[H\033[2J");  
      System.out.flush();
  }
}
