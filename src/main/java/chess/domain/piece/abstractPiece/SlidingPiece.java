package chess.domain.piece.abstractPiece;

import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.character.Character;
import java.util.ArrayList;
import java.util.List;

public abstract class SlidingPiece extends NormalPiece {
    protected SlidingPiece(Character character, boolean isMoved) {
        super(character, isMoved);
    }

    @Override
    public List<Position> findBetweenPositions(Movement movement) {
        List<Position> positions = new ArrayList<>();
        for (int i = MIN_MOVEMENT; i < movement.maxAbsoluteMoveDifference(); i++) {
            positions.add(movement.source().move(
                    movement.findRowDirection() * i, movement.findColumnDirection() * i));
        }
        return positions;
    }
}
