package chess.domain.pieces;

import chess.domain.pieces.nonsliding.King;
import chess.domain.pieces.nonsliding.Knight;
import chess.domain.pieces.pawn.Pawn;
import chess.domain.pieces.sliding.Bishop;
import chess.domain.pieces.sliding.Queen;
import chess.domain.pieces.sliding.Rook;
import java.util.Arrays;
import java.util.List;

public enum PieceType {

    PAWN(Pawn.class, new Score(1)),
    ROOK(Rook.class, new Score(5)),
    KNIGHT(Knight.class, new Score(2.5)),
    BISHOP(Bishop.class, new Score(3)),
    QUEEN(Queen.class, new Score(9)),
    KING(King.class, new Score(0)),
    EMPTY(EmptyPiece.class, new Score(0));

    private static final int CRITERIA_OF_CALCULATE_PAWN_SCORE = 2;
    private static final double SCORE_OF_OVERLAP_PAWNS = 0.5;

    private final Class<? extends Piece> piece;
    private final Score score;

    PieceType(final Class<? extends Piece> piece, final Score score) {
        this.piece = piece;
        this.score = score;
    }

    public static PieceType from(final Class<? extends Piece> piece) {
        return Arrays.stream(PieceType.values())
                .filter(pieceType -> pieceType.piece.isAssignableFrom(piece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물입니다."));
    }

    public static Score calculateSingleColumn(final List<Piece> singleColumnPieces) {
        Score totalScore = totalScore(singleColumnPieces);
        long pawnCount = getPawnCount(singleColumnPieces);

        if (pawnCount >= CRITERIA_OF_CALCULATE_PAWN_SCORE) {
            return totalScore.subtract(SCORE_OF_OVERLAP_PAWNS * pawnCount);
        }
        return totalScore;
    }

    private static Score totalScore(final List<Piece> singleColumnPieces) {
        return singleColumnPieces.stream()
                .map(piece1 -> from(piece1.getClass()))
                .map(PieceType::getScore)
                .reduce(Score::add)
                .orElse(Score.ZERO);
    }

    private static long getPawnCount(final List<Piece> singleColumnPieces) {
        return singleColumnPieces.stream()
                .filter(piece1 -> from(piece1.getClass()).equals(PAWN))
                .count();
    }

    public Score getScore() {
        return this.score;
    }
}
