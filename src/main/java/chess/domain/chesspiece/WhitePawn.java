package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;
import chess.domain.chesspoint.RelativeChessPoint;
import chess.domain.util.Counter;

import java.util.Arrays;
import java.util.List;

public class WhitePawn implements ChessPiece {
    public static final double SCORE = 1.0;
    private static final List<RelativeChessPoint> UNIT_DIRECTION_EMPTY_ON_TARGET = Arrays.asList(
            RelativeChessPoint.of(1, 0));
    private static final List<RelativeChessPoint> UNIT_DIRECTIONS_OPPONENT_ON_TARGET = Arrays.asList(
            RelativeChessPoint.of(1, -1), RelativeChessPoint.of(1, 1));
    private static final String NAME = "P";
    private static WhitePawn whitePawn = null;

    private WhitePawn() {
    }

    public static WhitePawn getInstance() {
        if (whitePawn == null) {
            whitePawn = new WhitePawn();
        }
        return whitePawn;
    }

    @Override
    public boolean checkRule(ChessPoint source, ChessPoint target, boolean opponentPieceOnTarget) {
        RelativeChessPoint direction = target.minus(source);

        return opponentPieceOnTarget
                ? UNIT_DIRECTIONS_OPPONENT_ON_TARGET.stream().anyMatch(d -> d.equals(direction))
                : UNIT_DIRECTION_EMPTY_ON_TARGET.stream().anyMatch(d -> d.equals(direction));
    }

    @Override
    public Counter<Integer> countPiecesOnSameColumn(Counter<Integer> pawnCounter, int column) {
        pawnCounter.increase(column);
        return pawnCounter;
    }

    @Override
    public double getScore(Counter<Integer> pawnCounter, int column) {
        return (1 < pawnCounter.count(column)) ? SCORE / 2 : SCORE;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean hasName(String name) {
        return NAME.equals(name);
    }
}
