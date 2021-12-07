public class Tag{
  //wrapper around an XML Tag
  private String header;
  private String body;
  private String footer;
  private String name;
  private int location; //internal state tracking how much of the body has been read already;
  public Tag(String raw, String name){
    int headerEnd = raw.indexOf(">") + 1; //index of end of current tag
    int footerStart = getFooterStart(raw); //index of beginning of the matching end tag
    this.header = raw.substring(0, headerEnd); //the contents of the header tag
    this.body = raw.substring(headerEnd, footerStart);
    //everything in between, including data or other tags
    this.footer = raw.substring(footerStart, raw.length()); //the contents of the footer tag
    this.name = name; //the name of the tag;
    this.location = 0;
  }
  public Tag nextChildTag(String tagName){
    //returns the next tag in the body, if none, return null;
    String openingTag = "<" + tagName;
    String closingTag = "</" + tagName + ">";
    int begin = this.body.indexOf(openingTag, location);
    this.location = begin;
    if(begin == -1){
      return null;
    }
    int end = this.body.indexOf(closingTag, location) + closingTag.length();
    this.location = end;
    return new Tag(this.body.substring(begin, end), tagName);
  }
  @Override
  public String toString(){
    //mainly used for debugging
    //also used in the level metadata
    return this.name + ": " + this.body + "\n";
  }
  public String getBody(){
    //return the raw content of the body
    //useful if there are no child tags
    return this.body;
  }
  private int getFooterStart(String raw){
    for(int i = raw.length(); i > 0; i--){
      String curr = raw.substring(i-1, i);
      if(curr.equals("<")){
        return i - 1;
      }
    }
    return -1; //should never reach here;
  }
  public String parseVariable(String variableName){
    //given the name of a variable, return the string represenation of the data assigned to it in the XML tag;
    int begin = this.header.indexOf(variableName) + variableName.length() + 2;
    //one character for the equal sign, one character for the quote;
    int end = this.header.indexOf("\"", begin);
    return this.header.substring(begin, end);
  }
}
