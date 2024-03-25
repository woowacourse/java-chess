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
        this(team, false);
    }

    private King(Team team, boolean hasMoved) {
        super(team, hasMoved);
    }

    @Override
    public Piece move() {
        if (hasMoved) {
            return this;
        }
        return new King(team, true);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.KING);
    }

    @Override
    protected List<Position> findBetweenPositions(Position position, int rowDifference, int columnDifference) {
        return new ArrayList<>();
    }

    @Override
    protected boolean isAttackable(int rowDifference, int columnDifference) {
        return isMovable(rowDifference, columnDifference);
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        return Math.abs(rowDifference) <= MAX_MOVE_DIFFERENCE && Math.abs(columnDifference) <= MAX_MOVE_DIFFERENCE;
    }
}
