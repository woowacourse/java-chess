package chess;

import static chess.Col.*;
import static chess.piece.Piece.*;
import static chess.Row.*;

import chess.piece.*;
import java.util.*;

public class ChessBoard {

    private final List<Piece> pieces;

    public ChessBoard() {
        pieces = List.of(
            new Rook(Color.BLACK, new Position(A, EIGHT)),
            knight(Color.BLACK, new Position(B, EIGHT)),
            new Bishop(Color.BLACK, new Position(C, EIGHT)),
            queen(Color.BLACK, new Position(D, EIGHT)),
            king(Color.BLACK, new Position(E, EIGHT)),
            new Bishop(Color.BLACK, new Position(F, EIGHT)),
            knight(Color.BLACK, new Position(G, EIGHT)),
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
            knight(Color.WHITE, new Position(B, ONE)),
            new Bishop(Color.WHITE, new Position(C, ONE)),
            queen(Color.WHITE, new Position(D, ONE)),
            king(Color.WHITE, new Position(E, ONE)),
            new Bishop(Color.WHITE, new Position(F, ONE)),
            knight(Color.WHITE, new Position(G, ONE)),
            new Rook(Color.WHITE, new Position(H, ONE)),
            new Pawn(Color.WHITE, new Position(A, TWO)),
            new Pawn(Color.WHITE, new Position(B, TWO)),
            new Pawn(Color.WHITE, new Position(C, TWO)),
            new Pawn(Color.WHITE, new Position(D, TWO)),
            new Pawn(Color.WHITE, new Position(E, TWO)),
            new Pawn(Color.WHITE, new Position(F, TWO)),
            new Pawn(Color.WHITE, new Position(G, TWO)),
            new Pawn(Color.WHITE, new Position(H, TWO)));
    }

    public ChessBoard(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(Position from, Position to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException();
        }

        Optional<Piece> optionalPiece = findPieceByPosition(from);

        if (optionalPiece.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Piece piece = optionalPiece.get();
        piece.move(to);
    }

    private Optional<Piece> findPieceByPosition(Position from) {
        return pieces.stream()
            .filter(piece -> piece.isSamePosition(from))
            .findFirst();
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
