import static org.junit.Assert.*;

import org.junit.Test;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 3.1 09/11/15 11:32:15
 *
 *  @author  TODO
 */

@RunWith(JUnit4.class)
public class BSTTest
{
  /** <p>Test {@link BST#prettyPrintKeys()}.</p> */
      
 @Test
 public void testPrettyPrint() {
     BST<Integer, Integer> bst = new BST<Integer, Integer>();
     assertEquals("Checking pretty printing of empty tree",
             "-null\n", bst.prettyPrintKeys());
      
                          //  -7
                          //   |-3
                          //   | |-1
                          //   | | |-null
     bst.put(7, 7);       //   | |  -2
     bst.put(8, 8);       //   | |   |-null
     bst.put(3, 3);       //   | |    -null
     bst.put(1, 1);       //   |  -6
     bst.put(2, 2);       //   |   |-4
     bst.put(6, 6);       //   |   | |-null
     bst.put(4, 4);       //   |   |  -5
     bst.put(5, 5);       //   |   |   |-null
                          //   |   |    -null
                          //   |    -null
                          //    -8
                          //     |-null
                          //      -null
     
     String result = 
      "-7\n" +
      " |-3\n" + 
      " | |-1\n" +
      " | | |-null\n" + 
      " | |  -2\n" +
      " | |   |-null\n" +
      " | |    -null\n" +
      " |  -6\n" +
      " |   |-4\n" +
      " |   | |-null\n" +
      " |   |  -5\n" +
      " |   |   |-null\n" +
      " |   |    -null\n" +
      " |    -null\n" +
      "  -8\n" +
      "   |-null\n" +
      "    -null\n";
     System.out.println(bst.prettyPrintKeys());
     assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
     }

  
     /** <p>Test {@link BST#delete(Comparable)}.</p> */
     @Test
     public void testDelete() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         bst.delete(1);
         assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());
         
         bst.put(7, 7);   //        _7_
         bst.put(8, 8);   //      /     \
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     \
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
                          //         5
         
         assertEquals("Checking order of constructed tree",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
         
         bst.delete(9);
         assertEquals("Deleting non-existent key",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
 
         bst.delete(8);
         assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());
 
         bst.delete(6);
         assertEquals("Deleting node with single child",
                 "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());
 
         bst.delete(3);
         assertEquals("Deleting node with two children",
                 "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
     }
     
     @Test
     public void testIsEmpty() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         assertTrue("Check if empty tree returns true", bst.isEmpty());
         bst.put(7, 7);      
         bst.put(8, 8);     
         bst.put(3, 3);       
         bst.put(1, 1);      
         bst.put(2, 2);       
         bst.put(6, 6);      
         bst.put(4, 4);       
         bst.put(5, 5); 
         assertFalse("Check if full tree returns false", bst.isEmpty());

     }
     
     @Test
     public void testSize() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         assertEquals("Test when tree is empty", (int) 0, bst.size());
         bst.put(7, 7);      
         assertEquals("Test when tree size is 1", (int) 1, bst.size());
         bst.put(8, 8);     
         bst.put(3, 3);       
         bst.put(1, 1);      
         bst.put(2, 2);       
         bst.put(6, 6);      
         bst.put(4, 4);       
         bst.put(5, 5); 
         assertEquals("Test when tree size is 8", (int) 8, bst.size());
     }
     
     @Test
     public void testContains() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         assertFalse("Test key on an empty tree", bst.contains(1));
         bst.put(8, 8);     
         bst.put(3, 3);       
         bst.put(1, 1);      
         bst.put(2, 2);       
         bst.put(6, 6);
         assertTrue("Test for key 2 in tree", bst.contains(2));
         assertTrue("Test for key 8 in tree", bst.contains(8));
         assertFalse("Test non existent key on an full tree", bst.contains(40));
     }
     
     @Test
     public void testGet() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         assertNull("Test get returns null for empty list", bst.get(1));
         bst.put(8, 8);     
         bst.put(3, 3);       
         bst.put(1, 1);      
         bst.put(2, 2);       
         bst.put(6, 6);
         assertEquals("Test get", (Integer) 8, bst.get(8));
         assertEquals("Test get", (Integer) 2, bst.get(2));
         assertNull("Test get non existent element", bst.get(7));
     }
     
     @Test
     public void testPut() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 bst.put(8, 8);     
         bst.put(3, 3);       
         bst.put(1, 1);      
         bst.put(2, 2);       
         bst.put(6, 6);
         assertEquals("Test get", (Integer) 8, bst.get(8));
         assertEquals("Test get", (Integer) 3, bst.get(3));
     }
     
     @Test
     public void testHeight() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         assertEquals("Test height on empty tree", (int) -1, bst.height());
         bst.put(8, 8);     
         assertEquals("Test height on tree with one element", (int) 0, bst.height());
         bst.put(3, 3);       
         bst.put(1, 1);      
         bst.put(2, 2);       
         bst.put(6, 6);
         assertEquals("Test height on tree where h = 3", (int) 3, bst.height());
     }
     
     @Test
     public void testMedian() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         assertNull("Test median on empty set", bst.median());
         bst.put(8, 8);     
         assertEquals("Test median on single element tree", (Integer) 8, bst.median());
         bst.put(3, 3);       
         bst.put(1, 1);      
         bst.put(2, 2);       
         bst.put(6, 6);
         assertEquals("Test median = 3", (Integer) 3, bst.median());

     }
     
     @Test
     public void testPrintInOrder() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         bst.put(8, 8);     
         System.out.println(bst.printKeysInOrder());
     }
     
    
}
