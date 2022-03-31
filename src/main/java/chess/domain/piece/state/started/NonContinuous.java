package chess.domain.piece.state.started;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.game.state.position.Direction;
import chess.domain.game.state.position.Position;
import chess.domain.piece.Piece;

public abstract class NonContinuous extends Started {
    public List<Position> findMovablePositions(List<Direction> directions, Position source, Map<Position, Piece> board) {
        return directions
            .stream()
            .filter(direction -> canMoveOrKillByOneStep(board, source, direction))
            .map(source::findNext)
            .collect(Collectors.toList());
    }
}
