package domain;

import domain.position.File;
import domain.position.Position;

import java.util.Arrays;
import java.util.List;

import static domain.position.File.*;
import static domain.position.Rank.*;

public enum PieceType {
    BLACK_PAWN(Arrays.stream(File.values()).map(file -> new Position(file, SEVEN)).toList()),
    BLACK_ROOK(List.of(new Position(A, EIGHT), new Position(H, EIGHT))),
    BLACK_KNIGHT(List.of(new Position(B, EIGHT), new Position(G, EIGHT))),
    BLACK_BISHOP(List.of(new Position(C, EIGHT), new Position(F, EIGHT))),
    BLACK_QUEEN(List.of(new Position(D, EIGHT))),
    BLACK_KING(List.of(new Position(E, EIGHT))),
    WHITE_PAWN(Arrays.stream(File.values()).map(file -> new Position(file, TWO)).toList()),
    WHITE_ROOK(List.of(new Position(A, ONE), new Position(H, ONE))),
    WHITE_KNIGHT(List.of(new Position(B, ONE), new Position(G, ONE))),
    WHITE_BISHOP(List.of(new Position(C, ONE), new Position(F, ONE))),
    WHITE_QUEEN(List.of(new Position(D, ONE))),
    WHITE_KING(List.of(new Position(E, ONE)));

    private final List<Position> initPosition;

    PieceType(final List<Position> initPosition) {
        this.initPosition = initPosition;
    }

    public List<Position> getInitPosition() {
        return initPosition;
    }
}
