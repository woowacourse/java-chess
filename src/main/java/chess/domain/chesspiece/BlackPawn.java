package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;
import chess.domain.chesspoint.RelativeChessPoint;

import java.util.Arrays;
import java.util.List;

public class BlackPawn implements ChessPiece {
    private static final List<RelativeChessPoint> UNIT_DIRECTION_EMPTY_ON_TARGET = Arrays.asList(
            RelativeChessPoint.of(-1, 0));
    private static final List<RelativeChessPoint> UNIT_DIRECTIONS_OPPONENT_ON_TARGET = Arrays.asList(
            RelativeChessPoint.of(-1, -1), RelativeChessPoint.of(-1, 1));
    private static BlackPawn blackPawn = null;

    private BlackPawn() {
    }

    public static BlackPawn getInstance() {
        if (blackPawn == null) {
            blackPawn = new BlackPawn();
        }
        return blackPawn;
    }

    @Override
    public boolean checkRule(ChessPoint source, ChessPoint target, boolean opponentPieceOnTarget) {
        RelativeChessPoint direction = target.minus(source);

        return opponentPieceOnTarget
                ? UNIT_DIRECTIONS_OPPONENT_ON_TARGET.stream().anyMatch(d -> d.equals(direction))
                : UNIT_DIRECTION_EMPTY_ON_TARGET.stream().anyMatch(d -> d.equals(direction));
    }
}
