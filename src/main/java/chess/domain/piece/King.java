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
    public Piece move(Position position) {
        return null;
    }
}
