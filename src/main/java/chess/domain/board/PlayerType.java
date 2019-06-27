package chess.domain.board;

public enum PlayerType {
    WHITE,
    BLACK;

    public static PlayerType of(String piece) {
        return Character.isUpperCase(piece.charAt(0)) ? BLACK : WHITE;
    }
}
