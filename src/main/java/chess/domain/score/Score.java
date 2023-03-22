package chess.domain.score;

import chess.domain.Position;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

import java.util.Map;

public enum Score {
    PAWN_DEFAULT_SCORE(1.0),
    PAWN_SPECIAL_SCORE(0.5),
    BISHOP_SCORE(3.0),
    KING_SCORE(0.0),
    KNIGHT_SCORE(2.5),
    QUEEN_SCORE(9.0),
    ROOK_SCORE(5.0),
    EMPTY_SCORE(0.0);


    private double score;

    Score(double score) {
        this.score = score;
    }

    public static double getPawnScore(Map<Position, Piece> chessBoard, Position targetPosition) {
        for (Position position : chessBoard.keySet()) {
            if (position.isSameColumn(targetPosition.getColumn())
                    && chessBoard.get(position) instanceof Pawn
                    && !position.equals(targetPosition)) {
                return PAWN_SPECIAL_SCORE.getScore();
            }
        }
        return PAWN_DEFAULT_SCORE.getScore();
    }

    public double getScore() {
        return score;
    }
}
