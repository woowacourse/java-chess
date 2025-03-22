package chess;

import chess.piece.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    Map<Position, Piece> board;

    public Board() {
        board = new HashMap<>();
        for(Row row : Row.values()) {
            for(Column column : Column.values()) {
                board.put(new Position(row, column), new None());
            }
        }
        setBoardWhite();
        setBoardBlack();

    }

    public void move(Position from, Position to) {
        Piece piece = board.get(from);
        board.put(from, new None());
        try {
            piece = piece.move(board, to);
            board.put(to, piece);
        } catch (Exception e) {
            board.put(from, piece);
            System.out.println(e.getMessage());
        }
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public void setBoardWhite() {
        List<Piece> pieces = new ArrayList<>();
        for(Column column : Column.values()) {
            pieces.add(new Pawn(Color.WHITE, new Position(Row.TWO, column), false));
        }
        pieces.add(new Rook(Color.WHITE, new Position(Column.A, Row.ONE)));
        pieces.add(new Rook(Color.WHITE, new Position(Column.H, Row.ONE)));
        pieces.add(new Knight(Color.WHITE, new Position(Column.B, Row.ONE)));
        pieces.add(new Knight(Color.WHITE, new Position(Column.G, Row.ONE)));
        pieces.add(new Bishop(Color.WHITE, new Position(Column.C, Row.ONE)));
        pieces.add(new Bishop(Color.WHITE, new Position(Column.F, Row.ONE)));
        pieces.add(new King(Color.WHITE, new Position(Column.E, Row.ONE)));
        pieces.add(new Queen(Color.WHITE, new Position(Column.D, Row.ONE)));
        for (Piece piece : pieces) {
            board.put(piece.getPosition(), piece);
        }
    }
    public void setBoardBlack() {
        List<Piece> pieces = new ArrayList<>();
        for(Column column : Column.values()) {
            pieces.add(new Pawn(Color.BLACK, new Position(Row.SEVEN, column), false));
        }
        pieces.add(new Rook(Color.BLACK, new Position(Column.A, Row.EIGHT)));
        pieces.add(new Rook(Color.BLACK, new Position(Column.H, Row.EIGHT)));
        pieces.add(new Knight(Color.BLACK, new Position(Column.B, Row.EIGHT)));
        pieces.add(new Knight(Color.BLACK, new Position(Column.G, Row.EIGHT)));
        pieces.add(new Bishop(Color.BLACK, new Position(Column.C, Row.EIGHT)));
        pieces.add(new Bishop(Color.BLACK, new Position(Column.F, Row.EIGHT)));
        pieces.add(new King(Color.BLACK, new Position(Column.E, Row.EIGHT)));
        pieces.add(new Queen(Color.BLACK, new Position(Column.D, Row.EIGHT)));
        for (Piece piece : pieces) {
            board.put(piece.getPosition(), piece);
        }
    }
}
