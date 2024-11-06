public class MissingNumberFinder {
  public static void main(String[] args) {
      int[] array = {3, 7, 1, 2, 6, 4};
      int n = array.length;
      System.out.println("Missing number: " + findMissingNumber(array, n));
  }

  public static int findMissingNumber(int[] array, int n) {
      // Calculate the expected sum of numbers from 1 to n + 1
      int expectedSum = (n + 1) * (n + 2) / 2;

      // Calculate the actual sum of the elements in the array
      int actualSum = 0;
      for (int num : array) {
          actualSum += num;
      }

      // The missing number is the difference between the expected sum and the actual sum
      return expectedSum - actualSum;
  }
}