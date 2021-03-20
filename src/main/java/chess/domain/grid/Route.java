package chess.domain.grid;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Route {
    private final Lines lines;
    public Route(final Lines lines) {
        this.lines = lines;
    }

    public List<Position> route(final Piece sourcePiece, final Direction direction, final int stepRange) {
        List<Position> positions = new ArrayList<>();
        Position sourcePosition = sourcePiece.position();

        for (int i = 1; i <= stepRange; i++) {
            Position movedPosition = sourcePosition.next(direction.getXDegree() * i, direction.getYDegree() * i);
            if (!movedPosition.isInGridRange()) {
                break;
            }
            positions.add(movedPosition);
            if (!lines.isEmpty(movedPosition)) {
                break;
            }
        }
        return positions;
    }

    public void validateGeneralSteps(final Piece sourcePiece, final Piece targetPiece) {
        List<Position> movablePositions = new ArrayList<>();
        for (Direction direction : sourcePiece.directions()) {
            movablePositions.addAll(route(sourcePiece, direction, sourcePiece.stepRange()));
        }
        targetPiece.validateTargetInMovablePositions(movablePositions);
    }

    public void validatePawnSteps(final Pawn sourcePiece, final Piece targetPiece) {
        List<Position> movablePositions = new ArrayList<>();
        for (Direction direction : sourcePiece.directions()) {
            movablePositions.addAll(route(sourcePiece, direction, sourcePiece.stepRange()));
        }
        for (Direction direction : sourcePiece.twoStepDirections()) {
            movablePositions.addAll(route(sourcePiece, direction, sourcePiece.twoStepRange()));
        }
        movablePositions = movablePositions.stream()
                .distinct()
                .collect(Collectors.toList());

        sourcePiece.validatePawnMove(targetPiece);
        targetPiece.validateTargetInMovablePositions(movablePositions);
    }
}
