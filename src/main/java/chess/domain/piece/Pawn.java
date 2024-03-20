package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class Pawn extends Piece {

    public Pawn(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.PAWN);
    }

    @Override
    protected Piece movedPiece(Position newPosition) {
        return new Pawn(newPosition, team);
    }

    @Override
    protected boolean isMovable(int differenceRow, int differenceColumn) {
        if (differenceColumn != 0) {
            return false;
        }

        if (team == Team.WHITE) {
            return differenceRow == 1 || (position.isSameRow(2) && differenceRow == 2);
        }
        if (team == Team.BLACK) {
            return differenceRow == -1 || (position.isSameRow(7) && differenceRow == -2);
        }

        return false;
    }
}
