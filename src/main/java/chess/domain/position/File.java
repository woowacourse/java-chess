package chess.domain.position;

public enum File {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private final String file;

    File(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }
}
