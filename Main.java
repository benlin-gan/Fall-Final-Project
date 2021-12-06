import java.util.Scanner;
class Main{
  public static void main(String[] args){
    Scanner stdin = new Scanner(System.in);
    System.out.println("Select a level collection:");
    String level = stdin.nextLine().strip();
    Game g = new Game("levels/" + level);
    g.loop();
    stdin.close();
  }
}
