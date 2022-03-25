package chess.domain.piece.state;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.ChessBoard;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;

public class StartedKing extends Started{

    @Override
    public List<Position> getMovablePositions(Position source, ChessBoard board) {
        return Direction.queen()
            .stream()
            .filter(direction -> board.canMoveOneStep(source, direction))
            .map(source::getNext)
            .collect(Collectors.toList());
    }

    @Override
    public State updateState() {
        return new StartedKing();
    }
}
