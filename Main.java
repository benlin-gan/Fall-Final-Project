import java.util.Scanner;
import java.io.File;
class Main{
  public static void main(String[] args){
    Scanner stdin = new Scanner(System.in);
    String levelNames = "";
    try{
      Scanner names = new Scanner(new File("levelNames.txt"));
      while(names.hasNext()){
        levelNames += names.nextLine();
        levelNames += "\n";
      }
    }catch (Exception e){
      System.out.println("error");
    }
    String level = null;
    boolean done = false;
    while(!done){
      System.out.println("Select a level collection:");
      System.out.println("Or type [h]elp to list all level names");
      System.out.println("Or [q]uit");
      String input = stdin.nextLine().strip();
      if(input.equals("h") || input.equals("help")){
        System.out.println(levelNames);
      }else if(input.equals("q") || input.equals("quit")){
        done = true;
      }else{
        int location = levelNames.indexOf(input);
        if(levelNames.substring(location-1, location).equals("\n") && levelNames.substring(location + input.length(),location + input.length() + 1).equals("\n")){
          level = input;
          Game g = new Game("levels/" + level, stdin);
          g.loop();
        }else{
          System.out.println(input + " is not a valid level name");
        }
      }
    }
    stdin.close();
  }
}
