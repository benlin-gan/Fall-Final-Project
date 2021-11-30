import java.util.Scanner;
import java.io.File;
public class LevelPack{
  private String raw;
  private String title;
  private String description;
  private String copyright;
  private int location;
  public LevelPack(String path){
    //File IO code copied form Consumer Review Lab
    try{
      Scanner f = new Scanner(new File(path));
      this.raw = "";
      while(f.hasNext()){
        this.raw += f.nextLine();
        this.raw += '\n';
      }
    }catch(Exception e){
      System.out.println("Error!");
    }
    this.title = Util.simpleTag(this.raw, "Title");
    this.description = Util.simpleTag(this.raw, "Description").strip();
    this.copyright = Util.parseVariable(Util.baseTag(this.raw, "LevelCollection"), "Copyright");
    this.location = this.raw.indexOf("<Level");
  }
  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return "Level Pack: " + this.title + 
           "\nDescription: " + this.description +
           "\nCopyright: " + this.copyright;
  }
} 