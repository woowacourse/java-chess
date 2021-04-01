package chess.domain.moveStrategy;

import chess.domain.game.Board;
import chess.domain.location.Position;
import chess.domain.location.Vector;
import chess.domain.piece.Color;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultipleMove implements MoveStrategy {
    private Color color;
    private List<Vector> directions;

    public MultipleMove(Color color, List<Vector> directions) {
        this.color = color;
        this.directions = directions;
    }

    @Override
    public List<Position> movablePositions(Position from, Board board) {
        return directions.stream()
                  .flatMap(direction -> movablePositionsOf(direction, from, board))
                  .collect(Collectors.toList());
    }

    private Stream<Position> movablePositionsOf(Vector direction, Position from, Board board) {
        List<Position> positions = new ArrayList<>();
        Position temp = from;
        while (board.at(temp).isEmpty() && temp.canMove(direction)) {
            positions.add(temp);
            temp = temp.move(direction);
        }
        if (!board.at(temp)
                  .isSameColor(color)) {
            positions.add(temp);
        }
        return positions.stream();
    }
}
