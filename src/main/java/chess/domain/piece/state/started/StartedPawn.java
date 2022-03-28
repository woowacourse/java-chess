package chess.domain.piece.state.started;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import chess.domain.game.state.ChessBoard;
import chess.domain.game.state.position.Direction;
import chess.domain.game.state.position.Position;
import chess.domain.piece.state.PieceState;

public class StartedPawn extends Started {

    private final Direction forward;

    public StartedPawn(Direction forward) {
        this.forward = forward;
    }

    @Override
    public List<Position> findMovablePositions(Position source, ChessBoard board) {
        List<Position> positions = new ArrayList<>();
        Position next = source.findNext(forward);

        if (board.canOnlyMoveByOneStep(source, forward)) {
            positions.add(next);
        }

        if (canOnlyMoveByTwoStep(source, board, forward)) {
            positions.add(next.findNext(forward));
        }

        List<Position> killablePositions = getKillablePositions(source, board);
        positions.addAll(killablePositions);
        return positions;
    }

    public List<Position> getKillablePositions(Position source, ChessBoard board) {
        return Direction.leftRight()
            .stream()
            .map(source::findNext)
            .map(position -> position.findNext(forward))
            .filter(direction -> board.canKill(source, direction))
            .collect(Collectors.toList());
    }

    @Override
    public PieceState updateState() {
        return new MovedPawn(forward);
    }

    private boolean canOnlyMoveByTwoStep(Position source, ChessBoard board, Direction direction) {
        Position next = source.findNext(direction);
        return board.canOnlyMoveByOneStep(source, forward) && board.canOnlyMoveByOneStep(next, forward);
    }
}
