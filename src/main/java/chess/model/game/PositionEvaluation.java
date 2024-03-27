package chess.model.game;

import chess.model.piece.Piece;
import chess.model.piece.Side;
import chess.model.position.ChessPosition;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.groupingBy;

public class PositionEvaluation {
    private final Map<Side, Double> evaluation;

    public PositionEvaluation(Map<ChessPosition, Piece> board) {
        this.evaluation = evaluate(board);
    }

    public Map<Side, Double> evaluate(Map<ChessPosition, Piece> board) {
        Map<Piece, List<ChessPosition>> positionsByPiece = board.entrySet().stream()
                .collect(groupingBy(Map.Entry::getValue, mapping(Map.Entry::getKey, toList())));

        return Side.colors()
                .stream()
                .collect(toMap(identity(), side -> sumScoresBySide(positionsByPiece, side)));
    }

    private Double sumScoresBySide(Map<Piece, List<ChessPosition>> positionsByPiece, Side side) {
        return positionsByPiece.keySet()
                .stream()
                .filter(piece -> piece.isSameSide(side))
                .mapToDouble(key -> calculateScoreByPiece(key, positionsByPiece.get(key)))
                .sum();
    }

    private double calculateScoreByPiece(Piece piece, List<ChessPosition> chessPositions) {
        PieceValue pieceValue = piece.value();
        return pieceValue.calculateScore(chessPositions);
    }

    public double getEvaluationBySide(Side side) {
        return evaluation.get(side);
    }
}
