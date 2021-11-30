import java.util.Scanner;
import java.io.File;
public class LevelPack{
  private String raw;
  private String title;
  private String description;
  private String copyright;
  private int location; //argument passed to indexOf that restricts search space. Ideally, we don't ever need to backtrack, leading to every character in the XML file being read only once, leading to amortized O(N) access time;
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
    this.location = 0;
    this.title = simpleTag("Title");
    this.description = simpleTag("Description").strip();
    this.copyright = parseVariable(this.baseTag("LevelCollection"), "Copyright");
  }
  @Override
  public String toString() {
    return "Level Pack: " + this.title + 
           "\nDescription: " + this.description +
           "\nCopyright: " + this.copyright;
  }
  public void getLevel(){
    String data = this.baseTag("Level");
    System.out.println(data);
    String name = parseVariable(data,  "Id");
    int columns = Integer.parseInt(parseVariable(data, "Width"));
    int rows = Integer.parseInt(parseVariable(data,  "Height"));
    System.out.println(name + " " +  columns + " " +  rows);
    String grid = "";
    for(int i = 0; i < rows; i++){
      grid += data;
    }
  }
  public String baseTag(String tagName){
    //returns the first subsection of the data which is wrapped in the tags specified by tagName;
    String openingTag = "<" + tagName + "";
    String closingTag = "</" + tagName + ">";
    int begin = this.raw.indexOf(openingTag, this.location) + openingTag.length();
    this.location = begin; 
    int end = this.raw.indexOf(closingTag, this.location); 
    return this.raw.substring(begin, end);
  }
  public String simpleTag(String tagName){
    //A "simpleTag" is a tag with no variables
    //ie: <tagName>[CONTENT]</tagName>
    return this.baseTag(tagName).substring(1);
  }
  public String parseVariable(String data, String variableName){
    //given the name of a variable, return the string represenation of the data assigned to it in the XML file;
    int begin = data.indexOf(variableName) + variableName.length() + 2;
    //one character for the equal sign, one character for the quote;
    int end = data.indexOf("\"", begin);
    return data.substring(begin, end);
  }
} 