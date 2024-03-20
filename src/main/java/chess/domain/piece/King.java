package chess.domain.piece;

import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class King extends Piece {
    public King(Team team, boolean hasNotMoved) {
        super(team, hasNotMoved);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.KING);
    }

    @Override
    protected boolean isRelativelyMovable(int differenceRow, int differenceColumn) {
        return Math.abs(differenceRow) <= 1 && Math.abs(differenceColumn) <= 1;
    }
}
