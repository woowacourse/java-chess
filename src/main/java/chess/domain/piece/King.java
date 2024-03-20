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
    protected Piece movedPiece(Position newPosition) {
        return new King(newPosition, team);
    }

    @Override
    protected boolean isMovable(int differenceRow, int differenceColumn) {
        return Math.abs(differenceRow) <= 1 && Math.abs(differenceColumn) <= 1;
    }
}
