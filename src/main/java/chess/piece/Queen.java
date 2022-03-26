package chess.piece;

import chess.Direction;
import chess.Player;
import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static chess.Direction.*;
import static chess.Direction.NORTH;

public class Queen extends Piece {

    public Queen(Player player, String symbol) {
        super(player, symbol);
    }

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        List<Position> positions = new ArrayList<>();
        for (Direction direction : getDirection()) {
            positions.addAll(createMovablePositions(direction, source, board));
        }
        return positions.contains(target);
    }

    private List<Position> createMovablePositions(Direction direction, Position source, Map<Position, Piece> board) {
        List<Position> positions = new ArrayList<>();
        if (!source.isInBoardAfterMoved(direction)) {
            return positions;
        }
        Position nextPosition = source.createMovablePosition(direction);
        while (board.get(nextPosition).isSame(Player.NONE) && nextPosition.isInBoardAfterMoved(direction)) {
            positions.add(nextPosition);
            nextPosition = nextPosition.createMovablePosition(direction);
        }
        if (!board.get(nextPosition).isSame(this.player)) {
            positions.add(nextPosition);
        }
        return positions;
    }

    @Override
    protected List<Direction> getDirection() {
        return List.of(EAST, WEST, SOUTH, NORTH, SOUTHEAST, SOUTHWEST, NORTHEAST, NORTHWEST);
    }
}
