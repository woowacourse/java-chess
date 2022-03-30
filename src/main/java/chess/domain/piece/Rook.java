package chess.domain.piece;

import static chess.domain.Direction.EAST;
import static chess.domain.Direction.NORTH;
import static chess.domain.Direction.SOUTH;
import static chess.domain.Direction.WEST;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Team;

public class Rook extends JumpPiece {

    private static final double ROOK_SCORE = 5;

    public Rook(Team team, Position position) {
        super(team, position);
    }

    @Override
    public double getScore() {
        return ROOK_SCORE;
    }

    @Override
    protected Direction findDirection(Position destination) {
        int colDiff = destination.getCol().getDifference(position.getCol());
        int rowDiff = destination.getRow().getDifference(position.getRow());

        if (colDiff == 0) {
            return findDirectionWithRowDiff(rowDiff);
        }
        if (rowDiff == 0) {
            return findDirectionWithColDiff(colDiff);
        }
        throw new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다.");
    }

    private Direction findDirectionWithRowDiff(int diff) {
        if (diff > 0) {
            return NORTH;
        }
        return SOUTH;
    }

    private Direction findDirectionWithColDiff(int colDiff) {
        if (colDiff > 0) {
            return EAST;
        }
        return WEST;
    }
}
