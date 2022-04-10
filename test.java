import java.util.Arrays;

public class test
{
    public static void main(String[] args)
    {
        String test = "switch Ali Reza";
        if(test.matches("switch \\w+ \\w+"))
            System.out.println("true");
        System.out.println(Arrays.asList(test.split("")));
    }
}
