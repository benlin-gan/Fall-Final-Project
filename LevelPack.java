import java.util.Scanner;
import java.io.File;
public class LevelPack{
  private String title;
  public LevelPack(String path){
    try{
      Scanner f = new Scanner(new File(path));
      String data = "";
      while(f.hasNext()){
        data += f.nextLine();
        data += '\n';
      }
      this.title = data.substring(data.indexOf("<Title>") + "<Title>".length(), data.indexOf("</Title>"));
    }catch(Exception e){
      System.out.println("Error!");
    }  
  }
  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return this.title;
  }
} 