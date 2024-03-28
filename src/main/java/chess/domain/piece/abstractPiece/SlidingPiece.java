package chess.domain.piece.abstractPiece;

import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.character.Character;
import java.util.HashSet;
import java.util.Set;

public abstract class SlidingPiece extends NormalPiece {
    protected SlidingPiece(Character character, boolean isMoved) {
        super(character, isMoved);
    }

    @Override
    public Set<Position> findBetweenPositions(Movement movement) {
        Set<Position> positions = new HashSet<>();
        for (int i = MIN_MOVEMENT; i < movement.maxAbsoluteMoveDifference(); i++) {
            positions.add(movement.source().move(
                    movement.findRowDirection() * i, movement.findColumnDirection() * i));
        }
        return positions;
    }
}
