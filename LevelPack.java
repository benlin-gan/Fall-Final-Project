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
    this.title = simpleTag("Title");
    this.description = simpleTag("Description").strip();
    this.copyright = LevelPack.parseVariable(this.baseTag("LevelCollection"), "Copyright");
    this.location = this.raw.indexOf("<Level");
  }
  @Override
  public String toString() {
    return "Level Pack: " + this.title + 
           "\nDescription: " + this.description +
           "\nCopyright: " + this.copyright;
  }
  public String getLevel(){
    return this.baseTag("Level");
  }
  public String baseTag(String tagName){
    //returns the first subsection of the data which is wrapped in the tags specified by tagName;
    String openingTag = "<" + tagName + "";
    String closingTag = "</" + tagName + ">";
    return this.raw.substring(this.raw.indexOf(openingTag) + openingTag.length(), this.raw.indexOf(closingTag));
  }
  public String simpleTag(String tagName){
    //A "simpleTag" is a tag with no variables
    //ie: <tagName>[CONTENT]</tagName>
    return this.baseTag(tagName).substring(1);
  }
  public static String parseVariable(String data, String variableName){
    int begin = data.indexOf(variableName) + variableName.length() + 2;
    //one character for the equal sign, one character for the quote;
    int end = data.indexOf("\"", begin);
    return data.substring(begin, end);
  }
} 