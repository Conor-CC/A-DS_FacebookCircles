import java.util.Iterator;

/**
 * Class FacebookCircles: calculates the statistics about the friendship circles in facebook data.
 *
 * @author
 * Conor Clery
 *
 * @version 14/12/17 14:00
 */
public class FacebookCircles {
	
	private int numberOfFacebookUsers;
	private DoublyLinkedList<Integer>[] userCircles;
	private boolean[] markingArray;
	private int smallestCircle;
	private int largestCircle;
	private int numberOfCircles;


  /**
   * Constructor
   * @param numberOfFacebookUsers : the number of users in the sample data.
   * Each user will be represented with an integer id from 0 to numberOfFacebookUsers-1.
   */
  @SuppressWarnings("unchecked")
  public FacebookCircles(int numberOfFacebookUsers) {
    // TODO
	  this.numberOfFacebookUsers = numberOfFacebookUsers;
	  this.userCircles = new DoublyLinkedList[numberOfFacebookUsers];
	  this.markingArray = new boolean[numberOfFacebookUsers];
	  for (int v = 0; v < numberOfFacebookUsers; v++) {
	      userCircles[v] = new DoublyLinkedList<Integer>();
	      markingArray[v] = false;
      }
      this.smallestCircle = Integer.MAX_VALUE;
	  this.largestCircle = 0;
  }

/**
   * creates a friendship connection between two users, represented by their corresponding integer ids.
   * @param user1 : int id of first user
   * @param user2 : int id of second  user
   */
  public void friends( int user1, int user2 ) {
     userCircles[user1].push(user2);
     userCircles[user2].push(user1);
  }

  /**
   * @return the number of friend circles in the data already loaded.
   */
  public int numberOfCircles() {
      int count = 0;
      for (int i = 0; i < userCircles.length; i++) {
          if (!markingArray[i]) {
              resolveCircle(i);
              count++;
          }
      }
      this.numberOfCircles = count;
      return count;
  }

  private void resolveCircle(int user) {
      int visitCount = 0;
      DoublyLinkedList<Integer> q = new DoublyLinkedList<Integer>();
      q.enqueue(user);
      visitCount++;
      markingArray[user] = true;
      while (!q.isEmpty()) {
          int v = q.dequeue();
          Iterator i = userCircles[v].iterator();
          while (i.hasNext()) {
              int next = (int) i.next();
              if (!markingArray[next]) {
                  q.enqueue(next);
                  visitCount++;
                  markingArray[next] = true;
              }
          }
          i.remove();
      }
      if (visitCount > largestCircle) {
          largestCircle = visitCount;
      }
      if (visitCount < smallestCircle) {
          smallestCircle = visitCount;
      }
  }


  /**
   * @return the size of the largest circle in the data already loaded.
   */
  public int sizeOfLargestCircle() {
    // TODO
	  return largestCircle;
  }

  /**
   * @return the size of the MEAN circle in the data already loaded.
   */
  public int sizeOfAverageCircle() {
    // TODO
      return numberOfFacebookUsers/numberOfCircles;
  }

  /**
   * @return the size of the smallest circle in the data already loaded.
   */
  public int sizeOfSmallestCircle() {
    // TODO
	  return smallestCircle;
  }


}