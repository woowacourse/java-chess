package chess.domain.chesspiece;

import chess.domain.ChessPoint;
import chess.domain.RelativeChessPoint;

import java.util.Arrays;
import java.util.List;

public class Rook implements ChessPiece {
    private static final List<RelativeChessPoint> DIRECTIONS = Arrays.asList(
            RelativeChessPoint.of(1, 0), RelativeChessPoint.of(0, 1)
            , RelativeChessPoint.of(-1, 0), RelativeChessPoint.of(0, -1));

    @Override
    public boolean checkRule(ChessPoint source, ChessPoint target) {
        RelativeChessPoint unitDirection = RelativeChessPoint.calculateUnitDirection(source, target);

        return DIRECTIONS.stream().anyMatch(d -> d.equals(unitDirection));
    }
}
