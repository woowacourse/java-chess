package chess.view;

public enum PieceTypeView {

    PAWN("p"),
    ROOK("r"),
    KNIGHT("n"),
    BISHOP("b"),
    KING("k"),
    QUEEN("q");

    private final String viewName;

    PieceTypeView(final String viewName) {
        this.viewName = viewName;
    }

    public static String findViewName(String name) {
        return valueOf(name).viewName;
    }
}
