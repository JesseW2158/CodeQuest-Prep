import java.util.*;
import java.util.stream.*;

/**
 * ╔══════════════════════════════════════════════════════╗
 *   LMT CodeQuest — Java Reference Functions
 *   Copy what you need. All methods are static.
 * ╚══════════════════════════════════════════════════════╝
 */
public class LMTCodeQuestRef {

    // ─────────────────────────────────────────────────────
    // CONSTANTS
    // ─────────────────────────────────────────────────────

    static final long MOD = 1_000_000_007L;


    // ─────────────────────────────────────────────────────
    // NUMBER THEORY
    // ─────────────────────────────────────────────────────

    /** Greatest common divisor (Euclidean). */
    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /** Least common multiple. */
    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    /** Returns true if n is prime. O(sqrt(n)). */
    static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n < 4) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; (long) i * i <= n; i += 6)
            if (n % i == 0 || n % (i + 2) == 0) return false;
        return true;
    }

    /** All primes up to limit via Sieve of Eratosthenes. O(n log log n). */
    static List<Integer> sieve(int limit) {
        boolean[] isComposite = new boolean[limit + 1];
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (!isComposite[i]) {
                primes.add(i);
                for (long j = (long) i * i; j <= limit; j += i)
                    isComposite[(int) j] = true;
            }
        }
        return primes;
    }

    /** n! — returns long (overflows beyond ~20; use modFactorial for large n). */
    static long factorial(int n) {
        long res = 1;
        for (int i = 2; i <= n; i++) res *= i;
        return res;
    }

    /** n! mod p. */
    static long modFactorial(int n, long mod) {
        long res = 1;
        for (int i = 2; i <= n; i++) res = res * i % mod;
        return res;
    }

    /** nCr using Pascal's triangle DP. Safe for moderate n. */
    static long combination(int n, int r) {
        if (r > n || r < 0) return 0;
        if (r == 0 || r == n) return 1;
        long[][] dp = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= i; j++)
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
        }
        return dp[n][r];
    }

    /** nCr mod p (p prime) using Fermat's little theorem. */
    static long modCombination(int n, int r, long mod) {
        if (r > n || r < 0) return 0;
        long num = 1, den = 1;
        for (int i = 0; i < r; i++) {
            num = num * ((n - i) % mod) % mod;
            den = den * ((i + 1) % mod) % mod;
        }
        return num % mod * modInverse(den, mod) % mod;
    }

    /** nPr = n! / (n-r)! */
    static long permutation(int n, int r) {
        if (r > n) return 0;
        long res = 1;
        for (int i = 0; i < r; i++) res *= (n - i);
        return res;
    }

    /** Fast exponentiation. O(log exp). */
    static long power(long base, long exp) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) res *= base;
            base *= base;
            exp >>= 1;
        }
        return res;
    }

    /** Modular exponentiation. O(log exp). */
    static long modPow(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = res * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }
        return res;
    }

    /** Modular inverse via Fermat's little theorem (mod must be prime). */
    static long modInverse(long a, long mod) {
        return modPow(a, mod - 2, mod);
    }

    /** Sum of 0..n = n(n+1)/2. */
    static long sumToN(long n) {
        return n * (n + 1) / 2;
    }

    /** All divisors of n, sorted. O(sqrt(n)). */
    static List<Integer> getDivisors(int n) {
        List<Integer> lo = new ArrayList<>(), hi = new ArrayList<>();
        for (int i = 1; (long) i * i <= n; i++) {
            if (n % i == 0) {
                lo.add(i);
                if (i != n / i) hi.add(n / i);
            }
        }
        Collections.sort(hi);
        lo.addAll(hi);
        return lo;
    }

    /** Returns true if n is a positive power of 2. */
    static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    /** Count number of 1-bits (set bits) in n. */
    static int countSetBits(int n) {
        return Integer.bitCount(n);
    }


    // ─────────────────────────────────────────────────────
    // SEQUENCES
    // ─────────────────────────────────────────────────────

    /** First n Fibonacci numbers (F(0)=0, F(1)=1). */
    static long[] fibonacci(int n) {
        if (n <= 0) return new long[0];
        long[] f = new long[n];
        if (n >= 1) f[0] = 0;
        if (n >= 2) f[1] = 1;
        for (int i = 2; i < n; i++) f[i] = f[i-1] + f[i-2];
        return f;
    }

    /** Single nth Fibonacci number (0-indexed). */
    static long nthFibonacci(int n) {
        if (n == 0) return 0;
        long a = 0, b = 1;
        for (int i = 1; i < n; i++) { long tmp = a + b; a = b; b = tmp; }
        return b;
    }

    /** nth row of Pascal's triangle (0-indexed). */
    static long[] pascalRow(int n) {
        long[] row = new long[n + 1];
        row[0] = 1;
        for (int i = 1; i <= n; i++) row[i] = row[i-1] * (n - i + 1) / i;
        return row;
    }

    /** Full Pascal's triangle up to `rows` rows. */
    static long[][] pascalTriangle(int rows) {
        long[][] tri = new long[rows][];
        for (int i = 0; i < rows; i++) tri[i] = pascalRow(i);
        return tri;
    }


    // ─────────────────────────────────────────────────────
    // STRING UTILITIES
    // ─────────────────────────────────────────────────────

    /** Reverse a string. */
    static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    /** Case-insensitive palindrome check (ignores spaces). */
    static boolean isPalindrome(String s) {
        String c = s.replace(" ", "").toLowerCase();
        return c.equals(new StringBuilder(c).reverse().toString());
    }

    /** Frequency map of each character. */
    static Map<Character, Integer> charFrequency(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char ch : s.toCharArray()) map.merge(ch, 1, Integer::sum);
        return map;
    }

    /** Length of Longest Common Subsequence. O(mn). */
    static int lcs(String a, String b) {
        int m = a.length(), n = b.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                dp[i][j] = a.charAt(i-1) == b.charAt(j-1)
                    ? dp[i-1][j-1] + 1
                    : Math.max(dp[i-1][j], dp[i][j-1]);
        return dp[m][n];
    }

    /** Check if two strings are anagrams. */
    static boolean isAnagram(String a, String b) {
        char[] ca = a.toCharArray(), cb = b.toCharArray();
        Arrays.sort(ca); Arrays.sort(cb);
        return Arrays.equals(ca, cb);
    }


    // ─────────────────────────────────────────────────────
    // BASE CONVERSION
    // ─────────────────────────────────────────────────────

    /** Decimal integer to binary string. */
    static String decimalToBinary(int n) {
        return Integer.toBinaryString(n);
    }

    /** Binary string to decimal integer. */
    static int binaryToDecimal(String b) {
        return Integer.parseInt(b, 2);
    }

    /** Decimal to any base (2–16) string. */
    static String decimalToBase(int n, int base) {
        return Integer.toString(n, base).toUpperCase();
    }

    /** Any base string to decimal. */
    static int baseToDecimal(String s, int base) {
        return Integer.parseInt(s, base);
    }


    // ─────────────────────────────────────────────────────
    // ARRAY UTILITIES
    // ─────────────────────────────────────────────────────

    /** Binary search on sorted array. Returns index or -1. */
    static int binarySearch(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    /** Frequency map of integer array elements. */
    static Map<Integer, Integer> elementFrequency(int[] arr) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int x : arr) map.merge(x, 1, Integer::sum);
        return map;
    }

    /** Max element in array. */
    static int maxElement(int[] arr) {
        int max = arr[0];
        for (int x : arr) max = Math.max(max, x);
        return max;
    }

    /** Min element in array. */
    static int minElement(int[] arr) {
        int min = arr[0];
        for (int x : arr) min = Math.min(min, x);
        return min;
    }

    /** Kadane's algorithm — maximum subarray sum. O(n). */
    static int maxSubarraySum(int[] arr) {
        int best = arr[0], curr = arr[0];
        for (int i = 1; i < arr.length; i++) {
            curr = Math.max(arr[i], curr + arr[i]);
            best = Math.max(best, curr);
        }
        return best;
    }

    /** Shuffle array in-place (Fisher-Yates). */
    static void shuffleArray(int[] arr) {
        Random rng = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1);
            int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
        }
    }

    /** All 2^n subsets of arr. */
    static List<List<Integer>> generateSubsets(int[] arr) {
        int n = arr.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int mask = 0; mask < (1 << n); mask++) {
            List<Integer> sub = new ArrayList<>();
            for (int i = 0; i < n; i++)
                if ((mask & (1 << i)) != 0) sub.add(arr[i]);
            result.add(sub);
        }
        return result;
    }

    /** Rotate array right by k positions. Returns new array. */
    static int[] rotateArray(int[] arr, int k) {
        int n = arr.length;
        k = ((k % n) + n) % n;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) res[(i + k) % n] = arr[i];
        return res;
    }

    /** Prefix sum array. ps[i] = arr[0]+...+arr[i]. */
    static int[] prefixSums(int[] arr) {
        int[] ps = new int[arr.length];
        ps[0] = arr[0];
        for (int i = 1; i < arr.length; i++) ps[i] = ps[i-1] + arr[i];
        return ps;
    }

    /** Range sum arr[l..r] using prefix sums. O(1). */
    static int rangeSum(int[] prefix, int l, int r) {
        return prefix[r] - (l > 0 ? prefix[l-1] : 0);
    }

    /** Two-sum: returns {i, j} where arr[i]+arr[j]==target, or null. */
    static int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (seen.containsKey(target - arr[i]))
                return new int[]{seen.get(target - arr[i]), i};
            seen.put(arr[i], i);
        }
        return null;
    }

    /** Merge sort. Returns new sorted array. */
    static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) return arr;
        int mid = arr.length / 2;
        int[] left  = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
        int[] res = new int[arr.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length)
            res[k++] = left[i] <= right[j] ? left[i++] : right[j++];
        while (i < left.length)  res[k++] = left[i++];
        while (j < right.length) res[k++] = right[j++];
        return res;
    }


    // ─────────────────────────────────────────────────────
    // GRAPH HELPERS
    // ─────────────────────────────────────────────────────

    /** BFS — returns distance map from start node. */
    static Map<Integer, Integer> bfs(Map<Integer, List<Integer>> graph, int start) {
        Map<Integer, Integer> dist = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        dist.put(start, 0);
        q.add(start);
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int nb : graph.getOrDefault(node, Collections.emptyList())) {
                if (!dist.containsKey(nb)) {
                    dist.put(nb, dist.get(node) + 1);
                    q.add(nb);
                }
            }
        }
        return dist;
    }

    /** DFS — returns visited order list. */
    static List<Integer> dfs(Map<Integer, List<Integer>> graph, int start) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> order = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);
                order.add(node);
                for (int nb : graph.getOrDefault(node, Collections.emptyList()))
                    if (!visited.contains(nb)) stack.push(nb);
            }
        }
        return order;
    }

    /**
     * Dijkstra's shortest path from src.
     * graph: adjacency list of {neighbor, weight} pairs.
     * Returns dist[] array.
     */
    static int[] dijkstra(List<int[]>[] graph, int src, int V) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, src});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0], u = cur[1];
            if (d > dist[u]) continue;
            for (int[] edge : graph[u]) {
                int v = edge[0], w = edge[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }
        return dist;
    }


    // ─────────────────────────────────────────────────────
    // DYNAMIC PROGRAMMING TEMPLATES
    // ─────────────────────────────────────────────────────

    /** 0/1 Knapsack — max value with capacity W. */
    static int knapsack(int W, int[] weights, int[] values) {
        int n = weights.length;
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 1; i <= n; i++)
            for (int w = 0; w <= W; w++) {
                dp[i][w] = dp[i-1][w];
                if (weights[i-1] <= w)
                    dp[i][w] = Math.max(dp[i][w], dp[i-1][w - weights[i-1]] + values[i-1]);
            }
        return dp[n][W];
    }

    /** Coin change — min coins to make amount, or -1 if impossible. */
    static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++)
            for (int coin : coins)
                if (coin <= i) dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /** Longest Increasing Subsequence length. O(n log n). */
    static int lis(int[] arr) {
        List<Integer> tails = new ArrayList<>();
        for (int x : arr) {
            int pos = Collections.binarySearch(tails, x);
            if (pos < 0) pos = -(pos + 1);
            if (pos == tails.size()) tails.add(x);
            else tails.set(pos, x);
        }
        return tails.size();
    }


    // ─────────────────────────────────────────────────────
    // DEMO / MAIN
    // ─────────────────────────────────────────────────────

    public static void main(String[] args) {
        System.out.println("=== Number Theory ===");
        System.out.println("gcd(48,18) = " + gcd(48, 18));
        System.out.println("lcm(4,6) = " + lcm(4, 6));
        System.out.println("isPrime(17) = " + isPrime(17) + ", isPrime(18) = " + isPrime(18));
        System.out.println("factorial(10) = " + factorial(10));
        System.out.println("combination(10,3) = " + combination(10, 3));
        System.out.println("permutation(10,3) = " + permutation(10, 3));
        System.out.println("getDivisors(36) = " + getDivisors(36));
        System.out.println("sumToN(100) = " + sumToN(100));
        System.out.println("power(2,10) = " + power(2, 10));
        System.out.println("modPow(2,100,MOD) = " + modPow(2, 100, MOD));
        System.out.println("isPowerOfTwo(64) = " + isPowerOfTwo(64));
        System.out.println("countSetBits(255) = " + countSetBits(255));
        System.out.println("sieve(30) = " + sieve(30));

        System.out.println("\n=== Sequences ===");
        System.out.println("fibonacci(10) = " + Arrays.toString(fibonacci(10)));
        System.out.println("nthFibonacci(10) = " + nthFibonacci(10));
        System.out.println("pascalRow(5) = " + Arrays.toString(pascalRow(5)));

        System.out.println("\n=== Strings ===");
        System.out.println("reverseString(\"hello\") = " + reverseString("hello"));
        System.out.println("isPalindrome(\"racecar\") = " + isPalindrome("racecar"));
        System.out.println("charFrequency(\"hello\") = " + charFrequency("hello"));
        System.out.println("isAnagram(\"listen\",\"silent\") = " + isAnagram("listen", "silent"));

        System.out.println("\n=== Base Conversion ===");
        System.out.println("decimalToBinary(42) = " + decimalToBinary(42));
        System.out.println("binaryToDecimal(\"101010\") = " + binaryToDecimal("101010"));

        System.out.println("\n=== Arrays ===");
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6};
        int[] sorted = arr.clone(); Arrays.sort(sorted);
        System.out.println("binarySearch(sorted,5) = " + binarySearch(sorted, 5));
        System.out.println("elementFrequency = " + elementFrequency(arr));
        System.out.println("maxElement = " + maxElement(arr) + ", minElement = " + minElement(arr));
        System.out.println("maxSubarraySum([-2,1,-3,4,-1,2,1,-5,4]) = "
            + maxSubarraySum(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println("generateSubsets([1,2,3]) = " + generateSubsets(new int[]{1,2,3}));

        System.out.println("\n=== DP ===");
        System.out.println("coinChange([1,2,5], 11) = " + coinChange(new int[]{1,2,5}, 11));
        System.out.println("lis([10,9,2,5,3,7,101,18]) = " + lis(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println("knapsack(W=6,[2,3,4],[3,4,5]) = "
            + knapsack(6, new int[]{2,3,4}, new int[]{3,4,5}));
    }
}