package chess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    Map<List<Integer>, ChessPiece> chessBoard;

    private ChessBoard(Map<List<Integer>, ChessPiece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoard generateChessBoard() {
        Map<List<Integer>, ChessPiece> chessBoard = new HashMap<>();
        generateRook(chessBoard);
        generateKnight(chessBoard);
        generateBishop(chessBoard);
        generateQueen(chessBoard);
        generateKing(chessBoard);
        generatePawn(chessBoard);
        initEmptySpace(chessBoard);
        return new ChessBoard(chessBoard);
    }

    private static void generateRook(Map<List<Integer>, ChessPiece> chessBoard) {
        chessBoard.put(List.of(1, 1), new Rook(Side.WHITE));
        chessBoard.put(List.of(8, 1), new Rook(Side.WHITE));
        chessBoard.put(List.of(1, 8), new Rook(Side.BLACK));
        chessBoard.put(List.of(8, 8), new Rook(Side.BLACK));
    }

    private static void generateKing(Map<List<Integer>, ChessPiece> chessBoard) {
        chessBoard.put(List.of(5, 1), new King(Side.WHITE));
        chessBoard.put(List.of(5, 8), new King(Side.BLACK));
    }

    private static void generateKnight(Map<List<Integer>, ChessPiece> chessBoard) {
        chessBoard.put(List.of(2, 1), new Knight(Side.WHITE));
        chessBoard.put(List.of(7, 1), new Knight(Side.WHITE));
        chessBoard.put(List.of(2, 8), new Knight(Side.BLACK));
        chessBoard.put(List.of(7, 8), new Knight(Side.BLACK));
    }

    private static void generateBishop(Map<List<Integer>, ChessPiece> chessBoard) {
        chessBoard.put(List.of(3, 1), new Bishop(Side.WHITE));
        chessBoard.put(List.of(6, 1), new Bishop(Side.WHITE));

        chessBoard.put(List.of(3, 8), new Bishop(Side.BLACK));
        chessBoard.put(List.of(6, 8), new Bishop(Side.BLACK));
    }

    private static void generateQueen(Map<List<Integer>, ChessPiece> chessBoard) {
        chessBoard.put(List.of(4, 1), new Queen(Side.WHITE));
        chessBoard.put(List.of(4, 8), new Queen(Side.BLACK));
    }

    private static void generatePawn(Map<List<Integer>, ChessPiece> chessBoard) {
        for (int i = 1; i <= 8; i++) {
            chessBoard.put(List.of(i, 2), new Pawn(Side.WHITE));
            chessBoard.put(List.of(i, 7), new Pawn(Side.BLACK));
        }
    }

    private static void initEmptySpace(Map<List<Integer>, ChessPiece> chessBoard) {
        for (int i = 3; i <= 8; i++) {
            for (int j = 3; j <= 6; j++) {
                chessBoard.put(List.of(i, j), new Empty(Side.BLANK));
            }
        }
    }

    public Map<List<Integer>, ChessPiece> getChessBoard() {
        return chessBoard;
    }
}
