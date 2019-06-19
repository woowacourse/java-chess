package chess.domain.piece;

import java.util.Arrays;
import java.util.function.Supplier;

public enum PieceType {
    PAWN("p", Pawn::new);

    private String piece;
    private Supplier<Piece> create;

    PieceType(String piece, Supplier<Piece> create) {
        this.piece = piece;
        this.create = create;
    }

    public static PieceType of(String piece) {
        return Arrays.stream(values())
                .filter(x -> x.piece.equals(piece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("aa"));
    }

    public Piece create() {
        return this.create.get();
    }
}
