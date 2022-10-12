import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

  public static void printLint(List<Pair<String, Character> > list){
    for(Pair<String, Character> p: list){
      System.out.println(p.getKey());
    }
  }
  
  public static void main(String args[]) {

    InputHandler inputHandler = new InputHandler(args);

    Queue<Pair<String, Character> > queue = inputHandler.getEnteredValues();
    List<Pair<String, Character> > maleList = new ArrayList<>();
    List<Pair<String, Character> > femaleList = new ArrayList<>();

    while(!queue.isEmpty()){
      Pair<String, Character> pair = queue.remove();

      if(pair.getValue() == 'F'){
        femaleList.add(pair);
      }else{
        maleList.add(pair);
      }
    }
    
    System.out.println("--- Male List ---");
    printLint(maleList);

    System.out.println("");

    System.out.println("--- Female List ---");
    printLint(femaleList);
  }

  static class InputHandler {

    private String args[];

    private Queue<Pair<String, Character> > queue = new LinkedList<>();

    public InputHandler(String args[]) {

      this.args = args;
      fillEnteredValuesArray();
    }

    private boolean isValidSex(Character c){
      if(c == 'M') return true;
      if(c == 'F') return true;
      return false;
    }

    private void fillEnteredValuesArray() {
      //patter for <String>,<Character>  or <String>-<Character> 
      Pattern pattern = Pattern.compile("^([a-zA-z])+(-|,)([a-zA-z])$");


      for (int i = 0; i < args.length; i++) {
        Matcher m = pattern.matcher(args[i]);
        if (!m.matches()) {
          throw new IllegalArgumentException("The " + (i + 1) + "º input doesn't matches with format <String>,<Character>  or <String>-<Character>.");
        }
        
        String[] parts = args[i].split("-|,");
        String name = parts[0];
        Character sex = parts[1].charAt(0);

        if(!isValidSex(sex)){
          throw new IllegalArgumentException("The sex must be 'M' or 'F'.");
        }

        Pair<String, Character> pair = new Pair<>(name, sex);
        queue.add(pair);
      }
    }

    private Queue<Pair<String, Character> > getEnteredValues(){
      return queue;
    }
  }

}
