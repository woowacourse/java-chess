package chess.domain;

public enum File {

    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H,
    ;

    public File moveByOffset(int offset) {
        try {
            return values()[this.ordinal() + offset];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("체스판 가로 범위를 넘어갔습니다.");
        }
    }

    public int calculateDifference(File file) {
        return this.ordinal() - file.ordinal();
    }
}
