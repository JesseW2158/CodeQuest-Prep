




for i in range(int(input())):
    pair = input().split(':')
    v = float(pair[0])
    x = float(pair[1])
    if v * 1 >= x:
        print("SWERVE");
    elif v * 5 >= x:
        print("BRAKE");
    else:
        print("SAFE");
