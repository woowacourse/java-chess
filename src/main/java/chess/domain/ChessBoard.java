package chess.domain;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private final static String WHITE_ROOK_LEFT = "a1";
    private final static String WHITE_ROOK_RIGHT = "h1";
    private final static String BLACK_ROOK_LEFT = "a8";
    private final static String BLACK_ROOK_RIGHT = "h8";
    private final static String WHITE_KNIGHT_LEFT = "b1";
    private final static String WHITE_KNIGHT_RIGHT = "g1";
    private final static String BLACK_KNIGHT_LEFT = "b8";
    private final static String BLACK_KNIGHT_RIGHT = "g8";
    private final static String WHITE_BISHOP_LEFT = "c1";
    private final static String WHITE_BISHOP_RIGHT = "f1";
    private final static String BLACK_BISHOP_LEFT = "c8";
    private final static String BLACK_BISHOP_RIGHT = "f8";
    private final static String WHITE_QUEEN = "d1";
    private final static String BLACK_QUEEN = "d8";
    private final static String WHITE_KING = "e1";
    private final static String BLACK_KING = "e8";
    private final static String WHITE_PAWN_FIRST = "a2";
    private final static String WHITE_PAWN_SECOND = "b2";
    private final static String WHITE_PAWN_THIRD = "c2";
    private final static String WHITE_PAWN_FOURTH = "d2";
    private final static String WHITE_PAWN_FIFTH = "e2";
    private final static String WHITE_PAWN_SIXTH = "f2";
    private final static String WHITE_PAWN_SEVENTH = "g2";
    private final static String WHITE_PAWN_EIGHT = "h2";
    private final static String BLACK_PAWN_FIRST = "a7";
    private final static String BLACK_PAWN_SECOND = "b7";
    private final static String BLACK_PAWN_THIRD = "c7";
    private final static String BLACK_PAWN_FOURTH = "d7";
    private final static String BLACK_PAWN_FIFTH = "e7";
    private final static String BLACK_PAWN_SIXTH = "f7";
    private final static String BLACK_PAWN_SEVENTH = "g7";
    private final static String BLACK_PAWN_EIGHT = "h7";

    private static final int MIN_RANK_LEVEL_FOR_EMPTY = 3;
    private static final int MAX_RANK_LEVEL_FOR_EMPTY = 6;

    private final Map<Position, ChessPiece> chessBoard;

    private ChessBoard(Map<Position, ChessPiece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoard GenerateChessBoard() {
        HashMap<Position, ChessPiece> chessBoard = new HashMap<>();
        generateRook(chessBoard);
        generateKnight(chessBoard);
        generateBishop(chessBoard);
        generateQueen(chessBoard);
        generateKing(chessBoard);
        generatePawn(chessBoard);
        generateEmpty(chessBoard);
        return new ChessBoard(chessBoard);
    }

    private static void generateRook(HashMap<Position, ChessPiece> chessBoard) {
        chessBoard.put(Position.findPosition(WHITE_ROOK_LEFT), new Rook(Color.WHITE));
        chessBoard.put(Position.findPosition(WHITE_ROOK_RIGHT), new Rook(Color.WHITE));
        chessBoard.put(Position.findPosition(BLACK_ROOK_LEFT), new Rook(Color.BLACK));
        chessBoard.put(Position.findPosition(BLACK_ROOK_RIGHT), new Rook(Color.BLACK));
    }

    private static void generateKnight(HashMap<Position, ChessPiece> chessBoard) {
        chessBoard.put(Position.findPosition(WHITE_KNIGHT_LEFT), new Knight(Color.WHITE));
        chessBoard.put(Position.findPosition(WHITE_KNIGHT_RIGHT), new Knight(Color.WHITE));
        chessBoard.put(Position.findPosition(BLACK_KNIGHT_LEFT), new Knight(Color.BLACK));
        chessBoard.put(Position.findPosition(BLACK_KNIGHT_RIGHT), new Knight(Color.BLACK));
    }

    private static void generateBishop(HashMap<Position, ChessPiece> chessBoard) {
        chessBoard.put(Position.findPosition(WHITE_BISHOP_LEFT), new Bishop(Color.WHITE));
        chessBoard.put(Position.findPosition(WHITE_BISHOP_RIGHT), new Bishop(Color.WHITE));
        chessBoard.put(Position.findPosition(BLACK_BISHOP_LEFT), new Bishop(Color.BLACK));
        chessBoard.put(Position.findPosition(BLACK_BISHOP_RIGHT), new Bishop(Color.BLACK));
    }

    private static void generateQueen(HashMap<Position, ChessPiece> chessBoard) {
        chessBoard.put(Position.findPosition(WHITE_QUEEN), new Queen(Color.WHITE));
        chessBoard.put(Position.findPosition(BLACK_QUEEN), new Queen(Color.BLACK));
    }

    private static void generateKing(HashMap<Position, ChessPiece> chessBoard) {
        chessBoard.put(Position.findPosition(WHITE_KING), new King(Color.WHITE));
        chessBoard.put(Position.findPosition(BLACK_KING), new King(Color.BLACK));
    }

    private static void generatePawn(HashMap<Position, ChessPiece> chessBoard) {
        chessBoard.put(Position.findPosition(WHITE_PAWN_FIRST), new Pawn(Color.WHITE));
        chessBoard.put(Position.findPosition(WHITE_PAWN_SECOND), new Pawn(Color.WHITE));
        chessBoard.put(Position.findPosition(WHITE_PAWN_THIRD), new Pawn(Color.WHITE));
        chessBoard.put(Position.findPosition(WHITE_PAWN_FOURTH), new Pawn(Color.WHITE));
        chessBoard.put(Position.findPosition(WHITE_PAWN_FIFTH), new Pawn(Color.WHITE));
        chessBoard.put(Position.findPosition(WHITE_PAWN_SIXTH), new Pawn(Color.WHITE));
        chessBoard.put(Position.findPosition(WHITE_PAWN_SEVENTH), new Pawn(Color.WHITE));
        chessBoard.put(Position.findPosition(WHITE_PAWN_EIGHT), new Pawn(Color.WHITE));
        
        chessBoard.put(Position.findPosition(BLACK_PAWN_FIRST), new Pawn(Color.BLACK));
        chessBoard.put(Position.findPosition(BLACK_PAWN_SECOND), new Pawn(Color.BLACK));
        chessBoard.put(Position.findPosition(BLACK_PAWN_THIRD), new Pawn(Color.BLACK));
        chessBoard.put(Position.findPosition(BLACK_PAWN_FOURTH), new Pawn(Color.BLACK));
        chessBoard.put(Position.findPosition(BLACK_PAWN_FIFTH), new Pawn(Color.BLACK));
        chessBoard.put(Position.findPosition(BLACK_PAWN_SIXTH), new Pawn(Color.BLACK));
        chessBoard.put(Position.findPosition(BLACK_PAWN_SEVENTH), new Pawn(Color.BLACK));
        chessBoard.put(Position.findPosition(BLACK_PAWN_EIGHT), new Pawn(Color.BLACK));
    }

    private static void generateEmpty(HashMap<Position, ChessPiece> chessBoard) {
        generateEmptyColumn(chessBoard, "a");
        generateEmptyColumn(chessBoard, "b");
        generateEmptyColumn(chessBoard, "c");
        generateEmptyColumn(chessBoard, "d");
        generateEmptyColumn(chessBoard, "e");
        generateEmptyColumn(chessBoard, "f");
        generateEmptyColumn(chessBoard, "g");
        generateEmptyColumn(chessBoard, "h");
    }

    private static void generateEmptyColumn(HashMap<Position, ChessPiece> chessBoard, String column) {
        for (int rank = MIN_RANK_LEVEL_FOR_EMPTY; rank <= MAX_RANK_LEVEL_FOR_EMPTY; rank++) {
            String position = column + rank;
            chessBoard.put(Position.findPosition(position), new Empty());
        }
    }

    public ChessPiece findChessPiece(Position position, Color color) {
        ChessPiece chessPiece = chessBoard.get(position);
        validateColor(chessPiece, color);
        return chessBoard.get(position);
    }

    private void validateColor(ChessPiece chessPiece, Color color) {
        if (chessPiece.getColor() == color) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 해당 색상의 기물을 선택할 수 없습니다.");
    }


    public void movePiece(ChessPiece chessPiece, Position targetPosition) {
        chessBoard.put(targetPosition, chessPiece);
    }

    public void removePiece(Position sourcePosition) {
        chessBoard.put(sourcePosition, new Empty());
    }

    public Map<Position, ChessPiece> getChessBoard() {
        return chessBoard;
    }


    //    Map<Position, ChessPiece> chessBoard;
//
//    private ChessBoard(Map<Position, ChessPiece> chessBoard) {
//        this.chessBoard = chessBoard;
//    }
//
//    public static ChessBoard generateChessBoard() {
//        Map<Position, ChessPiece> chessBoard = new HashMap<>();
//        generateRook(chessBoard);
//        generateKnight(chessBoard);
//        generateBishop(chessBoard);
//        generateQueen(chessBoard);
//        generateKing(chessBoard);
//        generatePawn(chessBoard);
//        initEmptySpace(chessBoard);
//        return new ChessBoard(chessBoard);
//    }
//
//    private static void generateRook(Map<Position, ChessPiece> chessBoard) {
//        chessBoard.put(Position.initPosition(1, 1), new Rook(Side.WHITE));
//        chessBoard.put(Position.initPosition(8, 1), new Rook(Side.WHITE));
//        chessBoard.put(Position.initPosition(1, 8), new Rook(Side.BLACK));
//        chessBoard.put(Position.initPosition(8, 8), new Rook(Side.BLACK));
//    }
//
//    private static void generateKing(Map<Position, ChessPiece> chessBoard) {
//        chessBoard.put(Position.initPosition(5, 1), new King(Side.WHITE));
//        chessBoard.put(Position.initPosition(5, 8), new King(Side.BLACK));
//    }
//
//    private static void generateKnight(Map<Position, ChessPiece> chessBoard) {
//        chessBoard.put(Position.initPosition(2, 1), new Knight(Side.WHITE));
//        chessBoard.put(Position.initPosition(7, 1), new Knight(Side.WHITE));
//        chessBoard.put(Position.initPosition(2, 8), new Knight(Side.BLACK));
//        chessBoard.put(Position.initPosition(7, 8), new Knight(Side.BLACK));
//    }
//
//    private static void generateBishop(Map<Position, ChessPiece> chessBoard) {
//        chessBoard.put(Position.initPosition(3, 1), new Bishop(Side.WHITE));
//        chessBoard.put(Position.initPosition(6, 1), new Bishop(Side.WHITE));
//        chessBoard.put(Position.initPosition(3, 8), new Bishop(Side.BLACK));
//        chessBoard.put(Position.initPosition(6, 8), new Bishop(Side.BLACK));
//    }
//
//    private static void generateQueen(Map<Position, ChessPiece> chessBoard) {
//        chessBoard.put(Position.initPosition(4, 1), new Queen(Side.WHITE));
//        chessBoard.put(Position.initPosition(4, 8), new Queen(Side.BLACK));
//    }
//
//    private static void generatePawn(Map<Position, ChessPiece> chessBoard) {
//        for (int horizontal = 1; horizontal <= 8; horizontal++) {
//            chessBoard.put(Position.initPosition(horizontal, 2), new Pawn(Side.WHITE));
//            chessBoard.put(Position.initPosition(horizontal, 7), new Pawn(Side.BLACK));
//        }
//    }
//
//    private static void initEmptySpace(Map<Position, ChessPiece> chessBoard) {
//        for (int horizontal = 1; horizontal <= 8; horizontal++) {
//            circuitVertical(chessBoard, horizontal);
//        }
//    }
//
//    private static void circuitVertical(Map<Position, ChessPiece> chessBoard, int i) {
//        for (int vertical = 3; vertical <= 6; vertical++) {
//            chessBoard.put(Position.initPosition(i, vertical), new Empty(Side.BLANK));
//        }
//    }
//
//    public ChessPiece getChessPieceByPosition(Position position) {
//        return chessBoard.get(position);
//    }
//
//    public Map<Position, ChessPiece> getChessBoard() {
//        return chessBoard;
//    }
}
