package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;
import chess.domain.chesspoint.RelativeChessPoint;

import java.util.Arrays;
import java.util.List;

public class King implements ChessPiece {
    private static King king = null;

    private King() {
    }

    public static King getInstance() {
        if (king == null) {
            king = new King();
        }
        return king;
    }

    private static final List<RelativeChessPoint> DIRECTIONS = Arrays.asList(
            RelativeChessPoint.of(1, 0), RelativeChessPoint.of(1, 1)
            , RelativeChessPoint.of(0, 1), RelativeChessPoint.of(-1, 1)
            , RelativeChessPoint.of(-1, 0), RelativeChessPoint.of(-1, -1)
            , RelativeChessPoint.of(0, -1), RelativeChessPoint.of(1, -1));

    @Override
    public boolean checkRule(ChessPoint source, ChessPoint target) {
        RelativeChessPoint direction = target.minus(source);
        return DIRECTIONS.stream().anyMatch(d -> d.equals(direction));
    }
}
