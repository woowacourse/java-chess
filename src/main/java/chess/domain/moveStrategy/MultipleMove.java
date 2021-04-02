package chess.domain.moveStrategy;

import chess.domain.location.Position;
import chess.domain.location.Vector;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultipleMove implements MoveStrategy {
    private final Color color;
    private final List<Vector> directions;

    public MultipleMove(Color color, List<Vector> directions) {
        this.color = color;
        this.directions = directions;
    }

    @Override
    public List<Position> movablePositions(Position from, Map<Position, Piece> pieceByPosition) {
        return directions.stream()
                         .flatMap(direction -> movablePositionsOf(direction, from, pieceByPosition))
                         .collect(Collectors.toList());
    }

    private Stream<Position> movablePositionsOf(Vector direction, Position from, Map<Position, Piece> pieceByPosition) {
        List<Position> positions = new ArrayList<>();
        Position temp = from;
        while (pieceByPosition.get(temp).isEmpty() && temp.canMove(direction)) {
            temp = temp.move(direction);
            positions.add(temp);
        }

        if (pieceByPosition.get(temp)
                            .isOpposite(color)) {
            positions.add(temp);
        }
        return positions.stream();
    }
}
