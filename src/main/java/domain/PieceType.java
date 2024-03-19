package domain;

import java.util.Arrays;
import java.util.List;

import static domain.File.*;
import static domain.Rank.*;

public enum PieceType {
    BLACK_PAWN(Arrays.stream(File.values()).map(file -> new Position(SEVEN, file)).toList()),
    BLACK_ROOK(List.of(new Position(EIGHT, A), new Position(EIGHT, H))),
    BLACK_KNIGHT(List.of(new Position(EIGHT, B), new Position(EIGHT, G))),
    BLACK_BISHOP(List.of(new Position(EIGHT, C), new Position(EIGHT, F))),
    BLACK_QUEEN(List.of(new Position(EIGHT, D))),
    BLACK_KING(List.of(new Position(EIGHT, E))),
    WHITE_PAWN(Arrays.stream(File.values()).map(file -> new Position(TWO, file)).toList()),
    WHITE_ROOK(List.of(new Position(ONE, A), new Position(ONE, H))),
    WHITE_KNIGHT(List.of(new Position(ONE, B), new Position(ONE, G))),
    WHITE_BISHOP(List.of(new Position(ONE, C), new Position(ONE, F))),
    WHITE_QUEEN(List.of(new Position(ONE, D))),
    WHITE_KING(List.of(new Position(ONE, E)));

    private final List<Position> initPosition;

    PieceType(final List<Position> initPosition) {
        this.initPosition = initPosition;
    }

    public List<Position> getInitPosition() {
        return initPosition;
    }
}
