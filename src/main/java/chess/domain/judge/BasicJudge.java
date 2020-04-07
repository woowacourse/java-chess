package chess.domain.judge;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import chess.domain.board.Board;
import chess.domain.piece.Side;
import chess.domain.piece.Type;

public class BasicJudge implements Judge {
    private static final double PAWN_SCORE_DEDUCTION_IF_ON_SAME_COLUMN = 0.5;

    private final Board board;
    private Map<Type, Double> scoreboard = Arrays.stream(Type.values())
        .filter(Type::isNotEmpty)
        .collect(toMap(Function.identity(), Type::getScore));

    public BasicJudge(final Board board) {
        this.board = board;
    }

    @Override
    public double calculateScore(final Side side) {
        double sum = Arrays.stream(Type.values())
            .filter(Type::isNotEmpty)
            .mapToDouble(type -> board.count(type, side) * scoreboard.get(type))
            .sum();
        return sum - board.countPawnsOnSameColumn(side) * PAWN_SCORE_DEDUCTION_IF_ON_SAME_COLUMN;
    }

    @Override
    public boolean isDraw() {
        return winner() == Side.NONE;
    }

    @Override
    public boolean isGameOver() {
        return isWhiteWinner() || isBlackWinner();
    }

    private boolean isBlackWinner() {
        return board.isKingDead(Side.WHITE);
    }

    private boolean isWhiteWinner() {
        return board.isKingDead(Side.BLACK);
    }

    @Override
    public Side winner() {
        if (isGameOver()) {
            return finalWinner();
        }
        return onGoingWinner();
    }

    private Side finalWinner() {
        if (isGameOver() && isWhiteWinner()) {
            return Side.WHITE;
        }
        if (isGameOver() && isBlackWinner()) {
            return Side.BLACK;
        }
        return Side.NONE;
    }

    private Side onGoingWinner() {
        if (calculateScore(Side.WHITE) > calculateScore(Side.BLACK)) {
            return Side.WHITE;
        }
        if (calculateScore(Side.BLACK) > calculateScore(Side.WHITE)) {
            return Side.BLACK;
        }
        return Side.NONE;
    }
}
