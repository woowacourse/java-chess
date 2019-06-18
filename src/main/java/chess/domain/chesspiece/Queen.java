package chess.domain.chesspiece;

import chess.domain.ChessPoint;
import chess.domain.RelativeChessPoint;

import java.util.Arrays;
import java.util.List;

public class Queen implements ChessPiece {
    private static final List<RelativeChessPoint> DIRECTIONS = Arrays.asList(
            RelativeChessPoint.of(1, 0), RelativeChessPoint.of(1, 1)
            , RelativeChessPoint.of(0, 1), RelativeChessPoint.of(-1, 1)
            , RelativeChessPoint.of(-1, 0), RelativeChessPoint.of(-1, -1)
            , RelativeChessPoint.of(0, -1), RelativeChessPoint.of(1, -1));

    @Override
    public boolean checkRule(ChessPoint source, ChessPoint target) {
        RelativeChessPoint direction = target.minus(source);
        RelativeChessPoint unitDirection = direction.toUnit();

        return DIRECTIONS.stream().anyMatch(d -> d.equals(unitDirection));
    }
}
