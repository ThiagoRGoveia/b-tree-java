// package arvore_twofour;

// Tester class
public class TwoFourTreeTest {

    public static void main(String args[])
    {
        TwoFourTree tft = new TwoFourTree();

        // System.out.println("Sorting of randomly generated numbers using a 2-4 Tree");
        int nums[] = {1,2,3,0,8,11,36,94,50};
        for (int i = 0; i < 9; i++) {
            tft.insert(
                new TreeNodeData(nums[i])
            );
            // System.out.println("Key:" + nums[i]);
        }
        // TreeNodeData item = tft.search(11);
        // if(item != null) System.out.println("Key:" + item.getKey()); else System.out.println("Key not found");
        // item = tft.search(36);
        // if(item != null) System.out.println("Key:" + item.getKey()); else System.out.println("Key not found");
        // item = tft.search(9);
        // if(item != null) System.out.println("Key:" + item.getKey()); else System.out.println("Key not found");
        // item = tft.search(0);
        // if(item != null) System.out.println("Key:" + item.getKey()); else System.out.println("Key not found");
        System.out.println("Traverse");
        tft.traverseInOrder();
        // System.out.println("\nThe sorted sequence is: ");
        // tft.inorder();
    }
}
