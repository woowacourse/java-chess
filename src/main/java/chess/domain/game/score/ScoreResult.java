package chess.domain.game.score;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.position.XAxis;
import java.util.EnumMap;
import java.util.Map;

public class ScoreResult {

    private final Map<PieceColor, Score> value;

    public ScoreResult(Board board) {
        this.value = initialize(board);
    }

    private Map<PieceColor, Score> initialize(Board board) {
        Map<PieceColor, Score> value = new EnumMap<>(PieceColor.class);

        for (PieceColor pieceColor : PieceColor.values()) {
            value.put(pieceColor, Score.from(calculateScore(board, pieceColor)));
        }

        return value;
    }

    private double calculateScore(Board board, PieceColor pieceColor) {
        return board.findPiecesByPieceColor(pieceColor)
                .stream()
                .mapToDouble(Piece::getScore)
                .sum() - adjustPawnScore(board, pieceColor);
    }

    private double adjustPawnScore(Board board, PieceColor pieceColor) {
        int totalDuplicatedPawnCount = 0;

        for (XAxis xAxis : XAxis.values()) {
            totalDuplicatedPawnCount += board.getDuplicatedPawnCountByXAxis(pieceColor, xAxis);
        }

        return totalDuplicatedPawnCount * 0.5;
    }

    public Score getScoreByPieceColor(PieceColor pieceColor) {
        return value.get(pieceColor);
    }
}
