package chess.score;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ScoreManager {

    public final Score calculateScore(Map<Position, Piece> board, Color color) {
        return IntStream.rangeClosed(1, 8)
                .mapToObj(i -> calculateFileScore(board, color, i))
                .reduce(new Score(0), Score::add);
    }

    private Score calculateFileScore(Map<Position, Piece> board, Color color, int file) {
        List<Piece> pieces = findFilePieces(board, color, file);
        Score baseScore = calculateBaseScore(pieces);
        int pawnCount = calculatePawnCount(pieces);
        if (pawnCount >= 2) {
            return baseScore.subtract(pawnCount * 0.5);
        }
        return baseScore;
    }

    private List<Piece> findFilePieces(Map<Position, Piece> board, Color color, int file) {
        return IntStream.rangeClosed(1, 8)
                .mapToObj(i -> board.get(new Position(file, i)))
                .filter(piece -> piece.isSameColor(color))
                .toList();
    }

    private Score calculateBaseScore(List<Piece> pieces) {
        return pieces.stream()
                .map(Piece::score)
                .reduce(new Score(0), Score::add);
    }

    private int calculatePawnCount(List<Piece> pieces) {
        return (int) pieces.stream()
                .filter(Piece::isPawn)
                .count();
    }
}
