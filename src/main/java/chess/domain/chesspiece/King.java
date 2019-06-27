package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;
import chess.domain.chesspoint.RelativeChessPoint;
import chess.domain.util.Counter;

import java.util.List;

public class King implements BasicChessPiece {
    public static final double SCORE = 0.0;
    private static final List<ChessDirection> UNIT_DIRECTIONS = ChessDirection.everyDirection();
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
        return UNIT_DIRECTIONS.stream().anyMatch(d -> d.match(direction));
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
