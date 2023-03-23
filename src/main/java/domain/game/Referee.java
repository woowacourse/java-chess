package domain.game;

import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Side;

import java.util.EnumMap;
import java.util.Map;

public class Referee {

    public Map<Side, Double> calculateScore(Map<Position, Piece> chessBoard) {
        Map<Side, Double> scores = new EnumMap<>(Side.class);

        for (Piece piece : chessBoard.values()) {
            if (piece.isEmptyPiece()) {
                continue;
            }
            if (piece.isSameSide(Side.WHITE)) {
                scores.put(Side.WHITE, piece.addScore(scores.getOrDefault(Side.WHITE, 0d)));
                continue;
            }
            scores.put(Side.BLACK, piece.addScore(scores.getOrDefault(Side.BLACK, 0d)));
        }
        return scores;
    }
}
