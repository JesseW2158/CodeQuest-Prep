
SECONDS = 1;
MINUTES = 60;
HOURS = 60 * 60;
DAYS = 24 * HOURS;

CONV = {
    "SECONDS": 1,
    "MINUTES": 60,
    "HOURS": HOURS,
    "DAYS": DAYS,
}


def problem():
    [val, unit, convert] = input().split()
    val = int(val);

    val2 = val * CONV[unit] / CONV[convert];

    og = f"{val} {unit}";
    second = f"{round(val2)} {convert}";
    print(f"{og}->{second}");





for _ in range(int(input())):
    problem()
