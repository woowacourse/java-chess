package chess.domain.judge;

import chess.domain.board.Board;
import chess.domain.piece.Side;
import chess.domain.piece.Type;

import java.sql.SQLException;
import java.util.Optional;

public class WoowaJudge implements Judge {
    private static final double PAWN_SCORE_DEDUCTION_IF_ON_SAME_COLUMN = 0.5;

    private final Board board;

    public WoowaJudge(final Board board) {
        this.board = board;
    }

    @Override
    public double calculateScore(final Side side) throws SQLException {
        double defaultSum = 0;
        for (Type type : Type.values()) {
            defaultSum += board.count(type, side) * type.getScore();
        }

        return defaultSum - board.countPawnsOnSameColumn(side) * PAWN_SCORE_DEDUCTION_IF_ON_SAME_COLUMN;
    }

    @Override
    public boolean isGameOver() throws SQLException {
        return isWhiteWinner() || isBlackWinner();
    }

    private boolean isBlackWinner() throws SQLException {
        return board.count(Type.KING, Side.WHITE) == 0;
    }

    private boolean isWhiteWinner() throws SQLException {
        return board.count(Type.KING, Side.BLACK) == 0;
    }

    @Override
    public Optional<Side> winner() throws SQLException {
        final Optional<Side> finalWinner = finalWinner();
        if (!finalWinner.isPresent()) {
            return onGoingWinner();
        }
        return Optional.empty();
    }

    public Optional<Side> finalWinner() throws SQLException {
        if (isGameOver() && isWhiteWinner()) {
            return Optional.of(Side.WHITE);
        }
        if (isGameOver() && isBlackWinner()) {
            return Optional.of(Side.BLACK);
        }
        return Optional.empty();
    }

    public Optional<Side> onGoingWinner() throws SQLException {
        if (calculateScore(Side.WHITE) > calculateScore(Side.BLACK)) {
            return Optional.of(Side.WHITE);
        }
        if (calculateScore(Side.BLACK) > calculateScore(Side.WHITE)) {
            return Optional.of(Side.BLACK);
        }
        return Optional.empty();
    }
}
