package chess.view;

public enum PieceColorView {
    WHITE,
    BLACK;

    public static PieceColorView find(String name) {
        return valueOf(name);
    }
}
