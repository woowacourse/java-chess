package chess.domain.piece;

import java.util.Arrays;
import java.util.function.Supplier;

public enum PieceFactory {
    BLACK_BISHOP("&#9821;", () -> new Bishop(Team.BLACK)),
    WHITE_BISHOP("&#9815;", () -> new Bishop(Team.WHITE)),
    BLACK_ROOK("&#9820;", () -> new Rook(Team.BLACK)),
    WHITE_ROOK("&#9814;", () -> new Rook(Team.WHITE)),
    BLACK_KNIGHT("&#9822;", () -> new Knight(Team.BLACK)),
    WHITE_KNIGHT("&#9816;", () -> new Knight(Team.WHITE)),
    BLACK_QUEEN("&#9819;", () -> new Queen(Team.BLACK)),
    WHITE_QUEEN("&#9813;", () -> new Queen(Team.WHITE)),
    BLACK_KING("&#9818;", () -> new King(Team.BLACK)),
    WHITE_KING("&#9812;", () -> new King(Team.WHITE)),
    BLACK_PAWN("&#9823;", () -> new Pawn(Team.BLACK)),
    WHITE_PANW("&#9817;", () -> new Pawn(Team.WHITE)),
    EMPTY("", () -> null);

    private final String uniCode;
    private final Supplier<Piece> supplier;

    PieceFactory(String uniCode, Supplier<Piece> supplier) {
        this.uniCode = uniCode;
        this.supplier = supplier;
    }

    public static Piece createPieceByUniCode(String uniCode) {
        PieceFactory pieceFactory = Arrays.stream(PieceFactory.values())
                .filter(value -> value.getUniCode().equals(uniCode))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        return pieceFactory.getSupplier().get();
    }

    public String getUniCode() {
        return uniCode;
    }

    public Supplier<Piece> getSupplier() {
        return supplier;
    }
}
