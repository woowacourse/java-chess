package chess.domain;

import chess.domain.piece.*;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Function;

public enum PieceConvertor {
    KING("K", (color) -> new King(color)),
    QUEEN("Q", (color) -> new Queen(color)),
    ROOK("R", (color) -> new Rook(color)),
    BISHOP("B", (color) -> new Bishop(color)),
    KNIGHT("N", (color) -> new Knight(color)),
    PAWN("P", (color) -> new Pawn(color)),
    ;

    private static final String NO_MATCHED_PIECE_FOUND = "일치하는 피스가 없습니다.";

    private final String letter;
    private final Function<Color, Piece> pieceMaker;

    PieceConvertor(String letter,
        Function<Color, Piece> pieceMaker) {
        this.letter = letter;
        this.pieceMaker = pieceMaker;
    }

    public static Piece of(String input, Color color){
        PieceConvertor pieceConvertor = Arrays.stream(values())
            .filter(piece -> piece.letter.equalsIgnoreCase(input))
            .findAny()
            .orElseThrow(() -> new NoSuchElementException(NO_MATCHED_PIECE_FOUND));

        return pieceConvertor.pieceMaker.apply(color);
    }
}
