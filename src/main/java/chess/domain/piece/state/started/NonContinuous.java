package chess.domain.piece.state.started;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.game.state.ChessBoard;
import chess.domain.game.state.position.Direction;
import chess.domain.game.state.position.Position;

public class NonContinuous {

    public List<Position> findMovablePositions(List<Direction> directions, Position source, ChessBoard board) {
        return directions
            .stream()
            .filter(direction -> board.canMoveOrKillByOneStep(source, direction))
            .map(source::findNext)
            .collect(Collectors.toList());
    }
}
