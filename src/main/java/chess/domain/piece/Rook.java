package chess.domain.piece;

import chess.Calculator;
import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(Team team) {
        this(team, false);
    }

    private Rook(Team team, boolean hasMoved) {
        super(team, hasMoved);
    }

    @Override
    public Piece move() {
        if (hasMoved) {
            return this;
        }
        return new Rook(team, true);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.ROOK);
    }

    @Override
    protected List<Position> findBetweenPositions(Position position, int rowDifference, int columnDifference) {
        int absoluteDifference = Math.max(Math.abs(rowDifference), Math.abs(columnDifference));
        int rowSign = Calculator.calculateSign(rowDifference);
        int columnSign = Calculator.calculateSign(columnDifference);

        List<Position> positions = new ArrayList<>();
        for (int movement = MIN_MOVEMENT; movement < absoluteDifference; movement++) {
            positions.add(position.move(rowSign * movement, columnSign * movement));
        }
        return positions;
    }

    @Override
    protected boolean isAttackable(int rowDifference, int columnDifference) {
        return isMovable(rowDifference, columnDifference);
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        return rowDifference == 0 || columnDifference == 0;
    }

}
