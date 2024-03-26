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
    KING_PATTERN(List.of(N, NE, E, SE, S, SW, W, NW)),
    KNIGHT_PATTERN(List.of(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS)),
    WHITE_PAWN_PATTERN(List.of(N, NE, NW)),
    BLACK_PAWN_PATTERN(List.of(S, SE, SW)),
    QUEEN_PATTERN(List.of(W, E, S, N, NW, SW, NE, SE)),
    ROOK_PATTERN(List.of(W, E, S, N)),
    BISHOP_PATTERN(List.of(NW, SW, NE, SE)),
    NONE(List.of());

    private final List<Direction> directions;

    ShiftPattern(List<Direction> directions) {
        this.directions = directions;
    }

    public boolean contains(Direction direction) {
        return directions.contains(direction);
    }

    public List<Direction> directions() {
        return Collections.unmodifiableList(directions);
    }
}
