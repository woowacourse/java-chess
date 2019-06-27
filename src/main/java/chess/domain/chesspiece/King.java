package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;
import chess.domain.chesspoint.RelativeChessPoint;
import chess.domain.util.Counter;

import java.util.Arrays;
import java.util.List;

public class King implements BasicChessPiece {
    public static final double SCORE = 0.0;
    private static final List<RelativeChessPoint> UNIT_DIRECTIONS = Arrays.asList(
            RelativeChessPoint.of(1, 0), RelativeChessPoint.of(1, 1)
            , RelativeChessPoint.of(0, 1), RelativeChessPoint.of(-1, 1)
            , RelativeChessPoint.of(-1, 0), RelativeChessPoint.of(-1, -1)
            , RelativeChessPoint.of(0, -1), RelativeChessPoint.of(1, -1));
    private static final String NAME = "K";
    private static King king = null;

    private King() {
    }

    public static King getInstance() {
        if (king == null) {
            king = new King();
        }
        return king;
    }

    @Override
    public boolean checkRule(ChessPoint source, ChessPoint target) {
        RelativeChessPoint direction = target.minus(source);
        return UNIT_DIRECTIONS.stream().anyMatch(d -> d.equals(direction));
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

    @Override
    public boolean isPawn() {
        return false;
    }
}
