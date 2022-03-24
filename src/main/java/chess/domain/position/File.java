package chess.domain.position;

public enum File {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int file;

    File(int file) {
        this.file = file;
    }

    public int getFile() {
        return file;
    }

    public int calculateAbsoluteValue(File other) {
        return Math.abs(file - other.getFile());
    }
}
