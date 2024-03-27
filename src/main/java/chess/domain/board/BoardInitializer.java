package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.pieceInfo.PieceInfo;
import chess.domain.pieceInfo.Position;
import chess.domain.pieceInfo.Team;

import java.util.ArrayList;
import java.util.List;

public class BoardInitializer {
    public static List<Piece> initialize() {
        List<Piece> board = new ArrayList<>();

        makeRook(board);
        makeKnight(board);
        makeBishop(board);
        makeQueenAndKing(board);
        makeWhitePawn(board);
        makeBlackPawn(board);
        makeEmptyPieces(board);

        return board;
    }

    private static void makeRook(final List<Piece> board) {
        board.add(new Rook(new PieceInfo(Position.of("a8"), Team.BLACK)));
        board.add(new Rook(new PieceInfo(Position.of("h8"), Team.BLACK)));
        board.add(new Rook(new PieceInfo(Position.of("a1"), Team.WHITE)));
        board.add(new Rook(new PieceInfo(Position.of("h1"), Team.WHITE)));
    }

    private static void makeKnight(final List<Piece> board) {
        board.add(new Knight(new PieceInfo(Position.of("b8"), Team.BLACK)));
        board.add(new Knight(new PieceInfo(Position.of("g8"), Team.BLACK)));
        board.add(new Knight(new PieceInfo(Position.of("b1"), Team.WHITE)));
        board.add(new Knight(new PieceInfo(Position.of("g1"), Team.WHITE)));
    }

    private static void makeBishop(final List<Piece> board) {
        board.add(new Bishop(new PieceInfo(Position.of("c8"), Team.BLACK)));
        board.add(new Bishop(new PieceInfo(Position.of("f8"), Team.BLACK)));
        board.add(new Bishop(new PieceInfo(Position.of("c1"), Team.WHITE)));
        board.add(new Bishop(new PieceInfo(Position.of("f1"), Team.WHITE)));
    }

    private static void makeQueenAndKing(final List<Piece> board) {
        board.add(new Queen(new PieceInfo(Position.of("d8"), Team.BLACK)));
        board.add(new King(new PieceInfo(Position.of("e8"), Team.BLACK)));
        board.add(new Queen(new PieceInfo(Position.of("d1"), Team.WHITE)));
        board.add(new King(new PieceInfo(Position.of("e1"), Team.WHITE)));
    }

    private static void makeWhitePawn(final List<Piece> board) {
        for (int x = 0; x < 8; x++) {
            char indexX = (char) ('a' + x);
            String index = indexX + "2";
            board.add(new Pawn(new PieceInfo(Position.of(index), Team.WHITE)));
        }
    }

    private static void makeBlackPawn(final List<Piece> board) {
        for (int x = 0; x < 8; x++) {
            char indexX = (char) ('a' + x);
            String index = indexX + "7";
            board.add(new Pawn(new PieceInfo(Position.of(index), Team.BLACK)));
        }
    }

    private static void makeEmptyPieces(final List<Piece> board) {
        for (int x = 0; x < 8; x++) {
            char indexX = (char) ('a' + x);
            makeEmptyPieceColumn(board, indexX);
        }
    }

    private static void makeEmptyPieceColumn(final List<Piece> board, final char indexX) {
        for (int y = 3; y < 7; y++) {
            char indexY = (char) ('0' + y);
            String index = indexX + Character.toString(indexY);
            board.add(new EmptyPiece(new PieceInfo(Position.of(index), Team.NONE)));
        }
    }
}
