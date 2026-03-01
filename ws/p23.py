#unsolved

SHIFT = 10**9;

DICT = {
    "G": 10**18,
    "M": 10**15,
    "k": 10**12,
    "H": 10**9,
    "m": 10**6,
    "u": 10**3,
    "n": 1
}
def boolInt(b):
    if b:
        return 1;
    else:
        return 0;


def upgrade(val):
    print((str(val))[-3:-1]);
    if (str(val))[-3:-1] == "000":
        return upgrade(val) / (10 ** 3);
    else:
        return val;


def problem():
    [first, operation, second] = input().split(",");
    if operation == "*":
        [first, unit1] = first.split();
        [second, unit2] = second.split();
        first = int(first);
        second = int(second);
        if "Hz" in unit1:
            first *= DICT[unit1[0]];
            first *= int(second);
            print(upgrade(first));
        else:
            second *= DICT[unit2[0]];
            second *= int(first);
            print(upgrade(second));
    elif operation == "+" or operation == "-":
        if "Hz" not in first or "Hz" not in second:
            print("INVALID");
            return;
        else:
            [first, unit1] = first.split();
            [second, unit2] = second.split();
            first *= DICT[unit1[0]];
            second *= DICT[unit2[0]];
            print("test")
            computed = int(first) + int(second) * (-1 if operation == "-" else -1);
            print(upgrade(computed));

















for _ in range(int(input())):
    problem()
