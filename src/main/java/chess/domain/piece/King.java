package chess.domain.piece;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class King extends Piece {

    private static final PieceType PIECE_TYPE = PieceType.KING;

    private static final int MAX_MOVE_DIFFERENCE = 1;

    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> getPositionsInPath(Position toPosition) {
        return new ArrayList<>();
    }

    @Override
    public void move(Position position) {
        validateMovable(position);
        this.position = position;
    }

    @Override
    public void validateMovable(Position toPosition) {
        if (!canMoveOneStep(toPosition)) {
            throw new IllegalArgumentException(INVALID_MOVABLE_POSITION_EXCEPTION_MESSAGE);
        }
    }

    private boolean canMoveOneStep(Position toPosition) {
        int fileDifference = position.fileDifference(toPosition);
        int rankDifference = position.rankDifference(toPosition);

        return fileDifference <= MAX_MOVE_DIFFERENCE
            && rankDifference <= MAX_MOVE_DIFFERENCE;
    }

    @Override
    protected void attack(Position enemyPosition) {
        move(enemyPosition);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double getScore() {
        return PIECE_TYPE.getScore();
    }

    @Override
    public String getName() {
        return PIECE_TYPE.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        King king = (King) o;
        return color == king.color
            && position == king.position;

    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }

    @Override
    public String toString() {
        return "King{" +
            "color=" + color +
            ", position=" + position +
            '}';
    }

}
