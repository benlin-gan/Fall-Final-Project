import java.util.Scanner;
public class Game {
  private int level;
  private Frame frame;
  public Game() {
    this.level = 1;
    this.frame = new Frame("level" + level + ".txt");
  }
  public void loop(){
    while (true){
      Scanner stdin = new Scanner(System.in);
      System.out.print("\033[H\033[2J");  
      System.out.flush(); 
      System.out.println("SOKOBAN - Console Edition"); 
      System.out.println(this.frame);
      System.out.println("up[w]\nleft[a]\ndown[s]\nright[d]\nrestart[r]");
      String command = stdin.next();
      if(command.trim().equals("r")){
        this.frame = new Frame("level" + level + ".txt");
      }
      frame.prepareNextFrame(command.trim());
    }
    //stdin.close();
  }
}
