package chess.domain.piece;

import chess.domain.piece.abstractPiece.Piece;
import chess.domain.piece.abstractPiece.UnslidingPiece;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class King extends UnslidingPiece {
    private static final int MAX_MOVE_DIFFERENCE = 1;

    public King(Team team) {
        this(new Character(team, Kind.KING), false);
    }

    public King(Team team, boolean isMoved) {
        this(new Character(team, Kind.KING), isMoved);
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
}
