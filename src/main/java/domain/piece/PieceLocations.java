package domain.piece;

import static domain.ChessColumn.*;
import static domain.Rank.*;
import static domain.piece.TeamColor.BLACK;
import static domain.piece.TeamColor.WHITE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Square;

public class PieceLocations {
    private static final List<Square> BLACK_SQUARES = List.of(
            Square.of(A, EIGHT), Square.of(B, EIGHT), Square.of(C, EIGHT), Square.of(D, EIGHT),
            Square.of(E, EIGHT), Square.of(F, EIGHT), Square.of(G, EIGHT), Square.of(H, EIGHT),
            Square.of(A, SEVEN), Square.of(B, SEVEN), Square.of(C, SEVEN), Square.of(D, SEVEN),
            Square.of(E, SEVEN), Square.of(F, SEVEN), Square.of(G, SEVEN), Square.of(H, SEVEN)
    );
    private static final List<Square> WHITE_SQUARES = List.of(
            Square.of(A, ONE), Square.of(B, ONE), Square.of(C, ONE), Square.of(D, ONE),
            Square.of(E, ONE), Square.of(F, ONE), Square.of(G, ONE), Square.of(H, ONE),
            Square.of(A, TWO), Square.of(B, TWO), Square.of(D, TWO), Square.of(E, TWO),
            Square.of(C, TWO), Square.of(F, TWO), Square.of(G, TWO), Square.of(H, TWO)
    );
    private static final List<Piece> BLACK_PIECES = List.of(
            new Rook(BLACK), new Knight(BLACK), new Bishop(BLACK), new Queen(BLACK),
            new King(BLACK), new Bishop(BLACK), new Knight(BLACK), new Rook(BLACK),
            new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK),
            new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK)
    );
    private static final List<Piece> WHITE_PIECES = List.of(
            new Rook(WHITE), new Knight(WHITE), new Bishop(WHITE), new Queen(WHITE),
            new King(WHITE), new Bishop(WHITE), new Knight(WHITE), new Rook(WHITE),
            new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE),
            new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE)
    );

    private final Map<Square, Piece> pieceLocations;

    public PieceLocations() {
        pieceLocations = new HashMap<>();
        for (int i = 0; i < BLACK_PIECES.size(); i++) {
            pieceLocations.put(BLACK_SQUARES.get(i), BLACK_PIECES.get(i));
            pieceLocations.put(WHITE_SQUARES.get(i), WHITE_PIECES.get(i));
        }
    }

    public Piece find(Square square) {
        return pieceLocations.get(square);
    }

    public void update(Square source, Square target) {
        Piece piece = pieceLocations.get(source);
        pieceLocations.remove(source);
        pieceLocations.put(target, piece);
    }

    public boolean hasPiece(Square square) {
        return pieceLocations.containsKey(square);
    }
}
