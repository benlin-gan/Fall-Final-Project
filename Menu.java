import java.util.Scanner;
import java.io.File;
public class Menu{
  //Represents the main menu
  Scanner stdin;
  String levelNames;
  public Menu(){
    this.stdin = new Scanner(System.in);
    this.levelNames = this.getLevelNames();
  }
  private String getLevelNames(){
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
    return levelNames;
  }
  public void loop(){
    //the main menu loop
    String level = null;
    boolean done = false; //controller variable for the main loop
    while(!done){
      System.out.println("SOKOBAN - Console Edition");
      System.out.println("Select a level collection:");
      System.out.println("Or type [h]elp to list all level collection names");
      System.out.println("Or [q]uit");
      String input = "";
      while(input == ""){
        input = this.stdin.nextLine().strip();
        //reject empty line input
      }
      if(input.equals("h") || input.equals("help")){
       listLevelNames();
      }else if(input.equals("q") || input.equals("quit")){
        //mark this variable to prepare to quit loop
        done = true;
      }else{
        int location = this.levelNames.indexOf(input);
        //make sure the string is in the levelNames, and is bounded on both sides by newlines
        if(location != -1 && levelNames.substring(location-1, location).equals("\n") && levelNames.substring(location + input.length(),location + input.length() + 1).equals("\n")){
          level = input;
          Game g = new Game("levels/" + level, stdin); //pass stdin into game
          this.stdin = g.loop(); //recieve it back;
          level = null;
        }else{
          System.out.println(input + " is not a valid level collection name.");
        }
      }
    }
  }
  private void listLevelNames(){
    //list the names of the files
    Util.clearScreen();
    String line = "";
    int lineNumber = 1;
    for(int i = 0; i < this.levelNames.length(); i++){
      String curr = this.levelNames.substring(i, i+1);
      if(curr.equals("\n")){
        System.out.println(line);
        line = "";
        if(lineNumber % 36 == 0){
          System.out.println("press [s] to scroll");
          System.out.println("or return main menu with [q]");
          String command = "";
          while(!command.equals("s")){
            if(command.equals("q")){
              Util.clearScreen();
              return;
            }
            command = stdin.nextLine().strip();
          }
          Util.clearScreen();
        }
        lineNumber++;
      }else{
        line += curr;
      }
    }
    return;
  }
}

