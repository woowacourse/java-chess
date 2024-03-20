package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(Team team, boolean hasNotMoved) {
        super(team, hasNotMoved);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.KING);
    }

    @Override
    protected boolean isRelativelyMovable(int rowDifference, int columnDifference) {
        return Math.abs(rowDifference) <= 1 && Math.abs(columnDifference) <= 1;
    }

    @Override
    protected List<Position> betweenPositions(Position position, int rowDifference, int columnDifference) {
        return new ArrayList<>();
    }

    @Override
    public Piece move() {
        if (hasNotMoved) {
            return new King(team, false);
        }
        return this;
    }
}
