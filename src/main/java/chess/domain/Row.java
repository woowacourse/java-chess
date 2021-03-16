package chess.domain;

public enum Row {
    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ONE("1");

    private String row;

    Row(String row){
        this.row = row;
    }

    public String getRow() {
        return row;
    }
}
