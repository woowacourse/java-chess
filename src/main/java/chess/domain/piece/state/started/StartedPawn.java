package chess.domain.piece.state.started;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.game.state.position.Direction;
import chess.domain.game.state.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.state.PieceState;

public class StartedPawn extends Started {

    private final Direction forward;

    public StartedPawn(Direction forward) {
        this.forward = forward;
    }

    @Override
    public List<Position> findMovablePositions(Position source, Map<Position, Piece> board) {
        List<Position> positions = new ArrayList<>();
        Position next = source.findNext(forward);

        if (canOnlyMoveByOneStep(board, source, forward)) {
            positions.add(next);
        }

        if (canOnlyMoveByTwoStep(source, board, forward)) {
            positions.add(next.findNext(forward));
        }

        List<Position> killablePositions = getKillablePositions(source, board);
        positions.addAll(killablePositions);
        return positions;
    }

    public List<Position> getKillablePositions(Position source, Map<Position, Piece> board) {
        return Direction.leftRight()
            .stream()
            .map(source::findNext)
            .map(position -> position.findNext(forward))
            .filter(direction -> canKill(board, source, direction))
            .collect(Collectors.toList());
    }

    @Override
    public PieceState updateState() {
        return new MovedPawn(forward);
    }

    private boolean canOnlyMoveByTwoStep(Position source, Map<Position, Piece> board, Direction direction) {
        Position next = source.findNext(direction);
        return canOnlyMoveByOneStep(board, source, forward) && canOnlyMoveByOneStep(board, next, forward);
    }
}
