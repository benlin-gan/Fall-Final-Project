public class Util{
  //namespace for miscellaneous functions
  public static String swapSubstringsAtIndexes(String original, int b1, int e1, int b2, int e2){
    //precondition [b1, e1) && [b2, e2) should not overlap
    if(b2 < b1){
      int tmp = b2;
      b2 = b1;
      b1 = tmp;
      tmp = e2;
      e2 = e1;
      e1 = tmp;
      //swaps [b1, e1) and [b2, e2)
      //to ensure that [b1, e1) < [b2, e2)
    }
    String prefix = original.substring(0, b1);
    String sub1 = original.substring(b1, e1);
    String infix = original.substring(e1, b2);
    String sub2 = original.substring(b2, e2);
    String postfix = original.substring(e2, original.length());
    return prefix + sub2 + infix + sub1 + postfix;
    //sub2 swapped with sub1;
  }
  public static void clearScreen(){
    //uses ANSI escape codes to clear the terminal
    //taken from Mastermind
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

}
