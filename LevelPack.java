import java.util.Scanner;
import java.io.File;
public class LevelPack{
  private String title;
  private String description;
  public LevelPack(String path){
    try{
      Scanner f = new Scanner(new File(path));
      String data = "";
      while(f.hasNext()){
        data += f.nextLine();
        data += '\n';
      }
      this.title = Util.simpleTag(data, "Title");
      this.description = Util.simpleTag(data, "Description").strip();
    }catch(Exception e){
      System.out.println("Error!");
    }  
  }
  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return "Level Pack: " + this.title + "\nDescription: " + this.description;
  }
} 