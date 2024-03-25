package chess.domain.piece;

import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class Bishop extends Piece {
    public Bishop(Team team) {
        this(new Character(team, Kind.BISHOP), false);
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
