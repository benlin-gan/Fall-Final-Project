import java.util.Scanner;
import java.io.File;
class Main{
  //Represents the main menu
  public static void main(String[] args){
    Scanner stdin = new Scanner(System.in);
    String levelNames = "";
    //File IO code taken from consumer reviews lab
    try{
      //dump levelNames.txt into a String
      Scanner names = new Scanner(new File("levelNames.txt"));
      while(names.hasNext()){
        levelNames += names.nextLine();
        levelNames += "\n";
      }
    }catch (Exception e){
      System.out.println("Error!");
    }
    String level = null;
    boolean done = false; //controller variable for the main loop
    while(!done){
      System.out.println("SOKOBAN - Console Edition");
      System.out.println("Select a level collection:");
      System.out.println("Or type [h]elp to list all level collection names");
      System.out.println("Or [q]uit");
      String input = "";
      while(input == ""){
        input = stdin.nextLine().strip();
        //reject empty line input
      }
      if(input.equals("h") || input.equals("help")){
        String line = "";
        for(int i = 0; i < levelNames.length(); i++){
          String curr = levelNames.substring(i, i+1);
          if(curr.equals("\n")){
            System.out.println(line);
            line = "";
            try{
              //wait 0.1 seconds in order to let the user see all the level collection  names
              Thread.sleep(100);
            }catch(Exception e){
              Thread.currentThread().interrupt();
            }
          }else{
            line += curr;
          }
        }
      }else if(input.equals("q") || input.equals("quit")){
        //mark this variable to prepare to quit loop
        done = true;
      }else{
        int location = levelNames.indexOf(input);
        //make sure the string is in the levelNames, and is bounded on both sides by newlines
        if(location != -1 && levelNames.substring(location-1, location).equals("\n") && levelNames.substring(location + input.length(),location + input.length() + 1).equals("\n")){
          level = input;
          Game g = new Game("levels/" + level, stdin); //pass stdin into game
          stdin = g.loop(); //recieve it back;
          level = null;
        }else{
          System.out.println(input + " is not a valid level collection name.");
        }
      }
    }
    stdin.close();
  }
}
