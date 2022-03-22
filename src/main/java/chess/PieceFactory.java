package chess;

import static chess.File.*;
import static chess.Rank.*;
import static chess.Type.BISHOP;
import static chess.Type.KING;
import static chess.Type.KNIGHT;
import static chess.Type.PAWN;
import static chess.Type.QUEEN;
import static chess.Type.ROOK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PieceFactory {

    private PieceFactory() {
    }

    public static List<Piece> whitePieces() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(new Piece(ROOK.getSymbol(), new Position(A, ONE)));
        pieces.add(new Piece(KNIGHT.getSymbol(), new Position(B, ONE)));
        pieces.add(new Piece(BISHOP.getSymbol(), new Position(C, ONE)));
        pieces.add(new Piece(QUEEN.getSymbol(), new Position(D, ONE)));
        pieces.add(new Piece(KING.getSymbol(), new Position(E, ONE)));
        pieces.add(new Piece(BISHOP.getSymbol(), new Position(F, ONE)));
        pieces.add(new Piece(KNIGHT.getSymbol(), new Position(G, ONE)));
        pieces.add(new Piece(ROOK.getSymbol(), new Position(H, ONE)));

        Arrays.stream(Rank.values())
                .map(rank -> pieces.add(new Piece(PAWN.getSymbol(), new Position(rank, TWO))));

        return pieces;
    }

    public static List<Piece> blackPieces() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(new Piece(ROOK.getSymbol().toUpperCase(), new Position(A, EIGHT)));
        pieces.add(new Piece(KNIGHT.getSymbol().toUpperCase(), new Position(B, EIGHT)));
        pieces.add(new Piece(BISHOP.getSymbol().toUpperCase(), new Position(C, EIGHT)));
        pieces.add(new Piece(QUEEN.getSymbol().toUpperCase(), new Position(D, EIGHT)));
        pieces.add(new Piece(KING.getSymbol().toUpperCase(), new Position(E, EIGHT)));
        pieces.add(new Piece(BISHOP.getSymbol().toUpperCase(), new Position(F, EIGHT)));
        pieces.add(new Piece(KNIGHT.getSymbol().toUpperCase(), new Position(G, EIGHT)));
        pieces.add(new Piece(ROOK.getSymbol().toUpperCase(), new Position(H,  EIGHT)));

        Arrays.stream(Rank.values())
                .map(rank -> pieces.add(new Piece(PAWN.getSymbol().toUpperCase(), new Position(rank, SEVEN))));

        return pieces;
    }
}
