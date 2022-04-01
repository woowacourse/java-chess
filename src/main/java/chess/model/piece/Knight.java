package chess.model.piece;

import chess.Board;
import chess.Direction;
import chess.model.square.Square;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "n";
    }

    @Override
    public boolean movable(Square source, Square target) {
        return getDirection().stream()
                .anyMatch(direction -> source.findLocation(direction, target));
    }

    @Override
    public boolean isObstacleOnRoute(Board board, Square source, Square target) {
        Piece targetPiece = board.get(target);
        return isNotAlly(targetPiece);
    }

    @Override
    List<Direction> getDirection() {
        return List.of(
                Direction.NNE,
                Direction.NNW,
                Direction.SSE,
                Direction.SSW,
                Direction.EEN,
                Direction.EES,
                Direction.WWN,
                Direction.WWS);
    }

    @Override
    public boolean isNotEmpty() {
        return true;
    }

    @Override
    public double getPoint() {
        return 2.5;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
