package chess.domain.board.service.mapper;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.jumper.King;
import chess.domain.piece.jumper.Knight;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.slider.Bishop;
import chess.domain.piece.slider.Queen;
import chess.domain.piece.slider.Rook;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum PieceInitialMapping {

    K(Color.BLACK, King.class, new King(Color.BLACK)),
    k(Color.WHITE, King.class, new King(Color.WHITE)),
    Q(Color.BLACK, Queen .class, new Queen(Color.BLACK)),
    q(Color.WHITE, Queen.class, new Queen(Color.WHITE)),
    R(Color.BLACK, Rook.class, new Rook(Color.BLACK)),
    r(Color.WHITE, Rook .class, new Rook(Color.WHITE)),
    N(Color.BLACK, Knight .class, new Knight(Color.BLACK)),
    n(Color.WHITE, Knight.class, new Knight(Color.WHITE)),
    B(Color.BLACK, Bishop .class, new Bishop(Color.BLACK)),
    b(Color.WHITE, Bishop.class, new Bishop(Color.WHITE)),
    P(Color.BLACK, Pawn.class, new Pawn(Color.BLACK)),
    p(Color.WHITE, Pawn .class, new Pawn(Color.WHITE));

    private final Color color;
    private final Class<?> classType;
    private final Piece piece;

    PieceInitialMapping(final Color color, final Class<?> classType, final Piece piece) {
        this.color = color;
        this.classType = classType;
        this.piece = piece;
    }

    public static String mapToPieceInitialFrom(final Piece piece) {

        return Arrays.stream(values())
                     .filter(it -> piece.isSameColor(it.color) && it.classType.equals(piece.getClass()))
                     .map(it -> it.name())
                     .findFirst()
                     .orElseThrow(NoSuchElementException::new);
    }

    public static Class<?> mapToClassTypeFrom(final String pieceInitial) {
        return Arrays.stream(values())
                     .filter(it -> it.name().equals(pieceInitial))
                     .map(it -> it.classType)
                     .findFirst()
                     .orElseThrow(NoSuchElementException::new);
    }

    public static Piece mapToPieceFrom(final String pieceInitial) {
        return Arrays.stream(values())
                     .filter(it -> it.name().equals(pieceInitial))
                     .map(it -> it.piece)
                     .findFirst()
                     .orElseThrow(NoSuchElementException::new);
    }
}
