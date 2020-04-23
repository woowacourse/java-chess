package chess.domain;

public enum Rank {

    EIGHT('8'),
    SEVEN('7'),
    SIX('6'),
    FIVE('5'),
    FOUR('4'),
    THREE('3'),
    TWO('2'),
    ONE('1');


    private final char name;

    Rank(char name) {
        this.name = name;
    }

    public char getName() {
        return name;
    }
}
