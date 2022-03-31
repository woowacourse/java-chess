package chess.domain.game.score;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.position.XAxis;
import java.util.Arrays;
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
            value.put(pieceColor, calculateScore(board, pieceColor));
        }

        return value;
    }

    private Score calculateScore(Board board, PieceColor pieceColor) {
        return board.findPiecesByPieceColor(pieceColor)
                .stream()
                .map(Piece::getScore)
                .reduce(Score::add)
                .map(score -> score.subtract(adjustPawnScore(board, pieceColor)))
                .orElse(Score.from(0)); // TODO: 계산실패시 적절한 처리 (Optional 관련)
    }

    private Score adjustPawnScore(Board board, PieceColor pieceColor) {
        int duplicatedPawnCount = Arrays.stream(XAxis.values())
                .mapToInt(xAxis -> board.getDuplicatedPawnCountByXAxis(pieceColor, xAxis))
                .sum();

        return Score.from(duplicatedPawnCount * 0.5);
    }

    public Score getScoreByPieceColor(PieceColor pieceColor) {
        return value.get(pieceColor);
    }
}
