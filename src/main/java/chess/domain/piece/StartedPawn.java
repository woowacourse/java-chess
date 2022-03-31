package chess.domain.piece;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StartedPawn extends Piece{

    private static final String NAME = "p";
    private static final double SCORE = 1.0;

    private final Direction forward;

    public StartedPawn(Color color) {
        super(color, NAME, SCORE);
        forward = color.getForwardDirection();
    }

    @Override
    public List<Position> getMovablePaths(Position source, ChessBoard board) {
        List<Position> positions = new ArrayList<>();
        Position next = source.getNext(forward);

        if (board.canOnlyMoveByOneStep(source, forward)) {
            positions.add(next);
        }

        if (canOnlyMoveByTwoStep(source, board, forward)) {
            positions.add(next.getNext(forward));
        }

        List<Position> killablePositions = getKillablePositions(source, board);
        positions.addAll(killablePositions);
        return positions;
    }

    private boolean canOnlyMoveByTwoStep(Position source, ChessBoard board, Direction direction) {
        Position next = source.getNext(direction);
        return board.canOnlyMoveByOneStep(source, forward) && board.canOnlyMoveByOneStep(next, forward);
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
