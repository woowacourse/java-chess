package chess.domain.board;

public enum PlayerType {
    WHITE(1),
    BLACK(-1);

    private int directionCorrection;

    PlayerType(int directionCorrection) {
        this.directionCorrection = directionCorrection;
    }

    public static PlayerType of(String piece) {
        return Character.isLowerCase(piece.charAt(0)) ? WHITE : BLACK;
    }
}
