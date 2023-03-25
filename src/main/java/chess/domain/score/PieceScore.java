package chess.domain.score;

import chess.domain.piece.Piece;
import chess.domain.piece.SquareState;
import chess.domain.piece.state.*;

import java.util.Arrays;

public enum PieceScore {
    PAWN(1, Pawn.class),
    ROOK(5, Rook.class),
    KNIGHT(2.5, Knight.class),
    BISHOP(3, Bishop.class),
    QUEEN(9, Queen.class),
    KING(0, King.class);

    private final double score;
    private final Class<? extends Piece> pieceClass;

    PieceScore(final double score, final Class<? extends Piece> pieceClass) {
        this.score = score;
        this.pieceClass = pieceClass;
    }

    public static double getScoreByPiece(final SquareState piece) {
        return Arrays.stream(PieceScore.values())
                .filter(pieceScore -> piece.getClass() == pieceScore.pieceClass)
                .map(PieceScore::getScore)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 종류의 기물입니다."));
    }

    private double getScore() {
        return score;
    }
}
