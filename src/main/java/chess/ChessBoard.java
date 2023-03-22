package chess;

import chess.piece.Bishop;
import chess.piece.ChessPiece;
import chess.piece.Empty;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Shape;
import chess.piece.Side;
import chess.position.Position;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, ChessPiece> chessBoard;

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
        chessBoard.put(Position.initPosition(1, 1), new Rook(Shape.ROOK, Side.WHITE));
        chessBoard.put(Position.initPosition(8, 1), new Rook(Shape.ROOK, Side.WHITE));
        chessBoard.put(Position.initPosition(1, 8), new Rook(Shape.ROOK, Side.BLACK));
        chessBoard.put(Position.initPosition(8, 8), new Rook(Shape.ROOK, Side.BLACK));
    }

    private static void generateKing(Map<Position, ChessPiece> chessBoard) {
        chessBoard.put(Position.initPosition(5, 1), new King(Shape.KING, Side.WHITE));
        chessBoard.put(Position.initPosition(5, 8), new King(Shape.KING, Side.BLACK));
    }

    private static void generateKnight(Map<Position, ChessPiece> chessBoard) {
        chessBoard.put(Position.initPosition(2, 1), new Knight(Shape.KNIGHT, Side.WHITE));
        chessBoard.put(Position.initPosition(7, 1), new Knight(Shape.KNIGHT, Side.WHITE));
        chessBoard.put(Position.initPosition(2, 8), new Knight(Shape.KNIGHT, Side.BLACK));
        chessBoard.put(Position.initPosition(7, 8), new Knight(Shape.KNIGHT, Side.BLACK));
    }

    private static void generateBishop(Map<Position, ChessPiece> chessBoard) {
        chessBoard.put(Position.initPosition(3, 1), new Bishop(Shape.BISHOP, Side.WHITE));
        chessBoard.put(Position.initPosition(6, 1), new Bishop(Shape.BISHOP, Side.WHITE));
        chessBoard.put(Position.initPosition(3, 8), new Bishop(Shape.BISHOP, Side.BLACK));
        chessBoard.put(Position.initPosition(6, 8), new Bishop(Shape.BISHOP, Side.BLACK));
    }

    private static void generateQueen(Map<Position, ChessPiece> chessBoard) {
        chessBoard.put(Position.initPosition(4, 1), new Queen(Shape.QUEEN, Side.WHITE));
        chessBoard.put(Position.initPosition(4, 8), new Queen(Shape.QUEEN, Side.BLACK));
    }

    private static void generatePawn(Map<Position, ChessPiece> chessBoard) {
        for (int horizontal = 1; horizontal <= 8; horizontal++) {
            chessBoard.put(Position.initPosition(horizontal, 2), new Pawn(Shape.PAWN, Side.WHITE));
            chessBoard.put(Position.initPosition(horizontal, 7), new Pawn(Shape.PAWN, Side.BLACK));
        }
    }

    private static void initEmptySpace(Map<Position, ChessPiece> chessBoard) {
        for (int horizontal = 1; horizontal <= 8; horizontal++) {
            circuitVertical(chessBoard, horizontal);
        }
    }

    private static void circuitVertical(Map<Position, ChessPiece> chessBoard, int i) {
        for (int vertical = 3; vertical <= 6; vertical++) {
            initBlank(chessBoard, i, vertical);
        }
    }

    private static void initBlank(Map<Position, ChessPiece> chessBoard, int i, int vertical) {
        if (!chessBoard.containsKey(Position.initPosition(i, vertical))) {
            chessBoard.put(Position.initPosition(i, vertical), new Empty(Shape.EMPTY, Side.EMPTY));
        }
    }

    public ChessPiece getChessPieceByPosition(Position position) {
        return chessBoard.get(position);
    }

    public Map<Position, ChessPiece> getChessBoard() {
        return chessBoard;
    }
}
