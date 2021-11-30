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
    this.title = LevelPack.simpleTag(this.raw, "Title");
    this.description = LevelPack.simpleTag(this.raw, "Description").strip();
    this.copyright = LevelPack.parseVariable(LevelPack.baseTag(this.raw, "LevelCollection"), "Copyright");
    this.location = this.raw.indexOf("<Level");
  }
  @Override
  public String toString() {

    return "Level Pack: " + this.title + 
           "\nDescription: " + this.description +
           "\nCopyright: " + this.copyright;
  }
  public String getLevel(){
    return LevelPack.baseTag(this.raw, "Level");
  }
  public static String baseTag(String data, String tagName){
    //returns the first subsection of the data which is wrapped in the tags specified by tagName;
    String openingTag = "<" + tagName + "";
    String closingTag = "</" + tagName + ">";
    return data.substring(data.indexOf(openingTag) + openingTag.length(), data.indexOf(closingTag));
  }
  public static String simpleTag(String data, String tagName){
    //A "simpleTag" is a tag with no variables
    //ie: <tagName>[CONTENT]</tagName>
    return baseTag(data, tagName).substring(1);
  }
  public static String parseVariable(String data, String variableName){
    int begin = data.indexOf(variableName) + variableName.length() + 2;
    //one character for the equal sign, one character for the quote;
    int end = data.indexOf("\"", begin);
    return data.substring(begin, end);
  }
} 