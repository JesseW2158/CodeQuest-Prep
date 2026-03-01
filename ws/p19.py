



def scanboi(text):
    writer = "";
    pat_reading = False;
    last_num = "";
    new_text = "";
    depth_counter = 0;
    for i in text:
        i = str(i)
        if pat_reading:
            if i == "(":
                depth_counter += 1;
            elif i == ")":
                # print("DEBUG:" + last_num)
                if depth_counter == 0:
                    writer += scanboi(new_text) * int(last_num);
                    new_text = "";
                    last_num = "";
                    pat_reading = False;
                    continue;
                depth_counter -= 1;
            new_text += i;
            continue;
        if i.isalpha():
            if pat_reading:
                new_text += i;
            else:
                writer += i;
        elif i.isnumeric():
            last_num += i;
            # print("DEBUG2:" + last_num)
        elif i == "(":
            pat_reading = True;
    return writer;





def problem():
    text = input();
    print(scanboi(text));
















for _ in range(int(input())):
    problem()
