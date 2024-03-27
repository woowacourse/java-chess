package model.direction;

import java.util.List;

import static model.direction.Direction.*;

public enum ShiftPattern {
    KING(List.of(N, NE, E, SE, S, SW, W, NW)),
    KNIGHT(List.of(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS)),
    BISHOP(List.of(NW, SW, NE, SE)),
    WHITE_PAWN(List.of(N, NW, NE)),
    BLACK_PAWN(List.of(S, SE, SW)),
    QUEEN(List.of(W, E, S, N, NW, SW, NE, SE)),
    ROOK(List.of(W, E, N, S));
    private final List<Direction> directions;

    ShiftPattern(List<Direction> directions) {
        this.directions = directions;
    }

    public List<Direction> getDirections() {
        return directions;
    }
}
