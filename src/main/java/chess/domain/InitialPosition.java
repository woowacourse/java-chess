package chess.domain;

import java.util.Arrays;
import java.util.function.Function;

public enum InitialPosition {
    FIRST(Rook::new),
    SECOND(Knight::new),
    THIRD(Bishop::new),
    FOURTH(Queen::new),
    FIFTH(King::new),
    SIXTH(Bishop::new),
    SEVENTH(Knight::new),
    EIGHTH(Rook::new);

    private Function<Boolean, Piece> pieceFunction;

    InitialPosition(Function<Boolean, Piece> pieceFunction) {
        this.pieceFunction = pieceFunction;
    }
}
