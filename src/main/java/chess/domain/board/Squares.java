package chess.domain.board;

import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.List;

public final class Squares {

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

    public static Squares initPawns(Color color) {
        final var pawns = new ArrayList<Square>();
        for (int i = 0; i < 8; i++) {
            pawns.add(new Square(new Pawn(color)));
        }
        return new Squares(pawns);
    }

    public static Squares initEmpty() {
        final var list = new ArrayList<Square>();
        for (int i = 0; i < 8; i++) {
            list.add(new Square(new Empty()));
        }
        return new Squares(list);
    }

    public Square get(final int file) {
        return squares.get(file);
    }
}
