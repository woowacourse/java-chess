package chess.domain;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {
    public final static String WHITE_ROOK_LEFT = "a1";
    public final static String WHITE_ROOK_RIGHT = "h1";
    public final static String BLACK_ROOK_LEFT = "a8";
    public final static String BLACK_ROOK_RIGHT = "h8";
    public final static String WHITE_KNIGHT_LEFT = "b1";
    public final static String WHITE_KNIGHT_RIGHT = "g1";
    public final static String BLACK_KNIGHT_LEFT = "b8";
    public final static String BLACK_KNIGHT_RIGHT = "g8";
    public final static String WHITE_BISHOP_LEFT = "c1";
    public final static String WHITE_BISHOP_RIGHT = "f1";
    public final static String BLACK_BISHOP_LEFT = "c8";
    public final static String BLACK_BISHOP_RIGHT = "f8";
    public final static String WHITE_QUEEN = "d1";
    public final static String BLACK_QUEEN = "d8";
    public final static String WHITE_KING = "e1";
    public final static String BLACK_KING = "e8";
    public final static String WHITE_PAWN_FIRST = "a2";
    public final static String WHITE_PAWN_SECOND = "b2";
    public final static String WHITE_PAWN_THIRD = "c2";
    public final static String WHITE_PAWN_FOURTH = "d2";
    public final static String WHITE_PAWN_FIFTH = "e2";
    public final static String WHITE_PAWN_SIXTH = "f2";
    public final static String WHITE_PAWN_SEVENTH = "g2";
    public final static String WHITE_PAWN_EIGHT = "h2";
    public final static String BLACK_PAWN_FIRST = "a7";
    public final static String BLACK_PAWN_SECOND = "b7";
    public final static String BLACK_PAWN_THIRD = "c7";
    public final static String BLACK_PAWN_FOURTH = "d7";
    public final static String BLACK_PAWN_FIFTH = "e7";
    public final static String BLACK_PAWN_SIXTH = "f7";
    public final static String BLACK_PAWN_SEVENTH = "g7";
    public final static String BLACK_PAWN_EIGHT = "h7";

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

    public boolean isEmpty(Position position) {
        return chessBoard.get(position).equals(new Empty());
    }

    public ChessPiece getChessPiece(Position position) {
        return chessBoard.get(position);
    }

    public Map<Position, ChessPiece> getChessBoard() {
        return chessBoard;
    }

    public Map<Position, ChessPiece> getChessPiecesByColor(Color color) {
        return chessBoard.entrySet().stream()
                .filter(positionChessPieceEntry -> positionChessPieceEntry.getValue().getColor().equals(color))
                .collect(Collectors.toMap(positionChessPieceEntry -> positionChessPieceEntry.getKey(), positionChessPieceEntry -> positionChessPieceEntry.getValue()));
    }
}

