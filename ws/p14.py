

import math;

def problem():
    [printers, jobs] = input().split()
    PRINT = dict();
    for _ in range(int(printers)):
        [ident, density, mass] = input().split();
        density = float(density);
        mass = float(mass);
        PRINT[ident] = (density, mass);
    for _ in range(int(jobs)):
        [id, vol, pct] = input().split();
        vol = float(vol);
        pct = float(pct);
        (density, max_mass) = PRINT[id];
        mass = vol * pct * density;
        print(math.floor(max_mass / mass));








for _ in range(int(input())):
    problem()
