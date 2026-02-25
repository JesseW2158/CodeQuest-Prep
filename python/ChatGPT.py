In Python competitive programming, speed comes from **(1) knowing built-ins** and **(2) using a small set of powerful standard-library tools** rather than big external packages (since contests usually restrict installs).

Here’s a practical “toolkit” most strong competitors rely on 👇

---

## 🔹 1. Fast I/O Utilities (VERY important)

Python’s default input can be slow.

```python
import sys
input = sys.stdin.readline
```

For output:

```python
sys.stdout.write(str(ans) + "\n")
```

Use this when input size is large (10⁵–10⁶ lines).

---

## 🔹 2. `itertools` — Combinatorics & iteration

One of the most useful modules.

```python
from itertools import permutations, combinations, product, accumulate
```

Common uses:

* `permutations(arr, k)` → generate orderings
* `combinations(arr, k)` → choose k elements
* `product(a, b)` → nested loops cleaner
* `accumulate(arr)` → prefix sums instantly

Example:

```python
prefix = list(accumulate(arr))
```

---

## 🔹 3. `collections` — Essential data structures

You will use this constantly.

### ✅ `deque` (fast queue/stack)

```python
from collections import deque
q = deque()
q.append(x)
q.popleft()
```

👉 O(1) queue operations (great for BFS).

### ✅ `Counter`

```python
from collections import Counter
cnt = Counter(arr)
```

Counts frequencies instantly.

### ✅ `defaultdict`

```python
from collections import defaultdict
graph = defaultdict(list)
```

Perfect for adjacency lists.

---

## 🔹 4. `heapq` — Priority queues

Used for greedy problems and Dijkstra.

```python
import heapq

pq = []
heapq.heappush(pq, (dist, node))
dist, node = heapq.heappop(pq)
```

Python heaps are **min-heaps** by default.

---

## 🔹 5. `bisect` — Binary search (SUPER useful)

Avoid writing binary search manually.

```python
import bisect

bisect.bisect_left(arr, x)
bisect.bisect_right(arr, x)
```

Used for:

* LIS problems
* maintaining sorted arrays
* range queries

---

## 🔹 6. `math` — Number theory helpers

```python
import math

math.gcd(a, b)
math.lcm(a, b)
math.isqrt(n)
math.ceil(x)
```

`isqrt` is especially useful for divisor problems.

---

## 🔹 7. `functools` — Memoization

Great for DP + recursion.

```python
from functools import lru_cache

@lru_cache(None)
def dp(x):
    ...
```

Turns exponential recursion into fast DP.

---

## 🔹 8. Useful Built-ins (often overlooked)

These win contests:

```python
sum(arr)
min(arr)
max(arr)
sorted(arr)
zip(a, b)
enumerate(arr)
```

Very fast because they’re implemented in C.

---

## 🔹 9. Common Competitive Programming Snippets

### Fast prefix sums

```python
prefix = [0]
for x in arr:
    prefix.append(prefix[-1] + x)
```

### Modular exponentiation

```python
pow(a, b, mod)   # extremely fast
```

---

## 🔹 10. Optional (if platform allows)

Some contests allow:

* `numpy` ❌ usually banned
* `sortedcontainers` ✅ occasionally allowed

But assume **standard library only**.

---

## ⭐ Most Important Ones (Top 6)

If you memorize only a few:

* `collections.deque`
* `collections.defaultdict`
* `heapq`
* `bisect`
* `itertools`
* `functools.lru_cache`

---

If you want, I can also give you a **ready-to-paste Python competitive programming template** that top contestants start every problem with — it saves a LOT of time.
