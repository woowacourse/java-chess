package chess.domain.piece;

import java.util.Arrays;

public enum PieceScore {

    BLACK_PAWN(new Pawn(Color.BLACK), 1),
    BLACK_ROOK(new Rook(Color.BLACK), 5),
    BLACK_KNIGHT(new Knight(Color.BLACK), 2.5),
    BLACK_BISHOP(new Bishop(Color.BLACK), 3),
    BLACK_QUEEN(new Queen(Color.BLACK), 9),
    BLACK_KING(new King(Color.BLACK), 0),

    WHITE_PAWN(new Pawn(Color.WHITE), 1),
    WHITE_ROOK(new Rook(Color.WHITE), 5),
    WHITE_KNIGHT(new Knight(Color.WHITE), 2.5),
    WHITE_BISHOP(new Bishop(Color.WHITE), 3),
    WHITE_QUEEN(new Queen(Color.WHITE), 9),
    WHITE_KING(new King(Color.WHITE), 0),
    ;

    private static final double NONE_PIECE_SCORE = 0.0;

    private final Piece piece;
    private final double score;

    PieceScore(final Piece piece, final double score) {
        this.piece = piece;
        this.score = score;
    }

    public static double getScore(final Piece other) {
        return Arrays.stream(PieceScore.values())
                .filter(pieceScore -> pieceScore.piece.equals(other))
                .map(pieceScore -> pieceScore.score)
                .findFirst()
                .orElse(NONE_PIECE_SCORE);
    }
}
