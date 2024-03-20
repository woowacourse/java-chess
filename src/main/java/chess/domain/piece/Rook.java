package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class Rook extends Piece {
    public Rook(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.ROOK);
    }


    @Override
    protected Piece movedPiece(Position newPosition) {
        return new Rook(newPosition, team);
    }

    @Override
    protected boolean isMovable(int differenceRow, int differenceColumn) {
        return differenceRow == 0 || differenceColumn == 0;
    }

}
