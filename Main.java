import java.util.Scanner;
class Main {
  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    Frame f = new Frame(new Player(3, 1), new Blocks("level1.txt"));
    Prompt p = new Prompt();
    while (true){
      System.out.print("\033[H\033[2J");  
      System.out.flush(); 
      System.out.println("SOKOBAN - Console Edition"); 
      System.out.println(f);
      System.out.println(p);
      String command = stdin.next();
      //System.out.print(command.trim());
      f.getPlayer().move(command.trim());
    }
    //stdin.close();
  }
}
