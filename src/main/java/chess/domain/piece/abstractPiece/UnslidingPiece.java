package chess.domain.piece.abstractPiece;

import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.character.Character;
import java.util.ArrayList;
import java.util.List;

public abstract class UnslidingPiece extends NormalPiece {
    protected UnslidingPiece(Character character, boolean isMoved) {
        super(character, isMoved);
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        return false;
    }

    @Override
    public List<Position> findBetweenPositions(Movement movement) {
        return new ArrayList<>();
    }
}
