package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public abstract class GeneralPiece extends Piece {
    private final List<Direction> possibleDirections;

    public GeneralPiece(final Team team, final String initialName) {
        super(team, initialName);
        this.possibleDirections = possibleDirections();
    }

    @Override
    final public boolean movable(final Position source, final Position target, final Piece piece) {
        return isPossibleDirection(source, target) && !friendly(piece.team());
    }

    private boolean isPossibleDirection(final Position source, final Position target) {
        return possibleDirections.stream()
                .anyMatch(possibleDirection -> possibleDirection.isSameDirection(target.subtract(source)));
    }

    protected abstract List<Direction> possibleDirections();
}
