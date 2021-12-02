class Main{
  public static void main(String[] args){
    LevelPack packOne = new LevelPack("696.slc");
    System.out.println(packOne);
    System.out.println(packOne.nextLevel());
    System.out.println(packOne.nextLevel());
    //Game g = new Game(1);
    //g.loop();
  }
}