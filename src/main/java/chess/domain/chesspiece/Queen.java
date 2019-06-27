package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;
import chess.domain.chesspoint.RelativeChessPoint;
import chess.domain.util.Counter;

import java.util.List;

public class Queen implements BasicChessPiece {
    public static final double SCORE = 9.0;
    private static final List<ChessDirection> UNIT_DIRECTIONS = ChessDirection.everyDirection();
    private static final String NAME = "Q";
    private static Queen queen = null;

    private Queen() {
    }

    public static Queen getInstance() {
        if (queen == null) {
            queen = new Queen();
        }
        return queen;
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
