package chess;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.Column.A;
import static chess.domain.piece.Column.B;
import static chess.domain.piece.Column.C;
import static chess.domain.piece.Column.D;
import static chess.domain.piece.Column.E;
import static chess.domain.piece.Column.F;
import static chess.domain.piece.Column.G;
import static chess.domain.piece.Column.H;
import static chess.domain.piece.Row.EIGHT;
import static chess.domain.piece.Row.ONE;
import static chess.domain.piece.Row.SEVEN;
import static chess.domain.piece.Row.TWO;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashSet;
import java.util.Set;

public class ChessInitializer {

    public static Set<Piece> initialize() {
        Set<Piece> pieces = new HashSet<>();

        pieces.add(new Rook(WHITE, new Position(A, ONE)));
        pieces.add(new Knight(WHITE, new Position(B, ONE)));
        pieces.add(new Bishop(WHITE, new Position(C, ONE)));
        pieces.add(new Queen(WHITE, new Position(D, ONE)));
        pieces.add(new King(WHITE, new Position(E, ONE)));
        pieces.add(new Bishop(WHITE, new Position(F, ONE)));
        pieces.add(new Knight(WHITE, new Position(G, ONE)));
        pieces.add(new Rook(WHITE, new Position(H, ONE)));

        pieces.add(new Pawn(WHITE, new Position(A, TWO)));
        pieces.add(new Pawn(WHITE, new Position(B, TWO)));
        pieces.add(new Pawn(WHITE, new Position(C, TWO)));
        pieces.add(new Pawn(WHITE, new Position(D, TWO)));
        pieces.add(new Pawn(WHITE, new Position(E, TWO)));
        pieces.add(new Pawn(WHITE, new Position(F, TWO)));
        pieces.add(new Pawn(WHITE, new Position(G, TWO)));
        pieces.add(new Pawn(WHITE, new Position(H, TWO)));

        pieces.add(new Rook(BLACK, new Position(A, EIGHT)));
        pieces.add(new Knight(BLACK, new Position(B, EIGHT)));
        pieces.add(new Bishop(BLACK, new Position(C, EIGHT)));
        pieces.add(new Queen(BLACK, new Position(D, EIGHT)));
        pieces.add(new King(BLACK, new Position(E, EIGHT)));
        pieces.add(new Bishop(BLACK, new Position(F, EIGHT)));
        pieces.add(new Knight(BLACK, new Position(G, EIGHT)));
        pieces.add(new Rook(BLACK, new Position(H, EIGHT)));

        pieces.add(new Pawn(BLACK, new Position(A, SEVEN)));
        pieces.add(new Pawn(BLACK, new Position(B, SEVEN)));
        pieces.add(new Pawn(BLACK, new Position(C, SEVEN)));
        pieces.add(new Pawn(BLACK, new Position(D, SEVEN)));
        pieces.add(new Pawn(BLACK, new Position(E, SEVEN)));
        pieces.add(new Pawn(BLACK, new Position(F, SEVEN)));
        pieces.add(new Pawn(BLACK, new Position(G, SEVEN)));
        pieces.add(new Pawn(BLACK, new Position(H, SEVEN)));

        return pieces;
    }

}
