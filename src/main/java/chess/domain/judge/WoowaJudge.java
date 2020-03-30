package chess.domain.judge;

import chess.domain.board.Board;
import chess.domain.piece.Side;
import chess.domain.piece.Type;

import java.util.Arrays;
import java.util.Optional;

public class WoowaJudge implements Judge {
    private static final double PAWN_SCORE_DEDUCTION_IF_ON_SAME_COLUMN = 0.5;

    private final Board board;

    public WoowaJudge(final Board board) {
        this.board = board;
    }

    @Override
    public double calculateScore(final Side side) {
        double sum = Arrays.stream(Type.values())
                .mapToDouble(type -> board.count(type, side) * type.getScore())
            .sum();
        return sum - board.countPawnsOnSameColumn(side) * PAWN_SCORE_DEDUCTION_IF_ON_SAME_COLUMN;
    }

    @Override
    public boolean isGameOver() {
        return isWhiteWinner() || isBlackWinner();
    }

    private boolean isBlackWinner() {
        return board.count(Type.KING, Side.WHITE) == 0;
    }

    private boolean isWhiteWinner() {
        return board.count(Type.KING, Side.BLACK) == 0;
    }

    @Override
    public Optional<Side> winner() {
        final Optional<Side> finalWinner = finalWinner();
        if (!finalWinner.isPresent()) {
            return onGoingWinner();
        }
        return Optional.empty();
    }

    public Optional<Side> finalWinner() {
        if (isGameOver() && isWhiteWinner()) {
            return Optional.of(Side.WHITE);
        }
        if (isGameOver() && isBlackWinner()) {
            return Optional.of(Side.BLACK);
        }
        return Optional.empty();
    }

    public Optional<Side> onGoingWinner() {
        if (calculateScore(Side.WHITE) > calculateScore(Side.BLACK)) {
            return Optional.of(Side.WHITE);
        }
        if (calculateScore(Side.BLACK) > calculateScore(Side.WHITE)) {
            return Optional.of(Side.BLACK);
        }
        return Optional.empty();
    }
}
