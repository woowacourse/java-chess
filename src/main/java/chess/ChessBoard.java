package chess;

import static chess.Col.*;
import static chess.Row.*;

import chess.piece.*;
import java.util.*;

public class ChessBoard {

    private final List<Piece> pieces;
    private Color currentColor;

    public ChessBoard() {
        pieces = List.of(
            new Rook(Color.BLACK, new Position(A, EIGHT)),
            new Knight(Color.BLACK, new Position(B, EIGHT)),
            new Bishop(Color.BLACK, new Position(C, EIGHT)),
            new Queen(Color.BLACK, new Position(D, EIGHT)),
            new King(Color.BLACK, new Position(E, EIGHT)),
            new Bishop(Color.BLACK, new Position(F, EIGHT)),
            new Knight(Color.BLACK, new Position(G, EIGHT)),
            new Rook(Color.BLACK, new Position(H, EIGHT)),
            new Pawn(Color.BLACK, new Position(A, SEVEN)),
            new Pawn(Color.BLACK, new Position(B, SEVEN)),
            new Pawn(Color.BLACK, new Position(C, SEVEN)),
            new Pawn(Color.BLACK, new Position(D, SEVEN)),
            new Pawn(Color.BLACK, new Position(E, SEVEN)),
            new Pawn(Color.BLACK, new Position(F, SEVEN)),
            new Pawn(Color.BLACK, new Position(G, SEVEN)),
            new Pawn(Color.BLACK, new Position(H, SEVEN)),
            new Rook(Color.WHITE, new Position(A, ONE)),
            new Knight(Color.WHITE, new Position(B, ONE)),
            new Bishop(Color.WHITE, new Position(C, ONE)),
            new Queen(Color.WHITE, new Position(D, ONE)),
            new King(Color.WHITE, new Position(E, ONE)),
            new Bishop(Color.WHITE, new Position(F, ONE)),
            new Knight(Color.WHITE, new Position(G, ONE)),
            new Rook(Color.WHITE, new Position(H, ONE)),
            new Pawn(Color.WHITE, new Position(A, TWO)),
            new Pawn(Color.WHITE, new Position(B, TWO)),
            new Pawn(Color.WHITE, new Position(C, TWO)),
            new Pawn(Color.WHITE, new Position(D, TWO)),
            new Pawn(Color.WHITE, new Position(E, TWO)),
            new Pawn(Color.WHITE, new Position(F, TWO)),
            new Pawn(Color.WHITE, new Position(G, TWO)),
            new Pawn(Color.WHITE, new Position(H, TWO)));
        this.currentColor = Color.WHITE;
    }

    public ChessBoard(List<Piece> pieces, Color color) {
        this.pieces = pieces;
        this.currentColor = color;
    }

    public void move(Position from, Position to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException();
        }

        Piece piece = findPieceByPosition(from);

        if (!piece.isSameColor(currentColor)) {
            throw new IllegalArgumentException();
        }

        Optional<Piece> toPiece = pieces.stream()
            .filter(p -> p.isSamePosition(to))
            .findFirst();

        if (toPiece.isPresent()) {
            if (toPiece.get().isSameColor(piece)) {
                throw new IllegalArgumentException();
            }
        }

        piece.move(to);
        currentColor = currentColor.reverse();
    }

    private Piece findPieceByPosition(Position from) {
        return pieces.stream()
            .filter(piece -> piece.isSamePosition(from))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
