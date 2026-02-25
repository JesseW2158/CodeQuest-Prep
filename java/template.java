import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

public class template {

  public static void main(String[] args) throws FileNotFoundException {
    // goes line by line and returns each input line as a string in the arraylist
    // remove the parameter completeley (not just empty string) to switch from file
    // input to submission input
    ArrayList<String> stuff = cq.in("1.in");
    // there are multiple ways to output. the way i did it is to add each line of
    // the output to the output arraylist, then print them at the end, this, in my
    // opinion, makes it easier to debug because your print statements at the end
    // won't interfere with debug print statements.
    ArrayList<String> output = new ArrayList<String>();
    // in every problem, the number of test cases is the first line of the input
    int testCases = Integer.parseInt(stuff.remove(0));
    // loop through each test case. the variable is named vv becsuse it is rarely
    // used if ever in my experience and it makes it so if you have muscle memory
    // like me typing a for loop with i as your variable doesnt get in the way
    for (int vv = 0; vv < testCases; vv++) {
      // this code is the simple hello world problem to test that everything is
      // working
      String line = stuff.remove(0);

      output.add(line);
    }
    // print output list
    for (int i = 0; i < output.size(); i++) {
      System.out.println(output.get(i));
    }

  }

  public static class cq {
    // this function is what you use when you submit, because submitted test cases
    // use System.in
    public static ArrayList<String> in() {
      ArrayList<String> out = new ArrayList<String>();
      Scanner in = new Scanner(System.in);
      while (in.hasNextLine()) {
        out.add(in.nextLine());
      }
      in.close();
      return out;
    }

    // this is the function you use when you are doing test cases with the file you
    // downloaded. pass in the filepath. if it is alredy in your project file then
    // all you need is the file name
    public static ArrayList<String> in(String filePath) throws FileNotFoundException {
      ArrayList<String> out = new ArrayList<String>();
      File file = new File(filePath);
      Scanner in = new Scanner(file);
      while (in.hasNextLine()) {
        out.add(in.nextLine());
      }
      in.close();
      return out;
    }

    // rounding is the most useful function becsuse a lot of problems require it.
    // codequest used "HALFUP" for rounding unless otherwise specified
    public static String round(double val, int decimals) {
      // Use BigDecimal for precise rounding
      BigDecimal bd = new BigDecimal(val);
      bd = bd.setScale(decimals, RoundingMode.HALF_UP);
      return bd.toString();
    }
  }

}