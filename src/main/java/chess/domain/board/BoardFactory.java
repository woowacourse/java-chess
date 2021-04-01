package chess.domain.board;

import chess.domain.piece.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class BoardFactory {

    private BoardFactory() {
    }

    public static Board create() {
        return new Board(createBoard());
    }

    private static Map<Position, Piece> createBoard() {
        Map<Position, Piece> board = initialize();

        setRook(board);
        setKnight(board);
        setBishop(board);
        setQueen(board);
        setKing(board);
        setPawn(board);

        return board;
    }

    private static Map<Position, Piece> initialize() {
        Map<Position, Piece> board = new LinkedHashMap<>();

        for (Position position : Position.getPositions()) {
            board.put(position, null);
        }

        return board;
    }

    private static void setRook(Map<Position, Piece> board) {
        setPieceByBlack(board, new Rook(Team.BLACK), Horizontal.A);
        setPieceByBlack(board, new Rook(Team.BLACK), Horizontal.H);
        setPieceByWhite(board, new Rook(Team.WHITE), Horizontal.A);
        setPieceByWhite(board, new Rook(Team.WHITE), Horizontal.H);
    }

    private static void setKnight(Map<Position, Piece> board) {
        setPieceByBlack(board, new Knight(Team.BLACK), Horizontal.B);
        setPieceByBlack(board, new Knight(Team.BLACK), Horizontal.G);
        setPieceByWhite(board, new Knight(Team.WHITE), Horizontal.B);
        setPieceByWhite(board, new Knight(Team.WHITE), Horizontal.G);
    }

    private static void setBishop(Map<Position, Piece> board) {
        setPieceByBlack(board, new Bishop(Team.BLACK), Horizontal.C);
        setPieceByBlack(board, new Bishop(Team.BLACK), Horizontal.F);
        setPieceByWhite(board, new Bishop(Team.WHITE), Horizontal.C);
        setPieceByWhite(board, new Bishop(Team.WHITE), Horizontal.F);
    }

    private static void setQueen(Map<Position, Piece> board) {
        setPieceByBlack(board, new Queen(Team.BLACK), Horizontal.D);
        setPieceByWhite(board, new Queen(Team.WHITE), Horizontal.D);
    }

    private static void setKing(Map<Position, Piece> board) {
        setPieceByBlack(board, new King(Team.BLACK), Horizontal.E);
        setPieceByWhite(board, new King(Team.WHITE), Horizontal.E);
    }

    private static void setPieceByBlack(Map<Position, Piece> board, Piece piece, Horizontal horizontal) {
        board.put(Position.of(horizontal, Vertical.EIGHT), piece);
    }

    private static void setPieceByWhite(Map<Position, Piece> board, Piece piece, Horizontal horizontal) {
        board.put(Position.of(horizontal, Vertical.ONE), piece);
    }

    private static void setPawn(Map<Position, Piece> board) {
        for (Horizontal horizontal : Horizontal.values()) {
            board.put(Position.of(horizontal, Vertical.SEVEN), new Pawn(Team.BLACK));
            board.put(Position.of(horizontal, Vertical.TWO), new Pawn(Team.WHITE));
        }
    }
}
