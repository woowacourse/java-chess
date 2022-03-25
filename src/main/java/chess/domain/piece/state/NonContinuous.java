package chess.domain.piece.state;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.ChessBoard;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;

public class NonContinuous {

    public List<Position> getMovablePositions(Position source, ChessBoard board, List<Direction> directions) {
        return directions
            .stream()
            .filter(direction -> board.canMoveOneStep(source, direction))
            .map(source::getNext)
            .collect(Collectors.toList());
    }
}
