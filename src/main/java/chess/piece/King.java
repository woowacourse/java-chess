package chess.piece;

import chess.Direction;
import chess.Player;
import chess.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.Direction.*;

public class King extends Piece {

    public King(Player player, String symbol) {
        super(player, symbol);
    }

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        List<Direction> directions = getDirection();
        List<Position> positions = directions.stream()
                .filter(source::isInBoardAfterMoved)
                .map(source::createMovablePosition)
                .filter(position -> !board.get(position).isSame(player))
                .collect(Collectors.toUnmodifiableList());
        return positions.contains(target);
    }

    @Override
    protected List<Direction> getDirection() {
        return List.of(EAST, WEST, SOUTH, NORTH, NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST);
    }
}
