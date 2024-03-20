package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Team team, boolean hasNotMoved) {
        super(team, hasNotMoved);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.PAWN);
    }

    @Override
    protected boolean isRelativelyMovable(int rowDifference, int columnDifference) {
        if (columnDifference != 0) {
            return false;
        }

        if (team == Team.WHITE) {
            return rowDifference == 1 || (hasNotMoved && rowDifference == 2);
        }
        if (team == Team.BLACK) {
            return rowDifference == -1 || (hasNotMoved && rowDifference == -2);
        }

        return false;
    }

    @Override
    protected List<Position> betweenPositions(Position position, int rowDifference, int columnDifference) {
        List<Position> positions = new ArrayList<>();
        if (hasNotMoved && Math.abs(rowDifference) == 2) {
            positions.add(position.move(rowDifference / 2, 0));
            return positions;
        }
        return new ArrayList<>();
    }
}
