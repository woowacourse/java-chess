package chess.domain.piece;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.List;
import java.util.Optional;

public class Queen extends Piece {

    public Queen(final File file, final Rank rank, final Color color) {
        super(file, rank, color);
    }

    @Override
    protected boolean canMove(final Position targetPosition) {
        return position.isInCrossPosition(targetPosition) || position.isInDiagonalPosition(targetPosition);
    }

    @Override
    public Piece move(final Position targetPosition, final Optional<Piece> pieceContainerOfTargetPosition) {
        pieceContainerOfTargetPosition.ifPresent(this::validateCachingSameColor);
        return new Queen(targetPosition.getFile(), targetPosition.getRank(), color);
    }

    @Override
    public List<Position> getPassingPositions(final Position targetPosition) {
        validateSamePosition(targetPosition);
        validateDestination(targetPosition);
        return position.findPassingPositions(targetPosition);
    }
}
