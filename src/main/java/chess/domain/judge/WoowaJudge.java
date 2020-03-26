package chess.domain.judge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import chess.domain.board.Board;
import chess.domain.piece.Side;
import chess.domain.piece.Type;

public class WoowaJudge implements Judge {
    private static final double PAWN_SCORE_DEDUCTION_IF_ON_SAME_COLUMN = 0.5;

    private final Board board;
    private Map<Type, Double> scoreboard = new HashMap<Type, Double>() {{
        put(Type.KING, 0.0);
        put(Type.QUEEN, 9.0);
        put(Type.ROOK, 5.0);
        put(Type.BISHOP, 3.0);
        put(Type.KNIGHT, 2.5);
        put(Type.PAWN, 1.0);
    }};

    public WoowaJudge(final Board board) {
        this.board = board;
    }

    @Override
    public double calculateScore(final Side side) {
        return Arrays.stream(Type.values())
            .mapToDouble(type -> board.count(type, side) * scoreboard.get(type))
            .sum() - board.countPawnsOnSameColumn(side) * PAWN_SCORE_DEDUCTION_IF_ON_SAME_COLUMN;
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
        if (isGameOver() && isWhiteWinner()) {
            return Optional.of(Side.WHITE);
        }
        if (isGameOver() && isBlackWinner()) {
            return Optional.of(Side.BLACK);
        }
        if (calculateScore(Side.WHITE) > calculateScore(Side.BLACK)) {
            return Optional.of(Side.WHITE);
        }
        if (calculateScore(Side.BLACK) > calculateScore(Side.WHITE)) {
            return Optional.of(Side.BLACK);
        }
        return Optional.empty();
    }
}
