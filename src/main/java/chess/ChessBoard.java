package chess;

import chess.database.ChessBoardDao;
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

    public static ChessBoard generateChessBoard(int gameIdx) {
        Map<Position, ChessPiece> chessBoard = new HashMap<>();
        if (gameIdx == 0) {
            return makeNewBoard(chessBoard);
        }
        return loadChessBoard(gameIdx, chessBoard);
    }

    public static ChessBoard generateChessBoard() {
        Map<Position, ChessPiece> chessBoard = new HashMap<>();
        return makeNewBoard(chessBoard);
    }

    private static ChessBoard makeNewBoard(Map<Position, ChessPiece> chessBoard) {
        generateRook(chessBoard);
        generateKnight(chessBoard);
        generateBishop(chessBoard);
        generateQueen(chessBoard);
        generateKing(chessBoard);
        generatePawn(chessBoard);
        initEmptySpace(chessBoard);
        return new ChessBoard(chessBoard);
    }

    private static ChessBoard loadChessBoard(int gameIdx, Map<Position, ChessPiece> chessBoard) {
        for (int file = 1; file <= 8; file++) {
            circuitRank(gameIdx, chessBoard, file);
        }
        return new ChessBoard(chessBoard);
    }

    private static void circuitRank(int gameIdx, Map<Position, ChessPiece> chessBoard, int file) {
        for (int rank = 1; rank <= 8; rank++) {
            ChessBoardDao chessBoardDao = new ChessBoardDao();
            Shape pieceShape = Shape.findShape(chessBoardDao.findPieceType(gameIdx, file, rank));
            Side pieceSide = Side.findSide(chessBoardDao.findPieceSide(gameIdx, file, rank));
            chessBoard.put(Position.initPosition(file, rank), Shape.makePieceByShape(pieceShape, pieceSide));
        }
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
            initBlank(chessBoard, i, vertical);
        }
    }

    private static void initBlank(Map<Position, ChessPiece> chessBoard, int i, int vertical) {
        if (!chessBoard.containsKey(Position.initPosition(i, vertical))) {
            chessBoard.put(Position.initPosition(i, vertical), new Empty(Side.EMPTY));
        }
    }

    public boolean checkKingIsDead() {
        long kingCount = chessBoard.values().stream()
                .filter(chessPiece -> chessPiece.getShape().equals(Shape.KING))
                .count();
        return kingCount != 2;
    }

    public double calculateScore(Side side) {
        double scoreSum = 0;
        for (int horizontal = 1; horizontal <= 8; horizontal++) {
            scoreSum = getScoreSumByCol(scoreSum, horizontal, side);
        }
        return scoreSum;
    }

    private double getScoreSumByCol(double scoreSum, int horizontal, Side side) {
        double pawnCountByCol = 0;
        for (int vertical = 1; vertical <= 8; vertical++) {
            ChessPiece chessPiece = chessBoard.get(Position.initPosition(horizontal, vertical));
            pawnCountByCol += checkPawnCount(chessPiece, side);
            scoreSum += chessPiece.getScore(side);
        }
        if (pawnCountByCol >= 2) {
            scoreSum -= (pawnCountByCol / 2);
        }
        return scoreSum;
    }

    private double checkPawnCount(ChessPiece chessPiece, Side side) {
        if (chessPiece.getShape().equals(Shape.PAWN) && chessPiece.getSide().equals(side)) {
            return 1;
        }
        return 0;
    }

    public ChessPiece getChessPieceByPosition(Position position) {
        return chessBoard.get(position);
    }

    public Map<Position, ChessPiece> getChessBoard() {
        return chessBoard;
    }
}
