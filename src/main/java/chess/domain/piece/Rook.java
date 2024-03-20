package chess.domain.piece;

import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class Rook extends Piece {
    public Rook(Team team, boolean hasNotMoved) {
        super(team, hasNotMoved);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.ROOK);
    }

    @Override
    protected boolean isRelativelyMovable(int differenceRow, int differenceColumn) {
        return differenceRow == 0 || differenceColumn == 0;
    }

}
