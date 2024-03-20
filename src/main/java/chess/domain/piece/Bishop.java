package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Team team, boolean hasNotMoved) {
        super(team, hasNotMoved);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.BISHOP);
    }

    @Override
    protected boolean isRelativelyMovable(int rowDifference, int columnDifference) {
        return Math.abs(rowDifference) == Math.abs(columnDifference);
    }

    @Override
    protected List<Position> betweenPositions(Position position, int rowDifference, int columnDifference) {
        List<Position> positions = new ArrayList<>();

        int absoluteDifference = Math.abs(rowDifference);
        int rowSign = rowDifference / absoluteDifference;
        int columnSign = columnDifference / absoluteDifference;

        for (int movement = 1; movement < absoluteDifference; movement++) {
            positions.add(position.move(rowSign * movement, columnSign * movement));
        }

        return positions;
    }
}
