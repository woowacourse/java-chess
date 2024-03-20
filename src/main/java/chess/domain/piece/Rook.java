package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(Team team, boolean hasNotMoved) {
        super(team, hasNotMoved);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.ROOK);
    }

    @Override
    protected boolean isRelativelyMovable(int rowDifference, int columnDifference) {
        return rowDifference == 0 || columnDifference == 0;
    }

    @Override
    protected List<Position> betweenPositions(Position position, int rowDifference, int columnDifference) {
        List<Position> positions = new ArrayList<>();

        if (columnDifference == 0) {
            int absoluteDifference = Math.abs(rowDifference);
            int rowSign = rowDifference / absoluteDifference;
            for (int movement = 1; movement < absoluteDifference; movement++) {
                positions.add(position.move(rowSign * movement, 0));
            }
        }

        if (rowDifference == 0) {
            int absoluteDifference = Math.abs(columnDifference);
            int columnSign = columnDifference / absoluteDifference;
            for (int movement = 1; movement < absoluteDifference; movement++) {
                positions.add(position.move(0, columnSign * movement));
            }
        }

        return positions;
    }

}
