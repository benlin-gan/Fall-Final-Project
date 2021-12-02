import java.util.Scanner;
import java.io.File;
public class LevelPack{
  private Tag global; //Aka "SokobanLevels", outermost tag that wraps all others;
  private Tag collection; //Aka "LevelCollection", immediate parent tag of all the levels;
  private String title;
  private String description;
  private String copyright;
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
      this.description = this.global.nextChildTag("Description").toString().strip();
      this.collection = this.global.nextChildTag("LevelCollection");
      this.copyright = this.collection.parseVariable("Copyright");
      
    }catch(Exception e){
      System.out.println("Error!");
    }
    
  }
  @Override
  public String toString(){
    return this.title + this.description + "\nCopyright: " + this.copyright;
  }
  public Tag nextLevel(){
    return this.collection.nextChildTag("Level");
  }
} 

