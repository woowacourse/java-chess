package domain.board;

import static domain.board.Board.CHESS_BOARD_SIZE;

import domain.piece.Bishop;
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
        initialBishops.put(new Position("c8"), new Bishop(true));
        initialBishops.put(new Position("f8"), new Bishop(true));
        initialBishops.put(new Position("c1"), new Bishop(false));
        initialBishops.put(new Position("f1"), new Bishop(false));
        return initialBishops;
    }

    private static Map<Position, Piece> createInitialKings() {
        Map<Position, Piece> initialKings = new HashMap<>();
        initialKings.put(new Position("e8"), new King(true));
        initialKings.put(new Position("e1"), new King(false));
        return initialKings;
    }

    private static Map<Position, Piece> createInitialKnights() {
        Map<Position, Piece> initialKnights = new HashMap<>();
        initialKnights.put(new Position("b8"), new Knight(true));
        initialKnights.put(new Position("g8"), new Knight(true));
        initialKnights.put(new Position("b1"), new Knight(false));
        initialKnights.put(new Position("g1"), new Knight(false));
        return initialKnights;
    }

    private static Map<Position, Piece> createInitialPawns() {
        Map<Position, Piece> initialPawns = new HashMap<>();
        putPawns(initialPawns, 1, true);
        putPawns(initialPawns, 6, false);
        return initialPawns;
    }

    private static void putPawns(Map<Position, Piece> pawns, int row, boolean isBlack) {
        for (int column = 0; column < 8; column++) {
            pawns.put(new Position(row, column), new Pawn(isBlack));
        }
    }

    private static Map<Position, Piece> createInitialQueens() {
        Map<Position, Piece> initialQueens = new HashMap<>();
        initialQueens.put(new Position("d8"), new Queen(true));
        initialQueens.put(new Position("d1"), new Queen(false));
        return initialQueens;
    }

    private static Map<Position, Piece> createInitialRooks() {
        Map<Position, Piece> initialRooks = new HashMap<>();
        initialRooks.put(new Position("a8"), new Rook(true));
        initialRooks.put(new Position("h8"), new Rook(true));
        initialRooks.put(new Position("a1"), new Rook(false));
        initialRooks.put(new Position("h1"), new Rook(false));
        return initialRooks;
    }
}
