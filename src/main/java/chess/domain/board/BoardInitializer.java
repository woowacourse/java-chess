package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;

import chess.domain.position.Position;

public class BoardInitializer {

    public static Map<Position, Piece> generate() {
        Map<Position, Piece> board = new HashMap<>();
        generateKings(board);
        generateQueens(board);
        generateBishops(board);
        generateKnights(board);
        generateRooks(board);
        generatePawns(board);
        return board;
    }

    private static void generateKings(Map<Position, Piece> board) {
        board.put(new Position(1, 5), King.createWhite());
        board.put(new Position(8, 5), King.createBlack());
    }

    private static void generateQueens(Map<Position, Piece> board) {
        board.put(new Position(1, 4), Queen.createWhite());
        board.put(new Position(8, 4), Queen.createBlack());
    }

    private static void generateBishops(Map<Position, Piece> board) {
        board.put(new Position(1, 3), Bishop.createWhite());
        board.put(new Position(1, 6), Bishop.createWhite());
        board.put(new Position(8, 3), Bishop.createBlack());
        board.put(new Position(8, 6), Bishop.createBlack());
    }

    private static void generateKnights(Map<Position, Piece> board) {
        board.put(new Position(1, 2), Knight.createWhite());
        board.put(new Position(1, 7), Knight.createWhite());
        board.put(new Position(8, 2), Knight.createBlack());
        board.put(new Position(8, 7), Knight.createBlack());
    }

    private static void generateRooks(Map<Position, Piece> board) {
        board.put(new Position(1, 1), Rook.createWhite());
        board.put(new Position(1, 8), Rook.createWhite());
        board.put(new Position(8, 1), Rook.createBlack());
        board.put(new Position(8, 8), Rook.createBlack());
    }

    private static void generatePawns(Map<Position, Piece> board) {
        for (int i = 1; i <= 8; i++) {
            board.put(new Position(7, i), Pawn.createBlack());
            board.put(new Position(2, i), Pawn.createWhite());
        }
    }
}
