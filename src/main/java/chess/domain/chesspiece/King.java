package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;
import chess.domain.chesspoint.RelativeChessPoint;

import java.util.Arrays;
import java.util.List;

public class King implements ChessPiece {
    private static final List<RelativeChessPoint> UNIT_DIRECTIONS = Arrays.asList(
            RelativeChessPoint.of(1, 0), RelativeChessPoint.of(1, 1)
            , RelativeChessPoint.of(0, 1), RelativeChessPoint.of(-1, 1)
            , RelativeChessPoint.of(-1, 0), RelativeChessPoint.of(-1, -1)
            , RelativeChessPoint.of(0, -1), RelativeChessPoint.of(1, -1));
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
    public boolean checkRule(ChessPoint source, ChessPoint target, boolean opponentPieceOnTarget) {
        RelativeChessPoint direction = target.minus(source);
        return UNIT_DIRECTIONS.stream().anyMatch(d -> d.equals(direction));
    }
}
