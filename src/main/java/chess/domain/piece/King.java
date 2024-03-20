package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class King extends Piece {
    public King(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.KING);
    }

    @Override
    public Piece move(Position newPosition) {
        int differenceRow = position.differenceRow(newPosition);
        int differenceColumn = position.differenceColumn(newPosition);

        if (!position.equals(newPosition) && (Math.abs(differenceRow) <= 1 && Math.abs(differenceColumn) <= 1)) {
            return new King(newPosition, team);
        }

        throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
    }
}
