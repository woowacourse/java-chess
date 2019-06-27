package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;
import chess.domain.chesspoint.RelativeChessPoint;
import chess.domain.util.Counter;

import java.util.List;

public class Rook implements BasicChessPiece {
    public static final double SCORE = 5.0;
    private static final List<ChessDirection> UNIT_DIRECTIONS = ChessDirection.linearDirection();
    private static final String NAME = "R";
    private static Rook rook = null;

    private Rook() {
    }

    public static Rook getInstance() {
        if (rook == null) {
            rook = new Rook();
        }
        return rook;
    }

    @Override
    public boolean checkRule(ChessPoint source, ChessPoint target) {
        RelativeChessPoint unitDirection = RelativeChessPoint.calculateUnitDirection(source, target);

        return UNIT_DIRECTIONS.stream().anyMatch(d -> d.match(unitDirection));
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
