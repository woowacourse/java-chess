package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private static final int MAX_MOVE_DIFFERENCE = 1;

    public King(Team team) {
        this(new Character(team, Kind.KING), false);
    }

    private King(Character character, boolean hasMoved) {
        super(character, hasMoved);
    }

    @Override
    public Piece move() {
        if (isMoved) {
            return this;
        }
        return new King(character, true);
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        return Math.abs(rowDifference) <= MAX_MOVE_DIFFERENCE && Math.abs(columnDifference) <= MAX_MOVE_DIFFERENCE;
    }

    @Override
    protected List<Position> findBetweenPositions(Position position, int rowDifference, int columnDifference) {
        return new ArrayList<>();
    }
}
