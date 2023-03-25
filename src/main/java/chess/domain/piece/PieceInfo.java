package chess.domain.piece;

public enum PieceInfo {

    WHITE_BISHOP_INFO(Color.WHITE, "b"),
    WHITE_KING_INFO(Color.WHITE, "k"),
    WHITE_KNIGHT_INFO(Color.WHITE, "n"),
    WHITE_PAWN_INFO(Color.WHITE, "p"),
    WHITE_QUEEN_INFO(Color.WHITE, "q"),
    WHITE_ROOK_INFO(Color.WHITE, "r"),
    BLACK_BISHOP_INFO(Color.BLACK, "B"),
    BLACK_KING_INFO(Color.BLACK, "K"),
    BLACK_KNIGHT_INFO(Color.BLACK, "N"),
    BLACK_PAWN_INFO(Color.BLACK, "P"),
    BLACK_QUEEN_INFO(Color.BLACK, "Q"),
    BLACK_ROOK_INFO(Color.BLACK, "R"),
    EMPTY_INFO(Color.NONE,".");

    private final Color color;
    private final String name;

    PieceInfo(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
