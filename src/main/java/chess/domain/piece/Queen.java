package chess.domain.piece;

import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class Queen extends Piece {
    public Queen(Team team, boolean hasNotMoved) {
        super(team, hasNotMoved);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.QUEEN);
    }

    @Override
    protected boolean isRelativelyMovable(int differenceRow, int differenceColumn) {
        return (differenceRow == 0 || differenceColumn == 0)
                || Math.abs(differenceRow) == Math.abs(differenceColumn);
    }
}
