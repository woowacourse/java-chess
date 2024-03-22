package model.direction;

import static model.direction.Direction.E;
import static model.direction.Direction.EEN;
import static model.direction.Direction.EES;
import static model.direction.Direction.N;
import static model.direction.Direction.NE;
import static model.direction.Direction.NNE;
import static model.direction.Direction.NNW;
import static model.direction.Direction.NW;
import static model.direction.Direction.S;
import static model.direction.Direction.SE;
import static model.direction.Direction.SSE;
import static model.direction.Direction.SSW;
import static model.direction.Direction.SW;
import static model.direction.Direction.W;
import static model.direction.Direction.WWN;
import static model.direction.Direction.WWS;

import java.util.Collections;
import java.util.List;

public enum ShiftPattern {
    KING_PATTERN(List.of(N, NE, E, SE, S, SW, W, NW), 1),
    KNIGHT_PATTERN(List.of(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS), 1),
    WHITE_PAWN_PATTERN(List.of(N, NE, NW), 1),
    BLACK_PAWN_PATTERN(List.of(S, SE, SW), 1),
    QUEEN_PATTERN(List.of(W, E, S, N, NW, SW, NE, SE), 7),
    ROOK_PATTERN(List.of(W, E, S, N), 7),
    BISHOP_PATTERN(List.of(NW, SW, NE, SE), 7),
    NONE(List.of(), 0);

    private final List<Direction> directions;
    private final int bound;

    ShiftPattern(List<Direction> directions, int bound) {
        this.directions = directions;
        this.bound = bound;
    }

    public List<Direction> directions() {
        return Collections.unmodifiableList(directions);
    }

    public int bound() {
        return bound;
    }
}
