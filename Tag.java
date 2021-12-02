public class Tag{
  //wrapper around an Xml Tag
  private String raw;
  private String name;
  private int location; //internal state tracking how much of the raw has been read already;
  public Tag(String raw, String name){
    this.raw = raw;
    this.name = name;
    this.location = 0;
  }
  public Tag nextChildTag(String tagName){
    String openingTag = "<" + tagName;
    String closingTag = "</" + tagName + ">";
    int begin = this.raw.indexOf(openingTag, location);
    this.location = begin; 
    int end = this.raw.indexOf(closingTag, location) + closingTag.length();
    this.location = end;
    return new Tag(this.raw.substring(begin, end), tagName);
  }
  @Override
  public String toString(){
    return this.raw;
  }
  public String parseVariable(String variableName){
    //given the name of a variable, return the string represenation of the data assigned to it in the XML tag;
    int begin = this.raw.indexOf(variableName) + variableName.length() + 2;
    //one character for the equal sign, one character for the quote;
    int end = this.raw.indexOf("\"", begin);
    return this.raw.substring(begin, end);
  }
}