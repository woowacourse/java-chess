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
    public Piece move(Position newPosition) {
        int differenceRow = position.differenceRow(newPosition);
        int differenceColumn = position.differenceColumn(newPosition);

        if (differenceColumn != 0) {
            throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
        }
        if (team == Team.WHITE) {
            if (differenceRow == 1 || (position.isSameRow(2) && differenceRow == 2)) {
                return new Pawn(newPosition, team);
            }
        }
        if (team == Team.BLACK) {
            if (differenceRow == -1 || (position.isSameRow(7) && differenceRow == -2)) {
                return new Pawn(newPosition, team);
            }
        }

        throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
    }
}
