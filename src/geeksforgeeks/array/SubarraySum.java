package geeksforgeeks.array;

/**
 * Created by Muneer on 20-12-2016.
 */
class SubarraySum
{
    /* Returns true if the there is a subarray of arr[] with sum equal to
       'sum' otherwise returns false.  Also, prints the result */
    int subArraySum(int arr[], int n, int sum)
    {
        if(arr == null || arr.length == 0) {
            return -1;
        }

        int l = 0, r = 0;

        int currSum = arr[0];
        while(r < arr.length) {

            if(currSum == sum) {
                System.out.println(l + " to " + r);
                currSum -= arr[l++];
            } else if(currSum < sum) {
                r++;
                if(r < arr.length) {
                    currSum += arr[r];
                }
            } else {
                currSum -= arr[l++];
            }

            if(l > r) {
                r = l;
            }

        }

        return 0;
    }

    public static void main(String[] args)
    {
        SubarraySum arraysum = new SubarraySum();
        int arr[] = {1, 1, 1, 1, 1, 1, 11, 1};
        int n = arr.length;
        int sum = 1;
        arraysum.subArraySum(arr, n, sum);
    }
}