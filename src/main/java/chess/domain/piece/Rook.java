package chess.domain.piece;

import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class Rook extends Piece {
    public Rook(Team team) {
        this(team, false);
    }

    private Rook(Team team, boolean hasMoved) {
        super(team, hasMoved);
    }

    @Override
    public Piece move() {
        if (hasMoved) {
            return this;
        }
        return new Rook(team, true);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.ROOK);
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        return rowDifference == 0 || columnDifference == 0;
    }

}
