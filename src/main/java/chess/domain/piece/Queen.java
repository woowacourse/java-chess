package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class Queen extends Piece {
    public Queen(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.QUEEN);
    }

    @Override
    public Piece move(Position newPosition) {
        int differenceRow = position.differenceRow(newPosition);
        int differenceColumn = position.differenceColumn(newPosition);

        if (!position.equals(newPosition)
                && ((differenceRow == 0 || differenceColumn == 0)
                || Math.abs(differenceRow) == Math.abs(differenceColumn))) {
            return new Queen(newPosition, team);
        }

        throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
    }
}
