package domain.board;

import static domain.board.Board.CHESS_BOARD_SIZE;

import domain.piece.Bishop;
import domain.piece.Color;
import domain.piece.EmptyPiece;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class InitialBoard {

    private InitialBoard(){
    }

    public static void initChessPieces(Map<Position, Piece> board) {
        board.putAll(createInitialKings());
        board.putAll(createInitialQueens());
        board.putAll(createInitialBishops());
        board.putAll(createInitialKnights());
        board.putAll(createInitialRooks());
        board.putAll(createInitialPawns());
    }

    public static Map<Position, Piece> emptyBoard() {
        Map<Position, Piece> emptyBoard = new HashMap<>();
        for (int row = 0; row < CHESS_BOARD_SIZE; row++) {
            putInitialPiece(emptyBoard, row);
        }
        return emptyBoard;
    }

    private static void putInitialPiece(Map<Position, Piece> emptyBoard, int row) {
        for (int column = 0; column < CHESS_BOARD_SIZE; column++) {
            emptyBoard.put(new Position(row, column), new EmptyPiece());
        }
    }

    private static Map<Position, Piece> createInitialBishops() {
        Map<Position, Piece> initialBishops = new HashMap<>();
        initialBishops.put(new Position("c8"), new Bishop(Color.BLACK));
        initialBishops.put(new Position("f8"), new Bishop(Color.BLACK));
        initialBishops.put(new Position("c1"), new Bishop(Color.WHITE));
        initialBishops.put(new Position("f1"), new Bishop(Color.WHITE));
        return initialBishops;
    }

    private static Map<Position, Piece> createInitialKings() {
        Map<Position, Piece> initialKings = new HashMap<>();
        initialKings.put(new Position("e8"), new King(Color.BLACK));
        initialKings.put(new Position("e1"), new King(Color.WHITE));
        return initialKings;
    }

    private static Map<Position, Piece> createInitialKnights() {
        Map<Position, Piece> initialKnights = new HashMap<>();
        initialKnights.put(new Position("b8"), new Knight(Color.BLACK));
        initialKnights.put(new Position("g8"), new Knight(Color.BLACK));
        initialKnights.put(new Position("b1"), new Knight(Color.WHITE));
        initialKnights.put(new Position("g1"), new Knight(Color.WHITE));
        return initialKnights;
    }

    private static Map<Position, Piece> createInitialPawns() {
        Map<Position, Piece> initialPawns = new HashMap<>();
        putPawns(initialPawns, 1, Color.BLACK);
        putPawns(initialPawns, 6, Color.WHITE);
        return initialPawns;
    }

    private static void putPawns(Map<Position, Piece> pawns, int row, Color color) {
        for (int column = 0; column < 8; column++) {
            pawns.put(new Position(row, column), new Pawn(color));
        }
    }

    private static Map<Position, Piece> createInitialQueens() {
        Map<Position, Piece> initialQueens = new HashMap<>();
        initialQueens.put(new Position("d8"), new Queen(Color.BLACK));
        initialQueens.put(new Position("d1"), new Queen(Color.WHITE));
        return initialQueens;
    }

    private static Map<Position, Piece> createInitialRooks() {
        Map<Position, Piece> initialRooks = new HashMap<>();
        initialRooks.put(new Position("a8"), new Rook(Color.BLACK));
        initialRooks.put(new Position("h8"), new Rook(Color.BLACK));
        initialRooks.put(new Position("a1"), new Rook(Color.WHITE));
        initialRooks.put(new Position("h1"), new Rook(Color.WHITE));
        return initialRooks;
    }
}
