Python Speed & Knowledge Cheatsheet 🐍
The Basics
input() — Reading from the keyboard (or judge’s test cases)

name = input()          # reads a line of text as a string
x = input("Enter x: ")  # optional prompt message (usually skip this in competitions)


In competitions, input always comes as a string, so you almost always need to convert it.

int(), float(), str() — Type conversion

n = int(input())        # read one integer
x = float(input())      # read a decimal number
s = str(42)             # "42"


Reading multiple values on one line

a, b = input().split()          # splits by spaces → still strings!
a, b = map(int, input().split()) # splits AND converts to int ✓
nums = list(map(int, input().split()))  # whole line of ints into a list


List Comprehensions — Compact loops

# Normal loop:
squares = []
for i in range(10):
    squares.append(i**2)

# List comprehension (MUCH faster to write and run):
squares = [i**2 for i in range(10)]

# With a filter:
evens = [i for i in range(20) if i % 2 == 0]

# Nested (like a 2D grid):
grid = [[0]*cols for _ in range(rows)]


Operators — The fun stuff



|Operator|Meaning                         |Example                    |
|--------|--------------------------------|---------------------------|
|`**`    |Power                           |`2**10` → 1024             |
|`//`    |Integer (floor) division        |`7//2` → 3                 |
|`%`     |Modulo (remainder)              |`7%3` → 1                  |
|`&`     |Bitwise AND / set intersection  |`{1,2} & {2,3}` → `{2}`    |
|`|`     |Bitwise OR / set union          |`{1,2} | {2,3}` → `{1,2,3}`|
|`^`     |Bitwise XOR / set symmetric diff|`{1,2} ^ {2,3}` → `{1,3}`  |
|`-`     |Set difference                  |`{1,2} - {2,3}` → `{1}`    |
|`~x`    |Bitwise NOT (equals `-x - 1`)   |`~5` → -6                  |
|`x << n`|Left shift (multiply by 2ⁿ)     |`3 << 2` → 12              |
|`x >> n`|Right shift (divide by 2ⁿ)      |`12 >> 2` → 3              |

Sets — The underrated hero

s = {1, 2, 3, 4}
s.add(5)
s.remove(3)       # error if missing
s.discard(99)     # safe remove, no error
3 in s            # O(1) lookup — WAY faster than lists!

# Set operations:
a | b    # union
a & b    # intersection
a - b    # difference
a ^ b    # symmetric difference
a <= b   # is a a subset of b?


Dictionaries — Key-value powerhouse

d = {}
d["key"] = "value"
d.get("key", default)    # returns default if key missing (no crash!)
d.keys(), d.values(), d.items()  # iterate

# Count occurrences (super common pattern):
from collections import Counter
c = Counter([1,1,2,3,3,3])  # {3:3, 1:2, 2:1}
c.most_common(2)            # top 2 elements

# Default dict (never get KeyError):
from collections import defaultdict
d = defaultdict(int)   # missing keys start at 0
d["x"] += 1
d = defaultdict(list)  # missing keys start as []
d["x"].append(1)


Lists — Know your methods

lst = [3, 1, 4, 1, 5]
lst.append(9)           # add to end — O(1)
lst.pop()               # remove from end — O(1)
lst.pop(0)              # remove from front — O(n) SLOW! use deque instead
lst.insert(i, val)      # insert at index — O(n)
lst.sort()              # sort in place
lst.sort(reverse=True)  # descending
sorted(lst)             # returns new sorted list, original untouched
lst.reverse()
lst.count(1)            # how many times 1 appears
lst.index(4)            # first index of value 4
sum(lst), min(lst), max(lst)  # built-ins!


Strings — Useful tricks

s = "hello world"
s.split()           # ["hello", "world"]
s.split(",")        # split by comma
s.strip()           # remove leading/trailing whitespace
s.upper(), s.lower()
s.replace("l", "r") # "herro worrd"
s.startswith("he"), s.endswith("ld")
",".join(["a","b","c"])  # "a,b,c"
s[1:4]              # slicing: "ell"
s[::-1]             # reverse string: "dlrow olleh"


Deque — Fast queue (when pop(0) from list is too slow)

from collections import deque
dq = deque([1, 2, 3])
dq.appendleft(0)    # O(1) add to front
dq.popleft()        # O(1) remove from front
dq.append(4)        # O(1) add to back
dq.pop()            # O(1) remove from back


Heaps — Priority queues

import heapq
h = [3, 1, 4, 1, 5]
heapq.heapify(h)         # min-heap in O(n)
heapq.heappush(h, 2)
smallest = heapq.heappop(h)  # always pops minimum

# Max-heap trick: negate values
heapq.heappush(h, -val)
max_val = -heapq.heappop(h)


Speed Hacks & Tricks
Fast I/O — for problems with massive input:

import sys
input = sys.stdin.readline   # drop-in replacement, much faster


any() and all()

any(x > 5 for x in lst)   # True if at least one x > 5
all(x > 0 for x in lst)   # True if ALL x > 0


Swap without temp variable

a, b = b, a


Ternary (one-line if/else)

val = "yes" if condition else "no"


enumerate() — index + value together

for i, val in enumerate(lst):
    print(i, val)


zip() — loop two lists at once

for a, b in zip(list1, list2):
    print(a, b)


range() tricks

range(10)           # 0..9
range(2, 10)        # 2..9
range(10, 0, -1)    # 10 down to 1
range(0, 10, 2)     # 0,2,4,6,8


Infinite values

float('inf')    # larger than any number
float('-inf')   # smaller than any number


abs(), pow(x, y, mod)

abs(-5)             # 5
pow(2, 10, 1000)    # 2^10 mod 1000 — fast modular exponentiation!


// and % for coordinate tricks

row = index // cols
col = index % cols


Useful math and itertools

import math
math.gcd(12, 8)     # 4
math.lcm(4, 6)      # 12
math.sqrt(16)       # 4.0
math.ceil(3.2)      # 4
math.floor(3.9)     # 3

import itertools
itertools.permutations([1,2,3])     # all orderings
itertools.combinations([1,2,3], 2)  # all pairs
itertools.product([0,1], repeat=3)  # like nested loops


Quick Complexity Cheat (know what’s too slow)



|Operation                  |Speed     |
|---------------------------|----------|
|List index, set/dict lookup|O(1)      |
|List search (`x in list`)  |O(n)      |
|Sorting                    |O(n log n)|
|Nested loops               |O(n²)     |

If n ≤ 10⁶, O(n) is fine. If n ≤ 10⁴, O(n²) is fine. If n ≤ 500, O(n³) might squeak by.

Good luck at your competition! The biggest gains come from knowing when to use a set vs list (membership checks), reading input fast, and trusting built-ins like sorted(), Counter, and heapq instead of reinventing the wheel.​​​​​​​​​​​​​​​​
