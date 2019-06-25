package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;
import chess.domain.chesspoint.RelativeChessPoint;
import chess.domain.util.Counter;

import java.util.Arrays;
import java.util.List;

public class Bishop implements ChessPiece {
    public static final double SCORE = 3.0;
    private static final List<RelativeChessPoint> UNIT_DIRECTIONS = Arrays.asList(
            RelativeChessPoint.of(1, 1), RelativeChessPoint.of(-1, 1)
            , RelativeChessPoint.of(-1, -1), RelativeChessPoint.of(1, -1));
    private static final String NAME = "B";
    private static Bishop bishop = null;

    private Bishop() {
    }

    public static Bishop getInstance() {
        if (bishop == null) {
            bishop = new Bishop();
        }
        return bishop;
    }

    @Override
    public boolean checkRule(ChessPoint source, ChessPoint target, boolean opponentPieceOnTarget) {
        RelativeChessPoint unitDirection = RelativeChessPoint.calculateUnitDirection(source, target);

        return UNIT_DIRECTIONS.stream().anyMatch(d -> d.equals(unitDirection));
    }

    @Override
    public Counter<Integer> countPiecesOnSameColumn(Counter<Integer> pawnCounter, int column) {
        return pawnCounter;
    }

    @Override
    public double getScore(Counter<Integer> pawnCounter, int column) {
        return SCORE;
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
