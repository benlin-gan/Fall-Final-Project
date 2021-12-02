public class Tag{
  //wrapper around an Xml Tag
  private String header;
  private String body;
  private String footer;
  private String name;
  private int location; //internal state tracking how much of the body has been read already;
  public Tag(String raw, String name){
    int headerEnd = raw.indexOf(">") + 1; //end of current tag
    int footerStart = getFooterStart(raw);
    this.header = raw.substring(0, headerEnd);
    this.body = raw.substring(headerEnd, footerStart).strip();
    this.footer = raw.substring(footerStart, raw.length());
    this.name = name;
    this.location = 0;
  }
  public Tag nextChildTag(String tagName){
    String openingTag = "<" + tagName;
    String closingTag = "</" + tagName + ">";
    int begin = this.body.indexOf(openingTag, location);
    this.location = begin; 
    int end = this.body.indexOf(closingTag, location) + closingTag.length();
    this.location = end;
    return new Tag(this.body.substring(begin, end), tagName);
  }
  @Override
  public String toString(){
    return this.name + ": " + this.body + "\n";
  }
  private int getFooterStart(String raw){
    for(int i = raw.length(); i > 0; i--){
      String curr = raw.substring(i-1, i);
      if(curr.equals("<")){
        return i - 1;
      }
    }
    return 0; //should never reach here;
  }
  public String parseVariable(String variableName){
    //given the name of a variable, return the string represenation of the data assigned to it in the XML tag;
    int begin = this.header.indexOf(variableName) + variableName.length() + 2;
    //one character for the equal sign, one character for the quote;
    int end = this.header.indexOf("\"", begin);
    return this.header.substring(begin, end);
  }
}