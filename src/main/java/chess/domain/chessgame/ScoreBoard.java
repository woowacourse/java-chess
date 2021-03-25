package chess.domain.chessgame;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.SquareState;
import chess.domain.board.Team;
import java.util.Arrays;

public class ScoreBoard {

    private static final double HALF_PAWN_SCORE = 0.5;

    private final Board board;

    public ScoreBoard(Board board) {
        this.board = board;
    }

    public double score(Team team) {
        return scoreSum(team) - HALF_PAWN_SCORE * pawnCountInSameColumn(team);
    }

    private long pawnCountInSameColumn(Team team) {
        return Arrays.stream(Column.values()).mapToLong(column ->
            board.pawnCountInColumn(team, column))
            .filter(count -> count >= 2)
            .sum();
    }

    private double scoreSum(Team team) {
        return board.AllSquaresFrom(team).stream()
            .mapToDouble(SquareState::score)
            .sum();
    }
}
