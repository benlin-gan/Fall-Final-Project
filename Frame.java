public class Frame{
  int width;
  int height;
  Player player;
  Blocks blocks;
  public Frame(Player player, Blocks blocks){
    this.width = blocks.getWidth();
    this.height = blocks.getHeight();
    this.player = player;
    this.blocks = blocks;
  }
  public Player getPlayer(){
    return this.player;
  }
  @Override
  public String toString() {
    System.out.print(this.blocks);
    return "";
  }
}