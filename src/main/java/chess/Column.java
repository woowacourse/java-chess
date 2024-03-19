package chess;

public enum Column {
    RANK1("1", 7),
    RANK2("2", 6),
    RANK3("3", 5),
    RANK4("4", 4),
    RANK5("5", 3),
    RANK6("6", 2),
    RANK7("7", 1),
    RANK8("8", 0);

    private String name;
    private int index;

    Column(String name, int index) {
        this.name = name;
        this.index = index;
    }
}
