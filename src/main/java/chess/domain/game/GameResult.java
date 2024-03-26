package chess.domain.game;

import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.pieces.piece.Score;
import chess.domain.square.Square;
import java.util.Map;

public class GameResult {

    private final Map<Square, Piece> positionToPiece;

    public GameResult(final Map<Square, Piece> positionToPiece) {
        this.positionToPiece = positionToPiece;
    }

    public Score getScore(final Color color) {
        double score = calculatePieceScore(color);
        return Score.of(score);
    }

    private double calculatePieceScore(final Color color) {
        return positionToPiece.values().stream()
                .filter(piece -> piece.color().equals(color))
                .map(piece -> piece.getScore().getValue())
                .mapToDouble(i -> i)
                .sum();
    }
}
