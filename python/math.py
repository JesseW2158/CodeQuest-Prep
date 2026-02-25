# Common Math Functions by Santa Claude

"""
LMT CodeQuest - Python Reference Functions
==========================================
A comprehensive reference sheet for competitive programming.
"""

import math
import random
from collections import Counter
from itertools import combinations as _combinations
from typing import List, Dict, Optional


# ─────────────────────────────────────────────
# NUMBER THEORY
# ─────────────────────────────────────────────

def gcd(a: int, b: int) -> int:
    """Greatest common divisor."""
    return math.gcd(a, b)

def lcm(a: int, b: int) -> int:
    """Least common multiple."""
    return a * b // math.gcd(a, b)

def is_prime(n: int) -> bool:
    """Returns True if n is prime. O(sqrt(n))."""
    if n < 2:
        return False
    if n < 4:
        return True
    if n % 2 == 0 or n % 3 == 0:
        return False
    i = 5
    while i * i <= n:
        if n % i == 0 or n % (i + 2) == 0:
            return False
        i += 6
    return True

def sieve_of_eratosthenes(limit: int) -> List[int]:
    """Return all primes up to limit (inclusive). O(n log log n)."""
    is_p = [True] * (limit + 1)
    is_p[0] = is_p[1] = False
    for i in range(2, int(limit**0.5) + 1):
        if is_p[i]:
            for j in range(i*i, limit + 1, i):
                is_p[j] = False
    return [i for i, v in enumerate(is_p) if v]

def factorial(n: int) -> int:
    """n! using math.factorial."""
    return math.factorial(n)

def combination(n: int, r: int) -> int:
    """nCr = n! / (r!(n-r)!)."""
    return math.comb(n, r)

def permutation(n: int, r: int) -> int:
    """nPr = n! / (n-r)!."""
    return math.perm(n, r)

def power(base: int, exp: int, mod: Optional[int] = None) -> int:
    """Fast exponentiation. Provide mod for modular exponentiation."""
    if mod:
        return pow(base, exp, mod)
    return pow(base, exp)

