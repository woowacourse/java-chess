package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(Team team, boolean hasNotMoved) {
        super(team, hasNotMoved);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.QUEEN);
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        return (rowDifference == 0 || columnDifference == 0)
                || Math.abs(rowDifference) == Math.abs(columnDifference);
    }

    @Override
    protected List<Position> findBetweenPositions(Position position, int rowDifference, int columnDifference) {
        List<Position> positions = new ArrayList<>();
        int absoluteDifference = Math.max(Math.abs(rowDifference), Math.abs(columnDifference));
        int rowSign = calculateSign(rowDifference);
        int columnSign = calculateSign(columnDifference);

        for (int movement = MIN_MOVEMENT; movement < absoluteDifference; movement++) {
            positions.add(position.move(rowSign * movement, columnSign * movement));
        }
        return positions;
    }

    private int calculateSign(int value) {
        if (value == 0) {
            return 0;
        }
        return value / Math.abs(value);
    }

    @Override
    public Piece move() {
        if (hasNotMoved) {
            return new Queen(team, false);
        }
        return this;
    }
}
