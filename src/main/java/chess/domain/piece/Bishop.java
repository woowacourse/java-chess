package chess.domain.piece;

import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class Bishop extends Piece {
    public Bishop(Team team, boolean hasNotMoved) {
        super(team, hasNotMoved);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.BISHOP);
    }

    @Override
    protected boolean isRelativelyMovable(int differenceRow, int differenceColumn) {
        return Math.abs(differenceRow) == Math.abs(differenceColumn);
    }
}