def get_divisors(n: int) -> List[int]:
    """Return sorted list of all divisors of n. O(sqrt(n))."""
    divs = []
    for i in range(1, int(n**0.5) + 1):
        if n % i == 0:
            divs.append(i)
            if i != n // i:
                divs.append(n // i)
    return sorted(divs)

def sum_to_n(n: int) -> int:
    """Sum of 0..n = n(n+1)/2."""
    return n * (n + 1) // 2

def is_power_of_two(n: int) -> bool:
    """Returns True if n is a positive power of 2."""
    return n > 0 and (n & (n - 1)) == 0

def count_set_bits(n: int) -> int:
    """Count number of 1-bits in binary representation of n."""
    return bin(n).count('1')


# ─────────────────────────────────────────────
# SEQUENCES
# ─────────────────────────────────────────────

def fibonacci(n: int) -> List[int]:
    """Return list of first n Fibonacci numbers (0-indexed: F(0)=0, F(1)=1)."""
    if n <= 0:
        return []
    fibs = [0, 1]
    for _ in range(2, n):
        fibs.append(fibs[-1] + fibs[-2])
    return fibs[:n]

def nth_fibonacci(n: int) -> int:
    """Return the nth Fibonacci number (0-indexed)."""
    if n == 0: return 0
    a, b = 0, 1
    for _ in range(1, n):
        a, b = b, a + b
    return b

def pascal_row(n: int) -> List[int]:
    """Return the nth row of Pascal's triangle (0-indexed)."""
    return [math.comb(n, k) for k in range(n + 1)]

def pascal_triangle(rows: int) -> List[List[int]]:
    """Return Pascal's triangle up to `rows` rows."""
    return [pascal_row(i) for i in range(rows)]


# ─────────────────────────────────────────────
# STRING UTILITIES
# ─────────────────────────────────────────────

def reverse_string(s: str) -> str:
    return s[::-1]

def is_palindrome(s: str) -> bool:
    """Case-insensitive palindrome check (ignores spaces)."""
    cleaned = s.replace(" ", "").lower()
    return cleaned == cleaned[::-1]

def char_frequency(s: str) -> Dict[str, int]:
    """Return frequency map of each character in s."""
    return dict(Counter(s))

def longest_common_subsequence(a: str, b: str) -> int:
    """Length of LCS of two strings. O(mn)."""
    m, n = len(a), len(b)
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if a[i-1] == b[j-1]:
                dp[i][j] = dp[i-1][j-1] + 1
            else:
                dp[i][j] = max(dp[i-1][j], dp[i][j-1])
    return dp[m][n]


# ─────────────────────────────────────────────
# BIT / BASE CONVERSION
# ─────────────────────────────────────────────

def decimal_to_binary(n: int) -> str:
    """Convert decimal integer to binary string (no '0b' prefix)."""
    return bin(n)[2:]

def binary_to_decimal(b: str) -> int:
    """Convert binary string to decimal integer."""
    return int(b, 2)

def decimal_to_hex(n: int) -> str:
    return hex(n)[2:].upper()

def decimal_to_base(n: int, base: int) -> str:
    """Convert n to any base (2-16) string."""
    digits = "0123456789ABCDEF"
    if n == 0: return "0"
    result = ""
    while n:
        result = digits[n % base] + result
        n //= base
    return result


# ─────────────────────────────────────────────
# ARRAY / LIST UTILITIES
# ─────────────────────────────────────────────

def binary_search(arr: List[int], target: int) -> int:
    """Return index of target in sorted arr, or -1 if not found."""
    lo, hi = 0, len(arr) - 1
    while lo <= hi:
        mid = (lo + hi) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            lo = mid + 1
        else:
            hi = mid - 1
    return -1

def element_frequency(arr: List[int]) -> Dict[int, int]:
    """Return frequency map of elements in arr."""
    return dict(Counter(arr))

def max_element(arr: List[int]) -> int:
    return max(arr)

def min_element(arr: List[int]) -> int:
    return min(arr)

def max_subarray_sum(arr: List[int]) -> int:
    """Kadane's algorithm. O(n)."""
    best = curr = arr[0]
    for x in arr[1:]:
        curr = max(x, curr + x)
        best = max(best, curr)
    return best

def shuffle_array(arr: List[int]) -> List[int]:
    """Return a new shuffled copy of arr (Fisher-Yates)."""
    a = arr[:]
    random.shuffle(a)
    return a

def generate_subsets(arr: List[int]) -> List[List[int]]:
    """Return all 2^n subsets of arr."""
    result = []
    n = len(arr)
    for mask in range(1 << n):
        subset = [arr[i] for i in range(n) if mask & (1 << i)]
        result.append(subset)
    return result

def rotate_array(arr: List[int], k: int) -> List[int]:
    """Rotate array right by k positions."""
    n = len(arr)
    k %= n
    return arr[-k:] + arr[:-k]

def two_sum(arr: List[int], target: int):
    """Return indices (i, j) where arr[i]+arr[j]==target, or None."""
    seen = {}
    for i, v in enumerate(arr):
        if target - v in seen:
            return (seen[target - v], i)
        seen[v] = i
    return None

def prefix_sums(arr: List[int]) -> List[int]:
    """Return prefix sum array. ps[i] = arr[0]+...+arr[i]."""
    ps = [0] * len(arr)
    ps[0] = arr[0]
    for i in range(1, len(arr)):
        ps[i] = ps[i-1] + arr[i]
    return ps

def range_sum(prefix: List[int], l: int, r: int) -> int:
    """Sum of arr[l..r] using prefix sum array. O(1)."""
    return prefix[r] - (prefix[l-1] if l > 0 else 0)


# ─────────────────────────────────────────────
# MODULAR ARITHMETIC
# ─────────────────────────────────────────────

MOD = 10**9 + 7

def mod_add(a, b, mod=MOD): return (a + b) % mod
def mod_mul(a, b, mod=MOD): return (a * b) % mod
def mod_pow(base, exp, mod=MOD): return pow(base, exp, mod)

def mod_inverse(a: int, mod: int = MOD) -> int:
    """Modular inverse using Fermat's little theorem (mod must be prime)."""
    return pow(a, mod - 2, mod)

def mod_combination(n: int, r: int, mod: int = MOD) -> int:
    """nCr mod p (p prime) using Fermat's little theorem."""
    if r > n: return 0
    num = 1
    den = 1
    for i in range(r):
        num = num * (n - i) % mod
        den = den * (i + 1) % mod
    return num * mod_inverse(den, mod) % mod


# ─────────────────────────────────────────────
# GRAPH HELPERS
# ─────────────────────────────────────────────

from collections import deque, defaultdict

def bfs(graph: Dict, start) -> Dict:
    """BFS returning distances from start."""
    dist = {start: 0}
    q = deque([start])
    while q:
        node = q.popleft()
        for nb in graph.get(node, []):
            if nb not in dist:
                dist[nb] = dist[node] + 1
                q.append(nb)
    return dist

def dfs(graph: Dict, start, visited=None) -> List:
    """Iterative DFS returning visited order."""
    if visited is None:
        visited = set()
    order = []
    stack = [start]
    while stack:
        node = stack.pop()
        if node not in visited:
            visited.add(node)
            order.append(node)
            for nb in graph.get(node, []):
                if nb not in visited:
                    stack.append(nb)
    return order


# ─────────────────────────────────────────────
# SORTING
# ─────────────────────────────────────────────

def merge_sort(arr: List[int]) -> List[int]:
    if len(arr) <= 1:
        return arr
    mid = len(arr) // 2
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])
    result = []
    i = j = 0
    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            result.append(left[i]); i += 1
        else:
            result.append(right[j]); j += 1
    return result + left[i:] + right[j:]


