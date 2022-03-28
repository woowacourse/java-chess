package chess.piece;

import chess.Direction;
import chess.Player;
import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.Direction.*;
import static chess.Player.BLACK;
import static chess.Player.NONE;

public class Pawn extends Piece {

    private static final double SCORE = 1;

    public Pawn(Player player, String symbol) {
        super(player, symbol);
    }

    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        List<Direction> directions = getDirection();
        List<Position> positions = new ArrayList<>();
        positions.addAll(findCatchPositions(source, board, directions.subList(0, 2)));
        positions.addAll(findMovedOnePosition(source, board, directions.get(2)));
        positions.addAll(findMovedTwoPosition(source, board, directions.subList(2, 4)));
        return positions.contains(target);
    }

    private List<Position> findCatchPositions(Position source, Map<Position, Piece> board, List<Direction> directions) {
        return directions.stream()
                .filter(source::isInBoardAfterMoved)
                .map(source::createMovablePosition)
                .filter(position -> board.get(position).isOpponent(player))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Position> findMovedOnePosition(Position source, Map<Position, Piece> board, Direction direction) {
        List<Direction> directions = List.of(direction);
        return directions.stream()
                .filter(source::isInBoardAfterMoved)
                .map(source::createMovablePosition)
                .filter(position -> board.get(position).isSame(NONE))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Position> findMovedTwoPosition(Position source, Map<Position, Piece> board, List<Direction> directions) {
        if (!source.isInBoardAfterMoved(directions.get(1))) {
            return new ArrayList<>();
        }
        Position position = source.createMovablePosition(directions.get(0));
        Position position2 = source.createMovablePosition(directions.get(1));
        if (source.isStart() && board.get(position).isSame(NONE) && board.get(position2).isSame(NONE)) {
            return List.of(position2);
        }
        return new ArrayList<>();
    }

    @Override
    protected List<Direction> getDirection() {
        if (player.equals(BLACK)) {
            return List.of(SOUTHEAST, SOUTHWEST, SOUTH, SS);
        }
        return List.of(NORTHEAST, NORTHWEST, NORTH, NN);
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
