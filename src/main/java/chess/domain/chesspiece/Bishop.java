package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;
import chess.domain.chesspoint.RelativeChessPoint;

import java.util.Arrays;
import java.util.List;

public class Bishop implements ChessPiece {
    private static Bishop bishop = null;

    private Bishop() {
    }

    public static Bishop getInstance() {
        if (bishop == null) {
            bishop = new Bishop();
        }
        return bishop;
    }

    private static final List<RelativeChessPoint> DIRECTIONS = Arrays.asList(
            RelativeChessPoint.of(1, 1), RelativeChessPoint.of(-1, 1)
            , RelativeChessPoint.of(-1, -1), RelativeChessPoint.of(1, -1));

    @Override
    public boolean checkRule(ChessPoint source, ChessPoint target) {
        RelativeChessPoint unitDirection = RelativeChessPoint.calculateUnitDirection(source, target);

        return DIRECTIONS.stream().anyMatch(d -> d.equals(unitDirection));
    }
}
