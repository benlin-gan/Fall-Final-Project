public class IntArray{
  //"Array" of integers using a String as an allocator;
  //Integers are stored as a string of ten characters;
  //one sign character, and a maximum of 9 digits
  //maximum 32 bit integer is around 2 billion (9 digits);
  private String data;
  public IntArray(){
    this.data = "";
  }
  public void push(int input){
    //append an integer onto IntArray
    this.data += getRepresentation(input);
  }
  public int at(int index){
    //return integer stored at indexed
    return Integer.parseInt(data.substring(index * 10, index * 10 + 10));
  }
  private static String getRepresentation(int input){
    //transform the integer into its 10 character representation
    String prefix = "+";
    if(input < 0){
      prefix = "-";
    }
    String init = "" + Math.abs(input);
    while(init.length() < 9){
      //if it has less than 9 digits, pad it with zeroes;
      init = "0" + init;
    }
    return prefix + init;
  }
}
