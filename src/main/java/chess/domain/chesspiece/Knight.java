package chess.domain.chesspiece;

import chess.domain.ChessPoint;
import chess.domain.RelativeChessPoint;

import java.util.Arrays;
import java.util.List;

public class Knight implements ChessPiece {
    private static final List<RelativeChessPoint> DIRECTIONS = Arrays.asList(
            RelativeChessPoint.of(2, 1), RelativeChessPoint.of(1, 2)
            , RelativeChessPoint.of(-1, 2), RelativeChessPoint.of(-2, 1)
            , RelativeChessPoint.of(-2, -1), RelativeChessPoint.of(-1, -2)
            , RelativeChessPoint.of(1, -2), RelativeChessPoint.of(2, -1));

    @Override
    public boolean checkRule(ChessPoint source, ChessPoint target) {
        RelativeChessPoint direction = target.minus(source);
        return DIRECTIONS.stream().anyMatch(d -> d.equals(direction));
    }
}