# ─────────────────────────────────────────────
# DEMO
# ─────────────────────────────────────────────

if __name__ == "__main__":
    print("=== Number Theory ===")
    print(f"gcd(48,18) = {gcd(48,18)}")
    print(f"lcm(4,6) = {lcm(4,6)}")
    print(f"is_prime(17) = {is_prime(17)}, is_prime(18) = {is_prime(18)}")
    print(f"factorial(10) = {factorial(10)}")
    print(f"combination(10,3) = {combination(10,3)}")
    print(f"permutation(10,3) = {permutation(10,3)}")
    print(f"get_divisors(36) = {get_divisors(36)}")
    print(f"sum_to_n(100) = {sum_to_n(100)}")
    print(f"power(2,10) = {power(2,10)}")
    print(f"is_power_of_two(64) = {is_power_of_two(64)}")
    print(f"count_set_bits(255) = {count_set_bits(255)}")

    print("\n=== Sequences ===")
    print(f"fibonacci(10) = {fibonacci(10)}")
    print(f"nth_fibonacci(10) = {nth_fibonacci(10)}")
    print(f"pascal_row(5) = {pascal_row(5)}")

    print("\n=== Strings ===")
    print(f"reverse_string('hello') = {reverse_string('hello')}")
    print(f"is_palindrome('racecar') = {is_palindrome('racecar')}")
    print(f"char_frequency('hello') = {char_frequency('hello')}")

    print("\n=== Base Conversion ===")
    print(f"decimal_to_binary(42) = {decimal_to_binary(42)}")
    print(f"binary_to_decimal('101010') = {binary_to_decimal('101010')}")

    print("\n=== Arrays ===")
    arr = [3, 1, 4, 1, 5, 9, 2, 6]
    print(f"binary_search(sorted, 5) = {binary_search(sorted(arr), 5)}")
    print(f"element_frequency = {element_frequency(arr)}")
    print(f"max_element = {max_element(arr)}, min_element = {min_element(arr)}")
    print(f"max_subarray_sum([-2,1,-3,4,-1,2,1,-5,4]) = {max_subarray_sum([-2,1,-3,4,-1,2,1,-5,4])}")
    print(f"generate_subsets([1,2,3]) = {generate_subsets([1,2,3])}")

    print("\n=== Modular Arithmetic ===")
    print(f"mod_combination(10,3) = {mod_combination(10,3)}")
    print(f"mod_pow(2,100) = {mod_pow(2,100)}")

    print("\n=== Primes up to 30 ===")
    print(sieve_of_eratosthenes(30))