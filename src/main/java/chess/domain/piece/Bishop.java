package chess.domain.piece;

import chess.domain.piece.abstractPiece.Piece;
import chess.domain.piece.abstractPiece.SlidingPiece;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class Bishop extends SlidingPiece {
    public Bishop(Team team) {
        this(new Character(team, Kind.BISHOP), false);
    }

    public Bishop(Team team, boolean isMoved) {
        this(new Character(team, Kind.BISHOP), isMoved);
    }

    private Bishop(Character character, boolean hasMoved) {
        super(character, hasMoved);
    }

    @Override
    public Piece move() {
        if (isMoved) {
            return this;
        }
        return new Bishop(character, true);
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        return Math.abs(rowDifference) == Math.abs(columnDifference);
    }
}
