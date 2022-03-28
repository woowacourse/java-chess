package chess.domain.piece.state.started;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;

public class NonContinuous {

    public List<Position> findMovablePositions(Position source, ChessBoard board, List<Direction> directions) {
        return directions
            .stream()
            .filter(direction -> board.canMoveOrKillByOneStep(source, direction))
            .map(source::findNext)
            .collect(Collectors.toList());
    }
}
