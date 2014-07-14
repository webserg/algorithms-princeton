import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by webserg on 14.07.2014.
 */
public class ArrayCopyTest {
    public static void main(String[] args) throws IOException, InterruptedException,
            PrinterException
    {
        //Verify array remains immutable.

        String[] str =  {"a","b","c"};
        String[] strings  = str.clone();
        //change returned array
        strings[2]= "d";
        System.out.println(Arrays.toString(str));
        System.out.println(Arrays.toString(strings));

        String[] stringsCopy = Arrays.copyOf(str, str.length);
        stringsCopy[2]= "d";
        System.out.println(Arrays.toString(str));
        System.out.println(Arrays.toString(stringsCopy));




    }
}
