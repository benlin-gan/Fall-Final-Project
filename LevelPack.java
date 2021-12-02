import java.util.Scanner;
import java.io.File;
public class LevelPack{
  private Tag global; //Aka "SokobanLevels", outermost tag that wraps all others;
  private String title;
  private String description;
  private String copyright;
  private int location; //argument passed to indexOf that restricts search space. Ideally, we should never call indexOf with this parameter numerically lower than it was before.
  public LevelPack(String path){
    //File IO code copied form Consumer Review Lab
    try{
      Scanner f = new Scanner(new File(path));
      String xmlHeader = f.nextLine();
      String raw = "";
      while(f.hasNext()){
        raw += f.nextLine();
        raw += '\n';
      }
      this.global = new Tag(raw, "SokobanLevels");
      this.title = this.global.nextChildTag("Title").toString();
      this.description = this.global.nextChildTag("Description").toString();
      this.copyright = this.global.nextChildTag("LevelCollection").parseVariable("Copyright");
    }catch(Exception e){
      System.out.println("Error!");
    }
    
  }
  @Override
  public String toString(){
    return "Title: " + this.title + 
           "\nDescription: " + this.description +
           "\nCopyright: " + this.copyright;
  }
} 

