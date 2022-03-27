package chess.domain.piece;

import static chess.domain.piece.Direction.E;
import static chess.domain.piece.Direction.N;
import static chess.domain.piece.Direction.NE;
import static chess.domain.piece.Direction.NW;
import static chess.domain.piece.Direction.S;
import static chess.domain.piece.Direction.SE;
import static chess.domain.piece.Direction.SW;
import static chess.domain.piece.Direction.W;

import chess.domain.PieceType;
import chess.domain.board.Position;
import java.util.List;

public class Queen extends Piece {

    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(N, S, W, E, NE, SE, SW, NW);

    public Queen(Color color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public Direction findValidDirection(Position current, Position target) {
        int rowDifference = target.calculateRowDifference(current);
        int columnDifference = target.calculateColumnDifference(current);
        Direction direction = Direction.calculate(rowDifference, columnDifference);
        validateDirection(direction);
        return direction;
    }

    private void validateDirection(Direction direction) {
        if (!POSSIBLE_DIRECTIONS.contains(direction)) {
            throw new IllegalArgumentException(INVALID_DIRECTION);
        }
    }
}
