package chess.domain.piece.state.started;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;
import chess.domain.piece.state.PieceState;

public class StartedPawn extends Started {

    private final Direction forward;

    public StartedPawn(Direction forward) {
        this.forward = forward;
    }

    @Override
    public List<Position> getMovablePositions(Position source, ChessBoard board) {
        List<Position> positions = new ArrayList<>();
        Position next = source.getNext(forward);

        if (board.canMoveOneStep(source, forward)) {
            positions.add(next);
        }

        if (canMoveTwoStep(source, board, forward)) {
            positions.add(next.getNext(forward));
        }

        positions.addAll(getKillablePositions(source, board));
        return positions;
    }

    @Override
    public PieceState updateState() {
        return new MovedPawn(forward);
    }

    private boolean canMoveTwoStep(Position source, ChessBoard board, Direction direction) {
        Position next = source.getNext(direction);
        return board.canMoveOneStep(source, forward) && board.canMoveOneStep(next, forward);
    }

    public List<Position> getKillablePositions(Position source, ChessBoard board) {
        return Direction.leftRight()
            .stream()
            .map(source::getNext)
            .map(position -> position.getNext(forward))
            .filter(direction -> board.canKill(source, direction))
            .collect(Collectors.toList());
    }
}
