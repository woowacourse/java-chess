package chess.domain.piece;

import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class Pawn extends Piece {
    public Pawn(Team team, boolean hasNotMoved) {
        super(team, hasNotMoved);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.PAWN);
    }

    @Override
    protected boolean isRelativelyMovable(int differenceRow, int differenceColumn) {
        if (differenceColumn != 0) {
            return false;
        }

        if (team == Team.WHITE) {
            return differenceRow == 1 || (hasNotMoved && differenceRow == 2);
        }
        if (team == Team.BLACK) {
            return differenceRow == -1 || (hasNotMoved && differenceRow == -2);
        }

        return false;
    }
}
