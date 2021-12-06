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
    boolean valid = false;
    String level = null;
    while(!valid){
      System.out.println("Select a level collection:");
      System.out.println("Or type [h]elp to list all level names");
      String input = stdin.nextLine().strip();
      if(input.equals("h") || input.equals("help")){
        System.out.println(levelNames);
      }else{
        int location = levelNames.indexOf(input);
        if(levelNames.substring(location-1, location).equals("\n") && levelNames.substring(location + input.length(),location + input.length() + 1).equals("\n")){
          level = input;
          valid = true;
        }else{
          System.out.println(input + " is not a valid level name");
        }
      }
    }
    Game g = new Game("levels/" + level);
    g.loop();
    stdin.close();

  }
}
