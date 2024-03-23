package chess.domain.piece;

import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class Rook extends Piece {
    public Rook(Team team) {
        this(new Character(team, Kind.ROOK), false);
    }

    private Rook(Character character, boolean hasMoved) {
        super(character, hasMoved);
    }

    @Override
    public Piece move() {
        if (hasMoved) {
            return this;
        }
        return new Rook(character, true);
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        return rowDifference == 0 || columnDifference == 0;
    }

}
