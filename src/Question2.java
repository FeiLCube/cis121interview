import java.util.Set;
import java.util.HashSet;

/**
 * <h1>QUESTION 2: ROOMMATE MATCHING</h1>
 * <p/>
 *
 * Class to implement the algorithm to select roommates from a 2d-array of pairs.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question2 {

    /**
     * Returns the set of roommates who mutually requested each other.
     *
     * @param requests the 2d-array of pairs of requests of the form (student, requested roommate).
     *                 Note: a student cannot request themself. 
     * @return a set of pairs of roommates who mutually requested each other
     * 
     * @implSpec you may assume that input is valid and non-null. Do not throw any exceptions.
     */
    public static Set<Pair<String, String>> findMatches(String[][] requests) {
        Set<Pair<String, String>> matchedPairs = new HashSet<>();
        
        if (requests.length == 1) {
            return matchedPairs;
        }
        
        mergeSort(requests, 0, requests.length - 1);
        
        for (int i = 0; i < requests.length; i++) {
            String[] searched = binarySearch(requests, requests[i][1]);
            if (searched == null) {
                continue;
            } else if (searched[1].equals(requests[i][0])) {
                // To prevent adding the same pair in the reverse order:
                String[] pair = min(requests[i][0], searched[0]);
                Pair<String, String> matchedPair = new Pair<>(pair[0], pair[1]);
                matchedPairs.add(matchedPair);
            }
        }
        
        return matchedPairs;
    }
    
    // Implement binary search
    private static String[] binarySearch(String[][] arr, String search) {
        int mid = (arr.length - 1) / 2;
        if (arr[mid][0].compareTo(search) == 0) {
            return arr[mid];
        } else if (arr[mid][0].compareTo(search) < 0) {
            return search(arr, search, mid + 1, arr.length - 1);
        } else {
            return search(arr, search, 0, mid);
        }
    }
    
    private static String[] search(String[][] arr, String search, int lo, int hi) {
        // Base case
        if (lo == hi) {
            if (arr[lo][0].equals(search)) {
                return arr[lo];
            } else {
                return null;
            }
        }
        
        int mid = (lo + hi) / 2;
        if (arr[mid][0].compareTo(search) == 0) {
            return arr[mid];
        } else if (arr[mid][0].compareTo(search) < 0) {
            return search(arr, search, mid + 1, hi);
        } else {
            return search(arr, search, 0, mid);
        }
    }
    
    private static String[] min(String a, String b) {
        String[] ordered = new String[2];
        
        if (a.compareTo(b) < 0) {
            ordered[0] = a;
            ordered[1] = b;
        } else {
            ordered[1] = a;
            ordered[0] = b;
        }
        
        return ordered;
    }
    
    // Implement merge sort.
    private static void mergeSort(String[][] arr, int lo, int hi) {
        int mid = (hi + lo) / 2;
        
        if (lo != hi) {
            mergeSort(arr, lo, mid);
            mergeSort(arr, mid + 1, hi);
            
            merge(arr, lo, mid, hi);
        }
    }
    
    private static void merge(String[][] arr, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        String[][] aux = new String[arr.length][2];
        
        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }
        
        int k = lo;
        
        while (i < mid + 1 && j < hi + 1) {
            if (aux[i][0].compareTo(aux[j][0]) <= 0) {
                arr[k++] = aux[i++];
            } else {
                arr[k++] = aux[j++];
            }
        }
        
        while (i < mid + 1) {
            arr[k++] = aux[i++];
        }
        
        while (j < hi + 1) {
            arr[k++] = aux[j++];
        }
    }
}
