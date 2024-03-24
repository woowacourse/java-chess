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
            throw new IllegalArgumentException("[ERROR] 체스판 가로의 범위를 넘어가도록 움직일 수는 없습니다.");
        }
    }

    public int calculateDifference(File file) {
        return this.ordinal() - file.ordinal();
    }
}
