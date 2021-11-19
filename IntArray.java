public class IntArray{
  private String data;
  public static final String DELIMETER = ","; 
  public IntArray(){
    this.data = "";
  }
  public void push(int input){
    this.data += DELIMETER + input;
  }
  public int at(int index){
    String out = "";
    int word = -1;
    for(int i = 0; i < this.data.length(); i++){
      String curr = this.data.substring(i, i+1);
      if(curr.equals(DELIMETER)){
        if(index == word++){
          return Integer.parseInt(out);
        }
        out = "";
      }else{
        out += curr;
      }
    }
    return 42; 
  }
}