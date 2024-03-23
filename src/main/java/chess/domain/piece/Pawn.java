package chess.domain.piece;

import chess.Calculator;
import chess.domain.Position;
import chess.domain.Positions;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import chess.exception.ImpossibleMoveException;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public static final int WHITE_NORMAL_MOVEMENT = 1;
    public static final int WHITE_START_MOVEMENT = 2;
    public static final int BLACK_NORMAL_MOVEMENT = -1;
    public static final int BLACK_START_MOVEMENT = -2;
    public static final int ATTACK_COLUMN_MOVEMENT = 1;


    public Pawn(Team team) {
        this(team, false);
    }

    private Pawn(Team team, boolean hasMoved) {
        super(team, hasMoved);
    }

    @Override
    public Piece move() {
        if (hasMoved) {
            return this;
        }
        return new Pawn(team, true);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.PAWN);
    }

    @Override
    public List<Position> findBetweenPositionsWhenAttack(Positions positions) {
        int rowDifference = positions.calculateRowDifference();
        int columnDifference = positions.calculateColumnDifference();
        validateAttackable(rowDifference, columnDifference);
        return findBetweenPositions(positions.source(), rowDifference, columnDifference);
    }

    private void validateAttackable(int rowDifference, int columnDifference) {
        if (isAttackable(rowDifference, columnDifference)) {
            return;
        }
        throw new ImpossibleMoveException("해당 위치로 움직일 수 없습니다.");
    }

    @Override
    protected List<Position> findBetweenPositions(Position position, int rowDifference, int columnDifference) {
        List<Position> positions = new ArrayList<>();
        if (Math.abs(rowDifference) == WHITE_START_MOVEMENT) {
            positions.add(position.move(Calculator.calculateSign(rowDifference), 0));
            return positions;
        }
        return new ArrayList<>();
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        if (columnDifference != 0) {
            return false;
        }
        if (team == Team.WHITE) {
            return rowDifference == WHITE_NORMAL_MOVEMENT || (!hasMoved && rowDifference == WHITE_START_MOVEMENT);
        }
        return rowDifference == BLACK_NORMAL_MOVEMENT || (!hasMoved && rowDifference == BLACK_START_MOVEMENT);
    }

    @Override
    protected boolean isAttackable(int rowDifference, int columnDifference) {
        if (team == Team.WHITE) {
            return rowDifference == WHITE_NORMAL_MOVEMENT && Math.abs(columnDifference) == ATTACK_COLUMN_MOVEMENT;
        }
        return rowDifference == BLACK_NORMAL_MOVEMENT && Math.abs(columnDifference) == ATTACK_COLUMN_MOVEMENT;
    }
}
