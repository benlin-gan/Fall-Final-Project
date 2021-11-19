import java.util.Scanner;
class Main {
  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    Frame frame = new Frame("level1.txt");
    Prompt prompt = new Prompt();
    while (true){
      System.out.print("\033[H\033[2J");  
      System.out.flush(); 
      System.out.println("SOKOBAN - Console Edition"); 
      System.out.println(frame);
      System.out.println(prompt);
      String command = stdin.next();
      if(command.trim().equals("r")){
        frame = new Frame("level1.txt");
      }
      frame.prepareNextFrame(command.trim());
    }
    //stdin.close();
  }
}
