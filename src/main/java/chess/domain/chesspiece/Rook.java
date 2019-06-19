package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;
import chess.domain.chesspoint.RelativeChessPoint;

import java.util.Arrays;
import java.util.List;

public class Rook implements ChessPiece {
    private static final List<RelativeChessPoint> UNIT_DIRECTIONS = Arrays.asList(
            RelativeChessPoint.of(1, 0), RelativeChessPoint.of(0, 1)
            , RelativeChessPoint.of(-1, 0), RelativeChessPoint.of(0, -1));
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
    public boolean checkRule(ChessPoint source, ChessPoint target, boolean opponentPieceOnTarget) {
        RelativeChessPoint unitDirection = RelativeChessPoint.calculateUnitDirection(source, target);

        return UNIT_DIRECTIONS.stream().anyMatch(d -> d.equals(unitDirection));
    }
}
