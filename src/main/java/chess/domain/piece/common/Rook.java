package chess.domain.piece.common;

import static chess.domain.piece.Direction.E;
import static chess.domain.piece.Direction.N;
import static chess.domain.piece.Direction.S;
import static chess.domain.piece.Direction.W;

import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import java.util.List;

public class Rook extends CommonPiece {

    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(N, S, W, E);

    public Rook(Color color) {
        super(PieceType.ROOK, color);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    protected void validateDirection(Direction direction) {
        if (!POSSIBLE_DIRECTIONS.contains(direction)) {
            throw new IllegalArgumentException(INVALID_DIRECTION);
        }
    }
}
