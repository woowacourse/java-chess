package chess.web.domain;

import chess.console.domain.piece.Bishop;
import chess.console.domain.piece.Color;
import chess.console.domain.piece.King;
import chess.console.domain.piece.Knight;
import chess.console.domain.piece.Pawn;
import chess.console.domain.piece.Piece;
import chess.console.domain.piece.Queen;
import chess.console.domain.piece.Rook;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum PieceFactory {

    WHITE_PAWN((color, type) -> color.equals(Color.WHITE.name()) && type.equals("PAWN"), Pawn.of(Color.WHITE)),
    BLACK_PAWN((color, type) -> color.equals(Color.BLACK.name()) && type.equals("PAWN"), Pawn.of(Color.BLACK)),
    WHITE_KNIGHT((color, type) -> color.equals(Color.WHITE.name()) && type.equals("KNIGHT"), new Knight(Color.WHITE)),
    BLACK_KNIGHT((color, type) -> color.equals(Color.BLACK.name()) && type.equals("KNIGHT"), new Knight(Color.BLACK)),
    WHITE_KING((color, type) -> color.equals(Color.WHITE.name()) && type.equals("KING"), new King(Color.WHITE)),
    BLACK_KING((color, type) -> color.equals(Color.BLACK.name()) && type.equals("KING"), new King(Color.BLACK)),
    WHITE_ROOK((color, type) -> color.equals(Color.WHITE.name()) && type.equals("ROOK"), new Rook(Color.WHITE)),
    BLACK_ROOK((color, type) -> color.equals(Color.BLACK.name()) && type.equals("ROOK"), new Rook(Color.BLACK)),
    WHITE_BISHOP((color, type) -> color.equals(Color.WHITE.name()) && type.equals("BISHOP"), new Bishop(Color.WHITE)),
    BLACK_BISHOP((color, type) -> color.equals(Color.BLACK.name()) && type.equals("BISHOP"), new Bishop(Color.BLACK)),
    WHITE_QUEEN((color, type) -> color.equals(Color.WHITE.name()) && type.equals("QUEEN"), new Queen(Color.WHITE)),
    BLACK_QUEEN((color, type) -> color.equals(Color.BLACK.name()) && type.equals("QUEEN"), new Queen(Color.BLACK)),
    ;

    private final BiPredicate<String, String> predicate;
    private final Piece piece;

    PieceFactory(BiPredicate<String, String> predicate, Piece piece) {
        this.predicate = predicate;
        this.piece = piece;
    }

    public static Piece create(String color, String type) {
        return Arrays.stream(PieceFactory.values())
                .filter(e -> e.predicate.test(color, type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("color 또는 type이 잘못되었습니다."))
                .piece;
    }
}
