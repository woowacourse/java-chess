package view;

import domain.Camp;
import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import java.util.Arrays;

public enum PieceTypeFormat {
    BLACK_PAWN("P", new Pawn(Camp.BLACK)),
    BLACK_KNIGHT("N", new Knight(Camp.BLACK)),
    BLACK_ROOK("R", new Rook(Camp.BLACK)),
    BLACK_BISHOP("B", new Bishop(Camp.BLACK)),
    BLACK_QUEEN("Q", new Queen(Camp.BLACK)),
    BLACK_KING("K", new King(Camp.BLACK)),
    WHITE_PAWN("p", new Pawn(Camp.WHITE)),
    WHITE_KNIGHT("n", new Knight(Camp.WHITE)),
    WHITE_ROOK("r", new Rook(Camp.WHITE)),
    WHITE_BISHOP("b", new Bishop(Camp.WHITE)),
    WHITE_QUEEN("q", new Queen(Camp.WHITE)),
    WHITE_KING("k", new King(Camp.WHITE)),
    ;

    static final String EMPTY_PIECE = ".";

    private final String format;
    private final Piece piece;

    PieceTypeFormat(final String format, final Piece piece) {
        this.format = format;
        this.piece = piece;
    }

    public static String findFormat(final Piece piece) {
        return Arrays.stream(values())
                .filter(type -> type.piece.equals(piece))
                .map(type -> type.format)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("피스 타입이 없습니다."));
    }
}
