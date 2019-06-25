package chess.domain;

public enum PieceSymbol {
    WHITE_PAWN(Piece.Color.WHITE, Rule.Type.PAWN, "♙"),
    WHITE_BISHOP(Piece.Color.WHITE, Rule.Type.BISHOP, "♗"),
    WHITE_KING(Piece.Color.WHITE, Rule.Type.KING, "♔"),
    WHITE_KNIGHT(Piece.Color.WHITE, Rule.Type.KNIGHT, "♘"),
    WHITE_QUEEN(Piece.Color.WHITE, Rule.Type.QUEEN, "♕"),
    WHITE_ROOK(Piece.Color.WHITE, Rule.Type.ROOK, "♖"),
    BLACK_PAWN(Piece.Color.BLACK, Rule.Type.PAWN, "♟️"),
    BLACK_BISHOP(Piece.Color.BLACK, Rule.Type.BISHOP, "♝"),
    BLACK_KING(Piece.Color.BLACK, Rule.Type.KING, "♚"),
    BLACK_KNIGHT(Piece.Color.BLACK, Rule.Type.KNIGHT, "♞"),
    BLACK_QUEEN(Piece.Color.BLACK, Rule.Type.QUEEN, "♛"),
    BLACK_ROOK(Piece.Color.BLACK, Rule.Type.ROOK, "♜");

    public static final String EMPTY_SYMBOL = " ";

    private final Piece.Color color;
    private final Rule.Type type;
    private final String symbol;

    PieceSymbol(final Piece.Color color, final Rule.Type type, final String symbol) {
        this.color = color;
        this.type = type;
        this.symbol = symbol;
    }

    public static String getSymbol(Piece piece) {
        for (final PieceSymbol pieceSymbol : values()) {
            if (piece.isSameColor(pieceSymbol.color) && piece.isSameType(pieceSymbol.type)) {
                return pieceSymbol.symbol;
            }
        }
        return EMPTY_SYMBOL;
    }

    public String getSymbol() {
        return symbol;
    }
}
