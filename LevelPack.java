import java.util.Scanner;
import java.io.File;
public class LevelPack{
  private Tag global; //Aka "SokobanLevels", outermost tag that wraps all others;
  private Tag collection; //Aka "LevelCollection", immediate parent tag of all the levels;
  private String title;
  private String description;
  private String copyright;
  private String email;
  private String url;
  public LevelPack(String path){
    //File IO code copied form Consumer Review Lab
    String raw = "";
    try{
      Scanner f = new Scanner(new File(path));
      String xmlHeader = f.nextLine();
      while(f.hasNext()){
        raw += f.nextLine();
        raw += '\n';
      }
    }catch(Exception e){
      System.out.println("Error!");
    }
    this.global = new Tag(raw, "SokobanLevels");
    this.title = this.global.nextChildTag("Title").toString();
    this.description = this.global.nextChildTag("Description").toString().strip();
    this.collection = this.global.nextChildTag("LevelCollection");
    this.copyright = this.collection.parseVariable("Copyright");
  }
  public String getMetadata(){
    //return metadata about the level collection
    return this.title + this.description + "\nAuthor: " + this.copyright;
  }
  public Tag nextLevel(){
    //return the data for the next level
    return this.collection.nextChildTag("Level");
  }
}

