package arvore_twofour;

import java.util.Random;

public class TwoFourTreeTest {
    int n = 30;
 
    public static void main(String args[]) 
    {
        Random random = new Random();
        TwoFourTree tft = new TwoFourTree();
 
        System.out.println("Sorting of randomly generated numbers using a 2-4 Tree");
 
        for (int i = 0; i < n; i++)
//            tft.insert(Math.abs(random.nextInt(666)));
 
        System.out.println("\nThe sorted sequence is: ");
        tft.inorder();
    }
}
