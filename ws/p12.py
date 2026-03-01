#unsolved

def score(speed, fuel, max, costs, distance, weight):
    if speed == 0.0:
        speed = 0.01;
    time = 60.0 * 60.0 * distance/speed;
    f_fuel = distance * fuel;
    return time + f_fuel + costs;



def problem():
    [missions, speed, fuel, max, costs, speed2, fuel2, max2, costs2] = map(float, input().split())
    for _ in range(int(missions)):
        [distance, weight] = map(float, input().split());
        if weight >= max:
            print("Sikorsky Raider")
            return;
        elif weight >= max2:
            print("F-22 Raptor")
            return;
        score1 = score(speed, fuel, max, costs, distance, weight);
        score2 = score(speed2, fuel2, max2, costs2, distance, weight);
        if abs(score1 - score2) < 0.01:
            f_fuel = distance * fuel;
            f_fuel2 = distance * fuel2;
            if f_fuel > f_fuel2:
                print("Sikorsky Raider")
            else:
                print("F-22 Raptor")
            return;
        if score1 > score2:
            print("Sikorsky Raider")
        else:
            print("F-22 Raptor")





for _ in range(int(input())):
    problem()
