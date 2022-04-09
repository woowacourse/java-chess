package chess.web.converter;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.function.Supplier;

public enum PieceConverter {
    BLACK_KING("black_k", () -> new King(BLACK)),
    BLACK_QUEEN("black_q", () -> new Queen(BLACK)),
    BLACK_BISHOP("black_b", () -> new Bishop(BLACK)),
    BLACK_KNIGHT("black_n", () -> new Knight(BLACK)),
    BLACK_ROOK("black_r", () -> new Rook(BLACK)),
    BLACK_PAWN("black_p", () -> new Pawn(BLACK)),

    WHITE_KING("white_k", () -> new King(WHITE)),
    WHITE_QUEEN("white_q", () -> new Queen(WHITE)),
    WHITE_BISHOP("white_b", () -> new Bishop(WHITE)),
    WHITE_KNIGHT("white_n", () -> new Knight(WHITE)),
    WHITE_ROOK("white_r", () -> new Rook(WHITE)),
    WHITE_PAWN("white_p", () -> new Pawn(WHITE));

    private final String name;
    private final Supplier<Piece> supplier;

    PieceConverter(String name, Supplier<Piece> supplier) {
        this.name = name;
        this.supplier = supplier;
    }

    public static Piece from(String type, String team) {
        String name = team + "_" + type;

        PieceConverter pieceConverter = Arrays.stream(values())
                .filter(piece -> piece.name.equals(name))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);

        return pieceConverter.supplier.get();
    }
}
