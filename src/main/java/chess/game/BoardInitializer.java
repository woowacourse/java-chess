package chess.game;

import static chess.game.Column.*;
import static chess.game.Row.*;
import static chess.piece.Color.BLACK;
import static chess.piece.Color.WHITE;

import chess.piece.*;
import java.util.HashMap;
import java.util.Map;

public class BoardInitializer {

    private static final Map<Position, Piece> board = new HashMap<>();

    static {
        createKings();
        createQueen();
        createBishop();
        createKnight();
        createRook();
        createPawn();
    }

    private BoardInitializer() {
    }

    public static Map<Position, Piece> getBoard() {
        return new HashMap<>(board);
    }

    private static void createKings() {
        board.put(Position.of(E, ONE), new King(WHITE));
        board.put(Position.of(E, EIGHT), new King(BLACK));
    }

    private static void createQueen() {
        board.put(Position.of(D, ONE), new Queen(WHITE));
        board.put(Position.of(D, EIGHT), new Queen(BLACK));
    }

    private static void createBishop() {
        board.put(Position.of(C, ONE), new Bishop(WHITE));
        board.put(Position.of(F, ONE), new Bishop(WHITE));
        board.put(Position.of(C, EIGHT), new Bishop(BLACK));
        board.put(Position.of(F, EIGHT), new Bishop(BLACK));
    }

    private static void createKnight() {
        board.put(Position.of(B, ONE), new Knight(WHITE));
        board.put(Position.of(G, ONE), new Knight(WHITE));
        board.put(Position.of(B, EIGHT), new Knight(BLACK));
        board.put(Position.of(G, EIGHT), new Knight(BLACK));
    }

    private static void createRook() {
        board.put(Position.of(A, ONE), new Rook(WHITE));
        board.put(Position.of(H, ONE), new Rook(WHITE));
        board.put(Position.of(A, EIGHT), new Rook(BLACK));
        board.put(Position.of(H, EIGHT), new Rook(BLACK));
    }

    private static void createPawn() {
        for (final Column column : Column.values()) {
            board.put(Position.of(column, TWO), new Pawn(WHITE));
            board.put(Position.of(column, SEVEN), new Pawn(BLACK));
        }
    }
}
