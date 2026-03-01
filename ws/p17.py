



def problem():
    text = input();
    CMAP = dict();
    chars = len(text);
    words = len(text.split());
    for char in text:
        count = 0;
        if char in CMAP:
            count = CMAP[char]
        CMAP[char] = count + 1;
    print(text);
    print(chars * "-");
    print(f"CHARACTERS: {chars}")
    print(f"WORDS: {words}")
    for k, v in sorted(CMAP.items(), key=lambda item: -item[1]):
        print(f"{k}: {v}");














for _ in range(int(input())):
    problem()
