public class IntArray{
  //wrapper around a string of a list of integers with O(N) access time;
  private String data;
  public IntArray(){
    this.data = "";
  }
  public void push(int input){
    this.data += getRepresentation(input);
  }
  public int at(int index){
    return Integer.parseInt(data.substring(index * 10, index * 10 + 10));
  }
  private static String getRepresentation(int input){
    String prefix = "+";
    if(input < 0){
      prefix = "-";
    }
    String init = "" + Math.abs(input);
    while(init.length() < 9){
      init = "0" + init;
    }
    return prefix + init;
  }
}