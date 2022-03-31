package chess.domain.piece;

import static chess.domain.board.Direction.D;
import static chess.domain.board.Direction.DL;
import static chess.domain.board.Direction.DR;
import static chess.domain.board.Direction.L;
import static chess.domain.board.Direction.R;
import static chess.domain.board.Direction.U;
import static chess.domain.board.Direction.UL;
import static chess.domain.board.Direction.UR;

import chess.domain.board.LocationDiff;
import chess.domain.board.Direction;
import java.util.List;

public class Queen extends Piece{
    private static final List<Direction> QUEEN_DIRECTIONS = List.of(U, D, R, L, UR, UL, DR, DL);
    private static final double SCORE = 9;

    public Queen(Team team) {
        super(team, Name.QUEEN);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return QUEEN_DIRECTIONS.contains(direction);
    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        return true;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
