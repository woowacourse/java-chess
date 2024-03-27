package model.chessboard;

import model.piece.Piece;
import model.piece.role.*;
import model.position.File;
import model.position.Position;
import model.position.Rank;

import java.util.HashMap;
import java.util.Map;

import static model.piece.Color.BLACK;
import static model.piece.Color.WHITE;

public class ChessBoardFactory {
    private static final Map<Position, Piece> chessBoard = new HashMap<>();

    private ChessBoardFactory() {
    }

    public static Map<Position, Piece> create() {
        initRook();
        initKnight();
        initBishop();
        initQueen();
        initKing();
        initPawn();
        initSquare();
        return chessBoard;
    }

    private static void initRook() {
        chessBoard.put(Position.of(File.A, Rank.ONE), new Piece(new Rook(WHITE)));
        chessBoard.put(Position.of(File.H, Rank.ONE), new Piece(new Rook(WHITE)));
        chessBoard.put(Position.of(File.A, Rank.EIGHT), new Piece(new Rook(BLACK)));
        chessBoard.put(Position.of(File.H, Rank.EIGHT), new Piece(new Rook(BLACK)));
    }

    private static void initKnight() {
        chessBoard.put(Position.of(File.B, Rank.ONE), new Piece(new Knight(WHITE)));
        chessBoard.put(Position.of(File.G, Rank.ONE), new Piece(new Knight(WHITE)));
        chessBoard.put(Position.of(File.B, Rank.EIGHT), new Piece(new Knight(BLACK)));
        chessBoard.put(Position.of(File.G, Rank.EIGHT), new Piece(new Knight(BLACK)));
    }

    private static void initBishop() {
        chessBoard.put(Position.of(File.C, Rank.ONE), new Piece(new Bishop(WHITE)));
        chessBoard.put(Position.of(File.F, Rank.ONE), new Piece(new Bishop(WHITE)));
        chessBoard.put(Position.of(File.C, Rank.EIGHT), new Piece(new Bishop(BLACK)));
        chessBoard.put(Position.of(File.F, Rank.EIGHT), new Piece(new Bishop(BLACK)));
    }

    private static void initQueen() {
        chessBoard.put(Position.of(File.D, Rank.ONE), new Piece(new Queen(WHITE)));
        chessBoard.put(Position.of(File.D, Rank.EIGHT), new Piece(new Queen(BLACK)));
    }

    private static void initKing() {
        chessBoard.put(Position.of(File.E, Rank.ONE), new Piece(new King(WHITE)));
        chessBoard.put(Position.of(File.E, Rank.EIGHT), new Piece(new King(BLACK)));
    }

    private static void initPawn() {
        for (File file : File.values()) {
            chessBoard.put(Position.of(file, Rank.TWO), new Piece(new Pawn(WHITE)));
            chessBoard.put(Position.of(file, Rank.SEVEN), new Piece(new Pawn(BLACK)));
        }
    }

    private static void initSquare() {
        for (File file : File.values()) {
            chessBoard.put(Position.of(file, Rank.THREE), new Piece(new Square()));
            chessBoard.put(Position.of(file, Rank.FOUR), new Piece(new Square()));
            chessBoard.put(Position.of(file, Rank.FIVE), new Piece(new Square()));
            chessBoard.put(Position.of(file, Rank.SIX), new Piece(new Square()));
        }
    }
}
