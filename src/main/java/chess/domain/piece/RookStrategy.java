package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;

public class RookStrategy extends InfiniteMovement {

    private final List<Direction> directions = Arrays.asList(N, E, W, S);

    @Override
    public List<Direction> getDirection() {
        return directions;
    }
}