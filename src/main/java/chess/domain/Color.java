package chess.domain;

import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public enum Color {
    WHITE(Piece::isWhite),
    BLACK(Piece::isBlack),
    EMPTY(Piece::isInValid),
    ;

    private final Predicate<Piece> colorPredicate;

    Color(Predicate<Piece> colorPredicate) {
        this.colorPredicate = colorPredicate;
    }

    public static Color from(Piece piece) {
        return Arrays.stream(values())
                .filter(color -> color.colorPredicate.test(piece))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }
}
