package chess.domain.piece;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

public enum PieceType {
    PAWN("p", () -> Optional.of(new Pawn())),
    ROOK("r", () -> Optional.of(new Rook())),
    KNIGHT("n", () -> Optional.of(new Knight())),
    KING("k", () -> Optional.of(new King())),
    BISHOP("b", () -> Optional.of(new Bishop())),
    QUEEN("q", () -> Optional.of(new Queen())),
    EMPTY(".", Optional::empty);


    private String piece;
    private Supplier<Optional<Piece>> create;

    PieceType(String piece, Supplier<Optional<Piece>> create) {
        this.piece = piece;
        this.create = create;
    }

    public static PieceType of(String piece) {
        return Arrays.stream(values())
                // TODO method 분리
                .filter(x -> x.piece.equals(piece.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

    public Optional<Piece> create() {
        return this.create.get();
    }
}
