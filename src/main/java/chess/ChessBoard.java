package chess;

import chess.piece.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    Map<Position, ChessPiece> chessBoard;

    private ChessBoard(Map<Position, ChessPiece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoard generateChessBoard() {
        Map<Position, ChessPiece> chessBoard = new HashMap<>();
        generateRook(chessBoard);
        generateKnight(chessBoard);
        generateBishop(chessBoard);
        generateQueen(chessBoard);
        generateKing(chessBoard);
        generatePawn(chessBoard);
        initEmptySpace(chessBoard);
        return new ChessBoard(chessBoard);
    }

    private static void generateRook(Map<Position, ChessPiece> chessBoard) {
        chessBoard.put(Position.initPosition(1, 1), new Rook(Side.WHITE));
        chessBoard.put(Position.initPosition(8, 1), new Rook(Side.WHITE));
        chessBoard.put(Position.initPosition(1, 8), new Rook(Side.BLACK));
        chessBoard.put(Position.initPosition(8, 8), new Rook(Side.BLACK));
    }

    private static void generateKing(Map<Position, ChessPiece> chessBoard) {
        chessBoard.put(Position.initPosition(5, 1), new King(Side.WHITE));
        chessBoard.put(Position.initPosition(5, 8), new King(Side.BLACK));
    }

    private static void generateKnight(Map<Position, ChessPiece> chessBoard) {
        chessBoard.put(Position.initPosition(2, 1), new Knight(Side.WHITE));
        chessBoard.put(Position.initPosition(7, 1), new Knight(Side.WHITE));
        chessBoard.put(Position.initPosition(2, 8), new Knight(Side.BLACK));
        chessBoard.put(Position.initPosition(7, 8), new Knight(Side.BLACK));
    }

    private static void generateBishop(Map<Position, ChessPiece> chessBoard) {
        chessBoard.put(Position.initPosition(3, 1), new Bishop(Side.WHITE));
        chessBoard.put(Position.initPosition(6, 1), new Bishop(Side.WHITE));

        chessBoard.put(Position.initPosition(3, 8), new Bishop(Side.BLACK));
        chessBoard.put(Position.initPosition(6, 8), new Bishop(Side.BLACK));
    }

    private static void generateQueen(Map<Position, ChessPiece> chessBoard) {
        chessBoard.put(Position.initPosition(4, 1), new Queen(Side.WHITE));
        chessBoard.put(Position.initPosition(4, 8), new Queen(Side.BLACK));
    }

    private static void generatePawn(Map<Position, ChessPiece> chessBoard) {
        for (int horizontal = 1; horizontal <= 8; horizontal++) {
            chessBoard.put(Position.initPosition(horizontal, 2), new Pawn(Side.WHITE));
            chessBoard.put(Position.initPosition(horizontal, 7), new Pawn(Side.BLACK));
        }
    }

    private static void initEmptySpace(Map<Position, ChessPiece> chessBoard) {
        for (int horizontal = 1; horizontal <= 8; horizontal++) {
            circuitVertical(chessBoard, horizontal);
        }
    }

    private static void circuitVertical(Map<Position, ChessPiece> chessBoard, int i) {
        for (int vertical = 3; vertical <= 6; vertical++) {
            chessBoard.put(Position.initPosition(i, vertical), new Empty(Side.BLANK));
        }
    }

    public ChessPiece getChessPieceByPosition(Position position) {
        return chessBoard.get(position);
    }

    public ChessPiece getChessPieceByLocate(int x, int y) {
        return chessBoard.get(List.of(x, y));
    }

    public Map<Position, ChessPiece> getChessBoard() {
        return chessBoard;
    }
}
