package model.chessboard;

import model.piece.PieceHolder;
import model.piece.state.*;
import model.position.File;
import model.position.Position;
import model.position.Rank;

import java.util.HashMap;
import java.util.Map;

import static model.piece.Color.BLACK;
import static model.piece.Color.WHITE;

public class ChessBoardFactory {
    private static final Map<Position, PieceHolder> chessBoard = new HashMap<>();

    private ChessBoardFactory() {
    }

    public static Map<Position, PieceHolder> create() {
        initRook();
        initBishop();
        initKnight();
        initQueen();
        initKing();
        initPawn();
        initSquare();
        return chessBoard;
    }

    private static void initRook() {
        chessBoard.put(Position.of(File.A, Rank.ONE), new PieceHolder(new Rook(WHITE)));
        chessBoard.put(Position.of(File.H, Rank.ONE), new PieceHolder(new Rook(WHITE)));
        chessBoard.put(Position.of(File.A, Rank.EIGHT), new PieceHolder(new Rook(BLACK)));
        chessBoard.put(Position.of(File.H, Rank.EIGHT), new PieceHolder(new Rook(BLACK)));
    }

    private static void initBishop() {
        chessBoard.put(Position.of(File.B, Rank.ONE), new PieceHolder(new Bishop(WHITE)));
        chessBoard.put(Position.of(File.G, Rank.ONE), new PieceHolder(new Bishop(WHITE)));
        chessBoard.put(Position.of(File.B, Rank.EIGHT), new PieceHolder(new Bishop(BLACK)));
        chessBoard.put(Position.of(File.G, Rank.EIGHT), new PieceHolder(new Bishop(BLACK)));
    }

    private static void initKnight() {
        chessBoard.put(Position.of(File.C, Rank.ONE), new PieceHolder(new Knight(WHITE)));
        chessBoard.put(Position.of(File.F, Rank.ONE), new PieceHolder(new Knight(WHITE)));
        chessBoard.put(Position.of(File.C, Rank.EIGHT), new PieceHolder(new Knight(BLACK)));
        chessBoard.put(Position.of(File.F, Rank.EIGHT), new PieceHolder(new Knight(BLACK)));
    }

    private static void initQueen() {
        chessBoard.put(Position.of(File.D, Rank.ONE), new PieceHolder(new Queen(WHITE)));
        chessBoard.put(Position.of(File.D, Rank.EIGHT), new PieceHolder(new Queen(BLACK)));
    }

    private static void initKing() {
        chessBoard.put(Position.of(File.E, Rank.ONE), new PieceHolder(new King(WHITE)));
        chessBoard.put(Position.of(File.E, Rank.EIGHT), new PieceHolder(new King(BLACK)));
    }

    private static void initPawn() {
        for (File file : File.values()) {
            chessBoard.put(Position.of(file, Rank.TWO), new PieceHolder(new Pawn(WHITE)));
            chessBoard.put(Position.of(file, Rank.SEVEN), new PieceHolder(new Pawn(BLACK)));
        }
    }

    private static void initSquare() {
        for (File file : File.values()) {
            chessBoard.put(Position.of(file, Rank.THREE), new PieceHolder(new Square()));
            chessBoard.put(Position.of(file, Rank.FOUR), new PieceHolder(new Square()));
            chessBoard.put(Position.of(file, Rank.FIVE), new PieceHolder(new Square()));
            chessBoard.put(Position.of(file, Rank.SIX), new PieceHolder(new Square()));
        }
    }
}
