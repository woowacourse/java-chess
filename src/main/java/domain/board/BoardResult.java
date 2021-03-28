package domain.board;

import static domain.board.Board.CHESS_BOARD_SIZE;
import static domain.board.Board.PAWN_ALLY_COUNT_CONDITION;

import domain.piece.Piece;
import domain.position.Column;
import domain.position.Position;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class BoardResult {

    private final Map<Position, Piece> board;

    public BoardResult(Map<Position, Piece> board) {
        this.board = board;
    }

    public Map<Position, Piece> pieces(boolean isBlack) {
        return board.entrySet()
            .stream()
            .filter(entry -> entry.getValue().isNotEmpty() && entry.getValue().isBlack() == isBlack)
            .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    public Score piecesScore(boolean isBlack) {
        Score score = new Score();
        Map<Position, Piece> pieces = pieces(isBlack);

        for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
            score = score.sum(entry.getValue().getScore());
        }
        score = minusPawnScore(score, isBlack);
        return score;
    }

    private Score minusPawnScore(Score score, boolean isBlack) {
        int minusCount = 0;
        for (int row = 0; row < CHESS_BOARD_SIZE; row++) {
            minusCount += rowAllyPawnCount(row, isBlack);
        }
        return score.minusPawnScore(minusCount);
    }

    private int rowAllyPawnCount(int column, boolean isBlack) {
        int count = (int) pieces(isBlack).entrySet()
            .stream()
            .filter(entry -> entry.getValue().isPawn()
                && entry.getKey().isColumnEquals(new Column(column)))
            .count();

        if (count >= PAWN_ALLY_COUNT_CONDITION) {
            return count;
        }
        return 0;
    }

    public boolean isKingAlive(boolean isBlack){
        return pieces(isBlack).entrySet().stream()
            .anyMatch(entry -> entry.getValue().isKing());
    }

}
