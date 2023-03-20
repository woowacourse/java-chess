package chess.domain.board;

import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Squares {

    private static final int SQUARES_SIZE = 8;
    private final List<Square> squares;

    public Squares(List<Square> squares) {
        this.squares = squares;
    }

    public static Squares initPieces(Color color) {
        final var pieces = new ArrayList<Square>();
        pieces.add(new Square(new Rook(color)));
        pieces.add(new Square(new Knight(color)));
        pieces.add(new Square(new Bishop(color)));
        pieces.add(new Square(new Queen(color)));
        pieces.add(new Square(new King(color)));
        pieces.add(new Square(new Bishop(color)));
        pieces.add(new Square(new Knight(color)));
        pieces.add(new Square(new Rook(color)));
        return new Squares(pieces);
    }

    public static Squares initWhitePawns() {
        final var pawns = new ArrayList<Square>();
        for (int i = 0; i < SQUARES_SIZE; i++) {
            pawns.add(new Square(new WhitePawn()));
        }
        return new Squares(pawns);
    }

    public static Squares initBlackPawns() {
        final var pawns = new ArrayList<Square>();
        for (int i = 0; i < SQUARES_SIZE; i++) {
            pawns.add(new Square(new BlackPawn()));
        }
        return new Squares(pawns);
    }

    public static Squares initEmpty() {
        final var list = new ArrayList<Square>();
        for (int i = 0; i < SQUARES_SIZE; i++) {
            list.add(new Square(new Empty()));
        }
        return new Squares(list);
    }

    public Square get(final int file) {
        return squares.get(file);
    }

    public List<Piece> getPieces() {
        return squares.stream()
                .map(Square::getPiece)
                .collect(Collectors.toList());
    }
}
